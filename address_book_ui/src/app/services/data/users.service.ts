import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserPartialResponse } from '@app/interfaces/responses/UserPartialResponse';
import { BASE_BATH } from '@app/shared/constants/data';
import { AuthService } from '@auth0/auth0-angular';
import { Observable, of, switchMap } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class UsersService {
    private path: string = BASE_BATH + "/users";
    private userId: BigInteger | null = null;

    constructor(private http: HttpClient, private auth: AuthService) { }

    getUserDetails(): Observable<UserPartialResponse> {
        return new Observable<UserPartialResponse>((subscriber) => {
            this.auth.idTokenClaims$.pipe(
                switchMap(idTokenClaims => {
                    const token = idTokenClaims?.__raw;
                    const headers = new HttpHeaders({
                        Authorization: `Bearer ${token}`,
                    });
    
                    return this.http.get<UserPartialResponse>(this.path, { headers });
                }),
                switchMap((response: UserPartialResponse) => {
                    if (response === null || response.id === null) {
                        return this.createUser();
                    }

                    return of(response);
                })
            ).subscribe({
                next: (response) => {
                    this.userId = response.id; 
                    subscriber.next(response);
                    subscriber.complete();
                },
                error: (err) => {
                    console.error("Error fetching or creating user", err);
                    subscriber.error(err);
                }
            });
        });
    }
    

    getUserId(): BigInteger | null {
        return this.userId;
    }

    createUser(): Observable<UserPartialResponse> {
        console.log("create user")
        return new Observable(observer => {
            this.auth.idTokenClaims$.subscribe(idTokenClaims => {
                const token = idTokenClaims?.__raw;
                const headers = new HttpHeaders(
                    {
                        Authorization: `Bearer ${token}`,
                        'Content-Type': 'application/json' 
                    }
                );

                const body = {}; 
                this.http.post<UserPartialResponse>(this.path, body, { headers })
                    .subscribe(
                        {
                            next: (response) => {
                                observer.next(response);
                            },
                            error: (err) => {
                                observer.error(err);
                            }
                        }
                    )
            })
        })
    }
}
