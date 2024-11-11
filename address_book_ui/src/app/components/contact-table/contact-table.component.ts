import { Component, Input, Output, EventEmitter } from '@angular/core';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';
import { BehaviorSubject } from 'rxjs';
import { ContactBlockComponent } from '../contact-block/contact-block.component';

@Component({
  selector: 'app-contact-table',
  standalone: true,
  imports: [TableModule,
    InputTextModule,
    ButtonModule,
    NgIf,
    NgFor,
    FormsModule,
    AsyncPipe,
    ContactBlockComponent],
  templateUrl: './contact-table.component.html',
  styleUrl: './contact-table.component.scss'
})
export class ContactTableComponent {
  @Input() type!: string;
  @Input() contactDetails: {
    type: string,
    value: string,
    isDisabled$: BehaviorSubject<boolean>,
    placeholders: string[]
  }[] = [];

  @Output() updateContactDetail = new EventEmitter<{ index: number, value: string, type: string }>();

  // Forward the event from ContactBlockComponent to the parent
  onUpdateContactDetail(event: { index: number; value: string }) {
    console.log("Emitting updateContactDetail:", { index: event.index, value: event.value, type: this.type });
    this.updateContactDetail.emit({ ...event, type: this.type });
  }
}
