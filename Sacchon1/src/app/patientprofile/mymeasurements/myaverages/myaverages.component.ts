import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MyMeasurement } from '../mymeasurement.model';
import { MyaveragesService } from './myaverages.service';

@Component({
  selector: 'app-myaverages',
  templateUrl: './myaverages.component.html',
  styleUrls: ['./myaverages.component.scss']
})
export class MyaveragesComponent implements OnInit {
  avgForm: FormGroup;
  fromDate: FormControl;
  toDate: FormControl;

  model;
  
  
  constructor(private myaverages:MyaveragesService,private router: Router) { }

  ngOnInit(): void {

   

    this.avgForm = new FormGroup({
      fromDate: new FormControl(null, Validators.required),
      toDate: new FormControl(null, Validators.required)
      
    })
         
        this.fromDate = this.avgForm.get('fromDate').value;
        this.toDate = this.avgForm.get('toDate').value;
        sessionStorage.setItem('fromDate',String(this.fromDate));
        sessionStorage.setItem('toDate',String(this.toDate));

    
  }



goToPage(pageName:string){
  this.router.navigate([`${pageName}`]);
}
}