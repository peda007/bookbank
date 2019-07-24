<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="add" uri="tlds/Adder.tld" %>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="/">Book Bank</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item ${add:ifSame(topMenu,'search','active')}">
					<a class="nav-link" href="/book/search">Search
						<span class="sr-only">(current)</span>
					</a>
				</li>
				<li class="nav-item ${add:ifSame(topMenu,'history','active')}">
					<a class="nav-link" href="/book/history">My History</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#" id="btnLogout">Logout</a>
				</li>
			</ul>
		</div>
	</div>
</nav>
<form role="form" id="formLogout" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<script>
	$(document).ready(function() {
		$('#btnLogout').on('click', function() {
			var wantLogout = confirm('정말 로그아웃 하시겠습니까?');
			if(wantLogout) {
				$('#formLogout').attr('action','/signout');
				$('#formLogout').submit();
			}
		})
	});
</script>