import { Component } from '@angular/core';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { BadgeModule } from 'primeng/badge';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf, AsyncPipe } from '@angular/common';
import { TableModule } from 'primeng/table';
import { InputGroupModule } from 'primeng/inputgroup';
import { ChangeDetectorRef } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ContactTableComponent } from '../contact-table/contact-table.component';


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
    InputGroupModule,
    ContactTableComponent],
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
    { type: 'Phone Number', value: '+359876616112', isDisabled$: new BehaviorSubject<boolean>(true) },
    { type: 'Email', value: 'john.doe@example.com', isDisabled$: new BehaviorSubject<boolean>(true) },
  ];
  address = [
    { type: 'Phone Number', value: '+359876616112' },
    { type: 'Email', value: 'john.doe@example.com' },
  ];
  phoneDetails = [
    { type: 'Phone Number', value: '+359876616112', isDisabled$: new BehaviorSubject<boolean>(true) },
    { type: 'Email', value: 'john.doe@example.com', isDisabled$: new BehaviorSubject<boolean>(true) },
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
