import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  isNotAdmin=true;
  constructor() { }

  ngOnInit(): void {
    if(sessionStorage['role']=='admin')
    this.isNotAdmin=false;
  }

}
