import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Measurement } from './measurement.model'

@Injectable({
  providedIn: 'root'
})
export class AddmeasurementService {

  private readonly endpoint = 'http://localhost:9000/app/measurement'; //

  constructor(private http: HttpClient) { }

  
  getMeasurement(): Observable<Measurement[]> {
    return this.http.get<Measurement[]>(this.endpoint, {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa(sessionStorage.getItem("credentials"))})}
    );
  } // for existing
  
  createMeasurement(measurement: Measurement): Observable<Measurement> {
    console.log(JSON.stringify(measurement));
  
    return this.http.post<Measurement>(this.endpoint, measurement,{headers:new HttpHeaders({'Authorization': 'Basic ' + btoa(sessionStorage.getItem("credentials"))})}
     ); 

  }
}