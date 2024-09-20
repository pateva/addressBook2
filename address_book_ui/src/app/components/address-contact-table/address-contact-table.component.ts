import { Component, Input } from '@angular/core';
import { NgFor, NgIf, AsyncPipe } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';
import { InputGroupModule } from 'primeng/inputgroup';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-address-contact-table',
  standalone: true,
  imports: [TableModule,
    InputTextModule,
    ButtonModule,
    NgIf,
    NgFor,
    AsyncPipe,
    FormsModule,
    InputGroupModule],
  templateUrl: './address-contact-table.component.html',
  styleUrl: './address-contact-table.component.scss'
})
export class AddressContactTableComponent {
  @Input() address: { type: string, value: string, isDisabled$: BehaviorSubject<boolean> }[] = [];

  // Method to enable the specific address input
  updateAddress(index: number) {
    this.address[index].isDisabled$.next(false);
  }

  // Method to save and disable the specific address input
  saveAddress(index: number) {
    this.address[index].isDisabled$.next(true);
  }

}
