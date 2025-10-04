import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
// import { Header } from './core/layout/header/header';
// import { Footer } from './core/layout/footer/footer';
// import { Navbar } from './core/layout/navbar/navbar';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrls: ['./app.css'],
  standalone: true
})
export class App {
  protected readonly title = signal('frontend');
}
