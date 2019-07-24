package com.msh.bookbank.util;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * xml 파싱 유틸
 * @author moon
 * @since 2019.07.23.
 */
public class XmlParseUtil {

	/**
	 * String으로 입력 받은 xml을 Document 형태로 반환
	 * @param xml
	 * @return
	 */
	public static Document parseXml(String xml) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		InputSource is = new InputSource(new StringReader(xml));
		DocumentBuilder documentBuilder;
		Document document = null;
		
		try {
			documentBuilder = factory.newDocumentBuilder();
			document = documentBuilder.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return document;
	}
	
}
