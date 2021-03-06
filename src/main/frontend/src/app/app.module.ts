import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {DataTableModule} from 'angular2-datatable';

import {AppComponent} from './app.component';
import {UsersComponent} from './stock-management-system/administration/users/users.component';
import {DriversComponent} from "./stock-management-system/administration/drivers/drivers.component";

import {UsersService} from './stock-management-system/administration/users/users.service';
import {DriversService} from "./stock-management-system/administration/drivers/drivers.service";

import {AppRoutingModule} from './app-routing.module';
import {ItemsComponent} from "./stock-management-system/administration/items/items.component";
import {ItemsService} from "./stock-management-system/administration/items/items.service";
import {InvoiceComponent} from './stock-management-system/administration/invoice/invoice.component';
import {InvoiceService} from './stock-management-system/administration/invoice/invoice.service';

@NgModule({
  declarations: [
    AppComponent, UsersComponent, ItemsComponent, InvoiceComponent, DriversComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, FormsModule, HttpModule, DataTableModule
  ],
  providers: [UsersService, ItemsService, InvoiceService, DriversService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
