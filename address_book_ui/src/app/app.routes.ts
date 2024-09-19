import { Routes } from '@angular/router';
import {ROUTES} from '@constants/routes'
import { AuthComponent } from './pages/auth/auth.component';
import { AuthGuard } from '@auth0/auth0-angular';
import { ContactComponent } from './components/contact/contact.component';

export const routes: Routes = [
    {
        path: ROUTES.login,
        component: AuthComponent,
        title: "Login"
    },
    {
        //TODO should forward to personal contact page
        path: ROUTES.home,
        component: ContactComponent,
        title: "Home",
        canActivate: [AuthGuard]
    },
    {
        //TODO should forward to a contactId and general contact page
        path: ROUTES.contact,
        component: ContactComponent,
        title: "Contact",
        canActivate: [AuthGuard]
    },
    {
        path: ROUTES.wildcard,
        redirectTo: ROUTES.home
    }
];
