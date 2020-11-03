import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms'
import { Measurement } from './measurement.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AddmeasurementService } from './addmeasurement.service';
import { stringify } from 'querystring';
import { DatePipe, formatDate } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-addmeasurement',
  templateUrl: './addmeasurement.component.html',
  styleUrls: ['./addmeasurement.component.scss']
})
export class AddmeasurementComponent implements OnInit {

  measurementsForm: FormGroup;

  private readonly endpoint = 'http://localhost:9000/app/measurement'; 
  
  constructor(
    private addmeasurementservice: AddmeasurementService,
    private datePipe: DatePipe,
    private router: Router) {}

  id = sessionStorage.getItem('id');
  username = sessionStorage.getItem('username');
  role = sessionStorage.getItem('role');

// 
  ngOnInit(): void {

    this.measurementsForm = new FormGroup({
      glucoseLevel: new FormControl(null, Validators.required),
      carbIntake: new FormControl(null, Validators.required),
      meds: new FormControl(null),
      date: new FormControl(null)
  })
  }

  formSubmit(form: FormGroup) {
    if (this.measurementsForm.valid) {
      // console.log(this.measurementsForm.value);
      let measurement: Measurement = {
        glucoseLevel: this.measurementsForm.get('glucoseLevel').value,
        carbIntake: this.measurementsForm.get('carbIntake').value,
        meds: this.measurementsForm.get('meds').value,
        date: formatDate(new Date(), 'yyyy-MM-dd', 'en-US'), //new Date().toLocaleDateString(), 
        patientID: Number(sessionStorage.getItem('id'))      
      };
    
      /////////////////////////////////////////////////////////////////////////////
      this.addmeasurementservice.createMeasurement(measurement).subscribe(data => { alert(JSON.stringify(data))});
      alert("Measurement added");
      this.router.navigate(['patientprofile/mymeasurements']);
      //this.service.postToServer(this.myForm.value);
    }
    else {
      // console.log('Form not valid');
      alert("Please enter values for all fields!");
    }
  }

}