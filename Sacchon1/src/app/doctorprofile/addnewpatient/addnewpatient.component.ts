import { Component, OnInit } from '@angular/core';
import { AddpatientService } from './addpatient.service';
import { Router } from "@angular/router";
import { DoctorAssign, NewPatient } from './newpatient.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-addnewpatient',
  templateUrl: './addnewpatient.component.html',
  styleUrls: ['./addnewpatient.component.scss']
})
export class AddnewpatientComponent implements OnInit {

  patients: NewPatient[];

  constructor(
    private viewpatients: AddpatientService, 
    private http: HttpClient, 
    private router: Router) { }

  ngOnInit(): void {
    // if(sessionStorage.getItem("credentials") == null){
    //   this.router.navigate(['login'])
    // }

    this.patients = [];

    this.viewpatients.getPatients().subscribe(patients => {
      this.patients = patients;
    });

  }

  private readonly endpoint = 'http://localhost:9000/app/patient'; //patient

  clickAdd(username) {

    let drusername = "username:" + sessionStorage.getItem('username');
    
    console.log(sessionStorage.getItem("username"));
    this.viewpatients.addPatient(username, drusername).subscribe(data => { alert(JSON.stringify(data)) });
  }

}
