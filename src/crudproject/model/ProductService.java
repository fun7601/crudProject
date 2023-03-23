package crudproject.model;

import java.util.List;

import crudproject.vo.ProductVO;

//서비스 : 업무 로직 담당 
public class ProductService {
	ProductDAO productdao = new ProductDAO();
	
	//전체 상품 조회
	public List<ProductVO> selectAll() {
		return productdao.selectAll();
	}
	
	//상품명 검색
	public List<ProductVO> selectByProductName(String productname) {
		return productdao.selectByProductName(productname);
	}
	
	//판매자 이름으로 상품 검색
	public List<ProductVO> selectBySeller(String sellername) {
		return productdao.selectBySeller(sellername);
	}
	
	//카테고리로 상품 검색
	public List<ProductVO> selectByCategoryname(String categoryname) {
		return productdao.selectByCategoryname(categoryname);
	}
	
}
