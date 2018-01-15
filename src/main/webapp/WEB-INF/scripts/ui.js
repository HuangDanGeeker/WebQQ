

function interactiveImg(selectedImgId){
	$("#conversation").attr("src", "./images/tab_icon_conversation.png");
	$("#contact").attr("src", "./images/tab_icon_contact.png");
	$("#opts").attr("src", "./images/tab_icon_opts.png");
	if(selectedImgId == "")
		return;
	$("#"+selectedImgId).attr("src", "./images/tab_icon_"+selectedImgId+"_selected.png");
}