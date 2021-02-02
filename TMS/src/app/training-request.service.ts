import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TrainingRequestService {



  constructor(private httpClient:HttpClient) { 

   
  }

  createRequest (empid: number, technology_id: number, trainer_id: number,  start_date: string, end_date: string, no_of_trainee: number, comment: string)
  {
    const body={
      empid:empid,
      technology_id:technology_id,
      trainer_id:trainer_id,
      startDate:start_date,
      endDate:end_date,
      no_of_trainee:no_of_trainee,
      project_mgr_status:"requested",
      traning_cor_status:"requested",
      comment:comment

    }
    const url='http://localhost:8080/trainings';
    const request= this.httpClient.post(url,body)
    return request;
  }



  updateRequest (id:number,empid: number, technology_id: number, trainer_id: number,  start_date: string, end_date: string, no_of_trainee: number, comment: string)
  {
    const body={
      empid:empid,
      technology_id:technology_id,
      trainer_id:trainer_id,
      startDate:start_date,
      endDate:end_date,
      no_of_trainee:no_of_trainee,
      project_mgr_status:"updated",
      traning_cor_status:"updated",
      comment:comment

    }
    const url='http://localhost:8080/trainings/'+id;
    const request= this.httpClient.put(url,body)
    return request;
  }


  getIncomingRequest()
  {
    const url='http://localhost:8080/trainings/requested-training';
    return this.httpClient.get(url);
  }

  getPendingRequest()
  {
    const url='http://localhost:8080/trainings/pending-training/'+sessionStorage['empid'];
    return this.httpClient.get(url);
  }
  getRequestById(id)
  {
    const url='http://localhost:8080/trainings/requested-training/'+id;
    return this.httpClient.get(url);

  }

  
  getTrainingDetails(id)
  {
    const url='http://localhost:8080/trainings/'+id;
    return this.httpClient.get(url);

  }
  getApproveByTrainingCor(id)
  {
    const body={}
    const url='http://localhost:8080/trainings/approve-by-training-cor/'+id;
    return this.httpClient.put(url,body);
  }
  rejectByTrainingCor(id)
  {
    const body={}
    const url='http://localhost:8080/trainings/reject-by-training-cor/'+id;
    return this.httpClient.put(url,body);
  }
  rejectByprojectMgr(id)
  {
    const body={}
    const url='http://localhost:8080/trainings/reject-by-project-mgr/'+id;
    return this.httpClient.put(url,body);
  }

  getTrainingHistory(id)
  {
    const url='http://localhost:8080/trainings/training-history/'+id;
    return this.httpClient.get(url);

  }
  getAllTrainingHistory()
  {
    const url='http://localhost:8080/trainings//training-history';
    return this.httpClient.get(url);

  }
}
