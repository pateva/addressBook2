import { Component } from '@angular/core';
import { SidePannelComponent } from '@app/core/side-pannel/side-pannel.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [SidePannelComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

}
