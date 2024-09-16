import { Component, ChangeDetectionStrategy, OnInit } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AuthComponent implements OnInit {
  constructor(public readonly authService: AuthService) {
  }

  ngOnInit() {
    this.authService.loginWithRedirect();
  }

  login() {
    this.authService.loginWithRedirect();
  }

}
