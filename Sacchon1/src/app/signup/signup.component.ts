import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms'
import { Patient } from './patient.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { SignupService } from './signup.service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  signUpForm: FormGroup

  private readonly endpoint = 'http://localhost:9000/app/patient'; //patient
  constructor(
    private signupservice: SignupService, 
    private router: Router) {}

  ngOnInit(): void {

    this.signUpForm = new FormGroup({
      name: new FormControl(null, Validators.required),
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required),
      gender: new FormControl(null, Validators.required),
      dob: new FormControl(null, Validators.required),
      height: new FormControl(),
      weight: new FormControl(),
      history: new FormControl()
    })
  }

  formSubmit(form: FormGroup) {
    if (this.signUpForm.valid) {
      // console.log(this.signUpForm.value);
      let patient: Patient = {
        name: this.signUpForm.get('name').value,
        username: this.signUpForm.get('username').value,
        password: this.signUpForm.get('password').value,
        gender: this.signUpForm.get('gender').value,
        dob: this.signUpForm.get('dob').value,
        height: this.signUpForm.get('height').value,
        weight: this.signUpForm.get('weight').value,
        history: this.signUpForm.get('history').value
      };
      this.signupservice.createPatient(patient).subscribe(data => { 
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
