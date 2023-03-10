import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {RecordComponent} from "./record/record.component";
import {AppRoutingModule} from "./app-routing.module";
import {BoxesComponent} from "./boxes/boxes.component";
import {PyramidComponent} from "./pyramid/pyramid.component";

@NgModule({
  declarations: [
    AppComponent,
    RecordComponent,
    BoxesComponent,
    PyramidComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
