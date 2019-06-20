$(function(){
    $('.tab-bar>div').on('click',function(){
        var index=$(this).index();
       $('.tab-bar>div').removeClass('active')
       $('.tab-bar>div').eq(index).addClass('active')
       $('.tab-container').removeClass('show')
       $('.tab-container').eq(index).addClass('show')
    })

    $('.point-bar>div').on('click',function(){
        var index=$(this).index();
       $('.point-bar>div').removeClass('active')
       $('.point-bar>div').eq(index).addClass('active')
       $('.point-main>div').removeClass('show')
       $('.point-main>div').eq(index).addClass('show')
    })

})