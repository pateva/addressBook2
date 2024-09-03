import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RecordComponent } from './record/record.component';
import { TestComponent } from './test/test.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RecordComponent, TestComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'address_book_ui';
}
