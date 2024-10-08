import { Component, Inject } from '@angular/core';
import { SidebarModule } from 'primeng/sidebar';
import { ButtonModule } from 'primeng/button';
import { TreeModule } from 'primeng/tree';
import { TreeNode } from 'primeng/api';
import { AuthService } from '@auth0/auth0-angular';
import { DOCUMENT } from '@angular/common';
import {ROUTES} from '@constants/routes'

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
      icon: 'pi pi-user', 
      // Define an action or a command later for navigation or opening panels
    },
    {
      label: 'Contacts',
      icon: 'pi pi-users', 
      // Define action for contacts here
    },
    {
      label: 'Labels',
      icon: 'pi pi-tag',
      // Define action for labels here
    }
  ];

  constructor(@Inject(DOCUMENT) public document: Document,
    public auth: AuthService) {
  }

  logout() {
    this.auth.logout({
      logoutParams: {
        returnTo: document.location.origin + '/' + ROUTES.login, 
      },
    });
  }
}
