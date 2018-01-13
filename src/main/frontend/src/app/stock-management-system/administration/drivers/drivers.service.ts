import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Driver} from "../../shared/Driver";

@Injectable()
export class DriversService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private driversUrl = 'api/drivers';

  constructor(private http: Http) {

  }

  getDrivers() {
    return this.http.get(this.driversUrl);
  }

  create(driver: Driver) {
    return this.http.post(this.driversUrl, driver, {headers: this.headers});
  }

  update(driver: Driver) {
    return this.http.put(this.driversUrl, driver, {headers: this.headers});
  }

  delete(driver: Driver) {
    return this.http.delete(this.driversUrl, {headers: this.headers, body: driver});
  }
}
