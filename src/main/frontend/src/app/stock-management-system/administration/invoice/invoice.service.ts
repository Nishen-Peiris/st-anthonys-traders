import {Injectable} from "@angular/core";
import {Invoice} from "../../shared/Invoice";

import {Item} from "../../shared/Item";
import {Headers, Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';

@Injectable()
export class InvoiceService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private invoiceUrl = 'api/product';
  private itemsUrl = 'api/item';

  constructor(private http: Http) {

  }

  getInvoices() {
    return this.http.get(this.invoiceUrl);
  }

  create(invoice: Invoice) {
    return this.http.post(this.invoiceUrl, invoice, {headers: this.headers});
  }

  update(invoice: Invoice) {
    return this.http.put(this.invoiceUrl, invoice, {headers: this.headers});
  }

  delete(invoice: Invoice) {
    return this.http.delete(this.invoiceUrl, {headers: this.headers, body: invoice});
  }

  //
  // getItems1() {
  //   return this.http.get(this.invoiceUrl);
  // }
  //
  create1(invoice: Item) {
    // alert(this.invoiceUrl)
    return this.http.post(this.itemsUrl, invoice, {headers: this.headers});
  }

  // update1(invoice: Invoice) {
  //   return this.http.put(this.invoiceUrl, invoice, {headers: this.headers});
  // }
  //
  // delete1(invoice: Invoice) {
  //   return this.http.delete(this.invoiceUrl, {headers: this.headers, body: invoice});
  // }
}


//
//
// import {Injectable} from "@angular/core";
// import {Item} from "../../shared/Item";
// import {Headers, Http} from "@angular/http";
//
// import 'rxjs/add/operator/toPromise';
//
// @Injectable()
// export class ItemsService {
//   // private headers = new Headers({'Content-Type': 'application/json'});
//   private itemsUrl = 'api/item';
//
//   constructor(private http: Http) {
//
//   }
//
//   getItems() {
//     return this.http.get(this.itemsUrl);
//   }
//
//   create(item: Item) {
//     return this.http.post(this.itemsUrl, item, {headers: this.headers});
//   }
//
//   update(item: Item) {
//     return this.http.put(this.itemsUrl, item, {headers: this.headers});
//   }
//
//   delete(item: Item) {
//     return this.http.delete(this.itemsUrl, {headers: this.headers, body: item});
//   }
// }
//
