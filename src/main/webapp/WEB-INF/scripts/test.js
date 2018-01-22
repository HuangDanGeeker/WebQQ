var userId = "";
var userIconUri = "";
var httpReqest;
var friendListDivChildren;
var talkDocument = [];
var initResult;
var recodeFlag = 10;
var flash = [];
var friendGroups = [];
window.onload = function () {
	emojiInit();
	
	$('#signBtn').click(function(){
		$.ajax({
	        url:"http://localhost:8080/SpringMVC/apply",
	        dataType:'jsonp',
	        processData: true, 
	        type:'get',
	        success:function(){
	        	$("#loginInfo").text("网络连接错误");
	        },
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				$("#loginInfo").text("你申请的账号为 : "+XMLHttpRequest.responseText)
			}
		});
	});
    httpReqest = getXmlHttpRequest();
    $('#userId').focus();
    document.getElementById('loginBtn').onclick = function () {
        userId = document.getElementById('userId').value;
        if(userId == ""){
            $("#loginInfo").text("用户账号为空，请重新输入");        
            return;
        }
     
        $.ajax({
        url:"http://localhost:8080/SpringMVC/User/exist/"+userId,
        dataType:'jsonp',
        processData: true, 
        type:'get',
        success:function(){
        	$("#loginInfo").text("网络连接错误");
        },
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(XMLHttpRequest.status);
//			console.log(XMLHttpRequest.readyState);
//			console.log(textStatus);
			var result = eval("("+XMLHttpRequest.responseText+")");
			initResult = result;
//            console.log(result);
//          console.log(result.friendsId.length);
			
            if(result.exist != 1){
                $("#loginInfo").text("用户不存在，请注册");
                return;
            }else{
            	$("title").html("we QQ -" + result.userId); 
                //创建websocket
            	var ws = startWS(userId);
            	
            	//获取用户头像
            	userIconUri = result.iconUri;
            	$('#icon').attr("src", result.iconUri);
            	//初始化好友分组选项(在添加好友时可用)
            	var friendGroups = new Set();
            	for(var i = 0; i < result.friendsGroupNames.length; i++){
            		friendGroups.add(result.friendsGroupNames[i]);
            	}
            	friendGroups.forEach(function (element, sameElement, set) {
            		$("#addFriendsDiv select").append("<option>"+element+"</option>");
            	});
            	$("#addFriendsDiv select").append()
            	//初始化好友列表
            	$('#list_content_friend').children().remove(); //清空容器
            	
                for(var i = 0; i < result.friendsId.length; i++){  
                	var groupDiv = $('#list_content_friend').find("#panel_"+result.friendsGroupNames[i]);
                	
                	if(groupDiv.length == 0 || groupDiv == null || groupDiv == undefined){
                	    $('#list_content_friend').append(
                	        '<div id="panel_'+result.friendsGroupNames[i]+'" class="panel panel-default"><div class="panel-heading"><h4 class="panel-title"><a href="#panel_group_'+result.friendsGroupNames[i]+'" data-toggle="collapse" data-parent="#list_content_friend">'+result.friendsGroupNames[i]+'</a><p id="count">0</p><br></h4></div></div>');

                	    $("#panel_"+result.friendsGroupNames[i]).after('<div id="panel_group_'+result.friendsGroupNames[i]+'" class="panel-collapse collapse"><div class="panel-body"></div></div>');
                	    
                	}
                	if(result.friendsId[i] == '___')
                		continue;
                	groupDiv = $("#panel_group_"+result.friendsGroupNames[i]).find(".panel-body");
                	groupDiv.append('<div class="list-friend" id="'+result.friendsId[i]+'"><a class="madia-left" href="#"><img class="media-object list-img" src="'+result.friendsIcons[i]+'" onerror="" /></a><div class="media-body"><h4 class="media-heading">'+result.friendsId[i]+'</h4></div></div>');
                	console.log("===>"+result.friendsGroupNames[i]);
                	$("#panel_group_"+result.friendsGroupNames[i]).prev().find('#count').html(" "+(Number($("#panel_"+result.friendsGroupNames[i]).find('#count').html().trim())+1));
                    talkDocument[result.friendsId[i]] = "";
                 }
                console.log(talkDocument);
                $('.list-friend').click( function () {
                	clearInterval(flash['friend'+this.id]);
                	clearInterval(flash['talk'+this.id]);
                	$('#'+this.id).css("background-color", "rgb(120, 120, 120)");
                    $("#optsDialog").css("display", 'none');
                    $("#dialog").css("display", 'block');
                    $("#friendName").text(this.id);
                    $('#talkMsg').val("");
                    $('#talkContent').children().remove();
                    $('#talkContent').append(talkDocument[this.id]);
                    twemoji.parse(document.getElementById('talkContent'), 
                    	    {
                    	        callback: function(icon, options) {
                    	            return './twemoji2/72x72/' + icon + '.png';
                    	        },
                    	        size: 72
                    	    }
                    );
                    
                });
                friendListDivChildren = $('#list_content_friend').children(); 
                
                
                //初始化 消息记录列表
                //{friendId:{"1":"talkcontent1","2":"talkContent2"},.....}
                $.ajax({
                	url:"http://localhost:8080/SpringMVC/history/"+userId,
                    dataType:'jsonp',
                    processData: true, 
                    type:'get',
                    error:function(XMLHttpRequest, textStatus, errorThrown) {
                    	console.log(result);
                    	historyOfAll = eval("("+XMLHttpRequest.responseText+")");
                    	console.log(historyOfAll);
                    	for(item in historyOfAll){
//                    		console.log("=>"+typeof(historyOfAll[item]));
                    		console.log("=>"+item[0][0]);
                    		//绘制历史消息对话框 list_content_talk
//                    		console.log(item);
                    		drawListTalkItem("#list_content_talk", result.friendMap[item], "", "list-talk", item, historyOfAll[item]);
                    	}
                    }
                });
                
                document.getElementById("wrapper").style = 'display:none';
                document.getElementById("list_header").innerHTML = '<font color="white">'+userId+'</font>';  

            }
		
		}});
        
    }

    
    document.getElementById('optsDiv').onclick = function () {
    	interactiveImg('opts');
        $("#optsDialog").css("display", 'block');
        $("#dialog").css("display", 'none');

    }
    

    
    document.getElementById("changeIcon").onclick = function () {
        $('#changeIconDiv').slideToggle("slow");
        if($('#changeIconDiv').css("display")  == "block"){
        	$.ajax({
        		url:"http://localhost:8080/SpringMVC/queryIcon",
    	        dataType:'jsonp',
    	        processData: true, 
    	        type:'get',
    			error:function(XMLHttpRequest, textStatus, errorThrown){
    				var imageUris = eval("("+XMLHttpRequest.responseText+")");
    				console.log(imageUris.IconImages);
    				$('#changeIconDiv').children().remove();
    				for(var i = 0; i < imageUris.IconImages.length; i++){
    					$('#changeIconDiv').append('<img class="icons" id="'+imageUris.IconImages[i].iconId+'" src="'+imageUris.IconImages[i].uri+'">');
    				}
    				$('.icons').click(function(){
    					var imageUri = this.src;
    					$.ajax({
    						url:"http://localhost:8080/SpringMVC/changeIcon/"+userId+"/"+this.src.substring(39),
    		    	        dataType:'jsonp',
    		    	        processData: true, 
    		    	        type:'get',
    		    			error:function(XMLHttpRequest, textStatus, errorThrown){
    		    				if("true" == XMLHttpRequest.responseText){
    		    					
    		    					$('#icon').attr("src", imageUri);
    		    					alert("头像修改成功");
    		    				}else{
    		    					alert("oops!头像修改失败");
    		    				}
    		    			}
    					
    					});
    				});
    			}
        	});
        }
    }

    document.getElementById("deleteFriend").onclick = function () {
        $('#deleteFriendsDiv').slideToggle("slow");
    }
    
    document.getElementById("addGroup").onclick = function () {
        $('#addGroupDiv').slideToggle("slow");
    }

    document.getElementById("addFriend").onclick = function () {
        $('#addFriendsDiv').slideToggle("slow");
    }
    
    document.getElementById("resignAccount").onclick = function () {
        $('#resignAccountDiv').slideToggle("slow");
    }

    document.getElementById('friendList').onclick = function () {
    	interactiveImg("contact");
    	$('#list_content_friend').css("display", "inline-table");
    	$('#list_content_talk').css("display", "none");
    	$('#list_content_selfInfo').css("display", "none");
        $('#list_content_friend').children().remove();
        $('#list_content_friend').append(friendListDivChildren);
        //重新绑定事件
        $('.list-friend').click( function () {
        	clearInterval(flash['friend'+this.id]);
        	clearInterval(flash['talk'+this.id]);
        	$('#'+this.id).css("background-color", "rgb(120, 120, 120)");
            $("#optsDialog").css("display", 'none');
            $("#dialog").css("display", 'block');
            $("#friendName").text(this.id);
            $('#talkMsg').val("");
            $('#talkContent').children().remove();
            $('#talkContent').append(talkDocument[this.id]);
            twemoji.parse(document.getElementById('talkContent'), 
            	    {
            	        callback: function(icon, options) {
            	            return './twemoji2/72x72/' + icon + '.png';
            	        },
            	        size: 72
            	    }
            );
        });

    };

    document.getElementById('chatList').onclick = function (chatList) {
    	interactiveImg("conversation");
    	$('#list_content_talk').css("display", "inline-table");
    	$('#list_content_friend').css("display", "none");
    	$('#list_content_selfInfo').css("display", "none");
    };
    
    $('#chatMsg').keydown(function (event) {
        if(event.which == 13){
            // alert("keydown");
            $("#sendBtn").trigger("click");
        }
    });
    
    $('#emojiTools').click(function(){
//    	console.log(typeof($('#emojiTools').css("display")));
//    	console.log($('#emojiTools').css("display"));
//    	console.log($('#emojiTools').css("display") == "none");
    	if($('#emojiContent').css("display") == "none"){
	    	$('#emojiContent').css("display", "block");
	    	$('#talkContent').css("height", "80%");
    	}else{
    		$('#talkContent').css("height", "100%");
    		$('#emojiContent').css("display", "none");
    	}
    });
    

    $('#resignBtn').click(function(){
    	alert(userId+"已经下线");
    	ws.close();
    	window.location = "http://localhost:8080/SpringMVC/hello";
    });
    
}

