function init(){
    var browId =window.localStorage.getItem("browId");
    getBDetail(browId);
    getAddressK();
    //收费标准
}

//根据procode获取详情
function getBDetail(browId){
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
    var browId =window.localStorage.getItem("browId");
    var bproCode =window.localStorage.getItem("bproCode");
    var bproName =window.localStorage.getItem("bproName");
    var bhpProdCode =window.localStorage.getItem("bhpProdCode");
    //服务类型、hpID(后台查询) 、二级类型、三级类型、故障类型\
    var serviceType ="T15";
    var twoProdCode =browId;
    var threePordCodeName = "";
    var ThreeB = "";
    var messageCode=$('#re-mobileCode').val();
    //ThreeB=ThreeBString.substring(0,ThreeBString.length-1);
    //服务内容、服务时间、品牌名称
    var twoProName = $('#proNameText').text();
    var serviceContent="[ 保养内容：" + twoProName + "，数量："+$('#test').val()+"]"+$('#serviceContent').val();
    var serviceTime=$("input[name='serviceTime']").val();
    var brandNm=$("input[name='brandNm']").val();

    //联系人、手机号、详细地址（省市区）、楼层门牌
    var customerName=$("input[name='customerName']").val();
    var mobilePhone=$("input[name='mobilePhone']").val();
    var adminCode=$("input[id='adminCode']").val();
    var longArea=$("input[id='address']").val();
   // var louceng=longArea+$("input[name='louceng']").val();
    var louceng=$("input[name='louceng']").val();
    var TwoProName=bproName;

    var moneyS=$('#moneyT').text();
    var num=$('#test').val();
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
    //报修内容校验
    if (isEmpty(louceng)) {
        return false;
    }
    if (isEmpty(adminCode)) {
        return false;
    }
    if(num>1){
        serviceContent=serviceContent+"[数量："+num+"]";
    }
    //加载条
    var loading = layer.open({
        type: 2,
        shadeClose: false,
        time: 5
    });
    //数据处理
    //把故障类型加入到服务内容
//    if(""!=ThreeBString){
//        serviceContent="[故障类型]为"+ThreeBString+serviceContent;
//    }
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

function moneyChange(){
    var num =$('#test').val();
    var oneMoney=$('#moneyS').text();
    var moneyChange =num*oneMoney;
    //$('#moneyS').text(moneyChange);
    $('#moneyT').text(moneyChange);
    $('#sfk').text(moneyChange);

}

function goPay(id){
    var loading = layer.open({
        type: 2,
        shadeClose: false,
        time: 5
    });
}