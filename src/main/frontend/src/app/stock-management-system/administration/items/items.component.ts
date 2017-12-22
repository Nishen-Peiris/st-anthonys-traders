import {Component, OnInit} from '@angular/core';
import {Item} from "../../shared/Item";
import {ItemsService} from "./items.service";

@Component({
  templateUrl: './items.component.html'
})

export class ItemsComponent implements OnInit {
  createMode: boolean;
  items: Item[];
  item: Item;
  rowsOnPage = 10;
  sortBy = "name";
  sortOrder = "asc";

  constructor(private itemsService: ItemsService) {

  }

  ngOnInit(): void {
    this.createMode = true;
    this.item = new Item();
    this.items = new Array();
    this.getItems();
  }

  getItems() {
    this.itemsService.getItems().subscribe(
      data => {
        this.items = data.json();
      },
      error => {
        console.log("Failed to receive the list of items. Please try again later.");
      }
    );
  }

  add(item: Item) {
    this.itemsService.create(this.item).subscribe(
      data => {
        console.log("Saved item");
        this.item = new Item();
        this.getItems();
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

  edit(item: Item) {
    this.itemsService.update(item).subscribe(
      data => {
        console.log("Updated item");
        this.item = new Item();
        this.getItems();
      },
      error => {
        console.log("Internal server error occurred while processing your request. Please try again later.");
      }
    );
  }

  delete(item: Item) {
    this.itemsService.delete(item).subscribe(
      data => {
        console.log("Deleted item");
        this.getItems();
      },
      error => {
        const status = error.status.toString();
        if (status === "400") {
          console.log("There are one or more products associated with this item. First, remove those products and then try again.");
        } else {
          console.log("Internal server error occurred while processing your request. Please try again later.");
        }
      }
    );
  }

  selectItem(item: Item) {
    this.item = item;
    this.createMode = false;
  }
}
