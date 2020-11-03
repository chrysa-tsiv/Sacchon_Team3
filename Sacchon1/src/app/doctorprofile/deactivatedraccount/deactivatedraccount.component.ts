import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DeactivatedraccountService } from './deactivatedraccount.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-deactivatedraccount',
  templateUrl: './deactivatedraccount.component.html',
  styleUrls: ['./deactivatedraccount.component.scss']
})
export class DeactivatedraccountComponent implements OnInit {

  id = sessionStorage.getItem('id');
  username = sessionStorage.getItem('username');
  role = sessionStorage.getItem('role');

  constructor(
    private deactivateaccount: DeactivatedraccountService,
    private http: HttpClient, 
    private router: Router) { }

  ngOnInit(): void {
  }

  deactivateAccount() {
    this.deactivateaccount.deactivateAccount().subscribe(data => { 
      console.log(data);
      alert("Account deactivated");
      this.router.navigate(['/home']);
    });
  }
}