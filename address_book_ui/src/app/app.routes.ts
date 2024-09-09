import { Routes } from '@angular/router';
import { RecordComponent } from './record/record.component';
import { TestComponent } from './test/test.component';
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
        component: TestComponent,
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
