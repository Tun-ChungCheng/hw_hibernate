package lab01.service;

import java.util.List;

import lab01.model.CustomerBean;

public interface CustomerService {

	boolean existsByCustomerId(String customerId);

	void save(CustomerBean mb);

	List<CustomerBean> findAll();

	CustomerBean findById(Integer id);

	void deleteById(Integer id);

	void update(CustomerBean mb);

}