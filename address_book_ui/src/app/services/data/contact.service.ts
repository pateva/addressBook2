import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_BATH } from '@app/shared/constants/data';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(private http: HttpClient) { }
}
