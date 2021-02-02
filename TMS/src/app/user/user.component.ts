import{UserinfoService} from '../userinfo.service'
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  allUsers:[];
  constructor(private userService:UserinfoService,
    private router:Router) { }

  ngOnInit(): void {
    this.loadUsers()
  }
  loadUsers(){

    const request=this.userService.getUsers();
    request.subscribe(response=>{
      console.log(this.allUsers)
       this.allUsers=response['data'];
    }
   
    );
  }

  onAdd()
  {
    this.router.navigate(['/home/add-user']);
  }
}
