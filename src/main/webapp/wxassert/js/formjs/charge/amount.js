/**
 * Created by dipu on 2016/5/9.
 */
/**
 * Created by dipu on 2016/3/14.
 */
//获取get方法传参
(function($) {
    $.extend({
        urlGet:function()
        {
            var aQuery = window.location.href.split("?");  //取得Get参数
            var aGET = new Array();
            if(aQuery.length > 1)
            {
                var aBuf = aQuery[1].split("&");
                for(var i=0, iLoop = aBuf.length; i<iLoop; i++)
                {
                    var aTmp = aBuf[i].split("=");  //分离key与Value
                    aGET[aTmp[0]] = aTmp[1];
                }
            }
            return aGET;
        }
    })
})(jQuery);
$().ready(function(){
    //获取get来的参数
    var GET = $.urlGet(); //获取URL的Get参数
    var id = GET['id']; //取得值
    //赋值
    $.post("../../data/charge/amount"+id+".json",{}, function (data) {
        //将json字符串转成json对象,正式环境需要放开注释
        data=$.parseJSON(data);
        if (data.status=="OK"){
            var dataTemplate = Handlebars.compile($("#amount-template").html());
            $("#amountInfo").html(dataTemplate(data.data));
        }
    });
})