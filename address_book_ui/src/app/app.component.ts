import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PlatformService } from './shared/platform.service'; // Import the new platform service
import { AuthService } from '@auth0/auth0-angular';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'address_book_ui';
}
