import { ActivatedRoute,Router } from '@angular/router';
import { TrainingRequestService } from './../training-request.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-incoming-request',
  templateUrl: './incoming-request.component.html',
  styleUrls: ['./incoming-request.component.css']
})
export class IncomingRequestComponent implements OnInit {

  trainingRequests=[]
  constructor(private trainingRequestService:TrainingRequestService,
    private router:Router,
    private activatedRoute:ActivatedRoute
    ) { 

  }

  ngOnInit(): void {
    this.loadIncomingRequest()
  

  }

loadIncomingRequest()
{
  this.trainingRequestService
      .getIncomingRequest()
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