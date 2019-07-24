function Util() {

}

Util.prototype.call = function(urlStr, reqData, csrf, successFunction) {
	
	console.log(urlStr, reqData, csrf);
	
	$.ajax({
		type : "POST",
		url : urlStr,
		beforeSend : function(xhr) {
			xhr.setRequestHeader("X-CSRF-TOKEN", csrf);
		},
		dataType : "json",
		data : JSON.stringify(reqData),
		contentType : "application/json",
		success : successFunction,
		error : function(jqXHR, e) {
			console.log(jqXHR);
			alert(jqXHR.responseJSON.message);
		}
	});
}