import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Editpatient } from './editpatient.model';
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class EditpatientService {

  private readonly endpoint = 'http://localhost:9000/app/patient/'; //patient

  constructor(
    private http: HttpClient, 
    private router: Router) { }

  updatePatient(patient: Editpatient): Observable<Editpatient> {
   
    return this.http.put<Editpatient>(
      this.endpoint + sessionStorage.getItem('id'), 
      patient, 
      {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa(sessionStorage.getItem("credentials"))})});
    
  }
}
