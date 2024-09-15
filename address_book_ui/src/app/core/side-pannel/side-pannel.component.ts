import { Component } from '@angular/core';
import { SidebarModule } from 'primeng/sidebar';
import { ButtonModule } from 'primeng/button';
import { TreeModule } from 'primeng/tree';
import { TreeNode } from 'primeng/api';  



@Component({
  selector: 'app-side-pannel',
  standalone: true,
  imports: [SidebarModule, ButtonModule, TreeModule],
  templateUrl: './side-pannel.component.html',
  styleUrl: './side-pannel.component.scss'
})
export class SidePannelComponent {

sidebarVisible: boolean = true;
files: TreeNode[] = [
  {
    label: 'My Account',
    icon: 'pi pi-user', // Icon for profile
    // Define an action or a command later for navigation or opening panels
  },
  {
    label: 'Contacts',
    icon: 'pi pi-users',  // Icon for contacts
    // Define action for contacts here
  },
  {
    label: 'Labels',
    icon: 'pi pi-tag', // Icon for labels
    // Define action for labels here
  }
];
}
