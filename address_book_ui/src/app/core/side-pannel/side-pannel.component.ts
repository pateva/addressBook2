import { Component } from '@angular/core';
import { SidebarModule } from 'primeng/sidebar';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-side-pannel',
  standalone: true,
  imports: [SidebarModule, ButtonModule],
  templateUrl: './side-pannel.component.html',
  styleUrl: './side-pannel.component.scss'
})
export class SidePannelComponent {
sidebarVisible: boolean = true;
}
