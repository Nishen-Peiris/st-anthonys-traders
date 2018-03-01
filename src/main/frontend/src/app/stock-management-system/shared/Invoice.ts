import {Driver} from "./Driver";

export class Invoice {
  id: number;
  date: Date;
  invoiceNumber: string;
  issueNumber: string;
  orderNumber: string;
  vehicleNumber: string;
  driver: Driver;
  subTotal: number;
  grandTotal: number;
}
