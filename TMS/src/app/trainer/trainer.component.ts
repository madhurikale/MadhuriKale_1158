import { Component, OnInit } from '@angular/core';
import{Trainer} from './trainer'
import{TrainerService} from '../trainer.service'
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-trainer',
  templateUrl: './trainer.component.html',
  styleUrls: ['./trainer.component.css']
})
export class TrainerComponent implements OnInit {
  allTrainers:Trainer[];
  isAdd=false
  constructor(private trainerService:TrainerService,
    private router:Router) { }

  ngOnInit(): void {
    this.loadTrainers()
    if(sessionStorage['role']=='Training Coordinator')
      this.isAdd=true;
  }

  handleTrainersSuccessResp(response) {
    this.allTrainers = response;
    console.log(this.allTrainers)
  }
  loadTrainers(){

    const request=this.trainerService.getTrainers();
    request.subscribe(response=>this. handleTrainersSuccessResp(response),
    error=>console.log(error)
    );
  }

  onAdd()
  {
    this.router.navigate(['/home/add-trainer']);
  }
}
