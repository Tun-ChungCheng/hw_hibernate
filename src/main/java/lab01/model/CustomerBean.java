package lab01.model;  

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Customer")
@Table(name = "customerlab01")
public class CustomerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;   			    		// ObjectId
	private String customerId;   				// 客戶代號
	private String password;   					// 密碼
	private String name;       					// 姓名
	private String	phone;     					// 電話
	private java.sql.Date birthday;			// 生日	
	private java.sql.Timestamp registerDate;	// 客戶登錄日期
	private double weight;						// 體重
	
	public CustomerBean() {
	} 
	
	public CustomerBean(String customerId, String name, String password, String phone, Date birthday,
			Timestamp registerDate, double weight) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.birthday = birthday;
		this.registerDate = registerDate;
		this.weight = weight;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
	public java.sql.Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(java.sql.Timestamp registerdate) {
		this.registerDate = registerdate;
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static java.util.Date convertDate(String temp){
		java.util.Date result = new java.util.Date();
		try {
			result=sdf.parse(temp);
		} catch (ParseException e) {
			result = null ; 
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "CustomerBean [id=" + id + ", customerId=" + customerId + ", password=" + password + ", name=" + name
				+ ", phone=" + phone + ", birthday=" + birthday + ", registerDate=" + registerDate + ", weight="
				+ weight + "]";
	}
	
	
	

}