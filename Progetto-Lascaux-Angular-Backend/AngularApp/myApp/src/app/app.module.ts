import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { myComponent } from './searchEngine/app.searchEngine';
import { tableAndRadio } from './tableAndRadio/app.tableAndRadio'; //// fa riferimento al file.ts
@NgModule({
  declarations: [
    AppComponent,
    myComponent,
    tableAndRadio


  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
