function im_wr_onoff() {
	var bt_st = document.getElementById('im_wr_btn').style;
	var wr_st = document.im_wr_frm.style;
	
	bt_st.display = 'none';
	wr_st.display = 'flex';
}

function setThumbnail(event) {
	var reader = new FileReader();
	var file = event.target.files[0];

	if (file && file.type.match("image.*")) {
		reader.onload = function(e) {
			var img = document.createElement("img");
			img.setAttribute("src", e.target.result);
			document.querySelector("#photo-container").innerHTML = "";
			document.querySelector("#photo-container").appendChild(img);
		}
		reader.readAsDataURL(file);
	}
}