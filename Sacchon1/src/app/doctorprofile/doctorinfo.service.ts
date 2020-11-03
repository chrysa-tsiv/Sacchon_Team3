import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Doctor } from '../drsignup/doctor.model';

@Injectable({
  providedIn: 'root'
})
export class DoctorinfoService {

  constructor(private http: HttpClient,private params:HttpClient) { }
  private readonly endpoint =  'http://localhost:9000/app/doctor';

  getInfo():Observable <Doctor> {
  
    return this.http.get<Doctor>(
      this.endpoint+'/'+sessionStorage.getItem('id'),
        {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa(sessionStorage.getItem("credentials"))})}
        );
}
}