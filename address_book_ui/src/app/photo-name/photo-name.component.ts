import { Component, Output, EventEmitter} from '@angular/core';
import { PanelModule } from 'primeng/panel';
import { AvatarModule } from 'primeng/avatar';
import { ButtonModule } from 'primeng/button';
import { MenuModule } from 'primeng/menu';

@Component({
  selector: 'app-photo-name',
  standalone: true,
  imports: [PanelModule,
    AvatarModule,
    ButtonModule,
    MenuModule],
  templateUrl: './photo-name.component.html',
  styleUrl: './photo-name.component.scss'
})
export class PhotoNameComponent {
  @Output() close = new EventEmitter<void>();
}
