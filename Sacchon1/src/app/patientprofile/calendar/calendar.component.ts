import { Component, OnInit } from '@angular/core';
import { Consultation } from './consultation.model';
import { Router } from "@angular/router";
import { ConsultationsService } from './consultations.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.scss']
})
export class CalendarComponent implements OnInit {

  loops: Consultation[];

  id1 = sessionStorage.getItem('id');
  username1 = sessionStorage.getItem('username');
  role1 = sessionStorage.getItem('role');

  constructor(
    private getconsultation: ConsultationsService, 
    private http: HttpClient, 
    private router: Router) { }

  ngOnInit(): void {

    this.loops = [];

    this.getconsultation.getConsultations().subscribe(consultation => {
      this.loops = consultation;
    });
  }

  private readonly endpoint = 'http://localhost:9000/app/appointment';

}
