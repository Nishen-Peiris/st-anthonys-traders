package com.nishenpeiris.StockManagementSystem.repository;

import com.nishenpeiris.StockManagementSystem.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DriverRepository implements Repository<Driver> {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Driver driver) throws Exception {
        Session session = sessionFactory.openSession();
        HibernateSpecification specification = new DriverSpecificationByName(driver.getFirstName());
        List<Driver> list = query(specification);
        if (!list.isEmpty()) {
            System.out.println("Failed to save driver: driver first name - " + driver.getFirstName() + " already in use.");
            throw new DriverNameAlreadyInUseException();
        }
        try {
            session.save(driver);
            System.out.println("Saved driver {" + driver.getId() + " " + driver.getFirstName() + " " + driver.getLastName() + "}.");
        } catch (HibernateException ex) {
            System.out.println("Failed to save driver {" + driver.getId() + " " + driver.getFirstName() + " " + driver.getLastName() + "}: Hibernate Exception");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Driver driver) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            Driver driverToBeRemoved = (Driver) session.merge(driver);
            session.delete(driverToBeRemoved);
            session.flush();
            System.out.println("Deleted driver {" + driver.getId() + " " + driver.getFirstName() + " " + driver.getLastName() + "}.");
        } catch (Exception ex) {
            System.out.println("Failed to delete driver {" + driver.getId() + " " + driver.getFirstName() + " " + driver.getLastName() + "}: Hibernate Exception");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Driver driver) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            session.update(driver);
            session.flush();
            System.out.println("Updated driver {" + driver.getId() + " " + driver.getFirstName() + " " + driver.getLastName() + "}.");
        } catch (HibernateException ex) {
            System.out.println("Failed to update driver {" + driver.getId() + " " + driver.getFirstName() + " " + driver.getLastName() + "}.");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Driver> query(HibernateSpecification specification) throws Exception {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Driver.class);
        if (specification != null) {
            criteria.add(specification.toCriteria());
        }
        criteria.addOrder(Order.asc("firstName"));
        return criteria.list();
    }
}
