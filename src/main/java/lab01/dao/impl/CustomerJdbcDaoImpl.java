package lab01.dao.impl;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.NoResultException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import lab01.HibernateUtils;
import lab01.dao.CustomerDao;
import lab01.model.CustomerBean;

public class CustomerJdbcDaoImpl implements CustomerDao {

	SessionFactory factory;
	
	public CustomerJdbcDaoImpl() {
		this.factory = HibernateUtils.getSessionFactory();
	}

	private static final String SELECT_BY_CUSTOMERID = "FROM Customer c WHERE c.customerId = :id";

	public CustomerBean findByCustomerId(String id) {
		CustomerBean result = null;
		Transaction transaction = null;
		Session session = factory.getCurrentSession();
		
		try {
			transaction = session.beginTransaction();
			result = session.createQuery(SELECT_BY_CUSTOMERID, CustomerBean.class)
							.setParameter("id", id)
							.getSingleResult();
			transaction.commit();
		} catch (NoResultException noResultException) {
			result = null;
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			throw new RuntimeException(e);
		} finally {
			if (session != null) session.close();
		}
		
		return result;
	}

	private static final String SELECT_ALL = "FROM Customer";

	public List<CustomerBean> findAll() {
		List<CustomerBean> result = null;
		Transaction transaction = null;
		Session session = factory.getCurrentSession();
		
		try {
			transaction = session.beginTransaction();
			result = session.createQuery(SELECT_ALL, CustomerBean.class)
							.getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			throw new RuntimeException(e);
		} finally {
			if (session != null) session.close();
		}
		
		return result;
	}

	public void save(CustomerBean bean) {
		Transaction transaction = null;
		Session session = factory.getCurrentSession();
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		bean.setRegisterDate(ts);
		
		try {
			transaction = session.beginTransaction();
			session.save(bean);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			throw new RuntimeException(e);
		} finally {
			if (session != null) session.close();
		}
	}

	public void deleteByCustomerId(String customerId) {
		Transaction transaction = null;
		Session session = factory.getCurrentSession();
		
		try {
			transaction = session.beginTransaction();
			CustomerBean customerBean = findByCustomerId(customerId);
			session.delete(customerBean);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			throw new RuntimeException(e);
		} finally {
			if (session != null) session.close();
		}
		
		return;
	}

	@Override
	public boolean existsByCustomerId(String customerId) {
		CustomerBean customerBean = findByCustomerId(customerId);
		return (customerBean != null);
	}

	@Override
	public CustomerBean findById(Integer id) {
		CustomerBean customerBean = null;
		Transaction transaction = null;
		Session session = factory.getCurrentSession();
		
		try {
			transaction = session.beginTransaction();
			customerBean = session.get(CustomerBean.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			throw new RuntimeException(e);
		} finally {
			if (session != null) session.close();
		}
		
		return customerBean;
	}

	
	
	private static final String DELETE_BY_ID = "DELETE FROM Customer c WHERE c.id = :id";

	@Override
	public void deleteById(Integer id) {
		Transaction transaction = null;
		Session session = factory.getCurrentSession();
		
		try {
			transaction = session.beginTransaction();
			session.createQuery(DELETE_BY_ID).setParameter("id", id)
											 .executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			throw new RuntimeException(e);
		} finally {
			if (session != null) session.close();
		}
		
		return;
	}

	@Override
	public void update(CustomerBean customerBean) {
		Transaction transaction = null;
		Session session = factory.getCurrentSession();
		
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(customerBean);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			throw new RuntimeException(e);
		} finally {
			if (session != null) session.close();
		}
	}

	

}