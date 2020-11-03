import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Patient } from '../signup/patient.model';
import { MyMeasurement } from './mymeasurements/mymeasurement.model';

@Injectable({
  providedIn: 'root'
})
export class PatientinfoService {

  constructor(
    private http: HttpClient,
    private params:HttpClient) { }

  private readonly endpoint =  'http://localhost:9000/app/patient';

  getInfo():Observable <Patient> {
  
    return this.http.get<Patient>(
      this.endpoint+'/'+sessionStorage.getItem('id'),
        {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa(sessionStorage.getItem("credentials"))})}
        );
}
}