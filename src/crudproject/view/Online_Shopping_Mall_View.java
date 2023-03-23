package crudproject.view;

import java.util.List;

import crudproject.vo.CustomerVO;
import crudproject.vo.Customer_OrderVO;
import crudproject.vo.ProductVO;

public class Online_Shopping_Mall_View {
	public static void deleteprint(CustomerVO customerid) {
		System.out.println("-----------------------------회원탈퇴-----------------------------");
		System.out.println(customerid);
	}
	
	public static void orderprint(Customer_OrderVO order) {
		System.out.println("-----------------------------주문 내역-----------------------------");

		System.out.println(order);

	}
	
	public static void updateprint(CustomerVO customer) {
		System.out.println("---------------------------------수정된 정보---------------------------------");

		System.out.println(customer);

	}
	
	public static void joinprint(CustomerVO customer) {
		System.out.println("-----------------------------회원가입정보-----------------------------");

		System.out.println(customer);

	}
	
	public static void customerprint(CustomerVO customer) {
		System.out.println("-----------------------------로그인정보--------------------------------");

		System.out.println(customer);

	}

	public static void print(List<ProductVO> productlist) {
		System.out.println("-----------------------------상품조회-----------------------------");
		for (ProductVO product : productlist) {
			System.out.println(product);
		}
	}

	public static void print(ProductVO product) {
		System.out.println("----------------------------------------------------------------");

		System.out.println(product);

	}

	public static void print(String massage) {
		System.out.println("[알림] " + massage);

	}
	
	public static void joinprint(String massage) {
		System.out.println("[알림] " + massage);
	}

	public static void orderprint(List<Customer_OrderVO> orderlist) {
		System.out.println("--------------------------------주문목록--------------------------------");
		for (Customer_OrderVO order : orderlist) {
			System.out.println(order);
		}
	}

}
