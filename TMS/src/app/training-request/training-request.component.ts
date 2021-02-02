import { Component, OnInit } from '@angular/core';
import { TechnologyService } from '../technology.service';
import { Technology } from './../technology/technology'
import { Trainer } from './../trainer/trainer'
import { TrainerService } from '../trainer.service'
import { TrainingRequestService } from '../training-request.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-training-request',
  templateUrl: './training-request.component.html',
  styleUrls: ['./training-request.component.css']
})
export class TrainingRequestComponent implements OnInit {



  trainingForm: FormGroup;

  training = null;

  technology: Technology[];
  allTrainers: Trainer[];
  trainers: Trainer[];
  constructor(private technologyService: TechnologyService, private trainerService: TrainerService,
    private trainingRequestService: TrainingRequestService, private toastr: ToastrService,
    private activatedRoute: ActivatedRoute,
    private router: Router) {
    this.trainingForm = new FormGroup({


      trainer_id: new FormControl('', Validators.required),
      technology_id: new FormControl('', Validators.required),

      comment: new FormControl(),
      no_of_trainee: new FormControl('', Validators.required),
      startDate: new FormControl('', Validators.required),
      endDate: new FormControl('', Validators.required),
    })

  }


  ngOnInit(): void {
    this.loadTechnologies()

    const id = this.activatedRoute.snapshot.queryParams['id'];
    console.log(id);
    if (id) {
      this.trainingRequestService.getTrainingDetails(id).
        subscribe(response => {
          if (response['status'] == 'success') {
            this.trainingForm.patchValue(response['data'])

            this.training = response['data']
            this.loadTrainers()

          }
        })
    }
    else
    {
      this.loadTrainers()
    }
  }
  handleTechnologySuccessResp(response) {
    this.technology = response;
    console.log(this.technology)
  }
  handleTrainersSuccessResp(response) {
    this.allTrainers = response;
    console.log(this.allTrainers)
  }
  loadTechnologies() {
    const request = this.technologyService.getTechnologies();
    request.subscribe(response => { this.handleTechnologySuccessResp(response['data']) },
    
      error => { console.log(`err : `)}
    );

  }
  loadTrainers() {

    const request = this.trainerService.getTrainers();
    request.subscribe(response => {
      this.handleTrainersSuccessResp(response);
      this.filterTrainers(this.training.technology_id);
    },
      error => console.log(error)
    );
  }
  filterTrainers(technologyId) {

    this.trainers = []
    console.log(technologyId)

    this.trainers = this.allTrainers.filter(trainer => {
      return trainer.technology_id == technologyId
    })


  }

  onSave() {
    const { technology_id, trainer_id, startDate, endDate, no_of_trainee, comment } = this.trainingForm.value
    if (this.training == null) {


      //console.log(empid,technology_id,trainer_id,startDate,endDate,no_of_trainee,comment)
      this.trainingRequestService.createRequest(sessionStorage['empid'], technology_id, trainer_id, startDate, endDate, no_of_trainee, comment)
        .subscribe(response => {
          console.log(`error:${response}`);
          this.toastr.success("request send succefully")
        }),
        error => console.log(error);

    }

    else {
      const id = this.activatedRoute.snapshot.queryParams['id'];
      this.trainingRequestService.updateRequest(id, this.training['empid'], technology_id, trainer_id, startDate, endDate, no_of_trainee, comment).
        subscribe(response => {
          if (response['status'] == 'success') {
            this.toastr.success(response['message'])
            this.router.navigate(['/home/incoming-request'])
          }
          else {
            this.toastr.success(response['message'])
            this.router.navigate(['/home/view-request'])
          }
        }, error => {
          this.toastr.error(error['message'])
        })
    }
    this.trainingForm.reset();
  }

  onCancel()
  {
    this.router.navigate(['/home/dashboard'])
  }
}
