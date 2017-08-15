import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {UsersComponent} from './stock-management-system/administration/users/users.component';

import {AppRoutingModule} from './app-routing.module';

@NgModule({
  imports: [BrowserModule, AppRoutingModule, FormsModule],
  declarations: [AppComponent, UsersComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
}
