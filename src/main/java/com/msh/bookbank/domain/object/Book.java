package com.msh.bookbank.domain.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 도서 object
 * 서로 다른 API를 통해 가져온 책의 attribute가 다르기 때문에,
 * 통일 하고자 만든 object
 * @author moon
 * @since 2019.07.22.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	private String isbn;
	private String isbnSplit;
	private String title;
	private String thumbnail;
	
	private String authors;
	private String translators;
	
	private String publisher;
	private String publishDateStr;
	
	private String price;
	private String discountedPrice;
	
	private String description;
	
}
