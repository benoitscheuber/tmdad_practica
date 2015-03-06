$(document).ready(function() {
	registerSearch();
});

function registerSearch() {
	$("#search").submit(function(ev){
		event.preventDefault();
		$.get($(this).attr('action'), 
				{q: $("#q").val()},
				function(data) {
					var template = $('#tweet').html();
					var html = Mustache.render(template, data);
					$('#resultsBlock').append(html);
		});	
	});
}