$(document).ready(function(){
        //获取一级类别
    getOneProd();
    $("button").on("tap", function() {
        var twoProdCode = $(this).attr("id");
        var twoProdName =$(this).text();
        window.localStorage.setItem("twoProdCode",twoProdCode);
        window.localStorage.setItem("twoProdName",twoProdName);
        putParamOrderingPage(twoProdCode);
        mui.openWindow({
            id: "ordering",
            url: "ordering.html",
            show: {
                aniShow: aniShow
            },
            waiting: {
                autoShow: false
            }
        });
    })
    $(".btn-gz").click(function() {
        if ($(this).hasClass('mui-btn-primary')) {
            $(this).removeClass("mui-btn-primary");
        } else {
            $(this).addClass("mui-btn-primary");
        }
    })
    $(".btn-pro").click(function() {
        if ($(this).hasClass('mui-btn-primary')) {
            $(".btn-pro").removeClass("mui-btn-primary");
            $(this).removeClass("mui-btn-primary");
        } else {
            $(".btn-pro").removeClass("mui-btn-primary");
            $(this).addClass("mui-btn-primary");
        }
    })

})


function  getOneProd(){

}

function zhankai(){
    var dalei =window.localStorage.getItem("FL-DL");
    if("DJD"==dalei){
        $("li[onecode=DJD]").addClass("mui-active");
    }else if("CHW"==dalei){
        $("li[onecode=CHW]").addClass("mui-active");
    }else if("JK"==dalei){
        $("li[onecode=JK]").addClass("mui-active");
    }else if("DN"==dalei){
        $("li[onecode=DN]").addClass("mui-active");
    }else if("QT"==dalei){
        $("li[onecode=QT]").addClass("mui-active");
    }else if("SHH"==dalei){
        $("li[onecode=SHH]").addClass("mui-active");
    }else{
        $("li[onecode=DJD]").addClass("mui-active");
    }
    window.localStorage.setItem("FL-DL","");
}

function  getTwoProd(oneProdCode){
}


function putParamOrderingPage(twoProdCode){
}

