import { Component } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css'],
  standalone: true
})
export class Login {
  loginError: string | null = null;
  loading = false;
  defaultLogin =  {
    username: 'bach2k2',
    password: 'bach2002'
  }
  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(loginForm: NgForm) {
    this.loginError = null;
    if (loginForm.valid) {
      this.loading = true;
      const { username, password } = loginForm.value
      this.authService.login(username, password)
        .subscribe({
          next: (value) => {
            this.loading = false;
            console.log('Login successful', value);
            this.router.navigate(['/dashboard']);
          },
          error: (error) => {
            this.loading = false;
            console.error('Login failed', error);
            this.loginError = 'Invalid username or password.';
          },
          complete: () => {
            this.loading = false;
            console.log('Login request completed');
          },
        });
    }
  }

  onForgotPassword(){
    alert('Forgot password functionality is not implemented yet.');
  }

  onPasswordKeydown(event: KeyboardEvent, loginBtn: HTMLButtonElement) {
    if (event.key === 'Enter') {
      event.preventDefault();
      loginBtn.focus();
    }
  }
}
