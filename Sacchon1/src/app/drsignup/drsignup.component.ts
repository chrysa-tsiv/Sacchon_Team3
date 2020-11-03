import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms'
import { Doctor } from './doctor.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DrsignupService } from './drsignup.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-drsignup',
  templateUrl: './drsignup.component.html',
  styleUrls: ['./drsignup.component.scss']
})
export class DrsignupComponent implements OnInit {

  DrSignUpForm: FormGroup
  private readonly endpoint = 'http://localhost:9000/app/doctor'; //doctor
  constructor(
    private drsignupservice: DrsignupService, 
    private router: Router) { }

  ngOnInit(): void {

    this.DrSignUpForm = new FormGroup({
      name: new FormControl(null, Validators.required),
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required),
      specialty: new FormControl(null, Validators.required),
      description: new FormControl()
    })

  }

  formSubmit(form: FormGroup) {
    if (this.DrSignUpForm.valid) {
      // console.log(this.signUpForm.value);
      let doctor: Doctor = {
        name: this.DrSignUpForm.get('name').value,
        username: this.DrSignUpForm.get('username').value,
        password: this.DrSignUpForm.get('password').value,
        specialty: this.DrSignUpForm.get('specialty').value,
        description: this.DrSignUpForm.get('description').value
      };
      // this.signupservice.getPatient().subscribe(data => { alert(JSON.stringify(data)) });
      this.drsignupservice.createDoctor(doctor).subscribe(data => { 
        // alert(JSON.stringify(data)) 
        alert("Your username is '" + JSON.stringify(data.username) + "'. Sign in with your password to view your account.");
        this.router.navigate(['signin']);
      });
  }
    else {
      // console.log('Form not valid');
      alert("Please enter values for all fields!");
    }
  }

}