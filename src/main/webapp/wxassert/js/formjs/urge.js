$(document).ready(function(){
    getOrderId();

})
function getOrderId(){
    var orderId=window.localStorage.getItem("orderid");
    $("input[name='orderId']").text(orderId);
    if(null==orderId||""==orderId){
        return false;
        history.back();
    }
}
//提交催单信息
function submit(){
    var orderId = $("input[name='orderId']").text();
    var type= $("input[name='type']:checked").val();
    var content =$("input[name='content']").val();
    if("3"==type){
        if(""==content||null==content){
            return;
        }
    }
}

function clearContent(){
    var type= $("input[name='type']:checked").val();
    if("1"==type||"2"==type){
        $("#content").attr("disabled",true);
        $("input[name='content']").val("");
    }else{
        $("#content").removeAttr("disabled");
    }

}

function orderFresh(message){
    var conform = layer.open({
        content: message,
        btn: ['确认'],
        shadeClose: false,
        yes: function() {
            //todo 点击确认按钮
            layer.close(conform);
            window.localStorage.setItem("source","order");
            mui.openWindow({
                id: "order",
                url: "../index.html",
                show: {
                    aniShow: aniShow
                },
                waiting: {
                    autoShow: false
                }
            });

        },
        no: function() {
            layer.close(conform);
            window.localStorage.setItem("source","order");
            mui.openWindow({
                id: "order",
                url: "../index.html",
                show: {
                    aniShow: aniShow
                },
                waiting: {
                    autoShow: false
                }
            });
        }
    });
}
