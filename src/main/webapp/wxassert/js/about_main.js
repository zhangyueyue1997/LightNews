$(document).ready(function(){
$(".icon-list-ul").click(function(){
    $("nav ul").slideToggle("slow");
  });
   $(function() {
        $("#hwslider").hwSlider({
            height: 250,
            autoPlay: true,
            arrShow: true,
            dotShow: true,
            touch: true
        });
    });

//回到顶部按钮***********************************************************
$(function(){//回到顶部按钮函数
    var clientH = document.documentElement.clientHeight; //获取当前视口高度
    var backTop = $('#gotop');
    $(window).on('scroll',function(){
        var osTop=document.documentElement.scrollTop||document.body.scrollTop; //计算的当前高度，兼容IE及其他
        if (osTop >= clientH/2) {
            backTop.fadeIn(200);
            backTop.css('display', 'block');
        } else {
            backTop.fadeOut(200);
        }
    });
    backTop.click(function(){
        $('body,html').animate({scrollTop:0}, 360);
    })
});
$(function(){
  $(".tab a").click(function(){
    $(this).siblings().removeClass('active');
    $(this).addClass('active');
    $('.lists').removeClass('show');
      $('.lists').eq($(this).index()).addClass('show');
   })

});

$('.j-desktop').click(function(){
	$('.w-q a').removeClass("active");
	$(this).addClass('active');
	$('.zhu').css('max-width','1920px')
})
$('.j-tablet').click(function(){
	$('.w-q a').removeClass("active");
	$(this).addClass('active');
	$('.zhu').css('max-width','1020px')
})
$('.j-mobile').click(function(){
	$('.w-q a').removeClass("active");
	$(this).addClass('active');
	$('.zhu').css('max-width','440px')
})
});
