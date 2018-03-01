package com.nishenpeiris.StockManagementSystem.repository;

import com.nishenpeiris.StockManagementSystem.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@Component
public class InvoiceRepository implements Repository<Invoice> {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Invoice invoice) throws Exception {
        Session session = sessionFactory.openSession();
        HibernateSpecification specification = new InvoiceSpecificationByInvoiceNumber(invoice.getInvoiceNumber());
        List<Invoice> list = query(specification);
        if (!list.isEmpty()) {
            System.out.println("Failed to save invoice: invoice number - " + invoice.getInvoiceNumber() + " already in use.");
            throw new DriverNameAlreadyInUseException();
        }
        try {
            session.save(invoice);
            System.out.println("Saved invoice {" + invoice.getId() + " " + invoice.getInvoiceNumber() + " " + invoice.getDriver().getFirstName() + ", " + invoice.getDriver().getLastName() + "}.");
        } catch (HibernateException ex) {
            System.out.println("Failed to save invoice {" + invoice.getId() + " " + invoice.getInvoiceNumber() + " " + invoice.getDriver().getFirstName() + ", " + invoice.getDriver().getLastName() + "}.");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Invoice invoice) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            Invoice invoiceToBeRemoved = (Invoice) session.merge(invoice);
            session.delete(invoiceToBeRemoved);
            session.flush();
            System.out.println("Deleted invoice {" + invoice.getId() + " " + invoice.getInvoiceNumber() + " " + invoice.getDriver().getFirstName() + ", " + invoice.getDriver().getLastName() + "}.");
        } catch (Exception ex) {
            System.out.println("Failed to delete invoice {" + invoice.getId() + " " + invoice.getInvoiceNumber() + " " + invoice.getDriver().getFirstName() + ", " + invoice.getDriver().getLastName() + "}: Hibernate Exception");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Invoice invoice) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            session.update(invoice);
            session.flush();
            System.out.println("Updated invoice {" + invoice.getId() + " " + invoice.getInvoiceNumber() + " " + invoice.getDriver().getFirstName() + ", " + invoice.getDriver().getLastName() + "}.");
        } catch (HibernateException ex) {
            System.out.println("Failed to update invoice {" + invoice.getId() + " " + invoice.getInvoiceNumber() + " " + invoice.getDriver().getFirstName() + ", " + invoice.getDriver().getLastName() + "}.");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Invoice> query(HibernateSpecification specification) throws Exception {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Invoice.class);
        if (specification != null) {
            criteria.add(specification.toCriteria());
        }
        criteria.addOrder(Order.asc("invoiceNumber"));
        return criteria.list();
    }
}
