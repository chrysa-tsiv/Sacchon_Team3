import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable,  } from 'rxjs';
import { identifierModuleUrl } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class SigninService {

  constructor(private http: HttpClient,private params:HttpClient) { }
 
  private readonly endpoint =  'http://localhost:9000/app/userSignIn';
 
  signIn(username,password): Observable<any> {
 
    let data = {
      "username":username,
      "password":password
    }
    
    return this.http.post<any>(this.endpoint, data);
  
      }
}