import { Injectable } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { Consultation } from './consultation.model';
import { Router } from "@angular/router";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConsultationsService {

  id = sessionStorage.getItem('id');
  username = sessionStorage.getItem('username');
  role = sessionStorage.getItem('role');

  private readonly endpoint = 'http://localhost:9000/app/appointment/?patientID=';

  constructor(
    private http: HttpClient, 
    private router: Router
  ) { }

  getConsultations():Observable<Consultation[]> {
    return this.http.get<Consultation[]>(
      this.endpoint + sessionStorage.getItem('id'),
      {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa(sessionStorage.getItem("credentials"))})}
        );
        // return this.http.get<Product[]>('assets/product.json');
    }



}
