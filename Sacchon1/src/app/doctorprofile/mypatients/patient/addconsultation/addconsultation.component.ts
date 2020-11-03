import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { stringify } from 'querystring';
import { DatePipe, formatDate } from '@angular/common';
import { Router } from '@angular/router';
import { NewconsultationService } from './newconsultation.service';
import { Consultation } from 'src/app/patientprofile/calendar/consultation.model';

@Component({
  selector: 'app-addconsultation',
  templateUrl: './addconsultation.component.html',
  styleUrls: ['./addconsultation.component.scss']
})
export class AddconsultationComponent implements OnInit {

  consultationForm: FormGroup;

  private readonly endpoint = 'http://localhost:9000/app/appointment'; 

  constructor(
    private addconsultation: NewconsultationService,
    private datePipe: DatePipe,
    private router: Router) { }

    id = sessionStorage.getItem('id');
    username = sessionStorage.getItem('username');
    role = sessionStorage.getItem('role');

  ngOnInit(): void {

    this.consultationForm = new FormGroup({
      consultation: new FormControl(),
      edit: new FormControl()
  })
  }

  // formSubmit(form: FormGroup) {
  //     // console.log(this.measurementsForm.value);
  //     let newconsultation: Consultation = {
  //       consultation: this.consultationForm.get('consultation').value,
  //       edit: this.consultationForm.get('edit').value 
  //     };   

  //     this.NewconsultationService.addConsultation(newconsultation).subscribe(data => { alert(JSON.stringify(data))});
  //     alert("Consultation added");
  //     this.router.navigate(['http://localhost:4200/doctorprofile/mypatients']);
  //     //this.service.postToServer(this.myForm.value);
  //   }

}
