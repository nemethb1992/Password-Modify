
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
	var list = [];
	$("#info_label").text("M�dos�t�s folyamatban");
	list.push($("#usr").val());
	list.push($("#pwd-old").val());
	list.push($("#pwd-new1").val());
	list.push($("#pwd-new2").val());
	$.ajax({
		type: "POST",
	    url:  '/Password_Modify/Dashboard_c',
        data: {
        	usr: list[0],
            pwd_old: list[1],
            pwd_new1: list[2],
            pwd_new2: list[3]
        },
	    success: function (response) {
	    	  $('#usr').val('');
	    	$("#pwd-old").val('');
	    	$("#pwd-new1").val('');
	    	$("#pwd-new2").val('');
//	    	var lista = response;
//            $.each(lista, function(i, item) {
//                $('<option value='+ lista[i] +'>'+lista[i]).html('</options>').appendTo('#roll-name');
//            });
	    	$("#info_label").text(response);
	    }
	});
}