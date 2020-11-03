import { Component, OnInit } from '@angular/core';
import { ViewpatientsService } from './viewpatients.service';
import { Router } from "@angular/router";
import { ChiefPatient } from './chiefpatient.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-viewpatients',
  templateUrl: './viewpatients.component.html',
  styleUrls: ['./viewpatients.component.scss']
})
export class ViewpatientsComponent implements OnInit {

  patients: ChiefPatient[];

  constructor(
    private viewpatients: ViewpatientsService, 
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

}
