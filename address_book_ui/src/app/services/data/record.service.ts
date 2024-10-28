import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_BATH } from '@app/shared/constants/data';
import { Observable, of, mergeMap } from 'rxjs';
import { AuthService } from '@auth0/auth0-angular';
import { RecordResponse } from '@app/interfaces/responses/RecordResponse';
import { CreateRecordBody } from '@app/interfaces/payloads/CreateRecordBody';

@Injectable({
    providedIn: 'root'
})
export class RecordService {
    private path: string = BASE_BATH + "/user/";
    private userId: BigInteger | null = null;

    constructor(private http: HttpClient, private auth: AuthService) { }

    setUserId(userId: BigInteger): void {
        this.userId = userId;
    }

    getBasePath(): string {
        return (this.path + this.userId?.toString() + '/records')
    }

    createRecord(body: CreateRecordBody): Observable<RecordResponse> {
        console.log("request path: " + this.getBasePath());

        return this.auth.idTokenClaims$.pipe(
            mergeMap((idTokenClaims) => {
                const token = idTokenClaims?.__raw;
                const headers = new HttpHeaders({
                    Authorization: `Bearer ${token}`,
                });

                return this.http.post<RecordResponse>(this.getBasePath(), body, { headers });
            })
        );
    }
}
