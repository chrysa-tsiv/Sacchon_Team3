import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms'
import { Editpatient } from './editpatient.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EditpatientService } from './editpatient.service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-editpatientinfo',
  templateUrl: './editpatientinfo.component.html',
  styleUrls: ['./editpatientinfo.component.scss']
})
export class EditpatientinfoComponent implements OnInit {

  editPatientForm: FormGroup;

  public username1: string;
  public id1: string;
  public role1: string;
  public name1: string;
  public gender1: string;
  public dob1: string;
  public height1: string;
  public weight1: string;
  public history1: string;

  private readonly endpoint = 'http://localhost:9000/app/patient'; //patient
  constructor(
    private editpatient: EditpatientService, 
    private router: Router) {}

  ngOnInit(): void {

    this.editPatientForm = new FormGroup({
      name: new FormControl(null, Validators.required),
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required),
      gender: new FormControl(null, Validators.required),
      dob: new FormControl(null, Validators.required),
      height: new FormControl(),
      weight: new FormControl(),
      history: new FormControl()

    })

    this.username1 = sessionStorage.getItem('username');
    this.role1 = sessionStorage.getItem('role');
    this.id1 = sessionStorage.getItem('id');
    this.name1 = sessionStorage.getItem('name');
    this.gender1 = sessionStorage.getItem('gender');
    this.dob1 = sessionStorage.getItem('dob');
    this.height1 = sessionStorage.getItem('height');
    this.weight1 = sessionStorage.getItem('weight');
    this.history1 = sessionStorage.getItem('history');
  }

  formEdit(form: FormGroup) {
    if (this.editPatientForm.valid) {
      // console.log(this.signUpForm.value);
      let patient: Editpatient = {
        name: this.editPatientForm.get('name').value,
        username: this.editPatientForm.get('username').value,
        password: this.editPatientForm.get('password').value,
        gender: this.editPatientForm.get('gender').value,
        dob: this.editPatientForm.get('dob').value,
        height: this.editPatientForm.get('height').value,
        weight: this.editPatientForm.get('weight').value,
        history: this.editPatientForm.get('history').value
      };
      console.log(JSON.stringify(patient));
      this.editpatient.updatePatient(patient).subscribe(data => { 
      
        // alert(JSON.stringify(data)) 
        alert("Your info has been updated");
        this.router.navigate(['patientprofile']);
      });
    }
    else {
      // console.log('Form not valid');
      alert("Please enter values for all fields!");
    }
  }

}
