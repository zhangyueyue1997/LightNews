/**
 * Created by dipu on 2016/3/14.
 */
$().ready(function(){
    //二级
    $("[dataId]").on("tap",function(){
        var id=$(this).attr("dataId");
        //赋值
    })
    //首页三级
    $("[topicId]").on("tap",function(){
        var topicId=$(this).attr("topicId");
        //赋值
    })
    //三级
    $("#faqInfo").on("tap","[topicId]",function(){
        var topicId=$(this).attr("topicId");
        //赋值
    })
})