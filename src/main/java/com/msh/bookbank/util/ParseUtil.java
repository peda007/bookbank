package com.msh.bookbank.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.msh.bookbank.domain.constant.EApiType;
import com.msh.bookbank.domain.constant.EBookAttr;
import com.msh.bookbank.domain.object.Book;

/**
 * 파싱 유틸
 * @author moon
 * @since 2019.07.23.
 */
public class ParseUtil {
	
	/**
	 * 도서 리스트로 변환
	 * @param json
	 * @param apiType
	 * @return
	 */
	public static List<Book> toBookList(JSONObject json, EApiType apiType) {
		
		List<Book> bookList = new ArrayList<>();
		JSONArray bookArr;
		
		switch(apiType) {
		case KAKAO:
			bookArr = json.getJSONArray("documents");
			for(int i=0; i<bookArr.length(); i++) {
				JSONObject bookInfo = bookArr.getJSONObject(i);
				
				// 책 리스트에 담기
				bookList.add(Book.builder()
									.isbn(EBookAttr.ISBN.withFormat(bookInfo.getString("isbn"), apiType))
									.isbnSplit(EBookAttr.ISBN_SPLIT.withFormat(bookInfo.getString("isbn"), apiType))
									.thumbnail(EBookAttr.THUMBNAIL.withFormat(bookInfo.getString("thumbnail"), apiType))
									.title(EBookAttr.TITLE.withFormat(bookInfo.getString("title"), apiType))
									
									.authors(EBookAttr.AUTHORS.withFormat(bookInfo.getJSONArray("authors"), apiType))
									
									.price(EBookAttr.PRICE.withFormat(bookInfo.getInt("price"), apiType))
									
									.publisher(EBookAttr.PUBLISHER.withFormat(bookInfo.getString("publisher"), apiType))
									.publishDateStr(EBookAttr.PUBLISH_DATE.withFormat(bookInfo.getString("datetime"), apiType))
									
									.build());
			}
			break;
		case NAVER:
			bookArr = json.getJSONArray("items");
			for(int i=0; i<bookArr.length(); i++) {
				JSONObject bookInfo = bookArr.getJSONObject(i);
				
				// 책 리스트에 담기
				bookList.add(Book.builder()
									.isbn(EBookAttr.ISBN.withFormat(bookInfo.getString("isbn"), apiType))
									.isbnSplit(EBookAttr.ISBN_SPLIT.withFormat(bookInfo.getString("isbn"), apiType))
									.thumbnail(EBookAttr.THUMBNAIL.withFormat(bookInfo.getString("image"), apiType))
									.title(EBookAttr.TITLE.withFormat(bookInfo.getString("title"), apiType))
									
									.authors(EBookAttr.AUTHORS.withFormat(bookInfo.getString("author"), apiType))
									
									.price(EBookAttr.PRICE.withFormat(bookInfo.getInt("price"), apiType))
									
									.publisher(EBookAttr.PUBLISHER.withFormat(bookInfo.getString("publisher"), apiType))
									.publishDateStr(EBookAttr.PUBLISH_DATE.withFormat(bookInfo.getString("pubdate"), apiType))
									
									.build());
			}
			break;
		}
		
		return bookList;
	}
	
	/**
	 * 카카오 전용 도서 상세 결과
	 * @param json
	 * @return
	 */
	public static Book toBook(JSONObject json) {
		
		JSONObject bookInfo = json.getJSONArray("documents").getJSONObject(0);
		
		EApiType apiType = EApiType.KAKAO;
		Book book = Book.builder()
				.isbn(EBookAttr.ISBN.withFormat(bookInfo.getString("isbn"), apiType))
				.thumbnail(EBookAttr.THUMBNAIL.withFormat(bookInfo.getString("thumbnail"), apiType))
				.title(EBookAttr.TITLE.withFormat(bookInfo.getString("title"), apiType))
				
				.authors(EBookAttr.AUTHORS.withFormat(bookInfo.getJSONArray("authors"), apiType))
				.translators(EBookAttr.TRANSLATORS.withFormat(bookInfo.getJSONArray("translators"), apiType))
				
				.price(EBookAttr.PRICE.withFormat(bookInfo.getInt("price"), apiType))
				.discountedPrice(EBookAttr.DISCOUNTED_PRICE.withFormat(bookInfo.getInt("sale_price"), apiType))
				
				.publisher(EBookAttr.PUBLISHER.withFormat(bookInfo.getString("publisher"), apiType))
				.publishDateStr(EBookAttr.PUBLISH_DATE.withFormat(bookInfo.getString("datetime"), apiType))
				
				.description(EBookAttr.DESCRIPTION.withFormat(bookInfo.getString("contents"), apiType))
				.build();
			
		return book;
	}

	/**
	 * 네이버 전용 도서 상세 결과
	 * @param responseStr
	 * @return
	 */
	public static Book toBook(String responseStr) {
		Document document = XmlParseUtil.parseXml(responseStr);
		NodeList nodeList = document.getElementsByTagName("item").item(0).getChildNodes();
		
		EApiType apiType = EApiType.NAVER;
		Book book = new Book();
		for(int i=0; i<nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			switch(node.getNodeName()) {
			case "isbn":
				book.setIsbn(EBookAttr.ISBN.withFormat(node.getTextContent(), apiType));
				break;
			case "title":
				book.setTitle(EBookAttr.TITLE.withFormat(node.getTextContent(), apiType));
				break;
			case "image":
				book.setThumbnail(EBookAttr.THUMBNAIL.withFormat(node.getTextContent(), apiType));
				break;
			case "author":
				book.setAuthors(EBookAttr.AUTHORS.withFormat(node.getTextContent(), apiType));
				break;
			case "publisher":
				book.setPublisher(EBookAttr.PUBLISHER.withFormat(node.getTextContent(), apiType));
				break;
			case "pubdate":
				book.setPublishDateStr(EBookAttr.PUBLISH_DATE.withFormat(node.getTextContent(), apiType));
				break;
			case "price":
				Integer price = 0;
				try {
					price = Integer.parseInt(node.getTextContent());
				} catch(Exception e) {}
				book.setPrice(EBookAttr.PRICE.withFormat(price, apiType));
				break;
			case "discount":
				Integer discountedPrice = 0;
				try {
					discountedPrice = Integer.parseInt(node.getTextContent());
				} catch(Exception e) {}
				book.setDiscountedPrice(EBookAttr.DISCOUNTED_PRICE.withFormat(discountedPrice, apiType));
				break;
			case "description":
				book.setDescription(EBookAttr.DESCRIPTION.withFormat(node.getTextContent(), apiType));
				break;
			}
		}
		return book;
	}

	
}
