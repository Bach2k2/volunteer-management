import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService, User } from '../../../services/auth/auth.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, RouterModule],
  standalone: true,
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.css']
})
export class Navbar {
  user:User|null = null;
  constructor(private authService:AuthService) {}

  ngOnInit(): void {
    this.user = this.authService.getUser();
  }

  onLogout() {
    this.authService.logout();
    this.user = null;
  }
}
