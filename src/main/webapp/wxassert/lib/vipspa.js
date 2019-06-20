/**
 * Created by dipu on 2016/3/2
 *
 * 轻量级模板
 * 修改记录
 * 时间           内容                 修改人
 * 2016-3-5       增加styles支持        dipu
 * 使用如下:
 * vipspa.start({
			view: '#ui-view',
			router: {
				'login': {
					templateUrl: 'views/login/login.html',
					style: 'css/login/login.css',
					controller: 'js/login/login.js'
				},
				'register': {
					templateUrl: 'views/login/register.html',
					style: 'css/login/register.css',
					controller:'js/login/register.js'
				},
				'wpass': {
					templateUrl: 'views/login/wpass.html',
					style: 'css/login/wpass.css',
					controller:'js/login/wpass.js'
				},
				'wpass_2': {
					templateUrl: 'views/login/wpass_2.html',
					style: 'css/login/wpass_2.css',
					controller:'js/login/wpass_2.js'
				},
				'wpass_3': {
					templateUrl: 'views/login/wpass_3.html',
					style: 'css/login/wpass_3.css',
					controller:'js/login/wpass_3.js'
				},
				'wpass_4': {
					templateUrl: 'views/login/wpass_4.html',
					style: 'css/login/wpass_4.css',
					controller:'js/login/wpass_4.js'
				},
				'success': {
					templateUrl: 'views/login/success.html',
					style: 'css/login/success.css',
					controller:'js/login/success.js'
				},
				'defaults': 'login' //默认路由
			},
			errorTemplateId: '#error'  //可选的错误模板，用来处理加载html模块异常时展示错误内
		});
 */
(function(){
    function Vipspa(){
        
    }
    Vipspa.prototype.start = function(config){
        var self = this;
        self.routerMap = config.router;
        self.mainView = config.view;
        self.errorTemplateId = config.errorTemplateId;
        startRouter();
        window.onhashchange = function(){
            startRouter();
        };
    };
    var messageStack = [];
    // {
    //     'id': 'home_bindcard',
    //     'content': {
    //     }
    // }
    Vipspa.prototype.getMessage = function(id){
        var msg = {};
        $.each(messageStack,function(i,e){
            if(e.id===id){
                msg = e;
            }
        });
        return msg;
    };

    Vipspa.prototype.setMessage = function(obj){
        var _obj = JSON.parse(JSON.stringify(obj));
        $.each(messageStack,function(i,e){
            if(e.id===_obj.id){
                e = _obj;
                return false;
            }
        });
        messageStack.push(_obj);
    };
    Vipspa.prototype.delMessage = function(id){
        if(typeof id==='undefined'){
            return false;
        }
        var index = 0;
        $.each(messageStack,function(i,e){
            if(e.id===id){
                index = i;
            }
        });
        $.each(messageStack,function(i,e){
            if(i>index){
                messageStack[i-1] = e;
            }
        });
    };
    Vipspa.prototype.clearMessage = function(id){
        var index = 0;
        messageStack = [];
    };
    
    Vipspa.prototype.stringify = function(routerUrl,paramObj){
        var paramStr='' ,hash;
        for(var i in  paramObj){
            paramStr += i + '=' + encodeURIComponent(paramObj[i]) + '&';
        }
        if(paramStr === ''){
            hash = routerUrl;
        }
        else{
            paramStr = paramStr.substring(0,paramStr.length-1);
            hash = routerUrl + '?' + paramStr;
        }
        return hash;
    };
    Vipspa.prototype.parse = function(routerHash){
        var hash = typeof routerHash ==='undefined'?location.hash:routerHash;
        var obj = {
            url:'',
            param: {}
        };
        var param = {},url='';
        var pIndex = hash.indexOf('?');
        if(hash===''){
            return obj;
        }

        if(pIndex>-1){
            url = hash.substring(1,pIndex);
            var paramStr = hash.substring(pIndex+1);
            var paramArr = paramStr.split('&');
            
            $.each(paramArr,function(i,e){
                var item = e.split('='),
                    key,
                    val;
                key = item[0];
                val = item[1];
                if(key!==''){
                    param[key] = decodeURIComponent(val);
                }
                

            });
        }
        else{
            url = hash.substring(1);
            param = {};
        }
        return {
            url:url,
            param: param
        };
    };
    function routerAction (routeObj){
        var routerItem = vipspa.routerMap[routeObj.url];
        if(typeof routerItem==='undefined'){
            var defaultsRoute = vipspa.routerMap.defaults;
            routerItem = vipspa.routerMap[defaultsRoute];
            location.hash = defaultsRoute;
            return false;
        }
        
        $.ajax({
            type: 'GET',
            url: routerItem.templateUrl,
            dataType: 'html',
            success: function(data, status, xhr){
                $(vipspa.mainView).html(data);
                if(routerItem.controller !=undefined && routerItem.controller !='undefined' && routerItem.controller.length>0)
                loadScript(routerItem.controller);
                if(routerItem.style !=undefined && routerItem.style !='undefined' &&routerItem.style.length>0)
                loadStyle(routerItem.style);
            },
            error: function(xhr, errorType, error){
                if($(vipspa.errorTemplateId).length===0){
                    return false;
                }
                var errHtml = $(vipspa.errorTemplateId).html();
                errHtml = errHtml.replace(/{{errStatus}}/,xhr.status);
                errHtml = errHtml.replace(/{{errContent}}/,xhr.responseText);
                $(vipspa.mainView).html(errHtml);
            }
        });
    }
   
    function startRouter  () {
        var hash = location.hash;
        var routeObj = vipspa.parse(hash);
        routerAction(routeObj);
    }
    
    function loadScript(src, callback) {
        var links=src.split(",");
        $.each(links,function(i,item){
            var script = document.createElement('script'),
                loaded;
            script.setAttribute('src', item);
            //增加class属性，在加载其他页面的时候删除这个class的script，防止冲突
            script.setAttribute("class","zheyangyinggaibuhuiyouxiangtongdebajs");
            //加载之前删除别的页面的js
            $(".zheyangyinggaibuhuiyouxiangtongdebajs").remove();
            //script.onreadystatechange = script.onload = function() {
            //    script.onreadystatechange = null;
            //    document.documentElement.removeChild(script);
            //    script = null;
            //    if (!loaded) {
            //        if(typeof callback==='function')
            //            callback();
            //    }
            //    loaded = true;
            //};
            document.documentElement.appendChild(script);
        });
    }

    function loadStyle(src, callback) {
        var links=src.split(",");
        $.each(links,function(i,item){
            var link = document.createElement('link');
            link.setAttribute('rel', 'stylesheet');
            link.setAttribute('href', item);
            //增加class属性，在加载其他页面的时候删除这个class的style，防止冲突
            link.setAttribute("class","zheyangyinggaibuhuiyouxiangtongdebacss");
            //加载之前删除别的页面的css
            $(".zheyangyinggaibuhuiyouxiangtongdebacss").remove();
            document.documentElement.appendChild(link);
        });
    }

    window.vipspa = new Vipspa();
})();