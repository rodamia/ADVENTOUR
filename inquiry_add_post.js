/*mpg_index.js*/

$(document).ready(function() {
//	헤더랑 푸터 담은 mpg_nvbox.html 로드
    $("#mpg_nvbox").load("mpg_nvbox.html");
});


//문의하기
function addPost(){
	
//	ajax로 디비 저장
	$.ajax({
		url: 'InquiryC',
		type: 'POST',
		dataType: 'text',
		data: $('form[name=myForm]').serialize(),
		success: function(data) {
			console.log(data);
			
			if(data.indexOf('sucess') > -1){//member_pjs.jsp의 out.println("")중에 sucess가 있다면
				alert("회원가입이 완료되었습니다.");
				window.location.href = '/adventour/mem/m_login_form.jsp';
			}else{
				alert("일시적인 사유로 회원가입이 실패하였습니다.");
			}
		},
		error: function(error) {
			console.error('Request error:', error);
		}
	});
	
}



