var subscription;
var stompClient;

$(document).ready(function() {
	connect();
	registerSearch();
});

function registerSearch() {
	$("#search").submit(function(ev){
	event.preventDefault();
	unsubscribe();
	subscribe($("#q").val());
	});
}

function connect() {
	var socket = new SockJS('/twitter');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {});
}

function unsubscribe(){
	$('#resultsBlock').children("div").remove();
	if(subscription != undefined){
	subscription.unsubscribe();
	}
}

function subscribe(query){
	stompClient.send("/app/search", {}, query);
	subscription = stompClient.subscribe('/queue/search/'+query, function(data){
		var tweet = JSON.parse(data.body);
		var template = $('#tweet').html();
		var html = Mustache.render(template, tweet);
		$('#resultsBlock').prepend(html);
	},
	{ id: query });
}