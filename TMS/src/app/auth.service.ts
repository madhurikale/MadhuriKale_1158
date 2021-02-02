import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService implements CanActivate {
  url = 'http://localhost:8080/users'
  adminarray = ['/home/user', '/home/add-user', '/home','/home/dashboard']
  trainingCoordinatorArray = ['/home', '/home/dashboard', '/home/trainer', '/home/add-trainer', '/home/technology', '/home/add-technology', '/home/history', '/home/view-request', '/home/incoming-request']

  projectManagerArray = ['/home', '/home/dashboard', '/home/trainer', '/home/training-request', '/home/technology', , '/home/history', '/home/view-request']

  constructor(
    private router: Router,
    private httpClient: HttpClient) { }


  login(email: string, password: string) {
    const body = {
      email: email,
      password: password
    }

    return this.httpClient.post(this.url + '/login', body)
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (!sessionStorage['empid']) {
      // user is already logged in
      // launch the component
      console.log(route.url)
      console.log(state.url)
      this.router.navigate(['/login'])
      return false;
  
    }
      if (sessionStorage['role'] == 'Project Manager' && this.projectManagerArray.indexOf(state.url) <0) {
        this.router.navigate(['/home/dashboard'])
        return false
      }
      if (sessionStorage['role'] == 'Training Coordinator' && this.trainingCoordinatorArray.indexOf(state.url) <0) {
        this.router.navigate(['/home/dashboard'])
        return false
      }
      if (sessionStorage['role'] == 'admin' && this.adminarray.indexOf(state.url) <0) {
        this.router.navigate(['/home/dashboard'])
        return false
      }
      //this.router.navigate(['/home/dashoboard'])
      return true;
    }

    // force user to login
    
    //if (sessionStorage['role'] == 'Project Manager' && this.projectManagerArray.indexOf(state.url) != -1) {
      //return true
 //   }
   // if (sessionStorage['role'] == 'Training Coordinator' && this.trainingCoordinatorArray.indexOf(state.url) != -1) {
  //    return true
  //  }
   // if (sessionStorage['role'] == 'admin' && this.adminarray.indexOf(state.url) != -1) {
   //   return true
   // }

   
   
}
