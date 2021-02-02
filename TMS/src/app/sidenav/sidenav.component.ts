import { Component, OnInit } from '@angular/core';
import{Router}from '@angular/router';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {

 isAdmin=false
 isProjectManager=false
 isTrainingCoordinator=false
name=""
role=""

  constructor(private router:Router) { }

  ngOnInit(): void {
    this.name=sessionStorage['name']
    this.role=sessionStorage['role']
    if(sessionStorage['role']=="admin")
    this.isAdmin=true
    if(sessionStorage['role']=="Project Manager")
    this.isProjectManager=true
    if(sessionStorage['role']=="Training Coordinator")
    this.isTrainingCoordinator=true
    console.log(this.isAdmin)
    console.log(this.isProjectManager)
    console.log( this.isTrainingCoordinator)
  }

  onLogout(){
    sessionStorage.removeItem('empid')
    sessionStorage.removeItem('name')
    sessionStorage.removeItem('email')
    this.router.navigate(['/login'])
  }
}
