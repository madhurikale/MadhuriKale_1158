
import { ActivatedRoute,Router } from '@angular/router';
import { TrainingRequestService } from './../training-request.service';
import { Component, OnInit,ElementRef,ViewChild } from '@angular/core';
import { FormGroup,FormControl, Validators } from '@angular/forms';
import {jsPDF} from 'jspdf'; 
import autoTable from 'jspdf-autotable'; 
import html2canvas from 'html2canvas';
@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  private htmlData: ElementRef;

 @ViewChild('htmlData') set content(content: ElementRef) {
    if(content) { // initially setter gets called with undefined
        this.htmlData = content;
    }
 }
 
  searchForm:FormGroup;
 
  requestType=[
    {name:"approved"},
    {name:"rejected"},
    {name:"reject by training cordinator"},
    {name:"reject by training project manager"},
    {name:"all"}
  ]
  trainingRequests=[]
  constructor(private trainingRequestService:TrainingRequestService,
    private router:Router,
    private activatedRoute:ActivatedRoute
    ) { 

      this.searchForm = new FormGroup({
        fromDate: new FormControl('',Validators.required),
        toDate: new FormControl('',Validators.required),
        requestType: new FormControl(this.requestType,Validators.required)
      })

  }
  ngOnInit(): void {
    this.loadHistory()
  }


  loadHistory()
  {
    if(sessionStorage['role']=='Training Coordinator')
    {
    this.trainingRequestService
        .getAllTrainingHistory()
        .subscribe(response => {
          if (response['status'] == 'success') {
            this.trainingRequests = response['data']
            console.log(this.trainingRequests)
          } else {
            console.log(response['error'])
          }
        })
      }
      else{
        this.trainingRequestService
        .getTrainingHistory(sessionStorage['empid'])
        .subscribe(response => {
          if (response['status'] == 'success') {
            this.trainingRequests = response['data']
            console.log(this.trainingRequests)
          } else {
            console.log(response['error'])
          }
        })
      }
  
  }
  onView(trainingRequest)
  {
    console.log(trainingRequest.training['training_id'])
    this.router.navigate(['/home/view-request'],{queryParams:{id:trainingRequest.training['id']}})
  }
   
  onSearch(){
    const {toDate,fromDate,requestType}=this.searchForm.value;
   const trainings= this.trainingRequests
   console.log(toDate)
   this.trainingRequests=trainings.filter(trainingReq=>{
     if(requestType=="approved")
     {
       return trainingReq.training['project_mgr_status']=='approved' &&
       trainingReq.training['traning_cor_status']=="approved" &&
       trainingReq.training['startDate']>=fromDate &&
       trainingReq.training['startDate']<=toDate

     }

     if(requestType=="rejected")
     {
       return (trainingReq.training['project_mgr_status']=='rejected' ||
       trainingReq.training['traning_cor_status']=="rejected") &&
       trainingReq.training['startDate']>=fromDate &&
       trainingReq.training['startDate']<=toDate

     }
     if(requestType=="reject by training cordinator")
     {
       return trainingReq.training['traning_cor_status']=="rejected" && 
       trainingReq.training['startDate']>=fromDate &&
       trainingReq.training['startDate']<=toDate

     }
     if(requestType=="reject by training project manager")
     {
       return trainingReq.training['project_mgr_status']=='rejected'  &&
       trainingReq.training['startDate']>=fromDate &&
       trainingReq.training['startDate']<=toDate

     }
     if(requestType=="all")
     {
       return trainingReq.training['startDate']>=fromDate && trainingReq.training['startDate']<=toDate

     }
    
   })
  }
  public downloadPDF():void {
    let DATA = this.htmlData.nativeElement;
   
    
    
    //const doc: jsPDF = new jsPDF('p', 'mm', 'a4');
   
  let element=document.getElementById('htmlData');
  html2canvas(element).then((canvas)=>{
    var imgData=canvas.toDataURL('image/png');
    var doc=new jsPDF();
    var imageHeight=canvas.height * 208/canvas.width;
    doc.addImage(imgData,0,0,208,imageHeight)
    doc.save("history.pdf");
  })
    /*doc.setFontSize(1);
    doc.setTextColor(100);
    doc.html(DATA, {callback: () => {
     
       //doc.output('dataurlnewwindow');
       doc.save();
    }});*/

   

  }

}
