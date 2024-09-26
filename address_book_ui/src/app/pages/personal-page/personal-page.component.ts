import { Component } from '@angular/core';
import { ContactComponent } from "../../components/contact/contact.component";

@Component({
  selector: 'app-personal-page',
  standalone: true,
  imports: [ContactComponent],
  templateUrl: './personal-page.component.html',
  styleUrl: './personal-page.component.scss'
})
export class PersonalPageComponent {

}
