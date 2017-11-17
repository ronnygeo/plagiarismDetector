import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {AssignmentListComponent} from "./components/assignmentlist/assignmentlist.component";
import {HomeComponent} from "./components/home/home.component";
import {SubmissionListComponent} from "./components/submissionlist/submissionlist.component";
import {AssignmentDetailComponent} from "./components/assignmentdetail/assignmentdetail.component";
import {UploadSubmissionComponent} from "./components/uploadsubmission/uploadsubmission.component";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {ReportComponent} from "./components/report/report.component";

const routes: Routes = [
  { path: 'assignments', component: AssignmentListComponent },
  { path: 'assignment/:assignmentId', component: AssignmentDetailComponent },
  { path: 'submissions', component: SubmissionListComponent },
  { path: 'report', component: ReportComponent },
  { path: 'submission/:assignmentId', component: SubmissionListComponent },
  { path: 'submissions/new', component: UploadSubmissionComponent },
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
