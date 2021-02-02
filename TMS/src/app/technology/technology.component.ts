import { Component, OnInit } from '@angular/core';
import { TechnologyService } from '../technology.service';
import{Technology} from './technology'
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-technology',
  templateUrl: './technology.component.html',
  styleUrls: ['./technology.component.css']
})
export class TechnologyComponent implements OnInit {

  isAdd=false
  technology:Technology[];
  constructor(private technologyService:TechnologyService,private router:Router) { 
    
  }

  ngOnInit(): void {
    this.loadTechnologies();
    if(sessionStorage['role']=='Training Coordinator')
      this.isAdd=true;
  }
  handleSuccessResp(response) {
    this.technology = response['data'];
    console.log(this.technology);
  }
  loadTechnologies(){
    const request=this.technologyService.getTechnologies();
    request.subscribe(response => this.handleSuccessResp(response),
    
    error =>  {
      console.log('Error Please retry')
    }
  );
      
  }

  onAdd()
  {
    this.router.navigate(['/home/add-technology']);
  }
  
}
