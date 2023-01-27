package com.tyss.product_category.jwtdto;

import java.io.Serializable;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse implements Serializable{
	 
	private static final long serialVersionUID = -3158158251760908093L;

	private String jwtToken;
}
