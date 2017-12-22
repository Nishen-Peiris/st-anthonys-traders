package com.nishenpeiris.StockManagementSystem;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class ItemSpecificationByCode implements HibernateSpecification {
    private String desiredCode;

    public ItemSpecificationByCode(String desiredCode) {
        super();
        this.desiredCode = desiredCode;
    }

    @Override
    public Criterion toCriteria() {
        return Restrictions.eq("code", desiredCode);
    }
}
