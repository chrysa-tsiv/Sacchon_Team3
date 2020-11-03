import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms'
import { Editdoctor } from './editdoctor.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EditdoctorService } from './editdoctor.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-editdoctorinfo',
  templateUrl: './editdoctorinfo.component.html',
  styleUrls: ['./editdoctorinfo.component.scss']
})
export class EditdoctorinfoComponent implements OnInit {

  editDoctorForm: FormGroup;

  public username1: string;
  public id1: string;
  public drname1: string;
  public specialty1: string;
  public description1: string;

  private readonly endpoint = 'http://localhost:9000/app/doctor';
  constructor(
    private editdoctor: EditdoctorService, 
    private router: Router
  ) {}

  ngOnInit(): void {

    this.editDoctorForm = new FormGroup({
      name: new FormControl(null, Validators.required),
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required),
      specialty: new FormControl(),
      description: new FormControl()
    })

    this.username1 = sessionStorage.getItem('username');
    this.id1 = sessionStorage.getItem('id');
    this.drname1 = sessionStorage.getItem('name');
    this.specialty1 = sessionStorage.getItem('specialty');
    this.description1 = sessionStorage.getItem('description');
    
  }

  formEdit(form: FormGroup) {
    if (this.editDoctorForm.valid) {
      // console.log(this.signUpForm.value);
      let doctor: Editdoctor = {
        name: this.editDoctorForm.get('name').value,
        username: this.editDoctorForm.get('username').value,
        password: this.editDoctorForm.get('password').value,
        specialty: this.editDoctorForm.get('specialty').value,
        description: this.editDoctorForm.get('description').value
      };
      // this.signupservice.getPatient().subscribe(data => { alert(JSON.stringify(data)) });
      //////////////////////////////// this doesnt work ;-;
      this.editdoctor.updateDoctor(doctor).subscribe(data => {
        alert("Your info has been updated");
        this.router.navigate(['doctorprofile']);
      });
      
    }
    else {
      // console.log('Form not valid');
      alert("Please enter values for all fields!");
    }
  }

}
