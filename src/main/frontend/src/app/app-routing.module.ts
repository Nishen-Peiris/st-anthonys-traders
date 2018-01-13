import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {UsersComponent} from './stock-management-system/administration/users/users.component';
import {ItemsComponent} from './stock-management-system/administration/items/items.component';
import {InvoiceComponent} from './stock-management-system/administration/invoice/invoice.component';
import {DriversComponent} from "./stock-management-system/administration/drivers/drivers.component";

const routes: Routes = [
  {path: 'users', component: UsersComponent},
  {path: 'items', component: ItemsComponent},
  {path: 'invoice', component: InvoiceComponent},
  {path: 'drivers', component: DriversComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
