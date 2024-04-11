var player;
var currentTrack = 0;
var playName;
var youtubeIDs;
function onYouTubeIframeAPIReady() {
	youtubeIDs = document.getElementById('music_list_path').innerText;
	playName = document.getElementById('music_list_title').innerText;
	youtubeIDs = youtubeIDs.substring(1,youtubeIDs.length-1).split(', ');
	playName = playName.substring(1,playName.length-1).split(', ');
	player = new YT.Player('BGM', {
	    height: '0',
	    width: '0',
	    videoId: youtubeIDs[currentTrack],
		playerVars: {
            autoplay: 1,
            loop: 1,
            playlist: youtubeIDs.join(', ')
        },
	    events: {
	      'onReady': onPlayerReady,
	      'onStateChange': onPlayerStateChange
    	}
  	});
}
onYouTubeIframeAPIReady();

function onPlayerReady(event) {
	event.target.playVideo();
}

var done = false;
function onPlayerStateChange(event) {
    if (event.data == YT.PlayerState.PLAYING && !done) {
		done = true;
    	//setTimeout(nextVideo, 6000);
		document.getElementById('bgmTitle').innerHTML = playName[currentTrack];
    }
}

function playVideo(){
	player.playVideo();
}

function stopVideo() {
	player.stopVideo();
}

function pauseVideo() {
	player.pauseVideo();
}

function nextVideo() {
	if (currentTrack == youtubeIDs.length-1){
		currentTrack = 0;
	} else {
		currentTrack++;
	}
	player.loadVideoById(youtubeIDs[currentTrack]);
	document.getElementById('bgmTitle').innerHTML = playName[currentTrack];
}

function beforeVideo() {
	if (currentTrack == 0){
		currentTrack = youtubeIDs.length-1;
	} else {
		currentTrack--;
	}
	player.loadVideoById(youtubeIDs[currentTrack]);
	document.getElementById('bgmTitle').innerHTML = playName[currentTrack];
}

function selectVideo(num) {
	currentTrack = num;
	player.loadVideoById(youtubeIDs[currentTrack]);
	document.getElementById('bgmTitle').innerHTML = playName[currentTrack];
}

function music_select_on(){
	if (document.getElementById('music_select').style.display == 'block'){
		document.getElementById('black').style.display = 'none';
		document.getElementById('music_select').style.display = 'none';
	} else {
		document.getElementById('black').style.display = 'block';
		document.getElementById('music_select').style.display = 'block';
	}
	
}
