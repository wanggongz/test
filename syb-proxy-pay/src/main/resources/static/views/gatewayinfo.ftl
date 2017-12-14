<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,user-scalable=no" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no, email=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<title>订单支付</title>
<meta name="keywords" content="" />
<meta name="Description" content="">

<link
	href="/views/css/global.min.css"
	type="text/css" rel="stylesheet" />
<link rel="stylesheet"
	href="/views/css/detail.css">

<script type="text/javascript" src="/views/js/my/my.js"></script>
<script src="/views/js/lib/jquery-1.8.1.min.js"></script>
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js?v=160719"
	charset="utf-8"></script>

<script type="text/javascript">
var url = location.href.split('#')[0];
console.log(url);
<%-- $.ajax({
	url :  '<%=sitePath%>/wxShareConfig',
	type : 'post',
	data : {
		"url" : url
	},
	dataType : 'json',
	success : function(data) {
		var json = JSON.parse(data);
		wx.config({
			debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			appId : json.appid, // 必填，公众号的唯一标识
			timestamp : json.timestamp, // 必填，生成签名的时间戳
			nonceStr : json.noncestr, // 必填，生成签名的随机串
			signature : json.signature,// 必填，签名，见附录1
			jsApiList : [ 'checkJsApi', 'chooseWXPay' ]
		// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});
		wx.ready(function() {
			console.log("微信jssdk加载成功");
		});
		wx.error(function(res) {
			console.log('微信jssdk加载出错' + res);
			// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
		});
	},
	error : function(data) {
		console.log(data);
		/* alert("系统忙碌中，请稍后再试!"); */
	}
}); --%>
</script>


</head>
<body>
	<section class="clearfix" id="productPage">
		<input type="hidden" value="${orderNo}" name="orderno" id="orderno" />
		<div class="info-infocon">
			<h2>保险信息</h2>
			<div class="info-list">
				<ul>
					<li>
						<p>
							<span>产品名称：</span>${body}
						</p>
					</li>
					<li>
						<p style="">
							<span>支付金额：</span>
							<strong id="premium-text" class="ff6"
								style="color: #ff6600;">${totalfee}</strong>元
						</p>
					</li>
				</ul>
			</div>
		</div>
		<div class="wx-payfor-box"></div>
		<div class="wx-payfor border-t">
			<a id="payBtn" href="javascript:void(0);" class="buy-btn">微信支付<span
				id="btn-premium">${totalfee}</span>元
			</a>
		</div>
	</section>
	<script>
	var orderNo = $("#orderno").val();
    (function () {
        var jsApiParameters = {};
        function callpay() {
            My.hideLoad();
            /* wx.chooseWXPay({
                paySign: jsApiParameters["sign"], 
                timestamp: jsApiParameters["timeStamp"],
                nonceStr: jsApiParameters["nonceStr"],
                package: jsApiParameters["package"],
                signType: jsApiParameters["signType"],
                paySign: jsApiParameters["paySign"],
                success: function (res) {
                   // jsApiParameters = {};
                   alert("callpay=================="+res); 
                   //跳转到回调成功页面
                    if(success_url.indexOf('?')>-1){
                        location.href = success_url+"&orderno="+orderNo;
                    }else{
                        location.href = success_url+"?orderno="+orderNo;
                    }

                },
                fail: function (res) {
                	alert("callpay  no=================="+res); 
                    alert(res.err_msg);
                   	alert(JSON.stringify(res)) ; 	
                	if (res.err_msg == "get_brand_wcpay_request:cancel") {
                        alert("支付失败");
                    }
                    else {
                        alert(res.err_msg + "! jsApiParameters:" + data.apiParam);
                    }
                }
            }); */
            onBridgeReady();
        }

        
        function onBridgeReady(){
     	   WeixinJSBridge.invoke(
     	       'getBrandWCPayRequest', {
     	    	  appId:jsApiParameters["appId"], 
     	    	  paySign: jsApiParameters["sign"], 
     	    	  timeStamp: jsApiParameters["timeStamp"],
                  nonceStr: jsApiParameters["nonceStr"],
                  package: jsApiParameters["package"],
                  signType: jsApiParameters["signType"],
                  paySign: jsApiParameters["paySign"],
     	       },
     	       function(res){     
     	           alert(JSON.stringify(res));
     	           if(res.err_msg == "get_brand_wcpay_request:fail" ) {
     	        	   alert("支付失败");
     	           } 
     	       }
     	   ); 
     	}
        
        $("#payBtn").on("click", function () {
        	My.showLoad();
            $.ajax({
                url: "/wxpay/unifiedorder.hsml?r=" + Math.random(),
                type: "get",
                data: {orderno: orderNo},
                success: function (json) {
                	var ret = json;
                    if (ret.code == '00000') {
                        alert("00000");
                        My.hideLoad();
                        jsApiParameters = eval('(' + ret.message + ')');
                        callpay();
                    } else {
                        My.hideLoad();
                        My.alertBox(ret.message);
                    }
                },
                error: function () {
                    My.hideLoad();
                }
            });
        })
    })();

</script>
</body>
</html>
