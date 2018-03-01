package com.nishenpeiris.StockManagementSystem.controller;

import com.nishenpeiris.StockManagementSystem.Driver;
import com.nishenpeiris.StockManagementSystem.DriverNameAlreadyInUseException;
import com.nishenpeiris.StockManagementSystem.Invoice;
import com.nishenpeiris.StockManagementSystem.InvoiceNumberAlreadyInUseException;
import com.nishenpeiris.StockManagementSystem.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(path = "/invoice")
public class InvoiceController {
    InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceController(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @RequestMapping(method = GET)
    public ResponseEntity<?> getInvoices() {
        try {
            return new ResponseEntity<>(invoiceRepository.query(null), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = POST)
    public ResponseEntity<?> create(@RequestBody Invoice invoice) {
        try {
            invoiceRepository.add(invoice);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InvoiceNumberAlreadyInUseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = PUT)
    public ResponseEntity<?> update(@RequestBody Invoice invoice) {
        try {
            invoiceRepository.update(invoice);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = DELETE)
    public ResponseEntity<?> delete(@RequestBody Invoice invoice) {
        try {
            invoiceRepository.remove(invoice);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
