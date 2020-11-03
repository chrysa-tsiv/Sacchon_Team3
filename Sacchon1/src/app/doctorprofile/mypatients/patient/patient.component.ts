import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Measurement } from 'src/app/patientprofile/addmeasurement/measurement.model';
import { Patient } from 'src/app/signup/patient.model';
import { PatientService } from './patient.service';


@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss']
})
export class PatientComponent implements OnInit {

  
  patient: Patient;
  public username: string;
  

  measurements;


  constructor(private viewpatientMeasurements:PatientService,private router: Router) { }

  ngOnInit(): void {


    this.username = sessionStorage.getItem('username')
  this.viewpatientMeasurements. getPatientMeasurements(this.username).subscribe(measurements => {
    this.measurements = measurements
  });

}




goToPage(pageName:string){
  this.router.navigate([`${pageName}`]);
}

}
