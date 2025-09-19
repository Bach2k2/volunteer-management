import { Component } from '@angular/core';
import { Header } from '@/app/core/layout/header/header';
import { Footer } from '@/app/core/layout/footer/footer';
import { Navbar } from '@/app/core/layout/navbar/navbar';
@Component({
  selector: 'app-home',
  imports: [Header, Footer, Navbar],
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class Home {

}
