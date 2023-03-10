import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChangePasswordAfterLoginComponent } from './change-password-after-login/change-password-after-login.component';
import { LoginComponent } from './login/login.component';
import { LoginsuccessComponent } from './loginsuccess/loginsuccess.component';
import { ProfileComponent } from './profile/profile.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
  {path:'', component:LoginComponent},
  {path:'loginsuccess', component:LoginsuccessComponent},
  {path:'registration', component:RegistrationComponent},
  {path:'login', component:LoginComponent},
  {path:'profile', component:ProfileComponent},
  {path:'change-password-after-login', component:ChangePasswordAfterLoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
