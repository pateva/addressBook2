import { Component, Input, Output, EventEmitter } from '@angular/core';
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
  @Input() type!: string;
  @Input() contactDetails: {
    type: string,
    value: string,
    isDisabled$: BehaviorSubject<boolean>,
    placeholders: string[]
  }[] = [];

  @Output() updateContactDetail = new EventEmitter<{ index: number, value: string, type: string }>();

  updateData(index: number) {
    this.contactDetails[index].isDisabled$.next(false);
  }

  saveData(index: number) {
    this.contactDetails[index].isDisabled$.next(true);
    this.updateContactDetail.emit({ index, value: this.contactDetails[index].value, type: this.type});
  }
}
