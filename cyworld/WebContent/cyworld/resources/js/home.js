function openPage() {
	var selectedPage = document.getElementById('pageSelect').value;
	if (selectedPage){
		window.open(selectedPage,'_blank');
	}
}

function pageChange(page) {
	var id = document.frm.id.value;
	var page = document.frm.page.value;
	location.href='home.jsp?page='+page+'&id='+id;
}

let query = window.location.search;
let param = new URLSearchParams(query);
let this_page = param.get('page');

document.addEventListener('DOMContentLoaded', function() {
	document.getElementById(this_page).style.backgroundColor = 'white';
	document.getElementById(this_page).style.borderLeft = 'none';
	document.getElementById(this_page).style.color = 'black';
});
