import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from "@angular/router";
import { ChiefPatient } from './chiefpatient.model';

@Injectable({
  providedIn: 'root'
})
export class ViewpatientsService {

  private readonly endpoint = 'http://localhost:9000/app/patient'; //patient

  constructor(
    private http: HttpClient, 
    private router: Router) { }


  getPatients():Observable <ChiefPatient[]> {
    return this.http.get<ChiefPatient[]>(
      this.endpoint,
        {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa('admin:passw0rd')})}
        );
        // return this.http.get<Product[]>('assets/product.json');
    }

}
