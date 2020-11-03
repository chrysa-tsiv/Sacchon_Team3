import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Measurement } from '../../addmeasurement/measurement.model';
import { MyMeasurement } from '../mymeasurement.model';


@Injectable({
  providedIn: 'root'
})
export class MyaveragesService {

  private readonly endpoint = 'http://localhost:9000/app/measurement';  

  
  constructor( private http: HttpClient, 
    private router: Router) { }

    
   
    getAverages():Observable <MyMeasurement[]> {
      let params = new HttpParams();
      params = params.append('patientID', sessionStorage.getItem('id'));
      params = params.append('fromDate',sessionStorage.getItem('fromDate') );
      params = params.append('toDate', sessionStorage.getItem('toDate'));
      

      return this.http.get<MyMeasurement[]>(
        this.endpoint,
          {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa(sessionStorage.getItem("credentials"))}),params:params}
          );
          
      }
}