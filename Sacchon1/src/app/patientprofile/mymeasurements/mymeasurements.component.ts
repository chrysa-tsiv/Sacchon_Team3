import { Component, OnInit } from '@angular/core';
import { MymeasurementsService } from './mymeasurements.service';
import { Router } from "@angular/router";
import { MyMeasurement } from './mymeasurement.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Measurement } from '../addmeasurement/measurement.model';
import { stringify } from 'querystring';
import { DatePipe, formatDate } from '@angular/common';

@Component({
  selector: 'app-mymeasurements',
  templateUrl: './mymeasurements.component.html',
  styleUrls: ['./mymeasurements.component.scss']
})
export class MymeasurementsComponent implements OnInit {

  measurements: Measurement[];

  

  id = sessionStorage.getItem('id');
  username = sessionStorage.getItem('username');
  role = sessionStorage.getItem('role');

  constructor(    
    private viewmeasurement: MymeasurementsService, 
    private http: HttpClient, 
    private router: Router) { }

  ngOnInit(): void {

    this.measurements = [];

    this.viewmeasurement.getMeasurement().subscribe(measurements => {
      this.measurements = measurements;
      
      
      
    });

  }


   goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }


}