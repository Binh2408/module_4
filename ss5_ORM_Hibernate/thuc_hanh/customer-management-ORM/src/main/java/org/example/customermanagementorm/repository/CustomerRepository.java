package org.example.customermanagementorm.repository;

import org.example.customermanagementorm.model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class CustomerRepository implements ICustomerRepository {
    @Override
    public List<Customer> findAll() {
        Session session = ConnectionUtil.sessionFactory.openSession();

        String queryStr = "SELECT c FROM Customer AS c";
        TypedQuery<Customer> query = session.createQuery(queryStr, Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        String queryStr = "SELECT c FROM Customer AS c WHERE c.id = :id";
        TypedQuery<Customer> query = session.createQuery(queryStr, Customer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Customer customer) {
        Transaction transaction = null;
        Customer origin;
        if (customer.getId() == 0) {
            origin = new Customer();
        } else {
            origin = findById(customer.getId());
        }
        try (Session session = ConnectionUtil.sessionFactory.openSession();
        ) {
            transaction = session.beginTransaction();
            origin.setName(customer.getName());
            origin.setEmail(customer.getEmail());
            origin.setAddress(customer.getAddress());
            session.saveOrUpdate(origin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(int id, Customer customer) {
        Transaction transaction = null;

        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Customer origin = session.get(Customer.class, id);
            if (origin != null) {
                origin.setName(customer.getName());
                origin.setEmail(customer.getEmail());
                origin.setAddress(customer.getAddress());

                session.update(origin);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void remove(int id) {
        Customer customer = findById(id);
        if (customer != null) {
            Transaction transaction = null;
            try (Session session = ConnectionUtil.sessionFactory.openSession();
            ) {
                transaction = session.beginTransaction();
                session.remove(customer);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }
}
