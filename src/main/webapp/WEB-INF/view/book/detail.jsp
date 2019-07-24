<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

				<h1 class="my-4">상세 정보</h1>

				<!-- Blog Post -->
				<div class="card mb-12">
					<table id="tbl-detail">
						<colgroup>
							<col width="30%"/>
							<col width="35%"/>
							<col width="35%"/>
						</colgroup>
						<tbody>
							<tr>
								<td rowspan="5"><img src="${book.thumbnail}"></td>
								<td colspan="2"><p class="mini-title">제목</p>${book.title}</td>
							</tr>
							<tr>
								<td colspan="2"><p class="mini-title">ISBN</p>${book.isbn}</td>
							</tr>
							<tr>
								<td><p class="mini-title">저자</p>${book.authors}</td>
								<td><p class="mini-title">역자</p>${book.translators}</td>
							</tr>
							<tr>
								<td><p class="mini-title">출판사</p>${book.publisher}</td>
								<td><p class="mini-title">출판일</p>${book.publishDateStr}</td>
							</tr>
							<tr>
								<td><p class="mini-title">정가</p>${book.price}</td>
								<td><p class="mini-title">판매가</p>${book.discountedPrice}</td>
							</tr>
							<tr>
								<td colspan="3"><p class="mini-title">소개</p>${book.description}</td>
							</tr>
						</tbody>
					</table>
				</div>

			</div>

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

	<!-- footer -->
	<%@include file="/WEB-INF/view/common/footer.jsp" %>

	<!-- Bootstrap core JavaScript -->
	<script src="/vendor/jquery/jquery.min.js"></script>
	<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
