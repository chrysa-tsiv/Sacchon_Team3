import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from "@angular/router";
import { MyMeasurement } from './mymeasurement.model'

@Injectable({
  providedIn: 'root'
})
export class MymeasurementsService {

  private readonly endpoint = 'http://localhost:9000/app/measurement'; 

  constructor(
    private http: HttpClient, 
    private router: Router  ) { }

    getMeasurement():Observable <MyMeasurement[]> {
      let params = new HttpParams();
      params = params.append('patientID', sessionStorage.getItem('id'));
      

      return this.http.get<MyMeasurement[]>(
        this.endpoint,
          {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa(sessionStorage.getItem("credentials"))}),params:params}
          );
          
      }
}