import { Component } from '@angular/core';
import { SidePannelComponent } from '@app/core/side-pannel/side-pannel.component';

@Component({
  selector: 'app-test',
  standalone: true,
  imports: [SidePannelComponent],
  templateUrl: './test.component.html',
  styleUrl: './test.component.scss'
})
export class TestComponent {

}
