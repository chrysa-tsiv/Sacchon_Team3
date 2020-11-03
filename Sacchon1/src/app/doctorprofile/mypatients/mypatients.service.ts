import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Patient } from 'src/app/signup/patient.model';

@Injectable({
  providedIn: 'root'
})
export class MypatientsService {

  constructor(private http: HttpClient,private params:HttpClient) { }

  private readonly endpoint =  'http://localhost:9000/app/patient';

  getPatients():Observable <Patient[]> {
  
    let params = new HttpParams();
    params = params.append('doctorID', sessionStorage.getItem('id'));
    return this.http.get<Patient[]>(
        this.endpoint,
          {headers:new HttpHeaders({'Authorization': 'Basic ' + btoa(sessionStorage.getItem("credentials"))}),params:params}
          );
         
    
}
}