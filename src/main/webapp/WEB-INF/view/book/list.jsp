<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="add" uri="tlds/Adder.tld" %>
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

				<h1 class="my-4"><c:out value="${param.query}"/>
					<small>: 검색 결과</small>
				</h1>

				<!-- Blog Post -->
				<div class="card mb-12">
					<div class="card-body">
						<table id="list">
							<colgroup>
								<col width="12%"/>
								<col width="38%"/>
								<col width="15%"/>
								<col width="10%"/>
								<col width="10%"/>
								<col width="15%"/>
							</colgroup>
							<thead>
								<tr>
									<th>이미지</th>
									<th>제목</th>
									<th>저자</th>
									<th>정가</th>
									<th>출판사</th>
									<th>출판일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="book" items="${bookList}">
									<tr data-isbn="${book.isbnSplit}">
										<td><img src="${book.thumbnail}"></td>
										<td>${book.title}</td>
										<td class="center">${book.authors}</td>
										<td class="right">${book.price}</td>
										<td class="center">${book.publisher}</td>
										<td class="center">${book.publishDateStr}</td>
									</tr>
								</c:forEach>		
							</tbody>
						</table>
					</div>
					
					<div class="card-body">
						<div id="page-area">
							<ul>
								<c:if test="${pageInfo.hasPrev}">
									<li class="page" data-page="${pageInfo.prev}">&lt;</li>
								</c:if>
								<c:forEach var="page" items="${pageList}">
									<li class="page ${add:ifSame(param.page, page,'active')}" data-page="${page}">${page}</li>
								</c:forEach>
								<c:if test="${pageInfo.hasNext}">
									<li class="page" data-page="${pageInfo.next}">&gt;</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>

			</div>

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

	<form id="formPage" action="/book/list">
		<input type="hidden" name="query" value="${param.query}"/>
		<input type="hidden" name="page"/>
	</form>

	<!-- footer -->
	<%@include file="/WEB-INF/view/common/footer.jsp" %>

	<!-- Bootstrap core JavaScript -->
	<script src="/vendor/jquery/jquery.min.js"></script>
	<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<script>
		$(document).ready(function() {
			$('table#list tr td').on('click', function() {
				location.href = '/book/detail?isbn=' + $(this).parent().data('isbn');
			});
			
			$('.page').on('click', function() {
				$('[name=page]').val($(this).data('page'));
				$('#formPage').submit();
			});
		});
	</script>
</body>

</html>
