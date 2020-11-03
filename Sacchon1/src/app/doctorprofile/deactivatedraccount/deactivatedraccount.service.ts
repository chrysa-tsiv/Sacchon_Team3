import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { Deactivatedoctor } from './deactivatedraccount.model';

@Injectable({
  providedIn: 'root'
})
export class DeactivatedraccountService {

  public id = sessionStorage.getItem('id');
  public username = sessionStorage.getItem('username');
  public role = sessionStorage.getItem('role');

  private readonly endpoint = 'http://localhost:9000/app/doctor/softRemove/'; 

  constructor(
    private http: HttpClient,
    private router: Router) 
  { }

  deactivateAccount():Observable<any> {
    // console.log(sessionStorage.getItem("credentials")+ sessionStorage.getItem('id'));
    return this.http.put<any>(this.endpoint + sessionStorage.getItem('id'), {},
      {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa(sessionStorage.getItem("credentials"))})
    });
  }
}