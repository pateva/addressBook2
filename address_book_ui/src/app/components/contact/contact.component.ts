import { Component } from '@angular/core';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { BadgeModule } from 'primeng/badge';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf} from '@angular/common';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [CardModule,
    ButtonModule,
    BadgeModule,
    InputTextModule,
    FormsModule,
    NgFor,
    NgIf,
    TableModule],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.scss'
})
export class ContactComponent {
  header: string = "First Name";
  subheader: string = "+12345678";
  streetAddress: string = '';  // To store street/precise address
  location: string = '';  // To store location
 
  contactDetails = [
    { type: 'Phone Number', value: '+359876616112' },
    { type: 'Email', value: 'john.doe@example.com' },
  ];
  address = [
    
  ];
  isDisabledAddress: boolean = true;
  isDisabled = true;


  enableTextFields() {
    this.isDisabledAddress = false;
  }

  saveAddress() {
    this.isDisabledAddress = true;
  }
}
