import { Routes } from '@angular/router';
import { RecordComponent } from './record/record.component';
import { TestComponent } from './test/test.component';

export const routes: Routes = [
    {
        path: 'test',
        component: TestComponent,
        title: "Placeholder 2"
    },
    {
        path: 'records',
        component: RecordComponent,
        title: "Record"
    }
];
