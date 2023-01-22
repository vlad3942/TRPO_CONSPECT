import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {RecordComponent} from './record/record.component';
import {BoxesComponent} from "./boxes/boxes.component";
import {PyramidComponent} from "./pyramid/pyramid.component";

const routes: Routes = [
  { path: 'records', component: RecordComponent },
  { path: 'boxes', component: BoxesComponent},
  { path: 'pyramid', component: PyramidComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
