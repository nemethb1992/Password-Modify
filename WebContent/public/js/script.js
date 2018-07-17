
$( document ).ready(function() {
	buttonEventHolderLogin();
});
function buttonEventHolderLogin()
{	
	$('#save_btn').click(function(){
		loginEnter();
	})
}

function loginEnter()
{
	$.ajax({
	    url:  '/MES/Dashboard_c',
	    data: {
	    },
	    success: function (response) {

	    		  console.log(response);
	    	
	    }
	});
}