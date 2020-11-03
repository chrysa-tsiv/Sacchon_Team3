import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Editdoctor } from './editdoctor.model'
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class EditdoctorService {

  private readonly endpoint = 'http://localhost:9000/app/doctor/'; 

  constructor(private http: HttpClient) { }

  updateDoctor(doctor: Editdoctor): Observable<Editdoctor> {
    return this.http.put<Editdoctor>(
      this.endpoint + sessionStorage.getItem('id'), 
      doctor, 
      {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa(sessionStorage.getItem("credentials"))})});
  }
}
