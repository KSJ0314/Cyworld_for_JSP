function checkAll(){
	document.mu_frm.check.checked = document.mu_frm.allCheck.checked;
	for(var i = 0; i < document.mu_frm.check.length; i++){
		document.mu_frm.check[i].checked = document.mu_frm.allCheck.checked;
	}
}

var music_add_box;

function music_add() {
	var width = 500;
	var height = 300;
	var left = window.screen.width/2 - width/2;
	var top = window.screen.height/2 - height/2;
	music_add_box = window.open('music_add.jsp','중복확인', 'width='+width+', height='+height+', left='+left+', top='+top);
	music_add_box.focus();
}

function re(){
	location.reload();
}

function close_music_add_box() {
	music_add_box.close();
}

function dufle(){
	document.querySelector('form[name=mu_frm]').action = '/cyworld/music_dufle.home';
	document.querySelector('form[name=mu_frm]').submit();
}
