package crudproject.model;

import java.util.List;

import crudproject.vo.Customer_OrderVO;

//서비스 : 업무 로직 담당 
public class Customer_orderService {
	Customer_orderDAO customerorderdao = new Customer_orderDAO();
	
	//상품 주문
	public String insertOrder(Customer_OrderVO order) {
	return customerorderdao.insertOrder(order)>0?"주문에 성공하였습니다.":"존재하지 않는 상품코드입니다.";
	}
	
	//주문취소
	public String deleteOrder(String customerid) {
		int result = customerorderdao.deleteOrder(customerid);
		return result > 0?"주문 취소하였습니다.":"존재하지 않는 아이디입니다.";
	}
	
    //주문 내역 조회
	public List<Customer_OrderVO> selectOrder(String customerid) {
		return customerorderdao.selectOrder(customerid);
	}
	//public String selectOrder(Customer_OrderVO order) {
		//int result = customerorderdao.selectOrder(order);
		//return result>0?"주문 내역 조회에 성공하였습니다.":"주문 내역 조회에 실패했습니다.";
		//return customerdao.selectOrder(order)>0?"주문 내역 조회에 성공하였습니다.":"주문 내역 조회에 실패했습니다.";
	//}
	

}
