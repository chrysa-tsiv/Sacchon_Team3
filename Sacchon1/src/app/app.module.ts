import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';


import { HomeComponent } from './home/home.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { DrsignupComponent } from './drsignup/drsignup.component';
import { ChiefComponent } from './chief/chief.component';
import { DoctorprofileComponent } from './doctorprofile/doctorprofile.component';
import { PatientprofileComponent } from './patientprofile/patientprofile.component';
import { CalendarComponent } from './patientprofile/calendar/calendar.component';
import { DrcalendarComponent } from './doctorprofile/drcalendar/drcalendar.component';
import { AddmeasurementComponent } from './patientprofile/addmeasurement/addmeasurement.component';
import { AddnewpatientComponent } from './doctorprofile/addnewpatient/addnewpatient.component';
import { DatePipe } from '@angular/common';

import { EditpatientinfoComponent } from './patientprofile/editpatientinfo/editpatientinfo.component';
import { ViewpatientsComponent } from './chief/viewpatients/viewpatients.component';
import { ApprovedoctorsComponent } from './chief/approvedoctors/approvedoctors.component';
import { ViewdoctorsComponent } from './chief/viewdoctors/viewdoctors.component';
import { MoreinfoComponent } from './moreinfo/moreinfo.component';
import { EditdoctorinfoComponent } from './doctorprofile/editdoctorinfo/editdoctorinfo.component';
import { MymeasurementsComponent } from './patientprofile/mymeasurements/mymeasurements.component';
import { DeactivateaccountComponent } from './patientprofile/deactivateaccount/deactivateaccount.component';

import { PersonalinfotableComponent } from './patientprofile/personalinfotable/personalinfotable.component';
import { MypatientsComponent } from './doctorprofile/mypatients/mypatients.component';
import { DeactivatedraccountComponent } from './doctorprofile/deactivatedraccount/deactivatedraccount.component';

import { PatientComponent } from './doctorprofile/mypatients/patient/patient.component';
import { AddconsultationComponent } from './doctorprofile/mypatients/patient/addconsultation/addconsultation.component';
import { AveragGlucoseLevelComponent } from './patientprofile/mymeasurements/myaverages/averag-glucose-level/averag-glucose-level.component';
import { AverageCarbIntakeComponent } from './patientprofile/mymeasurements/myaverages/average-carb-intake/average-carb-intake.component';
import { MyaveragesComponent } from './patientprofile/mymeasurements/myaverages/myaverages.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SigninComponent,
    SignupComponent,
    DrsignupComponent,
    ChiefComponent,
    PatientprofileComponent,
    DoctorprofileComponent,
    CalendarComponent,
    DrcalendarComponent,
    AddmeasurementComponent,
    AddnewpatientComponent,
    EditpatientinfoComponent,
    ViewpatientsComponent,
    ApprovedoctorsComponent,
    ViewdoctorsComponent,
    MoreinfoComponent,
    EditdoctorinfoComponent,
    MymeasurementsComponent,
    DeactivateaccountComponent,
    PersonalinfotableComponent,
    MypatientsComponent,
    DeactivatedraccountComponent,
    PatientComponent,
    AddconsultationComponent,
    MyaveragesComponent,
    AveragGlucoseLevelComponent,
    AverageCarbIntakeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
    
  ],
  providers: [DatePipe , [{provide: LOCALE_ID, useValue: 'en-US' }]],
  bootstrap: [AppComponent]
})
export class AppModule { }