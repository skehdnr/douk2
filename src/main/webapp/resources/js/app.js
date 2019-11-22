var app = app || {}
app = (()=>{
	let _,js
	let init =()=>{
		_=$.ctx()
		js=$.js()
	}
	let run = x =>{
		$.when(
				$.getScript(x+'/resources/js/router.js',()=>{
					$.extend(new Session(x))
				}),
				$.getScript(x+'/resources/js/pop.js')		
		)
		.done(()=>{
			init()
			onCreate().css({})
		})
		.fail(()=>{
			alert('fail')
		})
	}
	let onCreate=()=>{
		$(pop.view()).appendTo('#wrapper')
		pop.open()
		setContentView()
	}
	let setContentView=()=>{
		$('<table id="tab"><tr></tr></table>')
		.css({width: '80%',
            height: '800px',
            border: '1px solid black',
            margin: '0 auto'
        })
		.appendTo('#wrapper')
		$('<td/>',{id :'left'})
		.css({width: '20%',
                height: '100%',
                border: '1px solid black',
            'vertical-align': 'top'	
         })
		.appendTo('tr')
		$('<td/>',{id :'right'})
		.css({width: '80%',
                height: '100%',
                border: '1px solid black'
         })
		.appendTo('tr')
		
		$.each(['NAVER','CGV','BUGS'],(i,j)=>{
			$('<div/>')
			.text(j)
			.css({width: '100%',
	            height: '50px',
	            border: '1px solid black',
	            'text-align' : 'center'
			})
			.appendTo('#left')
			.click(function() {
				$(this).css({'background-color':'yellow'})
				$(this).siblings().css({'background-color':'white'})
				alert($(this).text()+'클릭')
				_ = $.ctx()
				switch ($(this).text()) {
				case 'NAVER':
					$.getJSON(_+'/crawls/naver',d=>{
						$('#right').empty()
						$.each(d,(i,j)=>{
							$('<div/>')
							.html('<h1>'+j.origin+'</h1><h4>'+j.teans+'</h4>')
							.css({width: '40%',
					              height: '40%',
					              border: '3px solid red',
					              float:'left','text-align' : 'center'})
							.appendTo('#right')
						})
					})
					break;
				case 'CGV':
					$.getJSON(_+'/crawls/cgv',d=>{
						$('#right').empty()
						$.each(d,(i,j)=>{
							$('<div/>')
							.html('<h4><img style="width:200px;" src="'+j.image+'"></h4>'+'<h3>'+j.title+'</h3><h4>'+j.percent+'</h4>'+j.info)
							.css({
					              border: '3px solid red',
					              float:'left','text-align' : 'center'})
							.appendTo('#right')
						})
					})
					break;
				case 'BUGS':
					list(0)
					break;
				}
			})
		})
	}
	let list = x =>{
		$.getJSON(_+'/crawls/bugs/page/'+x,d=>{
			let pager = d.pager;
			let list = d.list;
			$('#right').empty()
			$('<table id="content"><tr id="head"></tr></table>')
			.css({width: '99%',
					height: '50px',
	              border: '1px solid black'})
			.appendTo('#right')
			$.each(['앨범','NO','제목','가수'],(i, j)=>{
				$('<th/>')
				.html('<b>'+j+'</b>')
				.css({width: '25%',height: '100%',
		              border: '1px solid black'})
				.appendTo('#head')
			})
		   $.each(list,(i,j)=>{
			   $('<tr><td><img src="'+j.thumbnail+'"></td><td>'+j.seq+'</td><td>'+j.title+'</td><td>'+j.artist+'</td></tr>')
			   .css({width: '25%',height: '100%',
			          border: '1px solid black',
			        	  'text-align' : 'center'})
			   .appendTo('#content tbody')  
		   })
		   $('#content tr td').css({border: '1px solid black'})
		  
		   $('<div/>',{
			   id:'pagination'
		   })
		   .css({width: '50%',height: '50px',margin:'20px auto'})
		   .appendTo('#right')
		 /*페이지네이션*/
		   if(pager.existPrev){
			   $('<span/>')
			   .text('이전')
			   .css({width: '50px',height: '30px',display:'inline-block',
			              border: '1px solid black','text-align' : 'center'})
			   .appendTo('#pagination')
			   .click(()=>{
				   app.list(pager.prevBlock)
			   })
		   }
			let i = pager.startPage
			for(;i<=pager.endPage; i++){
			   $('<span/>')
			   .text(i+1)
			   .css({width: '30px',height: '30px',display:'inline-block',
			              border: '1px solid black','text-align' : 'center'})
			   .appendTo('#pagination')
			   .click(function(){
				   let page = parseInt($(this).text())
				   app.list(page-1)
			   }) 
		   }
		   if(pager.existNext){
			   $('<span/>')
			   .text('다음')
			   .css({width: '50px',height: '30px',display:'inline-block',
			              border: '1px solid black','text-align' : 'center'})
			   .appendTo('#pagination')
			   .click(()=>{
				   alert('다음페이지')
				   app.list(pager.nextBlock)
			   })
		   }
		 /*  $.each(['1','2','3','4','5'],(i,j)=>{
				 $('<span>)'+j+'</span>')
				.css({width: '30px',height: '30px',display:'inline-block',
                              border: '1px solid black'})	
	   			.appendTo('#pagenation')
				.click(()=>{})
			})*/
		})
	}
	
	return {run,list}
})()