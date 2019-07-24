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

				<h1 class="my-4">Index</h1>
				
				<div class="card mb-12">
					<div class="card-body">
						<h2 class="card-title">Search</h2>
						<p class="card-text">도서 검색</p>
					</div>
					
					<div class="card-body">
						<h2 class="card-title">My History</h2>
						<p class="card-text">내 검색 기록</p>
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
	<script src="/vendor/jquery/jquery.min.js"></script>
	<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
	<script>
		$(document).ready(function() {
			$('#btnSearch').on('click', function() {
				$('#formSearch').attr('action', '/book/list');
				$('#formSearch').submit();
			})
		});
	</script>

</body>

</html>
