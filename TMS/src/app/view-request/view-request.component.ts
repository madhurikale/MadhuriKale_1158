import { ActivatedRoute, Router } from '@angular/router';
import { TrainingRequestService } from './../training-request.service';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-view-request',
  templateUrl: './view-request.component.html',
  styleUrls: ['./view-request.component.css']
})
export class ViewRequestComponent implements OnInit {
 
  trainingRequest=null
  isApprove=false
  isEdit=false
  isReject=false
  constructor(private trainingRequestService:TrainingRequestService,
    private activatedRoute:ActivatedRoute,
    private tostr:ToastrService,
    private router:Router) { }

  ngOnInit(): void {
     
     
      this.trainingRequestService
        .getRequestById(this.activatedRoute.snapshot.queryParams['id'])
        .subscribe(response => {
          if (response['status'] == 'success') {
            this.trainingRequest = response['data']
            console.log(this.trainingRequest)
            console.log(this.trainingRequest.training['project_mgr_status'])
            console.log(this.trainingRequest.training['traning_cor_status'])
            console.log(sessionStorage['role'])
            //console.log(this.trainingRequest.training['project_mgr_status'])
            if((this.trainingRequest.training['project_mgr_status']=='requested' && 
            this.trainingRequest.training['traning_cor_status']=='requested' && 
            sessionStorage['role']=='Training Coordinator') || 
            (this.trainingRequest.training['project_mgr_status']=='updated' && 
            this.trainingRequest.training['traning_cor_status']=='updated' && 
            sessionStorage['role']=='Project Manager' ) )
            {
              console.log(this.isApprove)
        console.log(this.isReject)
        console.log(this.isEdit)
              this.isApprove=true;
              this.isReject=true;
            }
            if(this.trainingRequest.training['project_mgr_status']=='requested' && 
            this.trainingRequest.training['traning_cor_status']=='requested' && 
            sessionStorage['role']=='Training Coordinator') 
            {
              this.isEdit=true;
            }

          } else {
            console.log(response['error'])
          }
        })

       
        console.log(this.isApprove)
        console.log(this.isReject)
        console.log(this.isEdit)
      
  }
  onApprove(){
    
      this.trainingRequestService.getApproveByTrainingCor(this.trainingRequest.training['id'])
      .subscribe(response=>{
        console.log(response['message']);
        this.tostr.success(response['message']);
        if(sessionStorage['role']=='Training Coordinator')
        this.router.navigate(['/home/incoming-request']);
        if(sessionStorage['role']=='Project Manager')
        this.router.navigate(['/home/request-status']);
      })
    
  }

  onReject()
  {
    if(sessionStorage['role']=='Training Coordinator' && 
    this.trainingRequest.training['project_mgr_status']=='requested' && 
    this.trainingRequest.training['traning_cor_status']=='requested' )
    {
      this.trainingRequestService.rejectByTrainingCor(this.trainingRequest.training['id'])
      .subscribe(response=>{
        console.log(response['message']);
        this.tostr.success(response['message']);
        this.router.navigate(['/home/incoming-request']);
      })
    }
    if(sessionStorage['role']=='Project Manager' && 
    this.trainingRequest.training['project_mgr_status']=='updated' && 
    this.trainingRequest.training['traning_cor_status']=='updated' )
    {
      this.trainingRequestService.rejectByprojectMgr(this.trainingRequest.training['id'])
      .subscribe(response=>{
        console.log(response['message']);
        this.tostr.success(response['message']);
        this.router.navigate(['/home/request-status']);
      })
    }
    
    
  }
  onEdit()
  {
    this.router.navigate(['/home/training-request'],{queryParams:{id:this.trainingRequest.training['id']}})
  }
  
}
