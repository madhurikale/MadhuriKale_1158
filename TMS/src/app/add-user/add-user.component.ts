import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { FormGroup,FormControl, Validators } from '@angular/forms';
import{Router} from  '@angular/router'
@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  userForm:FormGroup;

 
 

  constructor(private httpClient:HttpClient,private toastr: ToastrService,private router:Router) { 
   this.userForm=new FormGroup({
    user_name: new FormControl('',Validators.required),
    mobile: new FormControl('',Validators.required),
    email: new FormControl('',Validators.required),
    role: new FormControl('',Validators.required),
    password: new FormControl('',Validators.required)
  })
  }

  ngOnInit(): void {
  }
saveDetails()
{
  const{user_name,mobile,role,email,password}=this.userForm.value
 const body={
  user_name:user_name,mobile:mobile,role:role,email:email,password:password
 }
 //console.log(this.form.get('email'))
    
  
  const url="http://localhost:8080/users"
  const request=this.httpClient.post(url,body)
  request.subscribe(response=>{
    this.toastr.success("User Added Succesfully")
    console.log(response);
    this.router.navigate(['/home/user']);
  },
  error => {
    this.toastr.error("User already exsist...please provide correct data")
    this.router.navigate(['/home/user']);
  })
  this.userForm.reset();
}

onCancel(){
  this.router.navigate(['/home/user']);
}
}
