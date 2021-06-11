import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AppState } from 'src/shared/state/app/app.state';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  public loginFormGroup: FormGroup;

  constructor(private formBuilder: FormBuilder, public appState: AppState) {
    this.loginFormGroup = this.formBuilder.group({
      email: [null, [Validators.required, Validators.minLength(3)]],
      password: [null, [Validators.required, Validators.minLength(4)]],
    });
    this.loginFormGroup.patchValue({
      email: 'admin@seedling.lk',
      password: 'gadgetM@N12x89',
    });
  }

  ngOnInit(): void {}

  public onSubmit(): void {
    this.appState.login(this.loginFormGroup.value).subscribe();
  }
}
