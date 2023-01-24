import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public formLogin: FormGroup;
  public isLoading: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private route: Router
  ) {
    this.formLogin = this.createFormLogin();
   }

  ngOnInit(): void {
  }

  public submitForm(): void {
    const {email, senha} = this.formLogin.value;

    this.authService.login(email, senha).subscribe(
      {next: (response) => {
        this.isLoading = true;
        this.route.navigate(['']);
        this.formLogin.reset();
      },
      error: err => {
        this.isLoading = false;
        console.log(err);
      }});
  }

  public isFormControlInvalid(controlName: string): boolean {
    return !!(this.formLogin.get(controlName)?.invalid && this.formLogin.get(controlName)?.touched);
  }

  public createFormLogin(): FormGroup {
    return this.formBuilder.group({
      email: ["", [Validators.required]],
      senha: ["", [Validators.required]]
    });
  }

}
