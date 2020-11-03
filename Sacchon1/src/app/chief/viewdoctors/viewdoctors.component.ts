import { Component, OnInit } from '@angular/core';
import { ViewdoctorsService } from './viewdoctors.service';
import { Router } from "@angular/router";
import { ChiefDoctor } from './chiefdoctor.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-viewdoctors',
  templateUrl: './viewdoctors.component.html',
  styleUrls: ['./viewdoctors.component.scss']
})
export class ViewdoctorsComponent implements OnInit {

  doctors: ChiefDoctor[];

  constructor(
    private viewdoctors: ViewdoctorsService, 
    private http: HttpClient, 
    private router: Router) { }

  ngOnInit(): void {

    this.doctors = [];

    this.viewdoctors.getDoctors().subscribe(doctors => {
      this.doctors = doctors;
    });

  }
  private readonly endpoint = 'http://localhost:9000/app/doctor';


}
