import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TrainerService {

  
  
  constructor(private httpClient:HttpClient) {
    
   }
   getTrainers(){
     const url='http://localhost:8080/trainers';
     const request=this.httpClient.get(url);
     return request;
        
    
   }
}
