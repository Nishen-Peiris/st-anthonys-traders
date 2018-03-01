package com.nishenpeiris.StockManagementSystem;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class DriverSpecificationByName implements HibernateSpecification {
    private String desiredName;

    public DriverSpecificationByName(String desiredName) {
        super();
        this.desiredName = desiredName;
    }

    @Override
    public Criterion toCriteria() {
        return Restrictions.eq("firstName", desiredName);
    }
}
