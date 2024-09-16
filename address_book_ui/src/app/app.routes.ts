import { Routes } from '@angular/router';
import { RecordComponent } from './components/record/record.component';
import {ROUTES} from '@constants/routes'
import { AuthComponent } from './pages/auth/auth.component';

export const routes: Routes = [
    {
        path: ROUTES.login,
        component: AuthComponent,
        title: "Login"
    },
    {
        path: ROUTES.home,
        component: RecordComponent,
        title: "Home"
    },
    {
        path: ROUTES.records,
        component: RecordComponent,
        title: "Record"
    },
    {
        path: ROUTES.wildcard,
        redirectTo: ROUTES.home
    }
];
