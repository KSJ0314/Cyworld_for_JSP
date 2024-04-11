function pwCheck() {
	var form = document.frm
	
    var id = form.id.value;
    var pw = form.pw.value;
    var pw2 = form.pw2.value;

	if (!id){
		alert("아이디를 입력해주세요.");
		return false;
	}
	if (!pw){
		alert("비밀번호를 입력해주세요.");
		form.pw.focus();
		return false;
	}
	if (!pw2){
		alert("비밀번호 확인을 입력해주세요.");
		form.pw2.focus();
		return false;
	}
    if (pw != pw2){
		document.querySelector('.pwCheckBox').innerHTML="<div class='wid-40'></div><div class='wid-60'><p id='pwCheck'>비밀번호 입력을 다시 확인해주세요.</p></div>";
		return false;
    }
}

var idCheckBox;

function idCheck() {
	var width = 500;
	var height = 300;
	var left = window.screen.width/2 - width/2;
	var top = window.screen.height/2 - height/2;
	
	idCheckBox = window.open('idCheck.jsp','중복확인', 'width='+width+', height='+height+', left='+left+', top='+top);
	idCheckBox.focus();
	
}

function addTextId(text){
	var id = document.getElementById("id");
	id.value = text;
}

function closeIdCheckBox() {
	idCheckBox.close();
}


