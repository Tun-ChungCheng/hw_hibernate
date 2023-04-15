# eeit58_hibernate_homework

 Refactor JDBC DAO using Hibernate

### [Hibernate version](https://github.com/Tun-ChungCheng/eeit58_hibernate_homework/blob/master/src/main/java/lab01/dao/impl/CustomerJdbcDaoImpl.java)

### JDBC version
```
public class CustomerJdbcDaoImpl implements CustomerDao {

	DataSource ds = null;

	public CustomerJdbcDaoImpl() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/CustomerDB");
		} catch (NamingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private static final String SELECT_BY_CUSTOMERID = "SELECT customerId, password, name, phone, birthday, registerdate, picture, weight FROM customerlab01 WHERE customerId = ?";

	public CustomerBean findByCustomerId(String id) {
		CustomerBean result = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_CUSTOMERID);) {
			stmt.setString(1, id);
			try (ResultSet rset = stmt.executeQuery();) {
				if (rset.next()) {
					result = new CustomerBean();
					result.setCustomerId(rset.getString("customerId"));
					result.setPassword(rset.getString("password"));
					result.setName(rset.getString("name"));
					result.setPhone(rset.getString("phone"));
					result.setBirthday(rset.getDate("birthday"));
					result.setWeight(rset.getDouble("weight"));
					result.setRegisterDate(rset.getTimestamp("registerDate"));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return result;
	}

	private static final String SELECT_ALL = "SELECT id, customerId, password, name, phone, birthday, registerdate, picture, weight FROM customerlab01";

	public List<CustomerBean> findAll() {
		List<CustomerBean> result = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			result = new Vector<>();
			while (rset.next()) {
				CustomerBean temp = new CustomerBean();
				temp.setId(rset.getInt("id"));
				temp.setCustomerId(rset.getString("customerId"));
				temp.setPassword(rset.getString("password"));
				temp.setName(rset.getString("name"));
				temp.setPhone(rset.getString("phone"));
				temp.setBirthday(rset.getDate("birthday"));
				temp.setWeight(rset.getDouble("weight"));
				temp.setRegisterDate(rset.getTimestamp("registerDate"));
				result.add(temp);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return result;
	}

	private static final String INSERT = "INSERT INTO customerlab01 (customerId, password, name, phone, birthday, registerdate, picture, weight) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	public void save(CustomerBean bean) {
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			stmt.setString(1, bean.getCustomerId());
			stmt.setString(2, bean.getPassword());
			stmt.setString(3, bean.getName());
			stmt.setString(4, bean.getPhone());

			java.util.Date temp = bean.getBirthday();
			if (temp != null) {
				java.sql.Date someTime = new java.sql.Date(temp.getTime());
				stmt.setDate(5, someTime);
			} else {
				stmt.setDate(5, null);
			}

			Timestamp ts = new Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(6, ts);
			Blob b = null;
			stmt.setBlob(7, b);
			stmt.setDouble(8, bean.getWeight());

			stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		}

	}

	private static final String DELETE_BY_CUSTOMERID = "DELETE FROM customerlab01 WHERE customerId=?";

	public void deleteByCustomerId(String customerId) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE_BY_CUSTOMERID);) {
			stmt.setString(1, customerId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return;
	}

	@Override
	public boolean existsByCustomerId(String customerId) {
		CustomerBean customerBean = findByCustomerId(customerId);
		return (customerBean != null);
	}

	private static final String SELECT_BY_ID = "SELECT id, customerId, password, name, phone, birthday, registerdate, picture, weight FROM customerlab01 WHERE id = ?";

	@Override
	public CustomerBean findById(Integer id) {
		CustomerBean customerBean = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setInt(1, id);
			try (ResultSet rset = stmt.executeQuery();) {
				if (rset.next()) {
					customerBean = new CustomerBean();
					customerBean.setId(rset.getInt("Id"));
					customerBean.setCustomerId(rset.getString("customerId"));
					customerBean.setPassword(rset.getString("password"));
					customerBean.setName(rset.getString("name"));
					customerBean.setPhone(rset.getString("phone"));
					customerBean.setBirthday(rset.getDate("birthday"));
					customerBean.setWeight(rset.getDouble("weight"));
					customerBean.setRegisterDate(rset.getTimestamp("registerDate"));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return customerBean;
	}

	
	
	private static final String DELETE_BY_ID = "DELETE FROM customerlab01 WHERE id=?";

	@Override
	public void deleteById(Integer id) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE_BY_ID);) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return;
	}

	private static final String UPDATE = "UPDATE customerlab01 set name = ?,  password = ?,  phone = ?, birthday = ?,  weight = ? WHERE id = ?";

	@Override
	public void update(CustomerBean customerBean) {
		try (
			Connection conn = ds.getConnection(); 
			PreparedStatement stmt = conn.prepareStatement(UPDATE);
		) {
			stmt.setString(1, customerBean.getName());
			stmt.setString(2, customerBean.getPassword());
			stmt.setString(3, customerBean.getPhone());

			java.util.Date temp = customerBean.getBirthday();
			if (temp != null) {
				java.sql.Date someTime = new java.sql.Date(temp.getTime());
				stmt.setDate(4, someTime);
			} else {
				stmt.setDate(4, null);
			}
			stmt.setDouble(5, customerBean.getWeight());
			stmt.setInt(6, customerBean.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
```