function getXmlHttpRequest(){
    if (window.XMLHttpRequest)
    {
        xmlhttp=new XMLHttpRequest();
    }
    else if (window.ActiveXObject)
    {
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}

function startWS(userId) {

    ws = new WebSocket("ws://localhost:8080/SpringMVC/websocket?uid="+userId);
    ws.onopen =  function (msg) {
        console.log('webSocket opened');
    };
    ws.onmessage = function (message) {
        //TODO 
        var messageEntity = eval("("+message.data+")");
        console.log("==> "+messageEntity);
        //实时聊天记录 红点
        var item_talk = $('#list_content_talk').children("#"+messageEntity.from);
        if(item_talk.length == 0 ||item_talk == undefined || item_talk == null ){
        	var div = '<div class="list-talk" id="'+messageEntity.from+'" ><div style="height:100%; width:85%; float:left;"><a class="madia-left" href="#"><img class="media-object list-img" src="'+initResult.friendMap[messageEntity.from]+'" onerror=""></a><div class="media-body"><h4 class="media-heading">'+messageEntity.from+'</h4></div><div id="lastTalkContent">'+messageEntity.msg+'</div></div><div style="height:100%;width:10%;float:left;"><span id="msgCount" style="background:red;  margin-top:30%;" class="badge">0</span></div></div>';
        	$('#list_content_talk').append(div);
        	item_talk = $('#list_content_talk').children("#"+messageEntity.from);
        }else{
        	$("#list_content_talk").find('#'+messageEntity.from).find('#lastTalkContent').text(messageEntity.msg);
        }
        var msgCount = $("#list_content_talk").find('#'+messageEntity.from).find('#msgCount');
        msgCount.css("display", "inline-block").html(Number(msgCount.html())+1);
        div = '<div class="leftBob"><img class="leftBobImg" src="'+initResult.friendMap[messageEntity.from]+'"><font>'+messageEntity.msg+'</font></div>';
        talkDocument[messageEntity.from] = talkDocument[messageEntity.from] + div;
        $('.list-talk').click( function () {
        	$("#list_content_talk").find('#'+messageEntity.from).find('#msgCount').html("0");
        	$(this).find("#msgCount").css("display", "none");
        	console.log($(this).find("#msgCount"));
        	$('#'+this.id).css("background-color", "rgb(120, 120, 120)");
            $("#optsDialog").css("display", 'none');
            $("#dialog").css("display", 'block');
            $("#friendName").text(this.id);
            document.getElementById("chatMsg").value = "";
            $("#talkContent").children().remove();
            drawBob(talkDocument[this.id]);
        });
        
    };
    ws.onerror = function (error) {
        console.log('error :' + error.name + error.number);
    };

    ws.onclose =  function () {
        console.log('webSocket closed');
    };
    
    $('#sendBtn').click(function (){
    	if($('#chatMsg').val() == "")
    		return;
    	var msg = $('#friendName').text() +':#{"from":"'+userId+'", "type":"text"'+',"msg":"'+ $('#chatMsg').val() +'"}';
        //alert("send invoke\n"+msg);
        ws.send(msg);
        console.log(userIconUri);
        var div = '<div class="rightBob"><font style="border-radius: 2px 2px; background-color:red;">'+$('#chatMsg').val()+'</font><img src="'+userIconUri+'"></div>';
        $("#talkContent").append(div);
        talkDocument[$('#friendName').text()] = talkDocument[$('#friendName').text()] + div;

        var listTlak = $('#list_content_talk').children("#"+$('#friendName').text())[0];
        console.log(listTlak);
        if(listTlak != undefined &&!listTlak.lenght){
        	console.log($('#'+$('#friendName').text()).find("#lastTalkContent").text($('#chatMsg').val()));
        }else{
        	var friendId = $('#friendName').text();
        	$('#list_content_talk').append('<div class="list-talk" id="'+friendId+'" >'+'<img class="list-img" src="'+initResult.friendMap[friendId]+'" onerror="./images/defaultIcon.jpg"><font><div>'+friendId+'</div><div id="lastTalkContent">'+$('#chatMsg').val()+'</div></font></div>');
        	$('.list-talk').click( function () {
        		clearInterval(flash['friend'+this.id]);
            	clearInterval(flash['talk'+this.id]);
            	$('#'+this.id).css("background-color", "rgb(120, 120, 120)");
                $("#optsDialog").css("display", 'none');
                $("#dialog").css("display", 'block');
                $("#friendName").text(this.id);
                document.getElementById("chatMsg").value = "";
                $("#talkContent").children().remove();
                drawBob(talkDocument[this.id]);
            });
        	
        }
        talkContent.from = userId;
        talkContent.to = $('#friendName').text();
        talkContent.content = $('#chatMsg').val();
        var now = new Date();
        var now_str = now.getFullYear()+'/'+now.getMonth()+"/"+now.getDate()+" "+now.getHours()+":"+now.getMinutes()+":"+now.getSeconds();
        talkContent.timestamp = now_str;
        $('#chatMsg').val("");
        INDEXDB.addData(myDB.db, talkContent.to, talkContent);
        
        
    });
    
    $('#MsgRecordBtn').click(function(){
    	$('#recordDialog').toggle();
    	if($('#recordDialog').css("display") != "none"){
    		historyOf($('#friendName').text(), recodeFlag);
    	}
    });

    $('#iconDiv').click(function(){
    	interactiveImg("");
    	$('#list_content_selfInfo').css("display", "inline-table");
        $('#list_content_talk').css("display", "none");
        $('#list_content_friend').css("display", "none");
        
        $('#selfId').text("id : " + userId);
        $.ajax({
			url:"http://localhost:8080/SpringMVC/querySelfInfo/"+userId,
	        dataType:'jsonp',
	        processData: true, 
	        type:'get',
			error:function(XMLHttpRequest, textStatus, errorThrown){
				var selfInfo = eval("("+XMLHttpRequest.responseText+")");
				$('#selfAge').val(selfInfo.age);
				$('#selfSignature').val(selfInfo.signature);
				if(selfInfo.sex == "woman"){
					$('#selfSex').val(1);
				}else{
					$('#selfSex').val(0);
				}
			}
		});
    });
    
    $('#rewriteSelfInfoBtn').click(function(){
//    	console.log($('#selfAge').val());
//    	console.log($('#selfSignature').val());
//    	console.log($('#selfSex').find("option:selected").text());
    	var selfInfo = {
    		"id"  : userId,
    		"age" : $('#selfAge').val(),	
    		"sex" : $('#selfSex').find("option:selected").text(),	
    		"signature" : $('#selfSignature').val()
    		
    	};
    	$.ajax({
    		url:"http://localhost:8080/SpringMVC/updateSelfInfo",
            dataType:'json',
            data:JSON.stringify(selfInfo),
            contentType:"application/json",
            processData: true, 
            type:'POST',
    		error:function(XMLHttpRequest, textStatus, errorThrown){}
        });
    });
}

function deleteFriend(){
	
	$('#deleteFriendInfo').text("");
    var preDeleteFriendId = document.getElementById('deleteFriendId').value;
    var deletedDiv = $('#list_content_friend').find("#"+preDeleteFriendId);

    if(0 == deletedDiv.length){
		$('#deleteFriendInfo').text("好友不存在，删除失败");
		return;
	}
    $.ajax({
		url:"http://localhost:8080/SpringMVC/delete/"+userId+"/"+ preDeleteFriendId+"/"+$('#fullDelete').is(':checked'),
        dataType:'jsonp',
        processData: true, 
        type:'get',
		error:function(XMLHttpRequest, textStatus, errorThrown){
			var result = eval("("+XMLHttpRequest.responseText+")");
			if(result.result == 1){
				$('#deleteFriendInfo').text("删除成功");
				$("#list_content_friend").find('#'+preDeleteFriendId).remove();
				$("#list_content_talk").find('#'+preDeleteFriendId).remove();
			}else if(result.result == 0){
				$('#deleteFriendInfo').text("oops!"+result.reason+"删除失败");
			}
		}
	});
    
}


function addFriend(){
	console.log("add Friend");
	$('#addFriendInfo').text("");
    var preAddFriendId = document.getElementById('addFriendId').value;
    var preAddFriendGroup = document.getElementById('addFriendGroup').value;
    friendListDivChildren = $('#list_content_friend').children(); 
    for(var i = 0; i < friendListDivChildren.length; i++){
    	if(friendListDivChildren[i].id == preAddFriendId){
    		$('#addFriendInfo').text("you already have the friend named : " +　preAddFriendId);
    		return;
    	}
    		
    }
//    	console.log(friendListDivChildren[i].id);
//    	console.log($('#fullDelete').is(':checked'));
	$.ajax({
		url:"http://localhost:8080/SpringMVC/queryUser/"+ userId+"/"+preAddFriendId+"/"+preAddFriendGroup,
        dataType:'jsonp',
        processData: true, 
        type:'get',
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest.responseText);
			var result = eval("("+XMLHttpRequest.responseText+")");
			if(result["exist"] == "true"){
				initResult.friendsId[initResult.friendsId.length] = preAddFriendId;
				$('#addFriendInfo').text("添加成功");

				groupDiv = $("#panel_group_"+preAddFriendGroup).find(".panel-body");
            	groupDiv.append('<div class="list-friend" id="'+preAddFriendId+'"><a class="madia-left" href="#"><img class="media-object list-img" src="'+result.imageIconUri+'" onerror="" /></a><div class="media-body"><h4 class="media-heading">'+preAddFriendId+'</h4></div></div>');
                talkDocument[preAddFriendId] = "";
                getUserIcon(preAddFriendId);
				$('#list_content_friend').find('#'+preAddFriendId).click(function(){
					clearInterval(flash['friend'+this.id]);
		        	clearInterval(flash['talk'+this.id]);
		        	$('#'+this.id).css("background-color", "rgb(120, 120, 120)");
					$("#optsDialog").css("display", 'none');
                    $("#dialog").css("display", 'block');
                    $("#friendName").text(this.id);
                    $('#talkMsg').val("");
                    talkDocument[preAddFriendId] = "";
                    
				});
				friendListDivChildren[friendListDivChildren.length] = $('#list_content_friend').find('#'+preAddFriendId);
//				console.log(friendListDivChildren);
			}else if(result["exist"] == "false"){
				$('#addFriendInfo').text("用户不存在,添加失败");
			}
		}
	});
    
}

