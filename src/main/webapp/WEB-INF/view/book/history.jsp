<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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

				<h1 class="my-4"><sec:authentication property="name"/>
					<small>: 검색 기록(최대 10개)</small>
				</h1>

				<!-- Blog Post -->
				<div class="card mb-12">
					<table>
						<thead>
							<tr>
								<th>검색어</th>
								<th>검색 날짜</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="history" items="${historyList}">
								<tr>
									<td>${history.keyword.keyword}</td>
									<td class="center">${history.datetime}</td>
								</tr>
							</c:forEach>		
						</tbody>
					</table>
					
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
			$('.page').on('click', function() {
				$('[name=page]').val($(this).data('page'));
				$('#formPage').submit();
			});
		});
	</script>
</body>

</html>
