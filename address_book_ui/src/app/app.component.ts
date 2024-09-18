import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoadingComponent } from './components/loading/loading.component';
import { SidePannelComponent } from './core/side-pannel/side-pannel.component';
import { AuthService } from '@auth0/auth0-angular';
import { NgIf, AsyncPipe } from '@angular/common';
import { AuthComponent } from './pages/auth/auth.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,
    LoadingComponent,
    SidePannelComponent,
    NgIf,
    AsyncPipe,
    AuthComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  constructor(public auth: AuthService) { }
  title = 'address_book_ui';
}
