package com.msh.bookbank.domain.object;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Restful API 호출 시 return 되는 object
 * @author moon
 * @since 2019.07.22.
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ApiResponse {

	private boolean success;
	private String message;
	private Object data;
	
}
