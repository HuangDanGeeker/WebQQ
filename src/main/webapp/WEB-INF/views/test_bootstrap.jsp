<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Web QQ</title>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <script src="http://upcdn.b0.upaiyun.com/libs/jquery/jquery-2.0.2.min.js"></script>
    <script src="../SpringMVC/twemoji2/twemoji.min.js" type="text/javascript"></script>
    <link href="../SpringMVC/styles/test.css" rel="stylesheet" style="text/css"/>  
    <link href="../SpringMVC/styles/emoji.css" rel="stylesheet" style="text/css"/>  
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container_fluid">
<div class="row" id="wrapper">
    <p class="text-center" >请输入用户账号</p>
    <p id="loginInfo" class="bg-warning text-center center-block"></p>
    
    <div class=" col-md-2 col-md-offset-5"><input type="text" class="form-control" id="userId" value="123"></div><br><br>
    <button type="button" class="btn btn-info" id="signBtn">sign in</button>
    <button type="button" class="btn btn-info" id="loginBtn">sign up</button>

</div>
</div>
<div class="container_fluid">
<div class="row" id="main">

<div id="iconDiv" class="col-md-1"><img id="icon" src=""  onerror="this.src='./images/defaultIcon.jpg'"></div>
<div id="list" class="col-md-3">
<div id="list_header" class="text-center"></div>
<div id="list_content_friend" class="panel-group">

</div>
<div id="list_content_talk" >
  
</div>
<div id="list_content_selfInfo" style="padding-left:2%; padding-top:1%;">
<form role="form">
   <div id="selfId"></div><br>
   <div class="form-group"><label for="selfsex">sex</label>
   		<select class="for-control" id="selfSex" style="font-size:16px;"> 
    		<option value="0">man</option> 
    		<option value="1">woman</option> 
	    </select> 
   </div>
   <div class="form-group">
   		<label for="selfAge">age</label>
   		<input type="text" class="form-control" id="selfAge"/>
   	</div>
   <div >
   		<label for="selfSignature">signature</label>
   		<textarea clss="form-control" cols="26" rows="3" id="selfSignature" ></textarea>
   </div><br>
   <div style="width:100%; text-align:center;"><input type="submit" class="btn btn-info" id="rewriteSelfInfoBtn" value="保存更改"></div>
</form>
</div>
<div id="list_footer" > 
    <div id="chatList" ><img  id="conversation" src="./images/tab_icon_conversation.png" ></div>
    <div id="friendList"> <img id="contact" src="./images/tab_icon_contact_selected.png" ></div>
    <div id="optsDiv"> <img id="opts" src="./images/tab_icon_opts.png" ></div>
</div>
</div>

<div id="dialog" class="col-md-4">
    <div id="friendName" ></div>
    <div style="height: 84%; background-color: black;">
        <div id="talkContent" >
        </div>
        <div id="emojiContent" class="pre-scrollable">
        	<ul class="emoji-list">
           
		      <li>&#x1F600;</li>
		      <li>&#x1F601;</li>
		      <li>&#x1F602;</li>
		      <li>&#x1F603;</li>
		      <li>&#x1F604;</li>
		      <li>&#x1F605;</li>
		      <li>&#x1F606;</li>
		      <li>&#x1F607;</li>
		      <li>&#x1F608;</li>
		      <li>&#x1F609;</li>
		      <li>&#x1F60A;</li>
		      <li>&#x1F60B;</li>
		      <li>&#x1F60C;</li>
		      <li>&#x1F60D;</li>
		      <li>&#x1F60E;</li>
		      <li>&#x1F60F;</li>
		      <li>&#x1F610;</li>
		      <li>&#x1F611;</li>
		      <li>&#x1F612;</li>
		      <li>&#x1F613;</li>
		      <li>&#x1F614;</li>
		      <li>&#x1F615;</li>
		      <li>&#x1F616;</li>
		      <li>&#x1F617;</li>
		      <li>&#x1F618;</li>
		      <li>&#x1F619;</li>
		      <li>&#x1F61A;</li>
		      <li>&#x1F61B;</li>
		      <li>&#x1F61C;</li>
		      <li>&#x1F61D;</li>
		      <li>&#x1F61E;</li>
		      <li>&#x1F61E;</li>
		      <li>&#x1F61F;</li>
		      <li>&#x1F620;</li>
		      <li>&#x1F621;</li>
		      <li>&#x1F622;</li>
		      <li>&#x1F623;</li>
		      <li>&#x1F624;</li>
		      <li>&#x1F625;</li>
		      <li>&#x1F626;</li>
		      <li>&#x1F627;</li>
		      <li>&#x1F628;</li>
		      <li>&#x1F629;</li>
		      <li>&#x1F62A;</li>
		      <li>&#x1F62B;</li>
		      <li>&#x1F62C;</li>
		      <li>&#x1F62D;</li>
		      <li>&#x1F62E;</li>
		      <li>&#x1F62F;</li>
		      <li>&#x1F630;</li>
		      <li>&#x1F631;</li>
		      <li>&#x1F632;</li>
		      <li>&#x1F633;</li>
		      <li>&#x1F634;</li>
		      <li>&#x1F635;</li>
		      <li>&#x1F636;</li>
		      <li>&#x1F637;</li>
		      <li>&#x1F638;</li>
		      <li>&#x1F639;</li>
		      <li>&#x1F63A;</li>
		      <li>&#x1F63B;</li>
		      <li>&#x1F63C;</li>
		      <li>&#x1F63D;</li>
		      <li>&#x1F63E;</li>
		      <li>&#x1F63F;</li>
		      <li>&#x1F640;</li>
		      <li>&#x1F641;</li>
		      <li>&#x1F642;</li>
		      
      		</ul>
        </div>
    </div>
    <div id="talkTools" >
        <div  id="emojiTools"><img src="./images/chat_bottombar_icon_face.png"></div>
        
        <div style=" width:55%; height:100%; float: left; padding: 4px 8px; line-height: 100%;" class="form-group"><input type="textarea" wrap="virtual" id="chatMsg"></div>
        <div style="margin-top: 1%; width:15%; height:85%; float: left; padding: 4px 8px;"><button  class="btn  btn-info" id="sendBtn" type="button">send</button></div>
        <div style="margin-top: 1%; width:10%; height:85%; float: left; padding: 4px 8px;"  id="MsgRecordBtn" ><button type="button" class="btn btn-info">Record</button></div>
    </div>
    <div></div>

