package lab01.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab01.SystemUtils;
import lab01.model.CustomerBean;
import lab01.service.CustomerService;
import lab01.service.impl.CustomerServiceImpl;

@WebServlet("/lab01/insertCustomer.do")
public class InsertCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 定義存放錯誤訊息的 Collection物件
		Map<String, String> errorMessage = new HashMap<>();
		request.setAttribute("ErrorMsg", errorMessage);
		// 設定輸入資料的編碼
		request.setCharacterEncoding("UTF-8");
		// 讀取使用者所輸入，由瀏覽器送來的 customerId 欄位內的資料，注意大小寫
		String customerId = request.getParameter("customerId");
		// 檢查使用者所輸入的資料
		if (customerId == null || customerId.trim().length() == 0) {
			errorMessage.put("customerId", "帳號欄必須輸入");
		}
		// 讀取使用者所輸入，由瀏覽器送來的 pswd 欄位內的資料，注意大小寫
		String password = request.getParameter("pswd");
		// 檢查使用者所輸入的資料
		if (password == null || password.trim().length() == 0) {
			errorMessage.put("password", "密碼欄必須輸入");
		}
		// 讀取使用者所輸入，由瀏覽器送來的 name 欄位內的資料
		String name = request.getParameter("name");
		// 檢查使用者所輸入的資料
		if (name == null || name.trim().length() == 0) {
			errorMessage.put("name", "姓名欄必須輸入");
		}
		// 讀取使用者所輸入，由瀏覽器送來的 phone 欄位內的資料
		String phone = request.getParameter("phone");
		// 讀取使用者所輸入，由瀏覽器送來的 birthday 欄位內的資料
		String birthday = request.getParameter("birthday");
		java.sql.Date date = null;
		if (birthday != null && birthday.trim().length() > 0) {
			try {
				date = SystemUtils.toSqlDate(birthday);
			} catch (Exception e) {
				errorMessage.put("birthday", "生日欄格式錯誤");
			}
		}
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String weight = request.getParameter("weight");
		double dWeight = -1;
		if (weight != null && weight.trim().length() > 0) {
			try {
				dWeight = Double.parseDouble(weight.trim());
			} catch (NumberFormatException e) {
				errorMessage.put("weight", "體重欄必須為數值");
			} 
		}
		// 如果有錯誤，呼叫view元件，送回錯誤訊息
		if (!errorMessage.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/lab01/InsertCustomerForm.jsp");
			rd.forward(request, response);
			return;
		}
		// CustomerBean 扮演封裝輸入資料的角色
		CustomerBean mb = new CustomerBean(customerId, name, password, phone, date, ts, dWeight);
		try {
			CustomerService customerService = new CustomerServiceImpl();
			if (customerService.existsByCustomerId(customerId)) {
				throw new RuntimeException("帳號重複");
			}
			customerService.save(mb);
			request.setAttribute("customerBean", mb);
			// 產生 RequestDispatcher 物件 rd
			RequestDispatcher rd = request.getRequestDispatcher("/lab01/InsertCustomerSuccess.jsp");
			// 請容器代為呼叫下一棒程式
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			if (e.getMessage().indexOf("代號重複") != -1) {
				errorMessage.put("customerId", "代號已存在，請重新輸入帳號");
			} else	if (e.getMessage().indexOf("重複的索引鍵") != -1 
						|| e.getMessage().indexOf("Duplicate entry") != -1
						) {
				errorMessage.put("customerId", "主鍵值重複，請檢查表格");
			} else {
				errorMessage.put("exception", "資料庫存取錯誤:" + e.getMessage());
			}
			RequestDispatcher rd = request.getRequestDispatcher("/lab01/InsertCustomerForm.jsp");
			rd.forward(request, response);
			return;
		}
	}
}
