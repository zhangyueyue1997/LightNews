
function  initPro(){
    var serviceType = window.localStorage.getItem("serviceType");
    var twoProdCode = window.localStorage.getItem("twoProdCode");
    var twoProdName = window.localStorage.getItem("twoProdName");
    if("T01"==serviceType){
        $("div[name='gzlx']").show();
    }
    $('#TwoProName').text(twoProdName);
    $('#goCharge').val(twoProdCode);

    //$('#TwoProName').text(twoProdName);
    $('#imgCope').attr("src","../img/product/"+twoProdCode+".png" )
    //保存到页面上
    $("input[name='serviceType']").text(serviceType);
    $("input[name='twoProdCode']").text(twoProdCode);
    //获取三级和鼓掌类别
    getThree(twoProdCode);
    //判断是否有快捷地址
    getAddressK();
}

function getThree(twoProdCode){
}
var cityPicker3 = new mui.PopPicker({
    layer: 3
});
function getRegionData(){
	   // cityPicker3.setData(cityData3);
}

function getAddressK(){
    if(window.localStorage.getItem("address")!=null&&window.localStorage.getItem("address")!="null"){
        var address = JSON.parse(window.localStorage.getItem("address"));
        $(".contacts").html(address.name+"<span>"+address.phone+"</span>");
        $(".address").html(address.address);
        //window.localStorage.setItem("address",null);
        $('#addressK').show();
        $("input[name='customerName']").val(address.name);
        $("input[name='mobilePhone']").val(address.phone);
        $("input[id='adminCode']").val(address.region);

        $("input[name='louceng']").val(address.loupai);
        address.address=address.address.substr(address.address.lastIndexOf('-'),address.address.length-1);
        $("input[id='address']").val(address.address);
        window.localStorage.setItem("address",null);
        return false;
    }else{
    }


}

//下单
function  orderAccept(){
//    var loading = layer.open({
//        type: 2,
//        shadeClose: false,
//        time: 5
//    });
    //关闭加载条
    //layer.close(loading);
    //参数接入
    //服务类型、hpID(后台查询) 、二级类型、三级类型、故障类型\
    var serviceType =$("input[name='serviceType']").text();
    var twoProdCode =$("input[name='twoProdCode']").text();
    var threePordCodeName = $('button.btn-pro.mui-btn-primary').text();
    var ThreeB = $('button.btn-gz.mui-btn-primary');
    var ThreeBString ="";

    $.each(ThreeB, function(index, onePro) {
        ThreeBString  +=$(this).text()+",";
    });
    ThreeB=ThreeBString.substring(0,ThreeBString.length-1);
    //服务内容、服务时间、品牌名称
    var serviceContent=$('#serviceContent').val();
    var serviceTime=$("input[name='serviceTime']").val();
    var brandNm=$("input[name='brandNm']").val();

    //联系人、手机号、详细地址（省市区）、楼层门牌
    var customerName=$("input[name='customerName']").val();
    var mobilePhone=$("input[name='mobilePhone']").val();
    var adminCode=$("input[id='adminCode']").val();
    var longArea=$("input[id='address']").val();
    var louceng=$("input[name='louceng']").val();
    var TwoProName=$('#TwoProName').text();
    var messageCode=$('#re-mobileCode').val();
    //校验表单信息
    if (isEmpty(serviceType)) {
        return false;
    }
    if (isEmpty(twoProdCode)) {
        return false;
    }
    if (isEmpty(serviceContent)) {
        return false;
    }
    if (isEmpty(serviceTime)) {
        return false;
    }
    if (isEmpty(customerName)) {
        return false;
    }
    if (!(/^1[3|4|5|7|8]\d{9}$/).test(mobilePhone)) {
        return false;
    }
    //地址信息内容校验
    if (isEmpty(louceng)) {
        return false;
    }

    if (isEmpty(adminCode)) {
        return false;
    }
    //数据处理
    //把故障类型加入到服务内容
    if(""!=ThreeBString){
        serviceContent="[故障类型:"+ThreeB+"]"+serviceContent;
    }
    var loading = layer.open({
					type: 2,
					shadeClose: false,
					time: 5
				});
}



function isEmpty(obj) {
    if (!obj) {
        return true;
    }
    if (obj == 'undefined') {
        return true;
    }
    if (obj.length <= 0) {
        return true;
    }
    if (obj == '') {
        return true;
    }
    return false;
}


//发送短信验证码
function sendMessage(){
    var mobile = $("#re-mobile").val();
}

