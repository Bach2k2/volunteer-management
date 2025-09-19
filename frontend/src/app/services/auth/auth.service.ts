import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseService } from '../base/base.service';
import { Observable } from 'rxjs/internal/Observable';
// import { BaseService } from '@/services/base/base.service';
export interface User {
  id: number;
  username: string;
  email: string;
  role: {
    roleName: string;
  };
  // Add other user properties as needed
}

@Injectable({
  providedIn: 'root',
})
export class AuthService extends BaseService {
  private user: User | null = null;

  constructor(http: HttpClient) {
    super(http);
  }

  login(username: string, password: string): Observable<any> {
    const response = this.post<{ token: string; user: User }>('/api/v1/auth/login', {
      username,
      password,
    });
    response.subscribe((res) => {
      localStorage.setItem('token', res.token);
      this.user = res.user;
    });
    return response;
  }

  register(username: string, email: string, password: string): Observable<any> {
    return this.post('/api/v1/auth/register', { username, email, password, role: { roleName: 'USER' } });
  }

  logout(): void {
    this.user = null;
  }

  getUser(): User | null {
    return this.user;
  }
}
