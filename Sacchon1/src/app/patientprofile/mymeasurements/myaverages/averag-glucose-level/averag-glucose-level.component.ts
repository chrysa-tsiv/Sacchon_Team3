import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Measurement } from 'src/app/patientprofile/addmeasurement/measurement.model';
import { MyMeasurement } from '../../mymeasurement.model';
import { MyaveragesService } from '../myaverages.service';
import { Chart } from 'chart.js';
import { map } from 'rxjs/operators';
@Component({
  selector: 'app-averag-glucose-level',
  templateUrl: './averag-glucose-level.component.html',
  styleUrls: ['./averag-glucose-level.component.scss']
})
export class AveragGlucoseLevelComponent implements OnInit {

 
  data;
  glucoseLevel: [];
  date: [];
  LineChart;
  constructor(private myaverages:MyaveragesService) { }

  ngOnInit(): void {

     this.data = [];
    
     this.myaverages.getAverages().
     pipe(map(data => data.map(({date, glucoseLevel })=>({date, glucoseLevel }))))
     .subscribe(data => {
       console.log(data)
       this.data = data;
     
    this.LineChart = new Chart('canvas',{
      type: 'line',
      data: {
        labels: data.map(data=>data.date),
        datasets: [
          { 
            data: data.map(data=>data.glucoseLevel),
            // borderColor: "#3cba9f",
            borderColor: "#007CC2", //pfizer color
            fill: false,
            lineTension: 0
          }
        ]
      },
      options: {
        legend: {
          display: false
        },
        scales: {
          xAxes: [{
            display: true
          }],
          yAxes: [{
            display: true
          }],
        }
      }
    });
     
      

      
});  
  
  }

}
