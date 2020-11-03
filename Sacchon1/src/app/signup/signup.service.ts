import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Patient } from './patient.model';
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  private readonly endpoint = 'http://localhost:9000/app/patient'; //patient

  constructor(
    private http: HttpClient, 
    private router: Router) { }

  getPatient(): Observable<Patient[]> {
    return this.http.get<Patient[]>(this.endpoint, {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa('admin:passw0rd')})}
    );  } // for existing

  createPatient(patient: Patient): Observable<Patient> {
    // console.log(JSON.stringify(patient)); // ta fernei ola pisw
   
    return this.http.post<Patient>(this.endpoint, patient, {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa("admin:passw0rd")})});
    
  }

}
