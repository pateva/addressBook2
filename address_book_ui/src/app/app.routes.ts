import { Routes } from '@angular/router';
import { RecordComponent } from './record/record.component';
import { TestComponent } from './test/test.component';

const homePath = '';
const recordsPath = 'records';

export const routes: Routes = [
    {
        path: homePath,
        component: TestComponent,
        title: "Home"
    },
    {
        path: recordsPath,
        component: RecordComponent,
        title: "Record"
    },
    {
        path: '**',
        redirectTo: homePath
    }
];