//function getUserIcon(userId){
//	$.ajax({
//		url:"http://localhost:8080/SpringMVC/queryIcon/"+ userId,
//		dataType:'jsonp',
//        processData: true, 
//        type:'get',
//		error:function(XMLHttpRequest, textStatus, errorThrown){
//			$("#"+userId+" img").attr("src", XMLHttpRequest.responseText);
//		}
//	});
//}



function drawListTalkItem(parentDiv, imgSrc, content, className, id, history){
	console.log(history);
	if(history.length == 0)
		return;
	$(parentDiv).append('<div class="'+className+'"  id="'+id+'" ><a class="media-left" href="#"><img class="list-img media-object" src="'+imgSrc+'"></a><font><div class="media-body"><h4 class="media-heading">'+id+'</h4><div id="lastTalkContent">'+content+'</div></font></div>');
	talkDocument[id] = "";
	for(entities in history){
		console.log(history[entities].flag);
		console.log(history[entities].content);
		if(history[entities].flag == "1"){
			talkDocument[id] = talkDocument[id] + '<div class="leftBob"><img class="leftBobImg" src="'+imgSrc+'"><font>'+history[entities].content+'</font></div>';
		}else{
			talkDocument[id] = talkDocument[id] + '<div class="rightBob"><font>'+history[entities].content+'</font><img class="rightBobImg" src="'+userIconUri+'"></div>';
		}
	}
	
	$('.'+className).click( function () {
		clearInterval(flash['friend'+this.id]);
    	clearInterval(flash['talk'+this.id]);
    	$('#'+this.id).css("background-color", "rgb(120, 120, 120)");
        $("#optsDialog").css("display", 'none');
        $("#dialog").css("display", 'block');
        $("#friendName").text(this.id);
        document.getElementById("chatMsg").value = "";
        $("#talkContent").children().remove();
        drawBob(talkDocument[this.id]);
    });
}
function drawBob(singleChatText){
	$("#talkContent").append(singleChatText);
	
    twemoji.parse(document.getElementById("talkContent"), 
    	    {
    	        callback: function(icon, options) {
    	            return './twemoji2/72x72/' + icon + '.png';
    	        },
    	        size: 72
    	    });
	
}

