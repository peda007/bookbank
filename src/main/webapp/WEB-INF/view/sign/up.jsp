<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">Book Bank 회원가입</div>
			<div class="card-body">
				<form id="formRegist" method="post">
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-12">
								<div class="form-label-group">
									<input type="text" id="inputUsername" name="username" class="form-control" required="required" autofocus="autofocus">
									<label for="inputUsername">닉네임</label>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<div class="form-label-group">
									<input type="password" id="inputPassword" name="password" class="form-control" required="required">
									<label for="inputPassword">비밀번호</label>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-label-group">
									<input type="password" id="confirmPassword" name="newPassword" class="form-control" required="required">
									<label for="confirmPassword">비밀번호 확인</label>
								</div>
							</div>
						</div>
					</div>
					<a class="btn btn-primary btn-block" href="#" id="btnRegist">등록</a>
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="/signin">로그인 페이지</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>
		
	<script>
		$(document).ready(function() {
			
			$('input').on('keydown', function(event) {
				if(event.keyCode == 13) {
					$('#btnRegist').click();
				}
			});
			
			$('#btnRegist').on('click', function() {
				$('#formRegist').submit();
				
			});
			
			$('#formRegist').submit(function(event) {
				event.preventDefault();
			}).validate({
				rules: {
					username :{ required : true },
					password : { required: true },
					newPassword : { required: true, equalTo: '#inputPassword' }
				},
				messages : {
					username : { required : "닉네임을 입력하세요" },
					password : { required : "비밀번호를 입력하세요" },
					newPassword : { required : "비밀번호 확인을 입력하세요", equalTo: "비밀번호와 비밀번호 확인이 일치하지 않습니다." }
				},
				submitHandler: function(form) {
					var util = new Util();
					util.call('${apiUrlSignup}', $('#formRegist').serializeObject(), getCsrf(), function(res) {
						alert(res.message);
						if(res.success) {
							location.href='/signin';
						}
					});
				}
			});
		});
	</script>

</body>

</html>