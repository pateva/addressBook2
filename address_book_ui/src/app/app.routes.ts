import { Routes } from '@angular/router';
import { RecordComponent } from './record/record.component';
import { TestComponent } from './test/test.component';
import {ROUTES} from '@constants/routes'

export const routes: Routes = [
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