function historyOf(friendId, num){
//	alert("function historyOf " + friendId + "  "+ num);
	$.ajax({
        url:"http://localhost:8080/SpringMVC/history/"+userId+"/"+friendId+"/"+num,
        dataType:'jsonp',
        processData: true, 
        type:'get',
        success:function(){
        	alert("网络连接错误");
        },
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			var result = eval("("+XMLHttpRequest.responseText+")");
			console.log(result.history);
			//TODO 在msgRecord.MsgRecordDiv上绘制
			var friendName = $('#friendName').text();
			$('#MsgRecordDiv').children().remove();
			for(var i = 0; i < result.history.length; i++){
				if(result.history[i].flag == "1"){
					$('#MsgRecordDiv').append('<div class="leftBob"><img src="'+initResult.friendMap[friendName]+'"><font>'+result.history[i].content+'</font></div>');
				}else{
					$('#MsgRecordDiv').append('<div class="rightBob"><font style="border-radius: 2px 2px; background-color:red;">'+result.history[i].content+'</font><img src="'+initResult.friendMap[friendName]+'"></div>');
				}
				//初始化 emoji
				twemoji.parse(document.getElementById('MsgRecordDiv'), 
					    {
					        callback: function(icon, options) {
					            return './twemoji2/72x72/' + icon + '.png';
					        },
					        size: 72
					    });
			}
			
		}
	});
}

function addGroup(){
	console.log("add Group");
	
	$('#addGroupInfo').text("");
	var preAddGroupName = document.getElementById('addGroupName').value;
	$.ajax({
	    url:"http://localhost:8080/SpringMVC/queryUser/"+ userId+"/___/"+preAddGroupName,
	    dataType:'jsonp',
	    processData: true, 
	    type:'get',
	    error:function(XMLHttpRequest, textStatus, errorThrown){
	        var result = eval("("+XMLHttpRequest.responseText+")");
	        console.log(result);
	        if(result["exist"] == "false"){
	            $('#addGroupInfo').text("添加成功");
	            $('list_content_friend').append('<div class="panel panel-default"><div class="panel-heading"><h4 class="panel-title"><a id="'+preAddGroupName+'" href="#'+preAddGroupName+'Div">'+preAddGroupName+'</a><br></h4></div></div><div id="'+preAddGroupName+'Div" class="panel-collapse collapse"><div class="panel-body"></div></div>');
	            $("#"+preAddGroupName).click(function(){
	                $("#"+preAddGroupName+"Div").toggle("show");
	            });
	        }else if(result["exist"] == "true"){
	            $('#addGroupInfo').text("添加失败,请重新尝试");
	        }
	    }
	});
    
}


