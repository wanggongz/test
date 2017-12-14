function $$(e) {
    var t = "string" == typeof e ? document.getElementById(e) : e,
    n = t.className || "";
    return t.show = function(e) {
        return e = e || "block",
        t.style.display = e,
        t
    },
    t.hide = function() {
        return t.style.display = "none",
        t
    },
    t.isShow = function() {
        return "block" == t.style.display
    },
    t.addClass = function(e) {
        return ! t.hasClass(e) && (t.className = n + " " + e),
        t
    },
    t.removeClass = function(e) {
        if (t.hasClass(e)) {
            var o = new RegExp("(\\s|^)" + e + "(\\s|$)");
            t.className = n.replace(o, "")
        }
        return t
    },
    t.hasClass = function(e) {
        var t = new RegExp("(\\s|^)" + e + "(\\s|$)");
        return !! n.match(t)
    },
    t.toggleClass = function(e) {
        return t.hasClass(e) ? t.removeClass(e) : t.addClass(e),
        t
    },
    t
}
function stopProp(e) {
    e.preventDefault(),
    e.stopPropagation()
}
function $getContentHeight() {
    var e = document.body,
    t = "BackCompat" == document.compatMode ? e: document.documentElement;
    return window.MessageEvent && navigator.userAgent.toLowerCase().indexOf("firefox") == -1 ? e.scrollHeight: t.scrollHeight
}
function $getContentWidth() {
    var e = document.body,
    t = "BackCompat" == document.compatMode ? e: document.documentElement;
    return window.MessageEvent && navigator.userAgent.toLowerCase().indexOf("firefox") == -1 ? e.scrollWidth: t.scrollWidth
}
function $getPageScrollHeight() {
    var e = document.body,
    t = "BackCompat" == document.compatMode ? e: document.documentElement,
    n = navigator.userAgent.toLowerCase();
    return window.MessageEvent && n.indexOf("firefox") == -1 && n.indexOf("opera") == -1 && n.indexOf("msie") == -1 ? e.scrollTop: t.scrollTop
}
function $getPageScrollWidth() {
    var e = document.body,
    t = "BackCompat" == document.compatMode ? e: document.documentElement;
    return window.MessageEvent && navigator.userAgent.toLowerCase().indexOf("firefox") == -1 ? e.scrollLeft: t.scrollLeft
}
function $getWindowHeight() {
    var e = document.body;
    return ("BackCompat" == document.compatMode ? e: document.documentElement).clientHeight
}
function $getWindowWidth() {
    var e = document.body;
    return ("BackCompat" == document.compatMode ? e: document.documentElement).clientWidth
}
Function.prototype.curry = function() {
    var e = Array.prototype.slice,
    t = e.apply(arguments),
    n = this;
    return function() {
        return n.apply(null, t.concat(e.apply(arguments)))
    }
},
Date.prototype.format = function(e) {
    var t = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        S: this.getMilliseconds()
    };
    /(y+)/.test(e) && (e = e.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length)));
    for (var n in t) new RegExp("(" + n + ")").test(e) && (e = e.replace(RegExp.$1, 1 == RegExp.$1.length ? t[n] : ("00" + t[n]).substr(("" + t[n]).length)));
    return e
},
Date.prototype.getWeek = function() {
    var e = this.getDay(),
    t = "";
    return 0 == e && (t = "\u5468\u65e5"),
    1 == e && (t = "\u5468\u4e00"),
    2 == e && (t = "\u5468\u4e8c"),
    3 == e && (t = "\u5468\u4e09"),
    4 == e && (t = "\u5468\u56db"),
    5 == e && (t = "\u5468\u4e94"),
    6 == e && (t = "\u5468\u516d"),
    t
},
window.requestAnimFrame = function() {
    return window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame ||
    function(e, t) {
        return window.setTimeout(e, 1e3 / 60)
    }
} ();
var My = function() {
    return function(e) {
        function t(e, n, o, i) {
            if (My.Load && o != My.Load && "" != My.Load.className) return window.requestAnimFrame(t.curry(e, n, o, i)),
            !1;
            if ($$(o).hasClass("hidden")) window.requestAnimFrame(t.curry(e, n, o, i));
            else if (i = i || "block", s = o.style.display == i ? 1 : 0, !s) {
                n && (o.style.cssText += " -webkit-animation-duration:" + n + "ms;"),
                e = e || "fadeAnim",
                $$(o).show(i).classList.add(e),
                o.classList.add("shown");
                var a = o;
                setTimeout(function() {
                    a.classList.remove(e),
                    a.classList.remove("shown")
                },
                n || 350)
            }
        }
        function n(e, t, o) {
            if ($$(o).hasClass("shown")) My.Load && o == My.Load && (My.Load.className = "", My.Load.style.display = "none"),
            window.requestAnimFrame(n.curry(e, t, o));
            else if (s = "none" != o.style.display ? 1 : 0) {
                t && (o.style.cssText += "-webkit-animation-duration:" + t + "ms;"),
                e = e || "fadeAnim",
                o.classList.add(e),
                o.classList.add("hidden");
                var i = o;
                setTimeout(function() {
                    i.style.display = "none",
                    i.classList.remove(e),
                    i.classList.remove("hidden")
                },
                t || 350)
            }
        }
        function o(e, t) {
            e = e || "touch",
            t.addEventListener("touchstart",
            function() {
                this.classList.add(e)
            }),
            t.addEventListener("touchend",
            function() {
                var t = this;
                setTimeout(function() {
                    t.classList.remove(e)
                },
                100)
            })
        }
        var i = "string" == typeof e ? document.querySelectorAll(e) : e,
        s = void 0;
        return {
            open: function(e, n, o) {
                var s = i.length;
                if (s) for (var a = 0; a < s; a++) t(e, n, i[a], o);
                else t(e, n, i, o)
            },
            close: function(e, t) {
                var o = i.length;
                if (o) for (var s = 0; s < o; s++) n(e, t, i[s]);
                else n(e, t, i)
            },
            touch: function(e) {
                var t = i.length;
                if (t) for (var n = 0; n < t; n++) o(e, i[n]);
                else o(e, i)
            }
        }
    }
} (); !
function(e) {
    function t() {
        var t = document.createElement("div"),
        n = document.createElement("div"),
        o = "",
        i = "";
        return e.FollowImg ? (t.style.cssText = "position:fixed;z-index:1100;width:100%;height:100%;top:0;bottom:0;right:0;left:0;background-color:rgba(0,0,0,.6);", o = "<div style = 'width:180px; height:200px; margin:30% auto; padding:25px; background-color:#fff; text-align:center;'><img src='" + e.FollowImg + "' style='width:180px;height:180px;'/><p style='margin:0; font-size:12px;'>\u957f\u6309\u4e8c\u7ef4\u7801\uff0c\u5173\u6ce8\u6211\u4eec</p>") : (t.style.cssText = "position:fixed;z-index:1100;width:100%;height:100%;top:0;bottom:0;right:0;left:0;background-color:#22292c;font-size:16px;", o = "<div style = 'width:300px;margin:0 auto;padding-top:40px; color:#fff;'><h3>\u65b9\u6cd51\uff1a</h3><p>\u70b9\u51fb\u53f3\u4e0a\u89d2<span class = 'wx_menuIcon'></span>\u67e5\u770b\u516c\u4f17\u53f7", o += "<span class = 'wx_weIcon'></span>\u5173\u6ce8\u6211\u4eec</p><div class = 'clear blank'></div><h3>\u65b9\u6cd52\uff1a</h3><div class = 'clear blank'></div><span>\u957f\u6309\u590d\u5236\u5fae\u4fe1\u53f7\uff1a</span><br/>", o += "<h3 style = 'display:inline-block;margin:8px 0;padding:2px 8px 0;background-color:#fff;color:#333;border-radius:5px;font-size:16px;line-height:30px;'>", o += e.WxName || "\u61d2\u4fdd\u9669</h3><br/><span>\u5230\u5fae\u4fe1 \u201c\u516c\u4f17\u53f7\u201d \u4e2d\u641c\u7d22\u5173\u6ce8</span></div>"),
        t.innerHTML = o,
        n.style.cssText = "position:fixed;bottom:16%;left:50%;margin-left:-25px;padding:16px;background-color: rgba(0,0,0,.5);text-align:center;border-radius:100px;",
        i = "<span class = 'wx_closeIcon'><span/>",
        n.innerHTML = i,
        t.appendChild(n),
        document.body.appendChild(t),
        t.addEventListener("touchmove", stopProp),
        {
            div: $$(t),
            btn: $$(n)
        }
    }
    var n;
    e.showFollow = function(o) {
        e.FollowUrl ? (e.showLoad("\u6b63\u5728\u8df3\u8f6c..."), window.location.href = e.FollowUrl) : (n || (n = t(), n.btn.addEventListener("click",
        function() {
            e.hideFollow(o)
        }), n.div.addEventListener("touchmove", stopProp)), e.FollowImg && (n.div.style.top = $getPageScrollHeight() + "px"), e(n.div).open(o))
    },
    e.hideFollow = function(t) {
        n && e(n.div).close(t)
    }
} (My),
function(e) {
    function t() {
        var e = document.createElement("div"),
        t = document.createElement("span"),
        n = "";
        return e.style.cssText = "display:none;position:fixed; top:0px;left:0;width:100%; height:100%; z-index:1200; overflow:hidden;background: rgba(0,0,0,.1);text-align:center;",
        n = "<div style = 'max-width:200px; color: #fff; font-size:16px;margin: 48% auto 0;line-height: 22px;border-radius: 5px;background: rgba(0, 0, 0, 0.6);display: inline-block;padding: 12px 15px 10px;'>",
        n += "<img style = 'width:22px;height:22px;margin:0 6px 3px 0;' src = 'data:image/gif;base64,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'/>",
        n += "</div>",
        e.innerHTML = n,
        e.firstChild.appendChild(t),
        document.body.appendChild(e),
        {
            div: $$(e),
            text: $$(t)
        }
    }
    var n;
    e.showLoad = function(o, i) {
        n = n || t(),
        n.text.innerHTML = o || "\u6b63\u5728\u52a0\u8f7d...",
        e(n.div).open(i, 1),
        e.Load = n.div
    },
    e.hideLoad = function(t) {
        n && e(n.div).close(t)
    }
} (My),
function(e) {
    function t() {
        var e = document.createElement("div"),
        t = document.createElement("span"),
        n = "";
        return e.style.cssText = "display:none; position: fixed; top: 30%; margin: 0 auto; z-index: 1300; width: 100%; text-align: center;",
        n = "<div style = 'color: #fff; font-size: 16px; line-height: 22px; padding: 10px 20px;margin: 0 auto;display: inline-block; border-radius: 4px; max-width: 200px; background: rgba(0, 0, 0, 0.6);'></div>",
        e.innerHTML = n,
        e.firstChild.appendChild(t),
        document.body.appendChild(e),
        {
            div: $$(e),
            text: $$(t)
        }
    }
    var n;
    e.showTip = function(o, i, s) {
        n = n || t(),
        n.div.className = "my_tip",
        n.text.innerHTML = o || "\u63d0\u793a\u4fe1\u606f",
        e(n.div).open(s),
        setTimeout(e.closeTip, i || 1500)
    },
    e.successTip = function(o, i, s) {
        n = n || t(),
        n.div.className = "my_tip tip_success",
        n.text.innerHTML = o || "\u6210\u529f\u4fe1\u606f",
        e(n.div).open(s),
        setTimeout(e.closeTip, i || 1500)
    },
    e.errorTip = function(o, i, s) {
        n = n || t(),
        n.div.className = "my_tip tip_error",
        n.text.innerHTML = o || "\u9519\u8bef\u4fe1\u606f",
        e(n.div).open(s),
        setTimeout(e.closeTip, i || 1500)
    },
    e.closeTip = function(t) {
        n && e(n.div).close(t)
    }
} (My),
function(e) {
    function t() {
        var e = document.createElement("div"),
        t = "";
        return e.style.cssText = "position:fixed;left:0;top:0;z-index:1000;width:100%;height:100%;background-color:rgba(0,0,0,.8);color:#fff;text-align:center;font-size:16px;",
        t = "<div><span class = 'wx_arrowIcon fr'></span><div style = 'padding-top: 48px;width: 300px;margin: 0 auto;line-height:32px;'>\u8bf7\u70b9\u51fb\u53f3\u4e0a\u89d2",
        t += "<p>\u901a\u8fc7<span class = 'wx_sendIcon'></span>\u3010\u53d1\u9001\u7ed9\u670b\u53cb\u3011\u529f\u80fd</p><p>\u628a\u6d88\u606f\u544a\u8bc9\u5c0f\u4f19\u4f34\u54df\uff5e</p>",
        t += "<div style = 'position:absolute;bottom:18%;left:50%;margin-left:-25px; padding:16px;background-color: rgba(0,0,0,.5);text-align:center;border-radius:100px;'>",
        t += "<span class = 'wx_closeIcon'><span/></div></div>",
        e.innerHTML = t,
        document.body.appendChild(e),
        e.addEventListener("touchmove", stopProp),
        {
            div: $$(e)
        }
    }
    var n;
    e.showShare = function(o) {
        n || (n = t(), n.div.addEventListener("click",
        function() {
            e.hideShare(o)
        })),
        e(n.div).open(o)
    },
    e.hideShare = function(t) {
        n && e(n.div).close(t)
    }
} (My),
function(e) {
    function t() {
        var e = document.createElement("div"),
        t = "";
        return $share_img = _LBXSTATIC.rootRes + "h5/dist/images/share/ico-share.gif",
        e.style.cssText = "position:fixed;left:0;top:0;z-index:1000;width:100%;height:100%;background-color:rgba(0,0,0,.8);color:#fff;text-align:center;font-size:16px;",
        t += '<div><img src="' + $share_img + '" />',
        t += "<div style = 'position:absolute;bottom:18%;left:50%;margin-left:-25px; padding:16px;background-color: rgba(0,0,0,.5);text-align:center;border-radius:100px;'>",
        t += '<span class = "wx_closeIcon"></span></div>',
        t += "</div>",
        e.innerHTML = t,
        document.body.appendChild(e),
        e.addEventListener("touchmove", stopProp),
        {
            div: $$(e)
        }
    }
    var n;
    e.showShare_cr = function(o) {
        n || (n = t(), n.div.addEventListener("click",
        function() {
            e.hideShare_cr(o)
        })),
        e(n.div).open(o)
    },
    e.hideShare_cr = function(t) {
        n && e(n.div).close(t)
    }
} (My),
function(e) {
    function t() {
        var e = document.createElement("div");
        e.style.cssText = "display:none; text-align:center; position:fixed; top:27%; left:50%; z-index:1400; margin-left:-130px; width:260px; font-size:14px; background-color:#fff; border-radius: 4px;",
        e.className = "alert";
        var t = document.createElement("div");
        t.style.cssText = "",
        t.className = "btn_cont";
        var n = document.createElement("div"),
        o = document.createElement("div"),
        i = document.createElement("h3"),
        s = document.createElement("a"),
        a = document.createElement("a");
        return i.className = "alert_tit",
        n.className = "alert_text",
        o.style.textAlign = "left",
        s.className = "btn_yes",
        a.style.cssText = "display:none;",
        a.className = "btn_cont",
        n.appendChild(o),
        e.appendChild(i),
        e.appendChild(n),
        t.appendChild(s),
        t.appendChild(a),
        e.appendChild(t),
        document.body.appendChild(e),
        e.addEventListener("touchmove", stopProp),
        {
            div: $$(e),
            tit: $$(i),
            text: $$(o),
            btnY: $$(s),
            btnN: $$(a)
        }
    }
    function n() {
        var e = document.createElement("div");
        return e.setAttribute("id", "myMask"),
        e.style.cssText = "position:fixed;left:0;top:0;z-index:1000;width:100%;height:100%;background-color:rgba(0,0,0,.8);display:none;",
        document.body.appendChild(e),
        e.addEventListener("touchmove", stopProp),
        $$(e)
    }
    var o, i;
    e.showAlert = function(n) {
        var s = {
            width: 260,
            title: "",
            text: "\u5f39\u7a97\u63d0\u793a",
            yesText: "\u597d\u7684",
            yesStyle: "b_dred",
            onYes: function() {
                return ! 0
            },
            noText: "",
            noStyle: "b_white",
            onNo: function() {
                return ! 0
            },
            animte: "fadeAnim",
            hasMask: !0,
            clickMaskHide: !1
        };
        for (var a in n) s[a] = n[a];
        var r = s.title,
        d = s.text,
        c = s.yesText,
        l = s.yesStyle,
        p = s.onYes,
        u = s.noText,
        m = s.noStyle,
        h = s.onNo,
        f = s.animte,
        A = s.clickMaskHide,
        g = s.width;
        i = s.hasMask,
        o && o.div.parentNode.removeChild(o.div),
        o = t(),
        o.div.style.width = g + "px",
        o.text.innerHTML = d,
        o.btnY.innerHTML = c,
        o.btnY.className = "btn btn_yes " + l,
        o.btnY.addEventListener("click",
        function() {
            p() !== !1 && e.hideAlert(f)
        }),
        r ? o.tit.show().innerHTML = r: o.tit.hide(),
        u ? (o.btnY.style.display = "table-cell", o.btnN.style.display = "table-cell", o.btnN.className = "btn btn_no " + m, o.btnN.innerHTML = u, o.btnN.addEventListener("click",
        function() {
            h() !== !1 && e.hideAlert(f)
        })) : o.btnN.hide(),
        e(o.div).open(f),
        i && e.showMask(),
        i && A && e.mask.addEventListener("click",
        function() {
            My.hideAlert()
        }),
        e.reshowAlert = function() {
            e(o.div).open(f),
            i && e.showMask()
        }
    },
    e.hideAlert = function(t) {
        e(o.div).close(t),
        i && e.hideMask()
    },
    e.showMask = function(t, o) {
        e.mask = e.mask || n(),
        e(e.mask).open(t, o)
    },
    e.hideMask = function(t, n) {
        e(e.mask).close(t, n)
    }
} (My),
function(e) {
    e.page = {
        open: function(e) {
            document.getElementsByTagName("html")[0].style.cssText = "position:static",
            document.getElementsByTagName("body")[0].style.cssText = "position:static",
            e.css("display", "block"),
            setTimeout(function() {
                e.addClass("current")
            },
            20),
            $(window).scrollTop(0)
        },
        close: function(e) {
            setTimeout(function() {
                e.removeClass("current")
            },
            20),
            $(window).scrollTop(0),
            setTimeout(function() {
                e.css("display", "none")
            },
            200)
        }
    }
} (My),
function(my) {
    my.showLayer = function(e, t) {
        var n = t || "popAnim";
        my.showMask(),
        "popEase" == n ? (e = e.replace("#", ""), document.getElementsByTagName("html")[0].style.cssText = "position:relative;height:" + window.screen.height + "px;overflow:hidden", document.getElementsByTagName("body")[0].style.cssText = "position:relative;height:" + window.screen.height + "px;overflow:hidden", $$(e).removeClass("hidden"), $$(e).addClass("shown"), $$(e).style.cssText = "height:" + $$(e).childNodes[1].offsetHeight + "px") : my(e).open(n),
        my.mask.addEventListener("click",
        function() {
            my.hideLayer(e, n, !1)
        })
    },
    my.hideLayer = function(e, t, n) { ! n && setTimeout(my.hideMask, 100);
        var o = t || "popAnim";
        my.hideMask(),
        "popEase" == o ? (e = e.replace("#", ""), document.getElementsByTagName("html")[0].style.cssText = "position:static", document.getElementsByTagName("body")[0].style.cssText = "position:static", $$(e).removeClass("shown"), $$(e).addClass("hidden")) : my(e).close(o)
    },
    my.alertBox = function(e, t, n) {
        My.showAlert({
            text: e || "\u7f51\u7edc\u4e0d\u7ed9\u529b\uff0c\u5237\u65b0\u518d\u8bd5~",
            yesText: t || "\u786e\u5b9a",
            yesStyle: "b_dred",
            onYes: function() {
                "function" == typeof n && n()
            }
        })
    },
    my.getParam = function(e) {
        var t = "",
        n = !1,
        o = window.location.search;
        if (0 == o.indexOf("?") && o.indexOf("=") > 1) {
            o = o.replace("&amp;", "&");
            for (var i = unescape(o).substring(1, o.length).split("&"), s = 0; s < i.length && !n;) i[s].indexOf("=") > 0 && i[s].split("=")[0].toLowerCase() == e.toLowerCase() && (t = i[s].split("=")[1], n = !0),
            s++
        }
        return t
    },
    my.convertTime = function(e, t) {
        return new Date(parseInt(1e3 * e, 10)).format(t || "yyyy-MM-dd hh:mm:ss")
    },
    my.jsonDeserialize = function(jsondata) {
        return eval("(" + jsondata + ")")
    },
    my.fix = function(e, t) {
        return "undefined" != typeof t ? parseFloat(e).toFixed(t) : parseFloat(e).toFixed(2)
    }
} (My),
function(e) {
    function t(e) {
        return e.replace(/^\s+|\s+$/g, "")
    }
    e.cookie = function(e, n, o) {
        if ("undefined" == typeof n) {
            var i = null;
            if (document.cookie && "" != document.cookie) for (var s = document.cookie.split(";"), a = 0; a < s.length; a++) {
                var r = t(s[a]);
                if (r.substring(0, e.length + 1) == e + "=") {
                    i = decodeURIComponent(r.substring(e.length + 1));
                    break
                }
            }
            return i
        }
        o = o || {},
        null === n && (n = "", o.expires = -1);
        var d = "";
        if (o.expires && ("number" == typeof o.expires || o.expires.toUTCString)) {
            var c;
            "number" == typeof o.expires ? (c = new Date, c.setTime(c.getTime() + 24 * o.expires * 60 * 60 * 1e3)) : c = o.expires,
            d = "; expires=" + c.toUTCString()
        }
        var l = o.path ? "; path=" + o.path: "",
        p = o.domain ? "; domain=" + o.domain: "",
        u = o.secure ? "; secure": "";
        document.cookie = [e, "=", encodeURIComponent(n), d, l, p, u].join("")
    }
} (My);