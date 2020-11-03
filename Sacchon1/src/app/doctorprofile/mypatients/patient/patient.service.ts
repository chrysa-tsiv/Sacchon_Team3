import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Measurement } from 'src/app/patientprofile/addmeasurement/measurement.model';

@Injectable({
  providedIn: 'root'
})
export class PatientService {                                                //http://localhost:9000/app/measurement/?patientUsername=dimi28
  private readonly endpoint = 'http://localhost:9000/app/measurement/';    // http://localhost:9000/app/measurement/?username=el4567
  constructor(
    private http: HttpClient, 
    private router: Router  ) { }

    getPatientMeasurements(username):Observable <Measurement> {
      let params = new HttpParams();
      params = params.append('patientUsername', sessionStorage.getItem('username'));
      

      return this.http.get<Measurement>(
        this.endpoint,
          {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa(sessionStorage.getItem("credentials"))}),params:params}
          );
          
      }
}