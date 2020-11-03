import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Doctor } from './doctor.model';
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class DrsignupService {

  private readonly endpoint = 'http://localhost:9000/app/doctor'; 
  private readonly signInPage = 'http://localhost:4200/signin';

  constructor(
    private http: HttpClient, 
    private router: Router) { }

  getDoctor(): Observable<Doctor[]> {
    return this.http.get<Doctor[]>(this.endpoint, {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa('admin:passw0rd')})}
    );  } // for existing

  createDoctor(doctor: Doctor): Observable<Doctor> {
    return this.http.post<Doctor>(this.endpoint, doctor, {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa("admin:passw0rd")})});

    ;
  }
    

}
