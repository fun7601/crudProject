package crudproject.model;

import crudproject.vo.CustomerVO;

//서비스 : 업무 로직 담당 
public class CustomerService {
	CustomerDAO customerdao = new CustomerDAO();
	
	//회원가입
	public String customerInsert(CustomerVO customer) {
		int result = customerdao.customerInsert(customer);
		return result > 0?"회원가입에 성공하였습니다.":"회원가입에 실패하였습니다.";
	}
		
	//회원탈퇴
	public String deleteID(String customerid) {
		int result = customerdao.deleteID(customerid);
		return result > 0?"회원 탈퇴하였습니다.":"존재하지 않는 아이디입니다.";
	}
	
	//회원 정보 수정
	public String customerUpdate(CustomerVO customer) {
		int result = customerdao.customerUpdate(customer);
		return result > 0?"회원정보 수정을 완료하였습니다.":"회원정보 수정을 실패하였습니다.";
	}
	
	//로그인
	public CustomerVO login(String customerID, String customerPassword) {
		return customerdao.login(customerID, customerPassword);
	}
			//List<CustomerVO> result = customerdao.login(customerID, customerPassword);
			//return result != null?"로그인에 성공하였습니다.":"로그인에 실패하였습니다.";
			//이거 하고 싶은데 일단 보류
					
			//int result = customerdao.login(customerID, customerPassword)
			//return result > 0?"회원가입에 성공하였습니다.":"회원가입에 실패하였습니다.";
		
	
	
	
	
	
	

}
