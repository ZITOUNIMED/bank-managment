import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  logout(): void {
    sessionStorage.setItem('isConnected', 'false');
    sessionStorage.setItem('token', '');
    this.router.navigate(['/sign-in']);
  }

  signIn(): void {
    sessionStorage.setItem('isConnected', 'false');
    sessionStorage.setItem('token', '');
    this.router.navigate(['/sign-in']);
  }

  isConnected(): boolean {
    return sessionStorage.getItem('isConnected') === 'true';
  }
}
