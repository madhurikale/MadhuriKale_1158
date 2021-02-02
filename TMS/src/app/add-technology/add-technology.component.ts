import { Component, OnInit } from '@angular/core';
import { TechnologyService } from '../technology.service';
import {HttpClient} from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup,FormControl, Validators } from '@angular/forms';
@Component({
  selector: 'app-add-technology',
  templateUrl: './add-technology.component.html',
  styleUrls: ['./add-technology.component.css']
})
export class AddTechnologyComponent implements OnInit {

  technologyForm:FormGroup;
  constructor(private httpClient:HttpClient,
    private toastr: ToastrService,
    private technologyService:TechnologyService,
    private router:Router) {

      this.technologyForm = new FormGroup({
        technology_name: new FormControl('',Validators.required),
        
      })

     }

  ngOnInit(): void {
  }

  saveDetails()
  {
    const{technology_name}=this.technologyForm.value
   const body={
    technology_name:technology_name
   }
    const url="http://localhost:8080/technologies"
    const request=this.httpClient.post(url,body)
    request.subscribe(response=>{
      console.log(response['status'])
      if(response['status']=='success')
      {
        this.toastr.success(response['message'])
      console.log(response)
      this.router.navigate(['/home/technology'])
      }
     if(response['status']=='error')
    {
      this.toastr.error(response['message'])
      this.router.navigate(['/home/technology'])
    }
  
    
  } , error => {
    this.toastr.error("Technology Already exsist..please provide correct Input")
  }
  )
  this.technologyForm.reset();
  }
  onCancel(){
    this.router.navigate(['/home/technology'])
  }

}
