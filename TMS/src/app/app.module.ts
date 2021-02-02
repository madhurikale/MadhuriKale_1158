import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import{ReactiveFormsModule} from '@angular/forms'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { TrainingRequestComponent } from './training-request/training-request.component';
import { AddUserComponent } from './add-user/add-user.component';
import{TrainingRequestService} from './training-request.service'
import{TechnologyService} from './technology.service'
import{TrainerService } from './trainer.service'
import{UserinfoService} from './userinfo.service'
import{AuthService} from './auth.service'
import{FormsModule }from '@angular/forms';

import { TechnologyComponent } from './technology/technology.component';
import { TrainerComponent } from './trainer/trainer.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { IncomingRequestComponent } from './incoming-request/incoming-request.component';
import { ViewRequestComponent } from './view-request/view-request.component';
import { RequestStatusComponent } from './request-status/request-status.component';
import { AddTrainerComponent } from './add-trainer/add-trainer.component';
import { InterceptorService } from './interceptor.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { UserComponent } from './user/user.component';
import { HistoryComponent } from './history/history.component';
import { AddTechnologyComponent } from './add-technology/add-technology.component';
import { DashboardComponent } from './dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent,
    TrainingRequestComponent,
    AddUserComponent,
    TechnologyComponent,
    TrainerComponent,
    SidenavComponent,
    IncomingRequestComponent,
    ViewRequestComponent,
    RequestStatusComponent,
    AddTrainerComponent,
    UserComponent,
    HistoryComponent,
    AddTechnologyComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
   
    HttpClientModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule,
    ReactiveFormsModule,
    
  ],
  providers: [
    TrainingRequestService,
    TechnologyService,
    TrainerService,
    UserinfoService,
   
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
      }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
