mui.init({
	swipeBack: true, //启用右滑关闭功能
	//预加载
	preloadPages: [{
		id: 'pro',
		url: 'views/pro.html',
	}],
	preloadLimit: 5, //预加载窗口数量限制(一旦超出,先进先出)默认不限制
});
//首页返回键处理
//处理逻辑：1秒内，连续两次按返回键，则退出应用；
var first = null;
mui.back = function() {
	if ($("a[href='index']").hasClass("mui-active")) {
		//首次按键，提示‘再按一次退出应用’
		if (!first) {
			first = new Date().getTime();
			mui.toast('再按一次退出应用');
			setTimeout(function() {
				first = null;
			}, 1000);
		} else {
			if (new Date().getTime() - first < 1000) {
				plus.runtime.quit();
			}
		}
	} else {
		if (viewApi.canBack()) { //如果view可以后退，则执行view的后退
			viewApi.back();
		} else { //执行webview后退
			oldBack();
		}
	}
};
//下拉刷新
(function($) {
	//阻尼系数
	var deceleration = mui.os.ios ? 0.003 : 0.0009;
	$('.mui-scroll-wrapper').scroll({
		bounce: false,
		indicators: true, //是否显示滚动条
		deceleration: deceleration
	});
	$.ready(function() {
		//订单下拉刷新，上拉加载。
		$.each(document.querySelectorAll('.mui-slider-group .mui-scroll'), function(index, pullRefreshEl) {
			$(pullRefreshEl).pullToRefresh({
				down: {
					callback: function() {
						var self = this;
						setTimeout(function() {
							//todo 下拉刷新
							self.endPullDownToRefresh();
						}, 1000);
					}
				},
				up: {
					callback: function() {
						var self = this;
						setTimeout(function() {
							//todo上拉加载下一页
							self.endPullUpToRefresh();
						}, 1000);
					}
				}
			});
		});
		//产品和消息列表下拉刷新，上拉加载。
		$.each(document.querySelectorAll('#proList,#messageList'), function(index, pullRefreshEl) {
			$(pullRefreshEl).pullToRefresh({
				down: {
					callback: function() {
						var self = this;
						setTimeout(function() {
							//todo 下拉刷新
							self.endPullDownToRefresh();
						}, 1000);
					}
				},
				up: {
					callback: function() {
						var self = this;
						setTimeout(function() {
							//todo上拉加载下一页
							self.endPullUpToRefresh();
						}, 1000);
					}
				}
			});
		});
	});
})(mui);

