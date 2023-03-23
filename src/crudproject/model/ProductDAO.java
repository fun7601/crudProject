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

import crudproject.vo.ProductVO;

//DAO(Data Access Object) : DB업무, CRUD 
public class ProductDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst; 
	ResultSet rs;
	int resultCount; //인서트, 업데이트, 딜리트 건수
	CallableStatement cst; //SP 지원
	
	
	//전체 상품 조회
	public List<ProductVO> selectAll() {
		String sql = """
				    select PRODUCT_CODE,
				     	   PRODUCT_NAME,
				     	   PRODUCT_PRICE,
				     	   PRODUCT_SELLER,
				     	   PRODUCT_STOCK, 
				     	   PRODUCT_LEADTIME,
				     	   PRODUCT_CATEGORY
					from PRODUCT
						""";
		List<ProductVO> productlist = new ArrayList<>();
		conn = OracleUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ProductVO product = makeProduct(rs);
				productlist.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		return productlist;
	}
	
	//상품명으로 조회
	public List<ProductVO> selectByProductName(String productname) {
		String sql = "select * from PRODUCT where PRODUCT_NAME like '%" + productname + "%'";
		List<ProductVO> productlist = new ArrayList<>();
		conn = OracleUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ProductVO product = makeProduct(rs);
				productlist.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		return productlist;
	}
	
	//판매자로 상품 검색
	public List<ProductVO> selectBySeller(String sellername) {
		String sql = "select * from PRODUCT where PRODUCT_SELLER like '%" + sellername + "%'";
		List<ProductVO> productlist = new ArrayList<>();
		conn = OracleUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ProductVO product = makeProduct(rs);
				productlist.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		return productlist;
	}
	
	//카테고리로 상품 검색
		public List<ProductVO> selectByCategoryname(String categoryname) {
			String sql = "select * from PRODUCT where PRODUCT_CATEGORY like '%" + categoryname + "%'";
			List<ProductVO> productlist = new ArrayList<>();
			conn = OracleUtil.getConnection();
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while(rs.next()) {
					ProductVO product = makeProduct(rs);
					productlist.add(product);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				OracleUtil.dbDisconnect(rs, st, conn);
			}
			return productlist;
		}
	
	
	private ProductVO makeProduct(ResultSet rs) throws SQLException {
		ProductVO product = new ProductVO();
		product.setPRODUCT_CODE(rs.getInt("PRODUCT_CODE"));
		product.setPRODUCT_NAME(rs.getString("PRODUCT_NAME"));
		product.setPRODUCT_PRICE(rs.getInt("PRODUCT_PRICE"));
		product.setPRODUCT_SELLER(rs.getString("PRODUCT_SELLER"));
		product.setPRODUCT_STOCK(rs.getInt("PRODUCT_STOCK"));
		product.setPRODUCT_LEADTIME(rs.getInt("PRODUCT_LEADTIME"));
		product.setPRODUCT_CATEGORY(rs.getString("PRODUCT_CATEGORY"));
		
		return product;
	}

}