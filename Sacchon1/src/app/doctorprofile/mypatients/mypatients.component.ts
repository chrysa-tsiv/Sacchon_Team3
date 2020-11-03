import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from 'src/app/signup/patient.model';
import { MypatientsService } from './mypatients.service';

@Component({
  selector: 'app-mypatients',
  templateUrl: './mypatients.component.html',
  styleUrls: ['./mypatients.component.scss']
})
export class MypatientsComponent implements OnInit {

  patients: Patient[];
  username:string;

  id = sessionStorage.getItem('id');
  drusername = sessionStorage.getItem('drusername');
  role = sessionStorage.getItem('role');

  constructor(private mypatients:MypatientsService,private router: Router) { }

  ngOnInit(): void {
    this.patients = [];

    this.mypatients.getPatients().subscribe(patients => {
      this.patients = patients;

      this.patients.forEach(patient => { sessionStorage.setItem("username",patient.username)
        
      });
  
    }) ;

    

    
  }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

  clickAdd(username) {

  sessionStorage.setItem('username',username)
    this.goToPage('doctorprofile/mypatients/patient')
  }

  
  
  

}