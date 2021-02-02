import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class UserinfoService {

  constructor(private httpClient:HttpClient) {
   
  }
  getUsers(){
    const url='http://localhost:8080/users';
    const request=this.httpClient.get(url);
    return request;
       
   
  }
}
