import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TechnologyService {

 
  constructor(private httpClient:HttpClient) {
    
   }
   getTechnologies(){
     const url='http://localhost:8080/technologies';
     const request=this.httpClient.get(url);
     return request;
        
    
   }
   addTechnologies(){
    const url='http://localhost:8080/technologies';
    const request=this.httpClient.get(url);
    return request;
       
   
  }
}
