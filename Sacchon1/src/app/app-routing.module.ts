import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ApprovedoctorsComponent } from './chief/approvedoctors/approvedoctors.component';
import { ChiefComponent } from './chief/chief.component';
import { ViewdoctorsComponent } from './chief/viewdoctors/viewdoctors.component';
import { ViewpatientsComponent } from './chief/viewpatients/viewpatients.component';
import { AddnewpatientComponent } from './doctorprofile/addnewpatient/addnewpatient.component';
import { DoctorprofileComponent } from './doctorprofile/doctorprofile.component';
import { DrcalendarComponent } from './doctorprofile/drcalendar/drcalendar.component';
import { EditdoctorinfoComponent } from './doctorprofile/editdoctorinfo/editdoctorinfo.component';
import { DrsignupComponent } from './drsignup/drsignup.component';
import { HomeComponent } from './home/home.component';
import { MoreinfoComponent } from './moreinfo/moreinfo.component';
import { AddmeasurementComponent } from './patientprofile/addmeasurement/addmeasurement.component';
import { CalendarComponent } from './patientprofile/calendar/calendar.component';
import { DeactivateaccountComponent } from './patientprofile/deactivateaccount/deactivateaccount.component';
import { EditpatientinfoComponent } from './patientprofile/editpatientinfo/editpatientinfo.component';
import { MymeasurementsComponent } from './patientprofile/mymeasurements/mymeasurements.component';
import { PatientprofileComponent } from './patientprofile/patientprofile.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { MypatientsComponent } from './doctorprofile/mypatients/mypatients.component';
import { DeactivatedraccountComponent } from './doctorprofile/deactivatedraccount/deactivatedraccount.component';
import { PatientComponent } from './doctorprofile/mypatients/patient/patient.component';
import { AddconsultationComponent } from './doctorprofile/mypatients/patient/addconsultation/addconsultation.component';
import { MyaveragesComponent } from './patientprofile/mymeasurements/myaverages/myaverages.component';
import { AveragGlucoseLevelComponent } from './patientprofile/mymeasurements/myaverages/averag-glucose-level/averag-glucose-level.component';
import { AverageCarbIntakeComponent } from './patientprofile/mymeasurements/myaverages/average-carb-intake/average-carb-intake.component';

const routes: Routes = [
  { path: "", pathMatch: "full", redirectTo: "/home"},
  { path: "home", component: HomeComponent},
  
  { path: "signin", component: SigninComponent},
  { path: "signup", component: SignupComponent},
  { path: "moreinfo", component: MoreinfoComponent},
  { path: "drsignup", component: DrsignupComponent},

  { path: "patientprofile", component: PatientprofileComponent},
  { path: "patientprofile/calendar", component: CalendarComponent},
  { path: "patientprofile/addmeasurement", component: AddmeasurementComponent},
  { path: "patientprofile/editpatientinfo", component: EditpatientinfoComponent},
  { path: "patientprofile/mymeasurements", component: MymeasurementsComponent},
  { path: "patientprofile/mymeasurements/myaverages", component: MyaveragesComponent},
  { path: "patientprofile/mymeasurements/myaverages/averag-glucose-level", component: AveragGlucoseLevelComponent},
  { path: "patientprofile/mymeasurements/myaverages/average-carb-intake", component: AverageCarbIntakeComponent},
  { path: "patientprofile/deactivateaccount", component: DeactivateaccountComponent},

  { path: "doctorprofile", component: DoctorprofileComponent},
  { path: "doctorprofile/addnewpatient", component: AddnewpatientComponent},
  { path: "doctorprofile/drcalendar", component: DrcalendarComponent},
  { path: "doctorprofile/editdoctorinfo", component: EditdoctorinfoComponent},
  { path: "doctorprofile/mypatients", component: MypatientsComponent},
  { path: "doctorprofile/mypatients/patient", component: PatientComponent},
  { path: "doctorprofile/mypatients/patient/addconsultation", component: AddconsultationComponent},
  { path: "doctorprofile/deactivateaccount", component: DeactivatedraccountComponent},

  { path: "chief", component: ChiefComponent},
  { path: "chief/approvedoctors", component: ApprovedoctorsComponent },
  { path: "chief/viewdoctors", component: ViewdoctorsComponent },
  { path: "chief/viewpatients", component: ViewpatientsComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 


}
