package com.msh.bookbank.controller.view.book;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.msh.bookbank.domain.constant.EApiType;
import com.msh.bookbank.domain.constant.ESearchType;
import com.msh.bookbank.domain.exception.NoViewValueException;
import com.msh.bookbank.domain.exception.PageIsMoreThanMaxException;
import com.msh.bookbank.domain.exception.UnknownException;
import com.msh.bookbank.domain.model.KeywordVO;
import com.msh.bookbank.domain.model.SearchHistoryVO;
import com.msh.bookbank.domain.object.Book;
import com.msh.bookbank.domain.param.SearchParam;
import com.msh.bookbank.service.KeywordService;
import com.msh.bookbank.service.SearchHistoryService;
import com.msh.bookbank.util.CommonUtil;
import com.msh.bookbank.util.ParseUtil;
import com.msh.bookbank.util.HttpUtil;
import com.msh.bookbank.util.PageUtil;

/**
 * Book 관련 뷰 컨트롤러
 * @author moon
 * @since 2019.07.21.
 */
@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private SearchHistoryService historyService;
	
	@Autowired
	private KeywordService keywordService;
	
	/**
	 * Book Bank의 홈 화면
	 * 검색 기능과 인기 키워드 랭킹이 표시됨
	 * @param model
	 * @return
	 */
	@GetMapping("/search")
	public String home(Model model) {
		
		// 인기 키워드
		List<KeywordVO> keywordList = keywordService.getHotKeywordList();
		model.addAttribute("keywordList", keywordList);
		
		// 인기 키워드 기준(시간)
		LocalDateTime date = LocalDateTime.now();
		model.addAttribute("curTime", date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")));
		
		// 탑 메뉴
		model.addAttribute("topMenu", "search");
		
		return "/book/search";
	}
	
	/**
	 * 검색 리스트 화면
	 * 사용자가 검색한 데이터(query)는 DB에 저장되어 랭킹 및 나의 검색 기록에 반영됨
	 * @param query
	 * @param page
	 * @param principal
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String list(@RequestParam String query, @RequestParam(defaultValue="1") int page, Principal principal, Model model) {
		// query 미 입력시
		if(CommonUtil.isNullOrEmpty(query)) {
			throw new NoViewValueException("검색어를 입력하지 않았습니다.");
		}
		
		// history 저장
		historyService.saveHistory(query, principal.getName());
		
		// 검색 조건 초기화
		SearchParam searchParam = SearchParam.builder()
												.query(query)
												.page(page)
												.build();
		
		//*
		List<Book> bookList = null;
		int totalCount = 0;
		for(EApiType api : EApiType.values()) {
			try {
				String responseStr = HttpUtil.callApi(api, searchParam, ESearchType.LIST);
				if(CommonUtil.isNullOrEmpty(responseStr)) {
					continue;
				}
				
				JSONObject json = new JSONObject(responseStr);
				if(json.has(api.getErrorKey()) || json.has(api.getErrorKey())) {
					throw new PageIsMoreThanMaxException("제공하는 페이지의 최대 한도를 초과했습니다.");
				} else {
					bookList = ParseUtil.toBookList(json, api);
					totalCount = api.getTotalCount(json);
					if(!CommonUtil.isNullOrEmpty(bookList)) {
						break;
					}
				}
			} catch(PageIsMoreThanMaxException e) {
				throw e;
			} catch(Exception e) {
				throw new UnknownException("API 요청 과정에서 알 수 없는 에러가 발생했습니다.");
			}
		}
		//*/
		/*
		String responseStr = HttpUtil.callKakaoApi(searchParam, ESearchType.LIST);
		JSONObject json = new JSONObject(responseStr);
		int totalCount = 0;
		List<Book> bookList = null;
		if(CommonUtil.isNullOrEmpty(responseStr)) {
			json = new JSONObject(responseStr);
			if(json.has("errorType") && json.getString("message").equals("page is more than max")) {
				//errorMessage/Invalid start value
			}
			bookList = ParseUtil.toBookList(json, EApiType.KAKAO);
			totalCount = json.getJSONObject("meta").getInt("pageable_count");
		} else {
			responseStr = HttpUtil.callNaverApi(searchParam, ESearchType.LIST);
			json = new JSONObject(responseStr);
			bookList = ParseUtil.toBookList(json, EApiType.NAVER);
			totalCount = json.getInt("total");
		}//*/
		
		model.addAttribute("bookList", bookList);
		model.addAttribute("pageList", PageUtil.getPageList(page, totalCount));
		model.addAttribute("pageInfo", PageUtil.getPageParam(page, totalCount));
		
		return "/book/list";
	}
	
	/**
	 * 도서 상세 정보
	 * @param isbn
	 * @param model
	 * @return
	 */
	@GetMapping("/detail")
	public String detail(@RequestParam String isbn, Model model) {
		
		SearchParam searchParam = SearchParam.builder()
												.isbn(isbn)
												.build();
		
		Book book = null;
		for(EApiType api : EApiType.values()) {
			try {
				String responseStr = HttpUtil.callApi(api, searchParam, ESearchType.DETAIL);
				if(CommonUtil.isNullOrEmpty(responseStr)) {
					continue;
				}
				
				if(responseStr.startsWith("{")) {
					JSONObject json = new JSONObject(responseStr);
					book = ParseUtil.toBook(json);
				} else {
					book = ParseUtil.toBook(responseStr);
				}
				if(!CommonUtil.isNullOrEmpty(book)) {
					break;
				}
			} catch(Exception e) {
				e.printStackTrace();
				throw new UnknownException("API 요청 과정에서 알 수 없는 에러가 발생했습니다.");
			}
		}
		
		/*
		Book book = null;
		String responseStr = HttpUtil.callKakaoApi(searchParam, ESearchType.DETAIL);
		if(CommonUtil.isNullOrEmpty(responseStr)) {
			JSONObject json = new JSONObject(responseStr);
			book = ParseUtil.toBook(json);
		} else {
			responseStr = HttpUtil.callNaverApi(searchParam, ESearchType.DETAIL);
			book = ParseUtil.toBook(responseStr);
		}//*/
		
		model.addAttribute("book", book);
		
		return "/book/detail";
	}
	
	/**
	 * 내 검색 기록
	 * @param principal
	 * @param model
	 * @return
	 */
	@GetMapping("/history")
	public String history(Principal principal, Model model) {
		
		List<SearchHistoryVO> historyList = historyService.getMyHistoryList(principal.getName());
		model.addAttribute("historyList", historyList);
		model.addAttribute("topMenu", "history");
		
		return "/book/history";
	}
}
