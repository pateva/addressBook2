import { Component } from '@angular/core';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { BadgeModule } from 'primeng/badge';


@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [CardModule, ButtonModule, BadgeModule],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.scss'
})
export class ContactComponent {
  header: string = "First Name";
  subheader: string = "+12345678"
}
