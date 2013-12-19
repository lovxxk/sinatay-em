function doClear(){
	$(':input','#frmInput')   
	 .not(':button, :submit, :reset, :hidden')   
	 .val('')   
	 .removeAttr('checked')   
	 .removeAttr('selected');
}

function formSubmit(){
	$("#frmInput").submit(); 
}
