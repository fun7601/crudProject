package crudproject.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shinhan.dbutil.OracleUtil;

import crudproject.vo.CustomerVO;

//DAO(Data Access Object) : DB업무, CRUD 
public class CustomerDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst; //? 지원
	ResultSet rs;
	int resultCount; //인서트, 업데이트, 딜리트 건수
	CallableStatement cst; //SP 지원
	
	    //회원탈퇴
		public int deleteID(String customerid) {
			String sql = """
					delete from CUSTOMER
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
			return resultCount;
			
		}
	      	//회원 정보 수정
			public int customerUpdate(CustomerVO customer) {
				System.out.println("------------------------수정 회원정보------------------------");
				System.out.println(customer);
				String sql ="""
						update CUSTOMER 
						set CUSTOMER_PASSWORD = ?,
						 	CUSTOMER_NAME = ?,
						 	CUSTOMER_ADDRESS = ?,
						 	CUSTOMER_PHONENUMBER = ?,
						 	CUSTOMER_BIRTHDATE = ?
						where CUSTOMER_ID = ?
						""";
				conn = OracleUtil.getConnection();
				
				try {
					pst = conn.prepareStatement(sql);
					
					pst.setString(1, customer.getCUSTOMER_PASSWORD());
					pst.setString(2, customer.getCUSTOMER_NAME());
					pst.setString(3, customer.getCUSTOMER_ADDRESS());
					pst.setString(4, customer.getCUSTOMER_PHONENUMBER());
					pst.setDate(5, customer.getCUSTOMER_BIRTHDATE());
					pst.setString(6, customer.getCUSTOMER_ID());
					
					resultCount =  pst.executeUpdate(); 
				} catch (SQLException e) {
					resultCount = -1;
					e.printStackTrace();
				} finally {
					OracleUtil.dbDisconnect(null, pst, conn);
				}
				System.out.println("업데이트 결과 : " + resultCount);
				return resultCount;
			}
	
	//로그인
	public CustomerVO login(String customerID, String customerPassword) {
		String sql = """
				select * 
				from CUSTOMER
				where CUSTOMER_ID = ?
				and CUSTOMER_PASSWORD = ?
				""";
		
		CustomerVO customer = null;
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, customerID);
			pst.setString(2, customerPassword);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				customer = makeCustomer(rs);
				 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return customer;
	}
	


	private CustomerVO makeCustomer(ResultSet rs) throws SQLException {
		CustomerVO customer = new CustomerVO();
		customer.setCUSTOMER_ID(rs.getString("CUSTOMER_ID"));
		customer.setCUSTOMER_PASSWORD(rs.getString("CUSTOMER_PASSWORD"));
		customer.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
		customer.setCUSTOMER_ADDRESS(rs.getString("CUSTOMER_ADDRESS"));
		customer.setCUSTOMER_PHONENUMBER(rs.getString("CUSTOMER_PHONENUMBER"));
		customer.setCUSTOMER_BIRTHDATE(rs.getDate("CUSTOMER_BIRTHDATE"));
		
		return customer;
	}
	
	//회원가입
	public int customerInsert(CustomerVO customer) {
		
		String sql ="""
				insert into CUSTOMER values(?, ?, ?, ?, ?, ?)
				""";
		conn = OracleUtil.getConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, customer.getCUSTOMER_ID());
			pst.setString(2, customer.getCUSTOMER_PASSWORD());
			pst.setString(3, customer.getCUSTOMER_NAME());
			pst.setString(4, customer.getCUSTOMER_ADDRESS());
			pst.setString(5, customer.getCUSTOMER_PHONENUMBER());
			pst.setDate(6, customer.getCUSTOMER_BIRTHDATE());
			
			resultCount =  pst.executeUpdate();
		} catch (SQLException e) {
			resultCount = -1;
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		return resultCount;
	}
}
	

