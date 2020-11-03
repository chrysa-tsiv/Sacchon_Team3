import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms'
import { Patient } from '../signup/patient.model';
import { PatientinfoService } from './patientinfo.service';

@Component({
  selector: 'app-patientprofile',
  templateUrl: './patientprofile.component.html',
  styleUrls: ['./patientprofile.component.scss']
})
export class PatientprofileComponent implements OnInit {
  
  patient: Patient;
  public username: string;
  public id: string;
  public role: string;
  public name: string;
  public gender: string;
  public dob: string;
  public height: string;
  public weight: string;
  public history: string;

  constructor(private patientInfo: PatientinfoService) { }

  ngOnInit(): void {
   
    this.username = sessionStorage.getItem('username');
    this.role = sessionStorage.getItem('role');
    this.id = sessionStorage.getItem('id');
    
      this.patientInfo.getInfo().subscribe(patient => {
      this.patient = patient;
      if (patient !== null) {
        sessionStorage.setItem("credentials", patient.username + ":" + patient.password)
        sessionStorage.setItem('name', patient.name)
        sessionStorage.setItem('gender', patient.gender)
        sessionStorage.setItem('dob', patient.dob)
        sessionStorage.setItem('height', String(patient.height))
        sessionStorage.setItem('weight', String(patient.weight))
        sessionStorage.setItem('history', patient.history)
      }else{
        alert("Patient info not available");
      }
  });

    this.username = sessionStorage.getItem('username');
    this.role = sessionStorage.getItem('role');
    this.id = sessionStorage.getItem('id');
    this.name = sessionStorage.getItem('name'),
    this.gender = sessionStorage.getItem('gender'),
    this.dob = sessionStorage.getItem('dob'),
    this.height = sessionStorage.getItem('height'),
    this.weight = sessionStorage.getItem('weight'),
    this.history = sessionStorage.getItem('history')
  

  }

 
}