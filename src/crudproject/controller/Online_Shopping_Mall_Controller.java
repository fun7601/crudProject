package crudproject.controller;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import crudproject.model.CustomerService;
import crudproject.model.Customer_orderService;
import crudproject.model.ProductService;
import crudproject.view.Online_Shopping_Mall_View;
import crudproject.vo.CustomerVO;
import crudproject.vo.Customer_OrderVO;
import crudproject.vo.ProductVO;

public class Online_Shopping_Mall_Controller {

	static CustomerVO customer;
	
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		ProductService productservice = new ProductService();
		CustomerService customerservice = new CustomerService();
		Customer_orderService customerorderservice = new Customer_orderService();
		
		while(true) {
			System.out.println("-------------------------------온라인 장보기-----------------------------");
			System.out.println("| 1. 회원가입 | 2. 로그인 | 3. 상품검색 | 4. 상품주문 | 5. 마이페이지 | 6. 종료 |");
			System.out.println("----------------------------------------------------------------------");
			
			System.out.print("원하시는 작업을 선택하세요 >>");
			String job = sc.next();
			if(job.equals("6")) break;
			switch (job) {
			case "1": {
				System.out.print("사용할 ID를 입력하세요 >>");
				String ID = sc.next();
				
				System.out.print("사용할 Password를 입력하세요 >>");
				String Password = sc.next();
				
				System.out.print("이름을 입력하세요 >>");
				String Name = sc.next();
				sc.nextLine();
				System.out.print("주소를 입력하세요 >>");
				String Address = sc.nextLine();
				
				System.out.print("전화번호를 입력하세요 >>");
				String Phonenumber = sc.next();
				
				System.out.print("생년월일을 입력하세요 >>");
				String BirthDate = sc.next();
				
				Date BirthDatetype = DateUtil.convertToDate(BirthDate);
				
				CustomerVO customer = new CustomerVO();
				customer.setCUSTOMER_ID(ID);
				customer.setCUSTOMER_PASSWORD(Password);
				customer.setCUSTOMER_NAME(Name);
				customer.setCUSTOMER_ADDRESS(Address);
				customer.setCUSTOMER_PHONENUMBER(Phonenumber);
				customer.setCUSTOMER_BIRTHDATE(BirthDatetype);
				
				Online_Shopping_Mall_View.joinprint(customerservice.customerInsert(customer));
				break;
			}
			case "2": {
				System.out.print("ID를 입력하세요 >>");
				String ID = sc.next();
				
				System.out.print("Password를 입력하세요 >>");
				String Password = sc.next();
				customer =  customerservice.login(ID, Password);
				Online_Shopping_Mall_View.customerprint(customer);
				break;
			}
			
			case "3": {
				System.out.println("-----------------------------------상품 검색----------------------------------");
				System.out.println("| 1. 전체 상품 조회 | 2. 상품명 검색 | 3.판매자 검색 | 4. 카테고리별 조회 | 5. 뒤로가기 |");
				System.out.println("----------------------------------------------------------------------------");
				System.out.print("원하시는 작업을 선택하세요 >>");
				String job1 = sc.next();
				if(job1.equals("1")) {
					Online_Shopping_Mall_View.print(productservice.selectAll());
				} if(job1.equals("2")) {
					System.out.print("상품명을 입력하세요 >>");
					String productname = sc.next();
					
					List<ProductVO> productnamelist = productservice.selectByProductName(productname);
					if(productnamelist.size()==0)
						Online_Shopping_Mall_View.print("상품이 존재하지 않습니다.");
					else
						Online_Shopping_Mall_View.print(productnamelist);
	
				} if(job1.equals("3")) {
					System.out.print("판매자를 입력하세요 >>");
					String sellername = sc.next();
					
					List<ProductVO> sellernamelist = productservice.selectBySeller(sellername);
					if(sellernamelist.size()==0)
						Online_Shopping_Mall_View.print("판매자가 존재하지 않습니다.");
					else
						Online_Shopping_Mall_View.print(sellernamelist);
				} if(job1.equals("4")) {
					System.out.print("카테고리를 입력하세요 >>");
					String categoryname = sc.next();
					List<ProductVO> categorynamelist = productservice.selectByCategoryname(categoryname);
					if(categorynamelist.size()==0)
						Online_Shopping_Mall_View.print("카테고리가 존재하지 않습니다.");
					else
						Online_Shopping_Mall_View.print(categorynamelist);
					
				} if(job1.equals("5")) {
					break;
				} 
				break;
			}
			case "4": {
				if(customer == null) {
					System.out.println("[알림] 로그인 후에 사용할 수 있습니다."); 
					break;
				}
					
				Online_Shopping_Mall_View.print(productservice.selectAll());
				System.out.print("상품코드를 선택하세요 >>");
				int product_code = sc.nextInt();
				Customer_OrderVO order = new Customer_OrderVO();
				order.setCUSTOMER_ID(customer.getCUSTOMER_ID());
				order.setPRODUCT_CODE(product_code);
				String result = customerorderservice.insertOrder(order);
				Online_Shopping_Mall_View.print(result);
				break;
			}
			case "5": {
				if(customer == null) {
					System.out.println("[알림] 로그인 후에 사용할 수 있습니다.");
					break;
				}
				
				System.out.println("----------------------------------마이페이지------------------------");
				System.out.println("| 1. 개인정보수정 | 2. 주문내역 | 3. 주문취소 | 4. 회원탈퇴  | 5. 뒤로가기 |");
				System.out.println("------------------------------------------------------------------");
				System.out.print("원하시는 작업을 선택하세요 >>");
				String job1 = sc.next();
				if(job1.equals("1")) {
					System.out.print("ID를 입력해주세요 >>");
					String customerid = sc.next();
					
					CustomerVO customer = makeCustomer2(sc, customerid);
					Online_Shopping_Mall_View.print(customerservice.customerUpdate(customer));
					break;
					
				} if(job1.equals("2")) {
					List<Customer_OrderVO> orderlist = customerorderservice.selectOrder(customer.getCUSTOMER_ID());
					Online_Shopping_Mall_View.orderprint(orderlist);
					break;
		
				} if(job1.equals("3")) {
					System.out.println("주문을 정말 취소하시겠습니까? (Y/N)");
					String yn = sc.next();
					if(yn.equals("Y"))
						Online_Shopping_Mall_View.print(customerorderservice.deleteOrder(customer.getCUSTOMER_ID()));
					break;
					
				}  if(job1.equals("4")) {
					System.out.print("회원탈퇴할 ID를 입력하세요 >>");
					String customerid = sc.next();
					System.out.println("정말 탈퇴하시겠습니까? (Y/N)");
					String yn = sc.next();
					if(yn.equals("Y"))
						Online_Shopping_Mall_View.print(customerservice.deleteID(customerid));
					break;
					
				} if(job1.equals("5")) {
					break;
					
				} break;
			}
			default:
				break;
			}
		}
		System.out.println("[알림] 온라인 장보기 프로그램이 종료되었습니다.");
	}


	private static CustomerVO makeCustomer2(Scanner sc, String customerid) {
		System.out.print("수정할 Password를 입력하세요 >>");
		String password = sc.next();
		
		System.out.print("수정할 이름을 입력하세요 >>");
		String name = sc.next();
		
		sc.nextLine();
		System.out.print("수정할 주소를 입력하세요 >>");
		String adrress = sc.nextLine();
		
		System.out.print("수정할 전화번호를 입력하세요 >>");
		String phonenumber = sc.next();
		
		System.out.print("수정할 생년월일을 입력하세요 >>");
		String birthdate = sc.next();
	
		Date newdate = DateUtil.convertToDate(birthdate);
		
		CustomerVO customer = new CustomerVO();
		customer.setCUSTOMER_PASSWORD(password);
		customer.setCUSTOMER_NAME(name);
		customer.setCUSTOMER_ADDRESS(adrress);
		customer.setCUSTOMER_PHONENUMBER(phonenumber);
		customer.setCUSTOMER_BIRTHDATE(newdate);
		customer.setCUSTOMER_ID(customerid);
		return customer;
	}
}
