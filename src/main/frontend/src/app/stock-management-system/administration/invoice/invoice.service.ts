import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Invoice} from "../../shared/Invoice";

@Injectable()
export class InvoiceService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private invoiceUrl = 'api/invoice';

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
}
