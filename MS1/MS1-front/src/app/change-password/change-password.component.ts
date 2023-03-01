import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {
  changePasswordForm: FormGroup;
  submitted = false;
  error = '';
  success = '';

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: Component
  ) {
    this.changePasswordForm = this.formBuilder.group({
      oldPassword: ['', Validators.required],
      newPassword: ['', Validators.required]
    });
  }

  onSubmit() {
    this.submitted = true;
    this.error = '';
    this.success = '';

    if (this.changePasswordForm.invalid) {
      return;
    }

    const oldPassword = this.changePasswordForm.controls.oldPassword.value;
    const newPassword = this.changePasswordForm.controls.newPassword.value;

    this.userService.changePassword(oldPassword, newPassword)
      .subscribe(
        data => {
          this.success = 'Password changed successfully';
        },
        error => {
          this.error = error;
        });
  }

  changePassword(){

  }
}
