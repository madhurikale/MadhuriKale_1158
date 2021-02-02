import { Component, OnInit } from '@angular/core';
import{TrainerService} from '../trainer.service'
import { TechnologyService } from '../technology.service';
import{Technology} from './../technology/technology'
import {HttpClient} from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup,FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-trainer',
  templateUrl: './add-trainer.component.html',
  styleUrls: ['./add-trainer.component.css']
})
export class AddTrainerComponent implements OnInit {

trainerForm:FormGroup;




 // name=''
 // email=''
  //mobile=''
  
  technology_id=0
  technology:Technology[];
  constructor(private httpClient:HttpClient,
    private toastr: ToastrService,
    private technologyService:TechnologyService,
    private router:Router) {
      this.trainerForm = new FormGroup({
        trainer_name: new FormControl('',Validators.required),
        mobile: new FormControl('',Validators.required),
        email: new FormControl('',Validators.required),
        technology_id: new FormControl('',Validators.required)
      })

     }

  ngOnInit(): void {
    this.loadTechnologies()
  }
  handleTechnologySuccessResp(response) {
    this.technology = response;
    console.log(this.technology)
  }
  loadTechnologies(){
    const request=this.technologyService.getTechnologies();
    request.subscribe(response => { this.handleTechnologySuccessResp(response['data']) },
    error => { console.log(`err : `) }
  );
  }
  saveDetails()
  {
    const{trainer_name,mobile,email,technology_id}=this.trainerForm.value
   const body={
    trainer_name:trainer_name,
    mobile:mobile,email:email,technology_id:technology_id
   }
    const url="http://localhost:8080/trainers"
    const request=this.httpClient.post(url,body)
    request.subscribe(response=>{
      console.log(response['status'])
      if(response['status']=='success')
      {
        this.toastr.success(response['message'])
      console.log(response)
      this.router.navigate(['/home/trainer'])
      }
     if(response['status']=='error')
    {
      this.toastr.success(response['message'])
      this.router.navigate(['/home/trainer'])
    }
  
    
  } , error => {
    this.toastr.error("Trainer Already exsist..please provide correct Input")
  }
  )
  this.trainerForm.reset();
  }

  onCancel(){
    this.router.navigate(['/home/trainer'])
  }
}
