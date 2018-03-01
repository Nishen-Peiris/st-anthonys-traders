import {Component, OnInit} from "@angular/core";
import {InvoiceService} from "./invoice.service";
import {DriversService} from "../drivers/drivers.service";
import {Invoice} from "../../shared/Invoice";
import {Driver} from "../../shared/Driver";

@Component({
  templateUrl: './invoice.component.html'
})

export class InvoiceComponent implements OnInit {
  createMode: boolean;
  invoices: Invoice[];
  invoice: Invoice;
  driver: Driver;
  drivers: Driver[];
  rowsOnPage = 10;
  sortBy = "invoiceNumber";
  sortOrder = "asc";

  constructor(private invoiceService: InvoiceService, private driverService: DriversService) {

  }

  ngOnInit(): void {
    this.createMode = true;
    this.invoice = new Invoice();
    this.invoices = new Array();
    this.getInvoices();
    this.getDrivers();
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

  getDrivers() {
    this.driverService.getDrivers().subscribe(
      data => {
        this.drivers = data.json();
      },
      error => {
        console.log("Failed to receive the list of drivers. Please try again later.");
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
    this.driver = this.invoice.driver;
    this.createMode = false;
  }

  onChangeDriver(id: number) {
    for (var i = 0; i < this.drivers.length; i++) {
      if (id == this.drivers[i].id) {
        this.invoice.driver = this.drivers[i];
      }
    }
  }
}
