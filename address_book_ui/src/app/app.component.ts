import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SidePannelComponent } from "./core/side-pannel/side-pannel.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SidePannelComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'address_book_ui';
}
