import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from "@angular/router";
import { ApprovedDoctor } from './approveddoctors.model';

@Injectable({
  providedIn: 'root'
})
export class ApprovedoctorsService {

  private readonly endpoint = 'http://localhost:9000/app/doctor/?active=0';
  private readonly endpoint2 = 'http://localhost:9000/app/admin/approveDoctor/';

  constructor(
    private http: HttpClient, 
    private router: Router
  ) { }

  getDoctors():Observable <ApprovedDoctor[]> {
   
  return this.http.get<ApprovedDoctor[]>(
    this.endpoint,
      {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa('admin:passw0rd')})}
      );
  }

  // http://localhost:9000/app/admin/approveDoctor/{username}

  // approveDoctor(username): Observable<ApprovedDoctor> {
    approveDoctor(username): Observable<any> {
      
      alert(this.endpoint2 + username);
      return this.http.put<ApprovedDoctor>(
        this.endpoint2 + username, 
        {},
        {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa("admin:passw0rd")})});

  }
}