//其他
$().ready(function() {

	/**个人中心点击地址管理返回到个人中心tab**/
	if (window.localStorage.getItem("source") == 'my') {
		//		$("a[href='#index']").removeClass("mui-active");
		//		$("#index").removeClass("mui-active");
		$("a[href='#my']").addClass("mui-active");
		$("#my").addClass("mui-active");
	}else if (window.localStorage.getItem("source") == 'order') {
		//		$("a[href='#index']").removeClass("mui-active");
		//		$("#index").removeClass("mui-active");
		$("a[href='#order']").addClass("mui-active");
		$("#order").addClass("mui-active");
	} else {
		$("a[href='#index']").addClass("mui-active");
		$("#index").addClass("mui-active");
	}
	//页面刷新就要清空localStorage,防止后面错误
	window.localStorage.clear();
	//加载未完成的订单
	//pulldownRefresh();

	/**轮播**/
	var slider = mui("#slider");
	slider.slider({
		interval: 5000
	});

	/**首页服务下单点击事件**/
	$('#goAz,#goWx,#goYj,#goBy').on('tap', function() {
        var serviceType = $(this).attr("id");
        //服务类型转义
                if("goAz"==serviceType){
                    serviceType="T02";
                }else if("goWx"==serviceType){
                    serviceType="T01";
                }else if("goYj"==serviceType){
                    serviceType="T12";
                }else{
                    serviceType="T15";
                }
             window.localStorage.setItem("serviceType", serviceType);
			if ($(this).attr("id") == "goBy") {
				mui.openWindow({
					id: "baoyang",
					url: "views/baoyang.html",
					show: {
						aniShow: aniShow
					},
					waiting: {
						autoShow: false
					}
				});
			} else {
				mui.openWindow({
					id: "pro",
					url: "views/pro.html",
					show: {
						aniShow: aniShow
					},
					waiting: {
						autoShow: false
					}
				});
			}

		})
		/**首页快捷下单点击事件**/
	$('#bx,#cd,#xyj,#yyj,#kt').on('tap', function() {
		mui.openWindow({
			id: "ordering",
			url: "views/ordering.html",
			show: {
				aniShow: aniShow
			},
			waiting: {
				autoShow: false
			}
		});
	})

	/**订单页面下单按钮**/
	$('#item1,#item2,#item3,#item4').on('tap', 'button', function() {
			$("a[href='#order']").removeClass("mui-active");
			$("#order").removeClass("mui-active");
			$("a[href='#index']").addClass("mui-active");
			$("#index").addClass("mui-active");
		})
		/**订单详情**/
	$('#goDetail').on('tap', function() {
			mui.openWindow({
				id: "detail",
				url: "views/detail.html?source=order",
				show: {
					aniShow: aniShow
				},
				waiting: {
					autoShow: false
				}
			});
		})
			/**服务评价**/
	$('#goEvaluate').on('tap', function() {
			mui.openWindow({
				id: "evaluate",
				url: "views/evaluate.html?source=order",
				show: {
					aniShow: aniShow
				},
				waiting: {
					autoShow: false
				}
			});
		})
		/**个人中心页面点击常用地址**/
	$('#goAddress').on('tap', function() {
			mui.openWindow({
				id: "address",
				url: "views/address.html?source=my",
				show: {
					aniShow: aniShow
				},
				waiting: {
					autoShow: false
				}
			});
		})
		/**个人中心页面点击登录**/
	$('#goLogin').on('tap', function() {
			mui.openWindow({
				id: "login",
				url: "views/login.html?source=my",
				show: {
					aniShow: aniShow
				},
				waiting: {
					autoShow: false
				}
			});
		})
		/**个人资料修改相关js开始**/
		//时间控件
	var optionsJson = $("#birthday > span").attr('data-options') || '{}';
	var options = JSON.parse(optionsJson);
	var picker = new mui.DtPicker(options);
	$("#birthday").on('tap', function() {
		picker.show(function(rs) {
			$("#birthday > span").text(rs.text);
		});
	}, false);
	//点击选择性别
	$("a[href='#sex']").on("tap", function() {
			if ($("a[href='#sex'] >  span").text() == "女") {
				$("#chooseSex li[value='0']").show();
			} else if ($("a[href='#sex'] >  span").text() == "男") {
				$("#chooseSex li[value='1']").show();
			} else {
				$("#chooseSex li[value='-1']").show();
			}
		})
		//选择性别回填
	$("#chooseSex li").on("tap", function() {
			$("#chooseSex span").hide();
			$(this).find("span").show();
			if ($(this).val() == 0) {
				$("a[href='#sex'] >  span").text("女");
			} else if ($(this).val() == 1) {
				$("a[href='#sex'] >  span").text("男");
			} else {
				$("a[href='#sex'] >  span").text("保密");
			}
			mui.back();
		})
		//点击修改真实姓名
	$("a[href='#name']").on("tap", function() {
		$("#editName").val($("a[href='#name'] >  span").text());
		$("#editName").focus();
	});
	//修改姓名保存
	$("#saveName").on("tap", function() {
			$("a[href='#name'] >  span").text($("#editName").val());
			mui.back();
		})
		//点击修改个人说明
	$("a[href='#statement']").on("tap", function() {
		$("#edtiStatement").val($("a[href='#statement'] >  span").text());
		$("#edtiStatement").focus();
	});
	//修改姓名保存
	$("#saveStatement").on("tap", function() {
			$("a[href='#statement'] >  span").text($("#edtiStatement").val());
			mui.back();
		})
		/**个人资料修改相关js结束**/
		//常见问题
	$("#goCjwt").on("tap", function() {
			mui.openWindow({
				id: "help",
				url: "views/help.html?source=my",
				show: {
					aniShow: aniShow
				},
				waiting: {
					autoShow: false
				}
			});
		})
		//使用技巧
	$("#goSyjq").on("tap", function() {
		mui.openWindow({
			id: "tip",
			url: "views/tip.html?source=my",
			show: {
				aniShow: aniShow
			},
			waiting: {
				autoShow: false
			}
		});
	})
	
	 //首页保养套餐-冰箱
    $("#baoyang1").on("tap", function() {
        window.localStorage.setItem("byFrom","GOOD_BY_6");
        mui.openWindow({
            id: "baoyang-by1",
            url: "views/baoyang.html",
            show: {
                aniShow: aniShow
            },
            waiting: {
                autoShow: false
            }
        });

    })
    //首页保养套餐--空调
    $("#baoyang2").on("tap", function() {
        window.localStorage.setItem("byFrom","GOOD_BY_2");
        mui.openWindow({
            id: "baoyang-by2",
            url: "views/baoyang.html",
            show: {
                aniShow: aniShow
            },
            waiting: {
                autoShow: false
            }
        });
    })

    //首页保养套餐--油烟机
    $("#baoyang3").on("tap", function() {
        window.localStorage.setItem("byFrom","GOOD_BY_3");
        mui.openWindow({
            id: "baoyang-by3",
            url: "views/baoyang.html",
            show: {
                aniShow: aniShow
            },
            waiting: {
                autoShow: false
            }
        });
    })

    //首页保养套餐--全家套餐
    $("#baoyang4").on("tap", function() {
        //window.localStorage.setItem("byFrom","GOOD_TC_4");
        window.localStorage.setItem("browId","GOOD_TC_4");
        mui.openWindow({
            id: "ordering-by4",
            url: "views/ordering-by.html",
            show: {
                aniShow: aniShow
            },
            waiting: {
                autoShow: false
            }
        });
    })

    //首页保养套餐--滚筒
    $("#baoyang5").on("tap", function() {
        window.localStorage.setItem("byFrom","GOOD_BY_11");
        mui.openWindow({
            id: "baoyang-by5",
            url: "views/baoyang.html",
            show: {
                aniShow: aniShow
            },
            waiting: {
                autoShow: false
            }
        });
    })

    //首页维修1
    $("#weixiu1").on("tap", function() {
        window.localStorage.setItem("serviceType","T01");
        window.localStorage.setItem("twoProdCode","DJD204");
        window.localStorage.setItem("twoProdName","空调");
        mui.openWindow({
            id: "orderingWX1",
            url: "views/ordering.html",
            show: {
                aniShow: aniShow
            },
            waiting: {
                autoShow: false
            }
        });
    })

//首页维修1
$("#weixiu2").on("tap", function() {
    window.localStorage.setItem("serviceType","T01");
    window.localStorage.setItem("twoProdCode","DJD201");
    window.localStorage.setItem("twoProdName","冰箱");
    mui.openWindow({
        id: "orderingWX2",
        url: "views/ordering.html",
        show: {
            aniShow: aniShow
        },
        waiting: {
            autoShow: false
        }
    });
})

//首页维修1
$("#weixiu4").on("tap", function() {
    window.localStorage.setItem("serviceType","T01");
    window.localStorage.setItem("twoProdCode","CHW208");
    window.localStorage.setItem("twoProdName","吸油烟机");
    mui.openWindow({
        id: "orderingWX3",
        url: "views/ordering.html",
        show: {
            aniShow: aniShow
        },
        waiting: {
            autoShow: false
        }
    });

});
//首页维修
$("#weixiu3").on("tap", function() {
    window.localStorage.setItem("serviceType","T01");
    window.localStorage.setItem("twoProdCode","DJD206");
    window.localStorage.setItem("twoProdName","电视");
    mui.openWindow({
        id: "orderingWX4",
        url: "views/ordering.html",
        show: {
            aniShow: aniShow
        },
        waiting: {
            autoShow: false
        }
    });
})

//首页维修1
$("#weixiu5").on("tap", function() {
    window.localStorage.setItem("serviceType","T01");
    window.localStorage.setItem("twoProdCode","DJD203");
    window.localStorage.setItem("twoProdName","洗衣机");
    mui.openWindow({
        id: "orderingWX5",
        url: "views/ordering.html",
        show: {
            aniShow: aniShow
        },
        waiting: {
            autoShow: false
        }
    });

});
 //分类页面处理
    $("div[dalei]").on("tap", function() {
        var dalei =$(this).attr("dalei");
        window.localStorage.setItem("FL-DL",dalei);
        $('#serviceTypeX').show();
        $('#oneProd').hide();
    })
	$("div[serviceTypeFL]").on("tap", function() {
        var serviceTypeFL =$(this).attr("serviceTypeFL");
        window.localStorage.setItem("serviceType",serviceTypeFL);
     goProPage();
        //$('#serviceTypeX').show();
    })
});

$("a[test='goDetail']").on('tap', function() {
                window.localStorage.setItem("orderid",$(this).attr("orderid"));
                mui.openWindow({
                    id: "detail",
                    url: "views/detail.html?source=order",
                    show: {
                        aniShow: aniShow
                    },
                    waiting: {
                        autoShow: false
                    }
                });
            })
function goProPage(){
    var serviceType = window.localStorage.getItem("serviceType");
    if("T15"==serviceType){
        mui.openWindow({
            id: "baoyang",
            url: "views/baoyang.html",
            show: {
                aniShow: aniShow
            },
            waiting: {
                autoShow: false
            }
        });
    }else{

        mui.openWindow({
            id: "pro",
            url: "views/pro.html",
            show: {
                aniShow: aniShow
            },
            waiting: {
                autoShow: false
            }
        });
    }

}