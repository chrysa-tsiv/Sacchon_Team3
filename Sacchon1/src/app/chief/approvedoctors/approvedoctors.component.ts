import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { ApprovedDoctor } from './approveddoctors.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ApprovedoctorsService } from './approvedoctors.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-approvedoctors',
  templateUrl: './approvedoctors.component.html',
  styleUrls: ['./approvedoctors.component.scss']
})
export class ApprovedoctorsComponent implements OnInit {

  doctors: ApprovedDoctor[];

  constructor(
    private approvedoctors: ApprovedoctorsService, 
    private http: HttpClient, 
    private router: Router
  ) { }

  ngOnInit(): void {

    // this.doctors;

    this.approvedoctors.getDoctors().subscribe(doctors => {
      this.doctors = doctors;
    });
  }

  private readonly endpoint = 'http://localhost:9000/app/doctor';

  clickApprove(username) {
    this.approvedoctors.approveDoctor(username).subscribe(data => { 
      console.log(data);
      // alert("doctor approved");
    });
    }
  
  
}
