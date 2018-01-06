function emojiInit(){
	var emojiUl = document.getElementsByClassName('emoji-list')[0];
    twemoji.parse(emojiUl, 
    {
        callback: function(icon, options) {
            return './twemoji2/72x72/' + icon + '.png';
        },
        size: 72
    });
    
    
    (function (img, i) {
        function copyToClipboard(e) {
          //prompt('Copy to clipboard via ' + metaKey + '+C and Enter', this.alt);
          //alert(this.alt);
          //TODO
          $('#chatMsg').val($('#chatMsg').val() +"<img class='emoji' draggable='false' src='"+this.src+"'/>");
          twemoji.parse(document.getElementById('chatMsg'), 
		    {
		        callback: function(icon, options) {
		            return './twemoji2/72x72/' + icon + '.png';
		        },
		        size: 72
		    });
        }
        for (i = 0; i < img.length; img[i++].onclick = copyToClipboard) {}
      }(
        document.getElementsByClassName('emoji') 
      ));
}

