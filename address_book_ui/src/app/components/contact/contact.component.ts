import { Component, OnInit } from '@angular/core';
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
import { TagModule } from 'primeng/tag';
import { UsersService } from '@app/services/data/users.service';
import { UserPartialResponse } from '@app/interfaces/responses/UserPartialResponse';
import { ContactResponse } from '@app/interfaces/responses/ContactResponse';
import { CreateRecordBody } from '@app/interfaces/payloads/CreateRecordBody';
import { ContactType } from '@app/shared/util/contactType';
import { ADD_ADDRESS, ADD_EMAIL, ADD_FAX, ADD_PHONE } from '@app/shared/constants/general';
import { RecordService } from '@app/services/data/record.service';

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
    ContactBlockComponent,
    TagModule],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.scss'
})
export class ContactComponent implements OnInit {
  constructor(private cdr: ChangeDetectorRef,
    private userService: UsersService,
    private recordService: RecordService
  ) { }

  ngOnInit(): void {
    this.userService.getUserDetails().subscribe({
      next: (user: UserPartialResponse) => {
        this.user = user;

        if (user.id) {
          this.recordService.setUserId(user.id);
        }

        this.populateUserDetails(user);
      },
      error: (err) => {
        console.error('Error: ', err);
      }
    })
  }

  user: UserPartialResponse | null = null;
  name: string = "Unknown User";

  contactDetails = [
    {
      type: 'Email',
      value: ADD_EMAIL,
      isDisabled$: new BehaviorSubject<boolean>(true),
      placeholders: ["gmail.com"]
    },
  ];
  address = [
    {
      type: 'Address',
      value: ADD_ADDRESS,
      isDisabled$: new BehaviorSubject<boolean>(true),
      placeholders: ["Street Address"]
    },
  ];
  phoneDetails = [
    {
      type: 'Phone Number',
      value: ADD_PHONE,
      isDisabled$: new BehaviorSubject<boolean>(true),
      placeholders: ["Phone Number"]
    },
  ];
  faxDetails = [
    {
      type: 'Fax Details',
      value: ADD_FAX,
      isDisabled$: new BehaviorSubject<boolean>(true),
      placeholders: ["Fax"]
    },
  ];

  isDisabled = true;


  enableTextFields() {
    this.isDisabled = false;
  }

  saveAddress(index: number) {
    this.contactDetails[index].isDisabled$.next(true);
  }

  updateAddress(index: number) {
    this.contactDetails[index].isDisabled$.next(true);
  }

  populateUserDetails(user: UserPartialResponse): void {
    let personalRecord: ContactResponse | undefined = user.personalRecords?.find(record => record.personal === true);
    const phoneDetail = personalRecord?.contactDetails?.find(detail => detail.type === ContactType.PHONE_NUMBER);
    const faxDetails = personalRecord?.contactDetails?.find(detail => detail.type === ContactType.FAX);
    this.name = personalRecord
      ? `${personalRecord.firstName ?? ''} ${personalRecord.lastName ?? ''}`.trim() || 'Unknown User'
      : 'Unknown User';
    this.contactDetails[0].value = user?.email ? user.email : ADD_EMAIL;

    if (personalRecord?.address) {
      const street = personalRecord.address.street ?? '';
      const city = personalRecord.address.city ?? '';
      const country = personalRecord.address.country ?? '';
      this.address[0].value = `${street}, ${city}, ${country}`.trim() || ADD_ADDRESS;
    } else {
      this.address[0].value = ADD_ADDRESS;
    }
    this.phoneDetails[0].value = phoneDetail ? phoneDetail.value.toString() : ADD_PHONE;
    this.faxDetails[0].value = faxDetails ? faxDetails.value.toString() : ADD_FAX;

    //TODO changedetection ?
  }

  //TODO create a special method for the address
  createUpdateRecord(updateData: { index: number, value: string }) {
    console.log("Emitting updateContactDetail:", updateData);

    if (this.user?.personalRecords.length === 0) {
      const body: CreateRecordBody = {
        userId: this.user.id,
        isPersonal: true,
        firstName: '',
        lastName: '',
        imageUrl: '',
        address: null,
        contactDetails: [
          {
            recordId: null,
            //TODO this is not quite correct with the type
            type: this.contactDetails[updateData.index].type.toUpperCase(),
            value: updateData.value
          }
        ]
      }

      this.recordService.createRecord(body).subscribe({
        next: (response) => {
          this.userService.getUserDetails().subscribe({
            next: (user) => {
              this.user = user;
              console.log('User data reloaded');
            },
            error: (err) => console.error('Error reloading user data:', err)
          });
        },
        error: (err) => console.error('Error creating record:', err)
      });
    } else {
      //TODO 
      console.log("else case");
    }

    //update
  }
}
