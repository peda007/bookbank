package com.msh.bookbank.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msh.bookbank.config.ApiProperties;
import com.msh.bookbank.domain.constant.EApiType;
import com.msh.bookbank.domain.constant.ESearchType;
import com.msh.bookbank.domain.param.KakaoSearchParam;
import com.msh.bookbank.domain.param.NaverSearchParam;
import com.msh.bookbank.domain.param.SearchParam;

/**
 * HTTP 요청 유틸
 * @author moon
 * @since 2019.07.20.
 */
@Component
public class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	private static ApiProperties apiProps;
	
	@Autowired
	public HttpUtil(ApiProperties apiProps) {
		HttpUtil.apiProps = apiProps;
	}
	
	/**
	 * API 호출
	 * @param api
	 * @param param
	 * @param searchType
	 * @return
	 */
	public static String callApi(EApiType api, SearchParam param, ESearchType searchType) {
		HttpGet httpGet = null;
		switch(api) {
		case KAKAO:
			KakaoSearchParam kakaoParam = new KakaoSearchParam(param);
			httpGet = init(api.getApiUrl(kakaoParam.createParamUrl(), searchType));
			
			break;
		case NAVER:
			NaverSearchParam naverParam = new NaverSearchParam(param);
			httpGet = init(api.getApiUrl(naverParam.createParamUrl(), searchType));
			break;
		}
		api.initHeaders(httpGet);
		return call(httpGet);
	}
	
	/**
	 * HTTP 요청 초기화(URL 설정 및 로그 출력)
	 * @param strUrl
	 * @return
	 */
	private static HttpGet init(String strUrl) {
		logger.info(String.format("[HTTP Util Request GET URL] %s", strUrl));
		HttpGet httpGet = new HttpGet(strUrl);
		return httpGet;
	}

	/**
	 * 실질적으로 http 요청 하는 메서드
	 * @param http
	 * @return
	 */
	private static String call(HttpRequestBase http) {
		
		String strResponse = null;
		CloseableHttpResponse httpResponse = null;
		
		try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			// connection-timeout 적용
			int sec = apiProps.getConnectionTimeout();
			RequestConfig config = null;
			if(sec > 0) {
				config = RequestConfig.custom()
						.setConnectTimeout(sec * 1000)
						.setConnectionRequestTimeout(sec * 1000)
						.setSocketTimeout(sec * 1000).build();
			}

			http.setConfig(config);
			httpResponse = httpClient.execute(http);

			if(httpResponse != null) {
				int responseCode = httpResponse.getStatusLine().getStatusCode();
				logger.info(String.format("[HTTP Util Response Code] %d", responseCode));
				
				try(BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()))) {
					String inputLine;
					StringBuilder response = new StringBuilder();
					while ((inputLine = reader.readLine()) != null) {
						response.append(inputLine);
					}
					strResponse = response.toString();
					
					
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				if(!CommonUtil.isNullOrEmpty(strResponse)) {
					logger.info(String.format("[HTTP Util Response Body] %s", strResponse));
				}
				
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return strResponse;
	}
	
}
