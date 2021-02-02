import { ActivatedRoute,Router } from '@angular/router';
import { TrainingRequestService } from './../training-request.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-request-status',
  templateUrl: './request-status.component.html',
  styleUrls: ['./request-status.component.css']
})
export class RequestStatusComponent implements OnInit {
  trainingRequests=[]
  constructor(private trainingRequestService:TrainingRequestService,
    private router:Router,
    private activatedRoute:ActivatedRoute
    ) { 

  }
  ngOnInit(): void {
    this.loadpendingRequest()
  

  }

loadpendingRequest()
{
  this.trainingRequestService
      .getPendingRequest()
      .subscribe(response => {
        if (response['status'] == 'success') {
          this.trainingRequests = response['data']
          console.log(this.trainingRequests)
        } else {
          console.log(response['error'])
        }
      })

}

onView(trainingRequest)
{
  console.log(trainingRequest.training['training_id'])
  this.router.navigate(['/home/view-request'],{queryParams:{id:trainingRequest.training['id']}})
}

}
