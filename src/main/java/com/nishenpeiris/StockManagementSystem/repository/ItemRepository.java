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
public class ItemRepository implements Repository<Item> {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Item item) throws Exception {
        Session session = sessionFactory.openSession();
        HibernateSpecification specification = new ItemSpecificationByName(item.getName());
        List<Item> list = query(specification);
        if (!list.isEmpty()) {
            System.out.println("Failed to save item: item name - " + item.getName() + " already in use");
            throw new ItemNameAlreadyInUseException();
        }
        try {
            session.save(item);
            System.out.println("Saved item {" + item.getId() + " " + item.getName() + " " + item.getCode() + "}.");
        } catch (HibernateException ex) {
            System.out.println("Failed to save item {" + item.getId() + " " + item.getName() + " " + item.getCode() + "}: Hibernate Exception");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Item item) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            Item itemToBeRemoved = (Item) session.merge(item);
            session.delete(itemToBeRemoved);
            session.flush();
            System.out.println("Deleted item {" + item.getId() + " " + item.getName() + " " + item.getCode() + "}.");
        } catch (Exception ex) {
            System.out.println("Failed to item product {" + item.getId() + " " + item.getName() + " " + item.getCode() + "}: Hibernate Exception");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Item item) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            session.update(item);
            session.flush();
            System.out.println("Updated item {" + item.getId() + " " + item.getName() + " " + item.getCode() + "}.");
        } catch (HibernateException ex) {
            System.out.println("Failed to update item {" + item.getId() + " " + item.getName() + " " + item.getCode() + "}.");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Item> query(HibernateSpecification specification) throws Exception {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Item.class);
        if (specification != null) {
            criteria.add(specification.toCriteria());
        }
        criteria.addOrder(Order.asc("name"));
        return criteria.list();
    }
}
