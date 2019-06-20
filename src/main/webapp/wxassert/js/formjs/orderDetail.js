
function getOrderId(){
    var orderId =window.localStorage.getItem("orderid");
    //var theRequest = new Object();
    $("input[name='orderId']").text(orderId);
    if(null!=orderId){
        tiaozhuan(orderId);
    }else{
    }
}

//获取工单详情
function tiaozhuan(orderId){
    $('#orderDetail').html("");
}

//好评差评处理
function haoping(sspEvaluate){
    //是否师傅本人上门
    var engineerCheck=sspEvaluate.engineerCheck;
    //好中差
    var satisCheck = sspEvaluate.satisCheck;
    //服务速度
    var speedSatis =sspEvaluate.speedSatis;
    //服务态度
    var tdSatis =sspEvaluate.tdSatis;
    //服务技能
    var tecSatis =sspEvaluate.tecSatis;
    if("Y"==satisCheck){
        $('#hzcpy').attr("src", "../img/hp-active.png")
    }else if("M"==satisCheck){
        $('#hzcpm').attr("src", "../img/zp-active.png")
    }else{
        $('#hzcpn').attr("src", "../img/cp-active.png")
    }

    for(var i=0;i<speedSatis;i++){
        $('#serSuDu').find("li").eq(i).addClass("hover");
    }
    for(var i=0;i<tdSatis;i++){
        $('#serTai').find("li").eq(i).addClass("hover");
    }
    for(var i=0;i<tecSatis;i++){
        $('#serPower').find("li").eq(i).addClass("hover");
    }
    //是否师傅本人上门
    if("N"==engineerCheck){
        $("input[name='sex'][value=N]").attr("checked",true);
    }else{
        $("input[name='sex'][value=Y]").attr("checked",true);
    }

}

function dateTo(value){
    if (value == undefined) {
        return "";
    }
    if (value == ""||null ==value ) {
        return "";
    }
    var date = new Date(value);
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    if(month<10){
        month = "0" + month;
    }
    if(day<10){
        day = "0" + day;
    }
    if(hour <10){
        hour = "0" + hour;
    }
    if(minute <10){
        minute = "0" + minute;
    }
//    if(second <10){
//        second = "0" + second ;
//    }
    //var second = date.getSeconds();
    var dateStr = year + '-' + month + '-' + day+' '+hour+':'+minute;
    return dateStr;
}
