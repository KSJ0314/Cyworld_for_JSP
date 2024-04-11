function enter() {
	var text = document.getElementById('id').innerText;
	window.opener.addTextId(text);
	window.opener.closeIdCheckBox();
}