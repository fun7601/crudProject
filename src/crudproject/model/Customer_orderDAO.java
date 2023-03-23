package crudproject.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.dbutil.OracleUtil;

import crudproject.vo.Customer_OrderVO;

//DAO(Data Access Object) : DB업무, CRUD 
public class Customer_orderDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst; //? 지원
	ResultSet rs;
	int resultCount; //인서트, 업데이트, 딜리트 건수
	CallableStatement cst; //SP 지원
	
	//주문 취소
	public int deleteOrder(String customerid) {
		String sql = """
				delete from CUSTOMER_ORDER
				where CUSTOMER_ID = ?
				""";
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, customerid);
			resultCount =  pst.executeUpdate();
			
		} catch (SQLException e) {
			resultCount = -1;
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		System.out.println("취소된 주문 : " + resultCount + "건");
		return resultCount;
	}
	
	//주문 내역 조회
	public List<Customer_OrderVO> selectOrder(String customerid) {
		String sql = """
				select *
				from CUSTOMER_ORDER
				where CUSTOMER_ID = ?
				order by order_code 
				""";
		
		List<Customer_OrderVO> customeroderlist = new ArrayList<>();
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, customerid);
			rs = pst.executeQuery();
			while(rs.next()) {
				Customer_OrderVO customer = makeCustomerorder(rs);
				customeroderlist.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
	   
		return customeroderlist;
	}

	private Customer_OrderVO makeCustomerorder(ResultSet rs2) throws SQLException {
		Customer_OrderVO customer = new Customer_OrderVO();
		customer.setORDER_CODE(rs.getInt("ORDER_CODE"));
		customer.setCUSTOMER_ID(rs.getString("CUSTOMER_ID"));
		customer.setPRODUCT_CODE(rs.getInt("PRODUCT_CODE"));
		customer.setORDER_DAY(rs.getDate("ORDER_DAY"));
		customer.setARRIVAL_DATE(rs.getDate("ARRIVAL_DATE"));
		return customer;
	}

	//상품주문
	public int insertOrder(Customer_OrderVO order) {
		String sql = """
				insert into CUSTOMER_ORDER values(seq_order.nextval, ?, ?, sysdate,  
				(select sysdate + product_leadtime
				 from product
				 where product_code = ?))
				""";
		conn = OracleUtil.getConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, order.getCUSTOMER_ID());
			pst.setInt(2, order.getPRODUCT_CODE());
			pst.setInt(3, order.getPRODUCT_CODE());
			resultCount = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	   
		return resultCount;
	}
	
	
}
	

