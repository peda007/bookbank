<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

	<!-- metadata -->
	<%@include file="/WEB-INF/view/common/metadata.jsp" %>
	
	<!-- Custom fonts for this template-->
	<link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

	<!-- Custom styles for this template-->
	<link href="/css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">

	<div class="container">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">Book Bank 로그인</div>
			<div class="card-body">
				<form id="formLogin" method="post">
					<div class="form-group">
						<div class="form-label-group">
							<input type="text" id="inputUsername" name="username" class="form-control" autofocus="autofocus">
							<label for="inputUsername">닉네임</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-label-group">
							<input type="password" id="inputPassword" name="password" class="form-control">
							<label for="inputPassword">비밀번호</label>
						</div>
					</div>
					<a class="btn btn-primary btn-block" href="#" id="btnLogin">로그인하기</a>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="/signup">간편 회원가입</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="/vendor/jquery/jquery.min.js"></script>
	<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

	<script>
		$(document).ready(function() {
			if('${error}' != '') {
				alert('${error.message}');
			}
			
			$('input').on('keydown', function(event) {
				if(event.keyCode == 13) {
					$('#btnLogin').click();
				}
			});
			
			$('#btnLogin').on('click', function() {
				$('#formLogin').attr('action','${apiUrlSignin}');
				$('#formLogin').submit();
			});
		});
		
		
	</script>

</body>

</html>
