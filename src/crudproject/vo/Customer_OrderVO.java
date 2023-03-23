package crudproject.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Customer_OrderVO {
	
	private int ORDER_CODE;
	private String CUSTOMER_ID;
	private int PRODUCT_CODE;
	private Date ORDER_DAY;
	private Date ARRIVAL_DATE;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("주문코드 : ").append(ORDER_CODE)
				.append("     ID : ").append(CUSTOMER_ID)
				.append("     상품코드 : ").append(PRODUCT_CODE)
				.append("\n")
				.append("\n")
				.append("주문일자 : ").append(ORDER_DAY)
				.append("          주문도착예정일 : ").append(ARRIVAL_DATE)
				.append("\n")
				.append("----------------------------------------------------------------");
		return builder.toString();
	}
	
}