import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from "@angular/router";
import { NewPatient } from './newpatient.model';

@Injectable({
  providedIn: 'root'
})
export class AddpatientService {

  drusername = sessionStorage.getItem("username");

  private readonly endpoint = 'http://localhost:9000/app/doctor/searchNewPatient/'; 
  private readonly endpoint2 = 'http://localhost:9000/app/doctor/assignPatient/'; 

  constructor(
    private http: HttpClient, 
    private router: Router) { }


  getPatients():Observable <NewPatient[]> {
    return this.http.get<NewPatient[]>(
      this.endpoint,
        {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa(sessionStorage.getItem("credentials"))})}
        );
        // return this.http.get<Product[]>('assets/product.json');
    }

    addPatient(username, drusername): Observable<any> {

      // let drusername = sessionStorage.getItem('username');
      // let params = new HttpParams();
      // params = params.append('username:', sessionStorage.getItem('username'));

      alert(this.endpoint2 + username + " /// " + this.drusername);
      return this.http.put<any>(
        this.endpoint2 + username, 
        {username: this.drusername},
        {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa(sessionStorage.getItem("credentials"))})});

  }

}
