import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserResponse } from '@app/interfaces/UserResponse';
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

    getUserDetails(): Observable<UserResponse> {
        return new Observable((observer) => {
            this.auth.idTokenClaims$.pipe(
                switchMap((idTokenClaims) => {
                    const token = idTokenClaims?.__raw;
                    const headers = new HttpHeaders({
                        Authorization: `Bearer ${token}`,
                    });

                    // TODO not yet working when the user does  not exist
                    return this.http.get<UserResponse>(this.path, { headers }).pipe(
                        switchMap((response: UserResponse) => {
                            // If the user ID is null, call createUser
                            if (!response || response.id === null) {
                                return this.createUser(); 
                            } 
                                
                            return of(response); 
                            
                        })
                    );
                })
            ).subscribe({
                next: (response) => {
                    this.userId = response.id; 
                    observer.next(response); 
                },
                error: (err) => {
                    observer.error(err); 
                },
            });
        });
    }

    getUserId(): BigInteger | null {
        return this.userId;
    }

    createUser(): Observable<UserResponse> {
        return new Observable(observer => {
            this.auth.idTokenClaims$.subscribe(idTokenClaims => {
                const token = idTokenClaims?.__raw;
                const headers = new HttpHeaders(
                    {
                        Authorization: `Bearer ${token}`
                    }
                );

                this.http.post<UserResponse>(this.path, { headers })
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
