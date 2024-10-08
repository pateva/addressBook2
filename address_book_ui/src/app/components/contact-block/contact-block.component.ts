import { Component, Input } from '@angular/core';
import { NgFor, NgIf, AsyncPipe } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';
import { InputGroupModule } from 'primeng/inputgroup';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-contact-block',
  standalone: true,
  imports: [TableModule,
    InputTextModule,
    ButtonModule,
    NgIf,
    NgFor,
    AsyncPipe,
    FormsModule,
    InputGroupModule],
  templateUrl: './contact-block.component.html',
  styleUrl: './contact-block.component.scss'
})
export class ContactBlockComponent {
  @Input() contactDetails: {
    type: string,
    value: string,
    isDisabled$: BehaviorSubject<boolean>,
    placeholders: string[]
  }[] = [];

  // Method to enable the specific address input
  updateAddress(index: number) {
    this.contactDetails[index].isDisabled$.next(false);
  }

  // Method to save and disable the specific address input
  saveAddress(index: number) {
    this.contactDetails[index].isDisabled$.next(true);
  }

}
