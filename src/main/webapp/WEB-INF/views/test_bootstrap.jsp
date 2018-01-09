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
<script src="../SpringMVC/scripts/test.js" type="text/javascript"></script>
<script src="../SpringMVC/scripts/emojiInit.js" type="text/javascript"></script>
<script src="../SpringMVC/scripts/indexedDB.js" type="text/javascript"></script>

<div class="container_fluid">
<div class="row" id="wrapper">
    <font size="5">请输入用户账号</font>
    <font size="4" color="red"><p id="loginInfo"></p></font>
    <div class=" col-md-2 col-md-offset-5"><input type="text" class="form-control" id="userId" value="123"></div><br><br>
    <button type="button" class="btn btn-info" id="signBtn">sign in</button>
    <button type="button" class="btn btn-info" id="loginBtn">sign up</button>

</div>
</div>
<div class="container_fluid">
<div class="row " id="main">

<div id="iconDiv" class="col-md-1"><img id="icon" src=""  onerror="this.src='./images/defaultIcon.jpg'"></div>
<div id="list" class="col-md-3">
<div id="list_header" class="text-center"></div>
<div id="list_content_friend">
    list_content_friend
    
</div>
<div id="list_content_talk" >
    list_content_talk
   
    
</div>
<div id="list_content_selfInfo" >
   <br>
   <div id="selfId"></div><br>
   <div >sex : <select id="selfSex" style="font-size:16px;"> 
    		<option value="0">man</option> 
    		<option value="1">woman</option> 
    			  </select> 
	</div><br>
   <div >age : <input type="text" id="selfAge"/></div><br>
   <div >signature : <textarea cols="26" rows="3" id="selfSignature" ></textarea></div><br>
   <div style="width:100%;"><input type="submit" id="rewriteSelfInfoBtn" value="保存更改"></div>
</div>
<div id="list_footer" > 
    <div id="chatList" ><img  src="./images/tab_icon_conversation.png" ><div>会话</div></div>
    <div id="friendList"> <img src="./images/tab_icon_contact.png" ><br>好友</div>
    <div id="opts"> <img src="./images/tab_icon_setup.png" ><br>设置</div>
</div>
</div>

<div id="dialog" class="col-md-4">
    <div id="friendName" ></div>
    <div style="height: 80%; background-color: black;">
        <div id="talkContent" >
            <!-- TODO 添加头像 -->
            <div class="leftBob" style=" "><img src=""><font >1233213</font></div>
            <div class="rightBob" ><font >1233213</font><img src=""></div>
        </div>
        <div id="emojiContent" >
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
        
        <div style=" width:55%; height:100%; float: left; padding: 4px 8px; line-height: 100%;" class="form-group"><input type="textarea"   wrap="virtual" id="chatMsg"></div>
        <div style="margin-top: 1%; width:15%; height:85%; float: left; padding: 4px 8px;"><button  class="btn  btn-info" id="sendBtn" type="button">send</button></div>
        <div  id="MsgRecordBtn"><img class="img-round" src="./images/record.jpg"></div>
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
		    <p id="deleteFriendInfo" ></p>
		    <form style="margin-left:2%;">
		        请输入好友账号:<input type="text" id="deleteFriendId" value="abc" /><br><br>
		        <input type="checkbox" id="fullDelete" value="1">同时在对方好友列表中删除<br><br>
		        <button type="button" onclick="deleteFriend()" >确认删除</button>
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
		    <p id="addFriendInfo" ></p>
		    <form>
		        请输入要添加的好友账号：<input type="text" id="addFriendId" value="1231321312" /><br><br>
		        <button type="button" class="btn btn-info" onclick="addFriend()">确认添加</button>
		    </form>
	    </div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
    		<h4><a id="resignAccount" href="#resignAccountDiv">注销账号</a><br></h4>
    	</div>
    </div>
    <div id="resignAccountDiv" class="panel-collapse collapse">
    	<div class="panel-body">
    		<button type="button" class="btn btn-info" id="resignBtn" >注销</button>
    	</div>
    </div>
</div>
</div>
<!--  <div style="width:2%; height: 510px; float: left;"></div>-->
<div id="recordDialog" class="col-md-4">
    <div id="MsgRecordDiv" sytle="height:90%;"></div>
    <div style="height:10%">
        <button type="button" class="btn btn-info" id="getFormerDiv" onclick="historyOf($('#friendName').text(), function(){recodeFlag = recodeFlag +　10; return recodeFlag;}());"> 上一页</button>
        <button type="button" class="btn btn-info" id="getLaterDiv" onclick="historyOf($('#friendName').text(), function(){recodeFlag = recodeFlag -　10; return recodeFlag;}());" >下一页</button>
    </div>  
</div>
</div>

</div>
</body>
</html>