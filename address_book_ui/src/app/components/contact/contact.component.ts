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
import { ContactDetailResponse } from '@app/interfaces/responses/ContactDetailResponse';
import { AddressCreateBody } from '@app/interfaces/payloads/AddressCreateBody';
import { ContactDetailsCreateBody } from '@app/interfaces/payloads/ContactDetailsCreateBody';
import { CreateRecordBody } from '@app/interfaces/payloads/CreateRecordBody';
import { ContactType } from '@app/shared/util/contactType';
import { ADD_ADDRESS, ADD_EMAIL, ADD_FAX, ADD_PHONE } from '@app/shared/constants/general';
import { RecordService } from '@app/services/data/record.service';
import { UpdateRecordBody } from '@app/interfaces/payloads/UpdateRecordBody';

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
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {
  public ContactType = ContactType;

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
  name: string = "";
  imageUrl: string = "";

  emailDetails = [
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
    this.emailDetails[index].isDisabled$.next(true);
  }

  updateAddress(index: number) {
    this.emailDetails[index].isDisabled$.next(true);
  }

  showPhotoNameComp() {
    console.log("test");
  }

  populateUserDetails(user: UserPartialResponse): void {
    let personalRecord: ContactResponse | undefined = user.personalRecords?.find(record => record.personal === true);
    const phoneDetail = personalRecord?.contactDetails?.find(detail => detail.type === ContactType.PHONE_NUMBER);
    const faxDetails = personalRecord?.contactDetails?.find(detail => detail.type === ContactType.FAX);
    this.imageUrl = personalRecord?.imageUrl || "";
    this.name = personalRecord
      ? `${personalRecord.firstName ?? ''} ${personalRecord.lastName ?? ''}`.trim() || 'Username'
      : 'Username';
    this.emailDetails[0].value = user?.email ? user.email : ADD_EMAIL;

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
  }

  createUpdateRecord(updateData: { index: number, value: string, type: string }) {
    if (this.user?.personalRecords?.length === 0 || this.user?.personalRecords === null) {
      console.log("create record");
      this.createRecord(updateData);

      return;
    }

    let personalRecord = this.user?.personalRecords.find(record => record.personal);

    if (personalRecord !== undefined) {
      console.log("update  record");
      this.updateRecord(personalRecord, updateData);
    } else {
      console.error('Personal contact not found');
    }
  }

  updateRecord(personalRecord: ContactResponse,
    updateData: { index: number, value: string, type: string }) {
      const body: UpdateRecordBody = 
      (updateData.type === ContactType.ADDRESS 
        ? this.createUpdateRecordWithAddress(personalRecord, updateData.value) 
        : this.createUpdateRecordWithContact(personalRecord, updateData));
  

    this.recordService.updateRecord(body).subscribe({
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
  }

  private createUpdateRecordWithAddress(personalRecord: ContactResponse,address: string) {
    const body: UpdateRecordBody = {
      id: personalRecord.id,
      userId: this.user?.id,
      isPersonal: personalRecord.personal,
      firstName: personalRecord.firstName,
      lastName: personalRecord.lastName,
      imageUrl: personalRecord.imageUrl,
      address: this.formatAddress(address),
      contactDetails: this.updateContactDetails(personalRecord.id, personalRecord.contactDetails, null)
    }

    return body;
  }


  private createUpdateRecordWithContact(personalRecord: ContactResponse,
    updateData: { index: number, value: string, type: string }
  ) {
    const body: UpdateRecordBody = {
      id: personalRecord.id,
      userId: this.user?.id,
      isPersonal: personalRecord.personal,
      firstName: personalRecord.firstName,
      lastName: personalRecord.lastName,
      imageUrl: personalRecord.imageUrl,
      address: personalRecord.address,
      contactDetails: this.updateContactDetails(personalRecord.id, personalRecord.contactDetails, updateData)
    }

    return body;
  }

  private updateContactDetails(recordId: BigInteger,
    existingContacts: ContactDetailResponse[],
    updateData: { index: number, value: string, type: string } | null)  {
    let newContacts: ContactDetailsCreateBody[] = [];
    let updatesType: boolean = false;

    if(updateData === null) {
      existingContacts.forEach(contact => {
        newContacts.push({
          recordId: recordId,
          type: contact.type,
          value: contact.value
        })
      })

      return newContacts;
    }

    existingContacts.forEach(contact => {
      if (contact.type === updateData.type) {
        newContacts.push({
          recordId: recordId,
          type: updateData.type,
          value: updateData.value
        })

        updatesType = true;
        return;
      }

      newContacts.push({
        recordId: recordId,
        type: contact.type,
        value: contact.value
      })
    })

    if (!updatesType) {
      newContacts.push({
        recordId: recordId,
        type: updateData.type,
        value: updateData.value
      })
    }

    return newContacts;
  }


  private createRecord(updateData: { index: number, value: string, type: string }) {
    let body: CreateRecordBody;

    if (updateData.type == ContactType.ADDRESS) {
      body = this.createRecordWithAddress(updateData.value);
    } else {
      body = this.createRecordWithContact(updateData);
    }

    this.recordService.createRecord(body).subscribe({
      next: (response) => {
        this.userService.getUserDetails().subscribe({
          next: (user) => {
            this.user = user;
          },
          error: (err) => console.error('Error reloading user data:', err)
        });
      },
      error: (err) => console.error('Error creating record:', err)
    });
  }

  private createRecordWithContact(updateData: { index: number, value: string, type: string }) {
    const createRecordBody: CreateRecordBody = {
      userId: this.user?.id,
      isPersonal: true,
      firstName: '',
      lastName: '',
      imageUrl: '',
      address: null,
      contactDetails: [
        {
          recordId: null,
          type: updateData.type.toUpperCase(),
          value: updateData.value
        }
      ]
    }

    return createRecordBody;
  }

  private createRecordWithAddress(address: string) {
    const createRecordBody: CreateRecordBody = {
      userId: this.user?.id,
      isPersonal: true,
      firstName: '',
      lastName: '',
      imageUrl: '',
      address: this.formatAddress(address),
      contactDetails: []
    }

    return createRecordBody;
  }

  private formatAddress(address: string) {
    const delimeter: string = ",";
    const maxSz: number = 3;
    let spltAddress = address.split(delimeter, maxSz);
    let addressCreateBody: AddressCreateBody = {
      street: spltAddress[0] ? spltAddress[0].trim() : "",
      city: spltAddress[1] ? spltAddress[1].trim() : "",
      country: spltAddress[2] ? spltAddress[2].trim() : ""
    }

    return addressCreateBody;
  }
}
