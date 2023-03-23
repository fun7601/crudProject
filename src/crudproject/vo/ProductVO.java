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

public class ProductVO {
	
	private int PRODUCT_CODE;
	private String PRODUCT_NAME;
	private int PRODUCT_PRICE;
	private String PRODUCT_SELLER;
	private int PRODUCT_STOCK;
	private int PRODUCT_LEADTIME;
	private String PRODUCT_CATEGORY;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("   상품코드 : ").append(PRODUCT_CODE)
				.append("       상품명 : ").append(PRODUCT_NAME)
				.append("      가격 : ").append(PRODUCT_PRICE)
				.append("\n")
				.append("   판매자 : ").append(PRODUCT_SELLER)
				.append("    상품수량 : ").append(PRODUCT_STOCK)
				.append("   	   배송소요기간 : ").append(PRODUCT_LEADTIME).append("일    ")
				.append("\n")
				.append("----------------------------------------------------------------") ;
		return builder.toString();
	}
	
}