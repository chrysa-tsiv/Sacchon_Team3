import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from "@angular/router";
import { ChiefDoctor } from './chiefdoctor.model';

@Injectable({
  providedIn: 'root'
})
export class ViewdoctorsService {

  private readonly endpoint = 'http://localhost:9000/app/doctor/?active=1';

  constructor(
    private http: HttpClient, 
    private router: Router) { }

  getDoctors():Observable <ChiefDoctor[]> {
    return this.http.get<ChiefDoctor[]>(
      this.endpoint,
        {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa('admin:passw0rd')})}
        );
        // return this.http.get<Product[]>('assets/product.json');
    }
}