</div>
<div id="optsDialog" class="col-md-4">
<div class="panel-group" id="optsPanels">
	<div class="panel panel-default">
		<div class="panel-heading">
    		<h4 class="panel-title"><a id="changeIcon" href="#changeIconDiv" data-parent="optsPanels">更改头像</a><br></h4>
    	</div>
    </div>
    <div id="changeIconDiv" class="panel-collapse collapse">
    	<div class="panel-body"></div>
    </div>

	<div class="panel panel-default">
		<div class="panel-heading">
    		<h4 class="panel-title"><a id="deleteFriend" href="#deleteFriendDiv">删除好友</a><br></h4>
    	</div>
    </div>
    <div id="deleteFriendsDiv" class="panel-collapse collapse">
    	<div class="panel-body">
		    <form style="margin-left:2%;" role="form">
		        <label for="deleteFriendId">请输入好友账号</label>
		        <h4><p id="deleteFriendInfo" class="label label-warning"></p></h4>
		        <input type="text" id="deleteFriendId" class="form-control" value="abc" />
		        <input type="checkbox" id="fullDelete" value="1"><label for="fullDelete">同时在对方好友列表中删除</label>
		        <div style="text-align:center"><button type="button" class="btn btn-warning" onclick="deleteFriend()" >确认删除</button></div>
		    </form>
	    </div>
    </div>
    
    <div class="panel panel-default">
    	<div class="panel-heading">
    		<h4 class="panel-title"><a id="addGroup" href="#addGroupDiv">添加分组</a><br></h4>
    	</div>
    </div>
    
    <div id="addGroupDiv" class="panel-collapse collapse">
    	<div class="panel-body">
		    <form role="form">
		        <label for="addGroupName">请输入要添加的分组</label>
		        <input id="addGroupName" type="text" class="form-control" value="" /><br>
		        <h4><p id="addGroupInfo" class="label label-warning"></p></h4>
		        <div style="text-align:center;"><button type="button" class="btn btn-info" onclick="addGroup()">确认添加</button></div>
		    </form>
	    </div>
	</div>
    
    <div class="panel panel-default">
    	<div class="panel-heading">
    		<h4 class="panel-title"><a id="addFriend" href="#addFriendsDiv">添加好友</a><br></h4>
    	</div>
    </div>
    
    <div id="addFriendsDiv" class="panel-collapse collapse">
    	<div class="panel-body">
		    <form role="form">
		        <label for="addFriendId">请输入要添加的好友账号</label>
		        <input id="addFriendId" type="text" class="form-control" value="" /><br>
		        <label for="addFriendGroup">请选择好友分组</label>
				<select id="addFriendGroup" class="form-control">
					
				</select>
		        
		        
		        <h4><p id="addFriendInfo" class="label label-warning"></p></h4>
		        <div style="text-align:center;"><button type="button" class="btn btn-info" onclick="addFriend()">确认添加</button></div>
		    </form>
	    </div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
    		<h4 class="panel-title"><a id="resignAccount" href="#resignAccountDiv">注销账号</a><br></h4>
    	</div>
    </div>
    <div id="resignAccountDiv" class="panel-collapse collapse">
    	<div class="panel-body" style="text-align:center; line-height:100%;">
    		<button type="button" class="btn btn-danger" id="resignBtn" >注销</button>
    	</div>
    </div>
</div>
</div>

<div id="recordDialog" class="col-md-4">
    <div id="MsgRecordDiv"></div>
    <div style="width:100%; height:10%">
        <button type="button" class="btn btn-info" id="getFormerDiv" onclick="historyOf($('#friendName').text(), function(){recodeFlag = recodeFlag +　10; return recodeFlag;}());"> 上一页</button>
        <button type="button" class="btn btn-info" id="getLaterDiv" onclick="historyOf($('#friendName').text(), function(){recodeFlag = recodeFlag -　10; return recodeFlag;}());" >下一页</button>
    </div>  
</div>
</div>

</div>
<div id="page_resources" class="container_fluid">
	<audio id="didi_audio" src="http://localhost:8080/SpringMVC/sounds/didi.wav" controls="controls" loop="false" hidden="false">
	<audio id="ios_message_audio" src="http://localhost:8080/SpringMVC/sounds/ios_message.wav" controls="controls" loop="false" hidden="true" >
	<audio id="ios_qq_audio" src="http://localhost:8080/SpringMVC/sounds/ios_qq.wav" controls="controls" loop="false" hidden="true" >
	<audio id="send_success_audio" src="http://localhost:8080/SpringMVC/sounds/send_success.wav" controls="controls" loop="false" hidden="true" >
	<audio id="friend_login_audio" src="http://localhost:8080/SpringMVC/sounds/friend_login.wav" controls="controls" loop="false" hidden="true" >
</div>

<script src="../SpringMVC/scripts/ui.js" type="text/javascript"></script>
<script src="../SpringMVC/scripts/test.js" type="text/javascript"></script>
<script src="../SpringMVC/scripts/emojiInit.js" type="text/javascript"></script>

</body>
</html>