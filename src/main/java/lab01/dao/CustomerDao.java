package lab01.dao;

import java.util.List;

import lab01.model.CustomerBean;

public interface CustomerDao {

	boolean existsByCustomerId(String customerId);

	void save(CustomerBean mb);

	List<CustomerBean> findAll();

	CustomerBean findById(Integer id);

	void deleteById(Integer id);

	void update(CustomerBean mb);

}