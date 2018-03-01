import {Component, OnInit} from "@angular/core";
import {InvoiceService} from "./invoice.service";
import {Invoice} from "../../shared/Invoice";

import {Item} from "../../shared/Item";
// import {InvoiceService} from "./invoice.service";

@Component({
  templateUrl: './invoice.component.html'
})

export class InvoiceComponent implements OnInit {
  createMode: boolean;
  invoices: Invoice[];
  invoice: Invoice;
  rowsOnPage = 10;
  sortBy = "invoiceNumber";
  sortOrder = "asc";

  items: Item[];
  item: Item;






  constructor(private invoiceService: InvoiceService) {

  }


  ngOnInit(): void {
    this.createMode = true;
    this.invoice = new Invoice();
    this.invoices = new Array();
    this.getInvoices();




  }




  getInvoices() {
    this.invoiceService.getInvoices().subscribe(
      data => {
        this.invoices = data.json();
      },
      error => {
        console.log("Failed to receive the list of invoices. Please try again later.");
      }
    );
  }

  add(invoice: Invoice) {
    this.invoiceService.create(this.invoice).subscribe(
      data => {
        console.log("Saved invoice");
        this.invoice = new Invoice();
        this.getInvoices();
      },
      error => {
        const status = error.status.toString();
        if (status === "409") {
          console.log("An invoice exists with the same invoice number. Please use a different invoice number.");
        } else {
          console.log("Internal server error occurred while processing your request. Please try again later.");
        }
      }
    );
  }

  edit(invoice: Invoice) {
    this.invoiceService.update(invoice).subscribe(
      data => {
        console.log("Updated invoice");
        this.invoice = new Invoice();
        this.getInvoices();
      },
      error => {
        console.log("Internal server error occurred while processing your request. Please try again later.");
      }
    );
  }

  delete(invoice: Invoice) {
    this.invoiceService.delete(invoice).subscribe(
      data => {
        console.log("Deleted invoice");
        this.getInvoices();
      },
      error => {
        const status = error.status.toString();
        if (status === "400") {
          console.log("There are one or more items in the stock associated with this invoice number. First, remove those items and then try again.");
        } else {
          console.log("Internal server error occurred while processing your request. Please try again later.");
        }
      }
    );
  }

  selectItem(invoice: Invoice) {
    this.invoice = invoice;
    this.createMode = false;
  }




  // item


  //
  //
  add1(invoice: Invoice) {



    this.invoiceService.create1(this.invoice).subscribe(
      data => {
        console.log("Saved item");
        this.invoice = new Invoice();
        this.getInvoices();
      },
      error => {
        const status = error.status.toString();
        if (status === "409") {
          console.log("A item exists with the same name. Please use a different name.");
        } else {
          console.log("Internal server error occurred while processing your request. Please try again later.");
        }
      }
    );



    this.invoiceService.create1(this.invoice).subscribe(
      data => {
        console.log("Saved item");
        this.invoice = new Invoice();
        this.getInvoices();
      },
      error => {
        const status = error.status.toString();
        if (status === "409") {
          console.log("A item exists with the same name. Please use a different name.");
        } else {
          console.log("Internal server error occurred while processing your request. Please try again later.");
        }
      }
    );
  }


}


