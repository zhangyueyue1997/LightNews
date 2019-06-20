//注册
function  doRegisteredAccount(){
    var userName = $("#re-userName").val();
    var password = $("#re-password").val();
    var password2 =$("#re-password2").val();
    var mobile = $("#re-mobile").val();
    var mobileCode = $("#re-mobileCode").val();
    if (isEmpty(userName)) {
        return false;
    }
    if (isEmpty(password)) {
        return false;
    }
    if (isEmpty(password2)) {
        return false;
    }
    //字母校验
    if(!(/[a-zA-Z]/).test(password)){
        return false;
    }
    //数字校验
    if(!(/[0-9]/).test(password)){
        return false;
    }
    //长度校验
    if(password.length>18||password.length<6){
        return false;
    }
    if (!(/^1[3|4|5|7|8]\d{9}$/).test(mobile)) {
        return false;
    }
    //判断两次密码是否一致
    if(password2!=password){
        return false;
    }


    //验证码校验、用户名唯一校验、短信验证码是否通过校验

}



//发送短信验证码
function sendMessage(){
    var mobile = $("#re-mobile").val();
}



//登陆操作
function  dologin(){
    var userName = $("#account").val();
    var password = $("#password").val();
    if (isEmpty(userName)) {
        return false;
    }
    if (isEmpty(password)) {
        return false;
    }
    //var from =window.localStorage.getItem("from");
}




(function($) {
    //处理view的后退与webview后退
    var oldBack = $.back;
    $.back = function() {
        if (viewApi.canBack()) { //如果view可以后退，则执行view的后退
            viewApi.back();
        } else { //执行webview后退
            oldBack();
        }
    };
    //监听页面切换事件方案1,通过view元素监听所有页面切换事件，目前提供pageBeforeShow|pageShow|pageBeforeBack|pageBack四种事件(before事件为动画开始前触发)
    //第一个参数为事件名称，第二个参数为事件回调，其中e.detail.page为当前页面的html对象
    view.addEventListener('pageBeforeShow', function(e) {
        //				console.log(e.detail.page.id + ' beforeShow');
        if (e.detail.page.id == "wpass2") {
            checkCode();
            var passwordMobile = window.localStorage.getItem("passwordMobile");
            if(null==passwordMobile||""==passwordMobile){
                e.detail.page.noGo=true;
                return false;
            }
            var passwordMessageCode = window.localStorage.getItem("passwordMessageCode");
            e.detail.page.noGo=false;
        }

        //找回密码第三步
        if (e.detail.page.id == "wpass3") {
            savePassword3();
            var passwordMobile = window.localStorage.getItem("passwordMobile");
            var password = window.localStorage.getItem("Password3-1");
            var password2 = window.localStorage.getItem("Password3-2");

            if(""==password||null==password){
                e.detail.page.noGo=true;
                return false;
            }
           if(""==password2||null==password2){
                e.detail.page.noGo=true;
                return false;
            }
            //字母校验
            if(!(/[a-zA-Z]/).test(password)){
                e.detail.page.noGo=true;
                return false;
            }
            //数字校验
            if(!(/[0-9]/).test(password)){
                e.detail.page.noGo=true;
                return false;
            }
            //长度校验
            if(password.length>18||password.length<6){
                e.detail.page.noGo=true;
                return false;
            }
            //判断两次密码是否一致
            if(password2!=password){
                e.detail.page.noGo=true;
                return false;
            }
        }
    });
    view.addEventListener('pageShow', function(e) {
        //				console.log(e.detail.page.id + ' show');
    });
    view.addEventListener('pageBeforeBack', function(e) {
        //				console.log(e.detail.page.id + ' beforeBack');
        Zepto(".mui-bar.mui-bar-tab").show();
    });
    view.addEventListener('pageBack', function(e) {
        //console.log(e.detail.page.id + ' back');
    });
})(mui);


function forgetPassword(){
    var password = $("#wpass2-password").val();
    var password2 =$("#wpass2-password2").val();
    window.localStorage.setItem("wpass3word",password);
    window.localStorage.setItem("wpass3word2",password2);
}


function checkCode(){
    var passwordMobile = $("#wpass2-mobile").val();
    var passwordMessageCode = $("#passwordMessageCode").val();
    window.localStorage.setItem("passwordMobile",passwordMobile);
    window.localStorage.setItem("passwordMessageCode",passwordMessageCode);
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


//登陆操作

function initUser(){
    NTKF_PARAM = {
        siteid:"kf_1007",//企业ID，必填，取值参见文档开始表
        settingid:"kf_1007_1460513696309",//缺省客服配置ID，必填，取值参见文档开始表
        uid:userAccountId,		//用户ID,未登录可以为空
        uname:userAccountName,//用户名，未登录可以为空
        isvip:         "0" ,//是否为vip用户:1|0
        userlevel:	"5"//网站自定义会员级别
    }
}



//我的产品
function getSspMessageByCus(){

}

function  goLoginPage(){
    var conform = layer.open({
        content: '注册成功，请登录！',
        btn: ['确认'],
        shadeClose: false,
        yes: function() {
            //todo 点击确认按钮
            layer.close(conform);
            history.go(0);
        },
        no: function() {
            layer.close(conform);
            history.go(0);
        }
    });

}

function   savePassword3(){
    var password = $("#wpass2-password").val();
    var password2 =$("#wpass2-password2").val();
    window.localStorage.setItem("Password3-1",password);
    window.localStorage.setItem("Password3-2",password2);

}