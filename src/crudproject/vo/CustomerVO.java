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

public class CustomerVO {
	
	 private String CUSTOMER_ID;
	 private String CUSTOMER_PASSWORD;
	 private String CUSTOMER_NAME;
	 private String CUSTOMER_ADDRESS;
	 private String CUSTOMER_PHONENUMBER;
	 private Date CUSTOMER_BIRTHDATE;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("아이디 : ").append(CUSTOMER_ID)
				.append("   비밀번호 : ").append(CUSTOMER_PASSWORD)
				.append("   이름 : ").append(CUSTOMER_NAME)
				.append("\n")
				.append("주소 : ").append(CUSTOMER_ADDRESS)
				.append("   전화번호 : ").append(CUSTOMER_PHONENUMBER)
				.append("   생년월일 : ").append(CUSTOMER_BIRTHDATE);
		return builder.toString();
	}
	 
}