<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<!-- metadata -->
	<%@include file="/WEB-INF/view/common/metadata.jsp" %>
	
	<!-- Bootstrap core CSS -->
	<link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Custom styles for this template -->
	<link href="/css/blog-home.css" rel="stylesheet">
	
</head>

<body>

	<!-- header -->
	<%@include file="/WEB-INF/view/common/header.jsp" %>

	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<!-- Blog Entries Column -->
			<div class="col-md-12">

				<h1 class="my-4">Search Book
					<small>that you want to find</small>
				</h1>
				
				<!-- Search -->
				<div class="card mb-12">
					<div class="card-body">
						<form id="formSearch" method="get" action="/book/list">
							<input type="text" name="query" placeholder="찾으려는 책의 제목이나 키워드를 입력해 주세요." required/>
							<input type="hidden" name="page" value="1"/>
							<button id="btnSearch" type="submit">검색</button>
						</form>
					</div>
				</div>

				<!-- Hot keyword in realtime -->
				<div class="card mb-12">
					<div class="card-body">
						<h2 class="card-title">인기 검색 키워드</h2>
						<table>
							<thead>
								<tr>
									<th>랭킹</th>
									<th>검색 키워드</th>
									<th>검색 횟수</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${keywordList != null && fn:length(keywordList) > 0}">
										<c:forEach var="keyword" items="${keywordList}" varStatus="idx">
											<tr>
												<td class="center">#${idx.count}</td>
												<td class="center"><a href="/book/list?query=${keyword.keyword}&page=1">${keyword.keyword}</a></td>
												<td class="center">${keyword.cnt}</td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td class="center" colspan="3">인기 키워드가 없습니다.</td>
										</tr>
									</c:otherwise>
								</c:choose>
								
							</tbody>
						</table>
					</div>
					<div class="card-footer text-muted">
						<a href="#">인기 검색 키워드</a>
						는 ${curTime}을 기준으로 표시됐습니다.
						
					</div>
				</div>

			</div>

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

	<!-- footer -->
	<%@include file="/WEB-INF/view/common/footer.jsp" %>

	<!-- Bootstrap core JavaScript -->
	<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
	<script>
		$(document).ready(function() {
			
			$('input').on('keydown', function(event) {
				if(event.keyCode == 13) {
					$('#btnSearch').click();
				}
			});			
		});
	</script>

</body>

</html>
