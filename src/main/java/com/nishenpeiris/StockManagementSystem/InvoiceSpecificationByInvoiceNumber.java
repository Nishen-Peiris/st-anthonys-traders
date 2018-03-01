package com.nishenpeiris.StockManagementSystem;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class InvoiceSpecificationByInvoiceNumber implements HibernateSpecification {
    private String desiredInvoiceNumber;

    public InvoiceSpecificationByInvoiceNumber(String desiredInvoiceNumber) {
        super();
        this.desiredInvoiceNumber = desiredInvoiceNumber;
    }

    @Override
    public Criterion toCriteria() {
        return Restrictions.eq("invoiceNumber", desiredInvoiceNumber);
    }
}
