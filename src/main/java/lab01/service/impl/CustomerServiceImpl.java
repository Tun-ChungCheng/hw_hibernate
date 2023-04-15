package lab01.service.impl;

import java.util.List;

import lab01.dao.CustomerDao;
import lab01.dao.impl.CustomerJdbcDaoImpl;
import lab01.model.CustomerBean;
import lab01.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	
	CustomerDao customerDao = null;
	
	public CustomerServiceImpl() {
		customerDao = new CustomerJdbcDaoImpl();
	}	

	public CustomerBean findById(Integer id) {
		return customerDao.findById(id);
	}
	
	public List<CustomerBean> findAll() {
		return customerDao.findAll();
	}

	public void save(CustomerBean bean) {
		 customerDao.save(bean);
	}


	public void deleteById(Integer id) {
		customerDao.deleteById(id);
	}

	@Override
	public boolean existsByCustomerId(String customerId) {
		return customerDao.existsByCustomerId(customerId);
	}

	@Override
	public void update(CustomerBean customerBean) {
		customerDao.update(customerBean);
		
	}
}