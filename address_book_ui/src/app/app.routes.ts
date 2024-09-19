import { Routes } from '@angular/router';
import { RecordComponent } from './components/record/record.component';
import {ROUTES} from '@constants/routes'
import { AuthComponent } from './pages/auth/auth.component';
import { AuthGuard } from '@auth0/auth0-angular';

export const routes: Routes = [
    {
        path: ROUTES.login,
        component: AuthComponent,
        title: "Login"
    },
    {
        path: ROUTES.home,
        component: RecordComponent,
        title: "Home",
        canActivate: [AuthGuard]
    },
    {
        path: ROUTES.records,
        component: RecordComponent,
        title: "Record",
        canActivate: [AuthGuard]
    },
    {
        path: ROUTES.wildcard,
        redirectTo: ROUTES.home
    }
];
