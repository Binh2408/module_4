package org.example.productmanagementjpa.repository;

import org.example.productmanagementjpa.model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {

    @Override
    public List<Product> findAll() {
        Session session = ConnectionUtil.sessionFactory.openSession();
        TypedQuery<Product> query = session.createQuery("from Product", Product.class);
//        List<Product> students = query.getResultList();
//        return students;
        return query.getResultList();
    }

    @Override
    public void save(Product product) {
        Transaction transaction = null;
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Product findById(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        return session.get(Product.class,id);
//        TypedQuery<Product> query = session.createQuery("from Product where id =: id", Product.class);
//        return query.setParameter("id", id).getSingleResult();
    }

    @Override
    public void update(int id, Product product) {
        Transaction transaction = null;
        try(Session session = ConnectionUtil.sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Product productUpdated = session.get(Product.class,id);
            if(productUpdated != null) {
                productUpdated.setName(product.getName());
                productUpdated.setPrice(product.getPrice());
                productUpdated.setDescription(product.getDescription());
                productUpdated.setManufacture(product.getManufacture());

                session.update(productUpdated);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        }
    }

    @Override
    public void remove(int id) {
        Transaction transaction = null;
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Product productDelete = session.get(Product.class, id);
            if (productDelete != null) {
                session.remove(productDelete);
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
    public List<Product> findByName(String keyword) {
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            String queryStr = "from Product where name like :keyword";
            return session.createQuery(queryStr, Product.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .getResultList();
        }
    }
}
