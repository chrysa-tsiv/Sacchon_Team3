import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms'
import { Doctor } from '../drsignup/doctor.model';
import { DoctorinfoService } from './doctorinfo.service';

@Component({
  selector: 'app-doctorprofile',
  templateUrl: './doctorprofile.component.html',
  styleUrls: ['./doctorprofile.component.scss']
})
export class DoctorprofileComponent implements OnInit {
  doctor:Doctor;
  public drusername: string;
  public id: string;
  public role: string;
  public drname: string;
  public specialty: string;
  public description: string;

  constructor(private doctorInfo: DoctorinfoService) { }

  ngOnInit(): void {
   
    this.drusername = sessionStorage.getItem('username');
    this.role = sessionStorage.getItem('role');
    this.id = sessionStorage.getItem('id');


    this.doctorInfo.getInfo().subscribe(doctor => {
      this.doctor = doctor;
      if (doctor !== null) {
        sessionStorage.setItem("credentials", doctor.username + ":" + doctor.password)
        sessionStorage.setItem('drname', doctor.name)
        sessionStorage.setItem('specialty', doctor.specialty)
        sessionStorage.setItem('description', doctor.description)
      }else{
        alert("Patient info not available");
      }
  });
  this.drname = sessionStorage.getItem('drname');
  this.specialty = sessionStorage.getItem('specialty');
  this.description = sessionStorage.getItem('description');
  

  

}

}