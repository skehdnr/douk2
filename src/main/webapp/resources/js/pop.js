var pop = pop ||{}
pop = {
	 open : ()=> {
           $('.popup-modal').magnificPopup({
                type: 'inline',
                preloader: false,
                modal: true,
                closeContentPos : true,
                fixedContentPos: true,
                alignTop: false, /* 최상단위치 */
                showCloseBtn: true
           });
           pop.close();
     },
     close : ()=> {
           $('.popup').click(e=> {
                e.preventDefault();
                $.magnificPopup.close();
           });
     },
     view : ()=>{
    	 return '<div style="width:300px;margin:auto;">'+
    	 '<h1 >'+
    	 '     Hi Siri'+
    	 '</h1>'+
    	 '<span><a class="popup-modal" href= "#login-modal">로그인</a ><span style="display: inline-block;width:100px"></span>'+
    	 '<a class="popup-modal" href= "#join-modal">회원가입</a >'+
    	 '</div>'+
    	 '<div id="login-modal" class= "white-popup-block mfp-hide"'+
    	 'style="width: 300px; height : 500px; background-color: white; margin : 0 auto;">'+
    	 '     <h1 >로그인 </h1 >'+
    	 '     <p ><a class ="popup" href="#"> 닫기</a></p>'+
    	 '</div>'+
    	 '<div id="join-modal" class= "white-popup-block mfp-hide"'+
    	 'style="width: 300px; height : 500px; background-color: white; margin : 0 auto;">'+
    	 '     <h1 >회원가입 </h1 >'+
    	 '     <p ><a class ="popup" href="#"> 닫기</a></p>'+
    	 '</div>'
     }
}