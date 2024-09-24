import { Component } from '@angular/core';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { BadgeModule } from 'primeng/badge';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf, AsyncPipe } from '@angular/common';
import { TableModule } from 'primeng/table';
import { ChangeDetectorRef } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ContactTableComponent } from '../contact-table/contact-table.component';
import { ContactBlockComponent } from '../contact-block/contact-block.component';

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
    AsyncPipe,
    TableModule,
    ContactTableComponent,
    ContactBlockComponent],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.scss'
})
export class ContactComponent {
  constructor(private cdr: ChangeDetectorRef) { }

  header: string = "First Name";
  subheader: string = "+12345678";
  streetAddress: string = '';  // To store street/precise address
  location: string = '';  // To store location

  contactDetails = [
    { type: 'Email', 
      value: 'john.doe@example.com', 
      isDisabled$: new BehaviorSubject<boolean>(true),
      placeholders: ["gmail.com"]
     },
  ];
  address = [
    { type: 'Address', 
      value: '+359876616112', 
      isDisabled$: new BehaviorSubject<boolean>(true),
      placeholders: ["gmail.com"]  },
  ];
  phoneDetails = [
    { type: 'Phone Number', 
      value: '+359876616112', 
      isDisabled$: new BehaviorSubject<boolean>(true),
      placeholders: ["gmail.com"] },
  ];
  faxDetails = [
    { type: 'Phone Number', 
      value: '+359876616112', 
      isDisabled$: new BehaviorSubject<boolean>(true),
      placeholders: ["gmail.com"] },
  ];

  isDisabled = true;


  enableTextFields() {
    this.isDisabled = false;
  }

  saveAddress(index: number) {
    this.contactDetails[index].isDisabled$.next(true);
    // this.cdr.detectChanges(); // Force change detection
  }

  updateAddress(index: number) {
    this.contactDetails[index].isDisabled$.next(true);
    // this.cdr.detectChanges(); // Force change detection
  }
}
