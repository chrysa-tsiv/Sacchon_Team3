import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { SigninService } from './signin.service';


@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

  
  signInForm: FormGroup
  username: string
  password: string


  constructor(private router: Router, private signInService: SigninService) { }


  ngOnInit(): void {


    this.signInForm = new FormGroup({
      username: new FormControl(),
      password: new FormControl()
    })

  }

  logIn(): void {

    this.username = this.signInForm.get('username').value,
    this.password = this.signInForm.get('password').value

    this.signInService.signIn(this.username, this.password).subscribe(data => {
      alert(JSON.stringify(data))
     // console.log(data)
      
     
      if (data !== null) {
        sessionStorage.setItem('username', data.username)
        sessionStorage.setItem('password', data.password)
        sessionStorage.setItem('role', data.role)
        sessionStorage.setItem('id', data.id)
        sessionStorage.setItem("credentials", data.username + ":" + data.password)
        if (data.role == 'doctor') {
          this.router.navigate(['doctorprofile']);
        } else if (data.role == 'patient') {
          this.router.navigate(['patientprofile']);
        } else if (data.role == 'admin'){
          this.router.navigate(['chief']);
        }
      }else{
        alert("Wrong username or password!");}
    });

  }

 
}
