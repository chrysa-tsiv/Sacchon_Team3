import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DeactivateaccountService } from './deactivateaccount.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-deactivateaccount',
  templateUrl: './deactivateaccount.component.html',
  styleUrls: ['./deactivateaccount.component.scss']
})
export class DeactivateaccountComponent implements OnInit {

  id = sessionStorage.getItem('id');
  username = sessionStorage.getItem('username');
  role = sessionStorage.getItem('role');

  constructor(
    private deactivateaccount: DeactivateaccountService,
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
