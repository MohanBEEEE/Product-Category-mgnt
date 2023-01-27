package com.tyss.product_category.jwtdto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable{

	private static final long serialVersionUID = -3201708095546207325L;
	
	private String userName;
	private String password;
}
