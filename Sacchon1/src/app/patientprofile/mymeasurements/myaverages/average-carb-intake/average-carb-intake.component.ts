import { Component, OnInit } from '@angular/core';
import { MyaveragesService } from '../myaverages.service';
import { Chart } from 'chart.js';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-average-carb-intake',
  templateUrl: './average-carb-intake.component.html',
  styleUrls: ['./average-carb-intake.component.scss']
})
export class AverageCarbIntakeComponent implements OnInit {
  data;
  carbIntake: [];
  date: [];
  LineChart;
  constructor(private myaverages:MyaveragesService) { }


  ngOnInit(): void {
    this.data = [];
    
    this.myaverages.getAverages().
    pipe(map(data => data.map(({date, carbIntake })=>({date, carbIntake }))))
    .subscribe(data => {
      console.log(data)
      this.data = data;
    
   this.LineChart = new Chart('canvas', {
     type: 'line',
     data: {
       labels: data.map(data=>data.date),
       datasets: [
         { 
           data: data.map(data=>data.carbIntake),
          //  borderColor: "#3cba9f",
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
