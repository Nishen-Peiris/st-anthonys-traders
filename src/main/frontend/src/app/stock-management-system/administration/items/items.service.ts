import {Injectable} from "@angular/core";
import {Item} from "../../shared/Item";
import {Headers, Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';

@Injectable()
export class ItemsService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private itemsUrl = 'api/item';

  constructor(private http: Http) {

  }

  getItems() {
    return this.http.get(this.itemsUrl);
  }

  create(item: Item) {
    return this.http.post(this.itemsUrl, item, {headers: this.headers});
  }

  update(item: Item) {
    return this.http.put(this.itemsUrl, item, {headers: this.headers});
  }

  delete(item: Item) {
    return this.http.delete(this.itemsUrl, {headers: this.headers, body: item});
  }
}
