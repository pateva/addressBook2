import { Component, Input} from '@angular/core';
import { NgFor, NgIf} from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-contact-table',
  standalone: true,
  imports: [TableModule, 
    InputTextModule, 
    ButtonModule, 
    NgIf, 
    NgFor,
  FormsModule],
  templateUrl: './contact-table.component.html',
  styleUrl: './contact-table.component.scss'
})
export class ContactTableComponent {
  @Input() contactDetails: { value: string }[] = [];  // Input for contact details
  @Input() headerTitle: string = '';  // Input for the table header
  @Input() isDisabled: boolean = true;  // Input for controlling disabled state
}
