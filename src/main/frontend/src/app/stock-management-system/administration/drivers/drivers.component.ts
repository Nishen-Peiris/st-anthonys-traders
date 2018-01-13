import {Component, OnInit} from "@angular/core";
import {DriversService} from "./drivers.service";
import {Driver} from "../../shared/Driver";

@Component({
  templateUrl: './drivers.component.html'
})

export class DriversComponent implements OnInit {
  createMode: boolean;
  drivers: Driver[];
  driver: Driver;
  rowsOnPage = 10;
  sortBy = "firstName";
  sortOrder = "asc";

  constructor(private driversService: DriversService) {

  }

  ngOnInit(): void {
    this.createMode = true;
    this.driver = new Driver();
    this.drivers = new Array();
    this.getDrivers();
  }

  getDrivers() {
    this.driversService.getDrivers().subscribe(
      data => {
        this.drivers = data.json();
      },
      error => {
        console.log("Failed to receive the list of drivers. Please try again later.");
      }
    );
  }

  add(driver: Driver) {
    this.driversService.create(this.driver).subscribe(
      data => {
        console.log("Saved driver");
        this.driver = new Driver();
        this.getDrivers();
      },
      error => {
        const status = error.status.toString();
        if (status === "409") {
          console.log("An driver exists with the same driver number. Please use a different driver number.");
        } else {
          console.log("Internal server error occurred while processing your request. Please try again later.");
        }
      }
    );
  }

  edit(driver: Driver) {
    this.driversService.update(driver).subscribe(
      data => {
        console.log("Updated driver");
        this.driver = new Driver();
        this.getDrivers();
      },
      error => {
        console.log("Internal server error occurred while processing your request. Please try again later.");
      }
    );
  }

  delete(driver: Driver) {
    this.driversService.delete(driver).subscribe(
      data => {
        console.log("Deleted driver");
        this.getDrivers();
      },
      error => {
        const status = error.status.toString();
        if (status === "400") {
          console.log("There are one or more items in the stock associated with this driver number. First, remove those items and then try again.");
        } else {
          console.log("Internal server error occurred while processing your request. Please try again later.");
        }
      }
    );
  }

  selectItem(driver: Driver) {
    this.driver = driver;
    this.createMode = false;
  }
}
