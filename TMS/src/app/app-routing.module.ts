import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { TrainingRequestComponent } from './training-request/training-request.component';
import { AddUserComponent } from './add-user/add-user.component';
import{LoginComponent} from './login/login.component';
import{SidenavComponent} from './sidenav/sidenav.component'
import{AuthService} from './auth.service';
import{TrainerComponent} from './trainer/trainer.component';
import{IncomingRequestComponent} from './incoming-request/incoming-request.component';
import{ViewRequestComponent} from './view-request/view-request.component';
import{RequestStatusComponent} from './request-status/request-status.component';
import{AddTrainerComponent} from './add-trainer/add-trainer.component';
import{UserComponent} from './user/user.component'
import{HistoryComponent} from './history/history.component'
import{TechnologyComponent} from './technology/technology.component'
import{AddTechnologyComponent} from './add-technology/add-technology.component'
import{DashboardComponent} from './dashboard/dashboard.component'
const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    component: SidenavComponent,
    canActivate: [AuthService],
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: '/home/dashboard'
      },
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: 'training-request',
        component: TrainingRequestComponent
      },
      {
        path: 'add-user',
        component: AddUserComponent
      },
      {
        path: 'trainer',
        component: TrainerComponent
      },
      {
        path:'incoming-request',
        component:IncomingRequestComponent
      },
      {
        path:'view-request',
        component:ViewRequestComponent
      },
      {
        path:'request-status',
        component:RequestStatusComponent
      },
      {
        path:'add-trainer',
        component:AddTrainerComponent
      },
      {
        path:'user',
        component:UserComponent
      },
      {
        path:'history',
        component:HistoryComponent
      },
      {
        path:'technology',
        component:TechnologyComponent
      },
      {
        path:'add-technology',
        component:AddTechnologyComponent
      }
    ]
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
