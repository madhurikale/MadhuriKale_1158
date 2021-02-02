import { Router } from '@angular/router';
import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email = ''
  password = ''

  constructor(
    private toastr: ToastrService,
    private router: Router,
    private authService: AuthService) { }

  ngOnInit(): void {
  }

  onLogin() {
    if (this.email.length == 0) {
      this.toastr.error('please enter email')
    } else if (this.password.length == 0) {
      this.toastr.error('please enter password')
    } else {
      this.authService
        .login(this.email, this.password)
        .subscribe(response => {
          if (response['status'] == 'success') {
            const data = response['data']
            console.log(data)

            // cache the user info
            sessionStorage['empid'] = data['empid']
            sessionStorage['role'] = data['role']
            sessionStorage['name'] = data['user_name']

            this.toastr.success(`Welcome ${data['user_name']} to TMS`)

            // goto the dashboard
            this.router.navigate(['/home'])

          } else {
            alert(response['message'])
          }
        })
    }
  }

}

