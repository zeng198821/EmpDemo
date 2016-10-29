var zeng = {
    isEmpty: function (value, allowEmptyString) {
        return (value === null) || (value === undefined) || (!allowEmptyString ? value === '' : false) || ($.isArray(value) && value.length === 0);
        
    },
    copyTo : function (c, b) {
        if (c && b) {
            for (var a in b) {
                c[a] = b[a]
            }
        }
        return c
    },
    copyIf : function (c, b) {
        if (c && b) {
            for (var a in b) {
                if (mini.isNull(c[a])) {
                    c[a] = b[a]
                }
            }
        }
        return c
    },
    form: {
        /**
         * 获取Form中的数据
         * @param formID_para  FormID
         * @returns {*} Form中的数据
         */
        getformdata: function (formID_para) {
            var formIns = $("#" + formID_para);
            var fieldList = formIns.find(":input");
            var tmpformfieldname = formIns.attr("field");
            var tmpresultData = {};
            var tmpformvalue = {};
            for (var i = 0; i < fieldList.length; i++) {
                var tmptag = $(fieldList[i]);
                var tmpvalue = "";
                var tmpfieldname = "";
                var tmptype = tmptag[0].type;
                tmpfieldname = tmptag.attr("field");
                if (tmpfieldname == null || tmpfieldname == undefined || tmpfieldname == "") {
                    continue;
                }
                if (tmptype == "checkbox") {
                    tmpvalue = tmptag[0].checked;
                } else {
                    tmpvalue = tmptag.val();
                }
                tmpformvalue[tmpfieldname] = tmpvalue;
            }
            console.log(tmpformvalue);
            if (!zeng.isEmpty(tmpformvalue)) {
                if (!zeng.isEmpty(tmpformfieldname)) {
                    tmpresultData[tmpformfieldname] = tmpformvalue;
                    return tmpresultData;
                } else {
                    return tmpformvalue;
                }
            }
            return  null;
        },
        getformAction : function (formID_para){
            var formIns = $("#" + formID_para);
            return formIns.attr("action");            
        },
        /**
         * 设置Form的数据
         * @param FormId_para FormID
         * @param FormData_para 待设置的Form数据
         */
        setFormData : function(FormId_para,FormData_para){
            var formIns = $("#" + FormId_para);
            var fieldList = formIns.find(":input");
            var tmpformfieldname = formIns.attr("field");
            var tmpData = zeng.isEmpty(tmpformfieldname) ? FormData_para : FormData_para[tmpformfieldname];
            for (var i = 0; i < fieldList.length; i++) {
                var tmptag = $(fieldList[i]);
                var tmpfieldname = "";
                var tmptype = tmptag[0].type;
                tmpfieldname = tmptag.attr("field");
                if (zeng.isEmpty(tmpfieldname)|| zeng.isEmpty(tmpData[tmpfieldname])) {
                    continue;
                }
                if (tmptype == "checkbox") {
                    tmptag[0].checked = tmpData[tmpfieldname];
                } else {
                    tmptag.val(tmpData[tmpfieldname]);
                }
            }
        },
    },
    request: {
        /**
         * Ajax请求
         * @param url_para Url地址
         * @param data_para 提交的数据
         * @param options_para Ajax其他扩展信息
         * @param showloading_para 异步请求时显示Loading的遮罩层
         * @param maskDomObject_para Loading的遮罩层上展示的信息
         * @param successCallback_para 请求成功回调函数
         * @param errorCallBack_para 请求失败回调函数
         * @returns {*} 服务器端返回的信息，Json格式
         */
        requestAjax: function (url_para, data_para, options_para, showloading_para, maskDomObject_para, successCallback_para, errorCallBack_para) {
            if (zeng.isEmpty(url_para)) {
                return null;
            }
            //请求标示 true:异步请求  , false 同步请求  默认：同步请求
            var tmpasync = false;
            if (!zeng.isEmpty(options_para) && !zeng.isEmpty(options_para.async) && typeof options_para.async == "boolean") {
                tmpasync = options_para.async;
            }
            //返回值
            var tmpresult = null;
            //默认请求数据为空对象
            var tmpdata = {};
            //默认请求成功回调函数
            var tmpSuccessCallBack = function (text_para) {

            };
            //默认请求失败回调函数
            var tmpErrorCallBack = function (text_para) {
                //console.log("tmpErrorCallBack");
                //console.log(text_para);
            };
            //默认请求完成回调函数
            var tmpCompleteCallBack = function (text_para) {
                //console.log("tmpCompleteCallBack");
            };
            // 默认不显示
            var tmpshowloading = false;
            if (!zeng.isEmpty(showloading_para) && typeof showloading_para == "boolean") {
                tmpshowloading = showloading_para;
                if(showloading_para){
                    tmpasync = true;
                }
            }
            if (!zeng.isEmpty(options_para)) {
                if (!zeng.isEmpty(options_para.success) && typeof options_para.success == "function") {
                    tmpSuccessCallBack = options_para.success;
                }
                if (!zeng.isEmpty(options_para.error) && typeof options_para.error == "function") {
                    tmpErrorCallBack = options_para.error;
                }
                if (!zeng.isEmpty(options_para.complete) && typeof options_para.complete == "function") {
                    tmpCompleteCallBack = options_para.complete;
                }
                if (!zeng.isEmpty(options_para.data)) {
                    tmpdata = options_para.data;
                }
            }
            if (!zeng.isEmpty(successCallback_para) && typeof successCallback_para == "function") {
                tmpSuccessCallBack = successCallback_para;
            }
            if (!zeng.isEmpty(errorCallBack_para) && typeof errorCallBack_para == "function") {
                tmpErrorCallBack = errorCallBack_para;
            }
            if (!zeng.isEmpty(data_para)) {
                tmpdata = data_para;
                //若参数传进来是对象实例，则转换成字符串格式
                if (typeof tmpdata != "string") {
                    tmpdata = zeng.JSON.encode(tmpdata);
                }
            }
            var tmpMaskDom = window.body;
            if (!zeng.isEmpty(maskDomObject_para)) {
                tmpMaskDom = maskDomObject_para;
            }
            var tmpReqData = {
                url: url_para,
                type: 'POST',
                data: tmpdata,
                cache: false,
                async: tmpasync,
                contentType: 'text/json',
                success: function (text) {
                    //console.log("SuccessCallBack");
                    tmpresult = text;
                    if (tmpasync) {
                        try {
                            tmpSuccessCallBack(zeng.JSON.decode(text));
                        } catch (e) {
                            console.log("页面代码错误：" + e.stack);
                        }
                    }
                    if (tmpasync && tmpshowloading) {
                        zeng.showLoading(tmpMaskDom, false);
                    }
                },
                error: function (text) {
                    //console.log(text);
                    tmpresult = text;
                    if (tmpasync) {
                        try {
                            tmpErrorCallBack(zeng.JSON.decode(text));
                        } catch (e) {
                            console.log("页面代码错误：" + e.stack);
                        }
                    }
                    if (tmpasync && tmpshowloading) {
                        zeng.showLoading(tmpMaskDom, false);
                    }
                },
                complete: function (text) {
                    //console.log("CompleteCallBack");
                    tmpresult = text;
                    try {
                        tmpCompleteCallBack(zeng.JSON.decode(text));
                    } catch (e) {
                        console.log("页面代码错误：" + e.stack);
                    }
                    if (tmpasync && tmpshowloading) {
                        zeng.showLoading(tmpMaskDom, false);
                    }
                }
            };
            if (tmpasync && tmpshowloading) {
                zeng.showLoading(tmpMaskDom, true);
            }
            $.ajax(tmpReqData);
            if (!tmpasync) {
                return zeng.JSON.decode(tmpresult.responseText);
            }
        }
    }
};

__js_dateRegEx = new RegExp('(^|[^\\\\])\\"\\\\/Date\\((-?[0-9]+)(?:[a-zA-Z]|(?:\\+|-)[0-9]{4})?\\)\\\\/\\"', "g");
__js_dateRegEx2 = new RegExp("[\"']/Date\\(([0-9]+)\\)/[\"']", "g");
zeng.JSON = new (function () {
    var sb = [];
    var _dateFormat = null;
    var useHasOwn = !!{}

    .hasOwnProperty,
            replaceString = function (a, b) {
                var c = m[b];
                if (c) {
                    return c;
                }
                c = b.charCodeAt();
                return "\\u00" + Math.floor(c / 16).toString(16) + (c % 16).toString(16);
            },
            doEncode = function (o, field) {
                if (o === null) {
                    sb[sb.length] = "null";
                    return;
                }
                var t = typeof o;
                if (t == "undefined") {
                    sb[sb.length] = "null";
                    return;
                } else {
                    if (o.push) {
                        sb[sb.length] = "[";
                        var b,
                                i,
                                l = o.length,
                                v;
                        for (i = 0; i < l; i += 1) {
                            v = o[i];
                            t = typeof v;
                            if (t == "undefined" || t == "function" || t == "unknown") {
                            }
                            else {
                                if (b) {
                                    sb[sb.length] = ",";
                                }
                                doEncode(v);
                                b = true;
                            }
                        }
                        sb[sb.length] = "]";
                        return;
                    } else {
                        if (o.getFullYear) {
                            if (_dateFormat) {
                                sb[sb.length] = '"';
                                if (typeof _dateFormat == "function") {
                                    sb[sb.length] = _dateFormat(o, field);
                                } else {
                                    sb[sb.length] = zeng.formatDate(o, _dateFormat);
                                }
                                sb[sb.length] = '"';
                            } else {
                                var n;
                                sb[sb.length] = '"';
                                sb[sb.length] = o.getFullYear();
                                sb[sb.length] = "-";
                                n = o.getMonth() + 1;
                                sb[sb.length] = n < 10 ? "0" + n : n;
                                sb[sb.length] = "-";
                                n = o.getDate();
                                sb[sb.length] = n < 10 ? "0" + n : n;
                                sb[sb.length] = "T";
                                n = o.getHours();
                                sb[sb.length] = n < 10 ? "0" + n : n;
                                sb[sb.length] = ":";
                                n = o.getMinutes();
                                sb[sb.length] = n < 10 ? "0" + n : n;
                                sb[sb.length] = ":";
                                n = o.getSeconds();
                                sb[sb.length] = n < 10 ? "0" + n : n;
                                sb[sb.length] = '"';
                            }
                            return;
                        } else {
                            if (t == "string") {
                                if (strReg1.test(o)) {
                                    sb[sb.length] = '"';
                                    sb[sb.length] = o.replace(strReg2, replaceString);
                                    sb[sb.length] = '"';
                                    return;
                                }
                                sb[sb.length] = '"' + o + '"';
                                return;
                            } else {
                                if (t == "number") {
                                    sb[sb.length] = o;
                                    return;
                                } else {
                                    if (t == "boolean") {
                                        sb[sb.length] = String(o);
                                        return;
                                    } else {
                                        sb[sb.length] = "{";
                                        var b,
                                                i,
                                                v;
                                        for (i in o) {
                                            if (!useHasOwn || Object.prototype.hasOwnProperty.call(o, i)) {
                                                v = o[i];
                                                t = typeof v;
                                                if (t == "undefined" || t == "function" || t == "unknown") {
                                                }
                                                else {
                                                    if (b) {
                                                        sb[sb.length] = ",";
                                                    }
                                                    doEncode(i);
                                                    sb[sb.length] = ":";
                                                    doEncode(v, i);
                                                    b = true;
                                                }
                                            }
                                        }
                                        sb[sb.length] = "}";
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            },
            m = {
                "\b": "\\b",
                "\t": "\\t",
                "\n": "\\n",
                "\f": "\\f",
                "\r": "\\r",
                '"': '\\"',
                "\\": "\\\\"
            },
            strReg1 = /["\\\x00-\x1f]/,
            strReg2 = /([\x00-\x1f\\"])/g;
    this.encode = function () {
        var ec;
        return function (o, dateFormat) {
            sb = [];
            _dateFormat = dateFormat;
            doEncode(o);
            _dateFormat = null;
            return sb.join("");
        };
    }
    ();
    this.decode = function () {
        var dateR;
        e1 = /^(\d{4})-(\d{2})-(\d{2})[T ](\d{2}):(\d{2}):(\d{2}(?:\.*\d*)?)Z*$/;
        var dateRe2 = new RegExp("^/+Date\\((-?[0-9]+).*\\)/+$", "g");
        var re = /[\"\'](\d{4})-(\d{2})-(\d{2})[T ](\d{2}):(\d{2}):(\d{2})[\"\']/g;
        return function (json, parseDate) {
            if (json === "" || json === null || json === undefined) {
                return json;
            }
            if (typeof json == "object") {
                json = this.encode(json);
            }
            function evalParse(json) {
                if (parseDate !== false) {
                    json = json.replace(__js_dateRegEx, "$1new Date($2)");
                    json = json.replace(re, "new Date($1,$2-1,$3,$4,$5,$6)");
                    json = json.replace(__js_dateRegEx2, "new Date($1)");
                }
                return eval("(" + json + ")");
            }
            var data = null;
            if (window.JSON && window.JSON.parse) {
                var dateReviver = function (key, value) {
                    if (typeof value === "string" && parseDate !== false) {
                        dateRe1.lastIndex = 0;
                        var a = dateRe1.exec(value);
                        if (a) {
                            value = new Date(a[1], a[2] - 1, a[3], a[4], a[5], a[6]);
                            return value;
                        }
                        dateRe2.lastIndex = 0;
                        var a = dateRe2.exec(value);
                        if (a) {
                            value = new Date(parseInt(a[1]));
                            return value;
                        }
                    }
                    return value;
                };
                try {
                    var json2 = json.replace(__js_dateRegEx, '$1"/Date($2)/"');
                    data = window.JSON.parse(json2, dateReviver);
                } catch (ex) {
                    data = evalParse(json);
                }
            } else {
                data = evalParse(json);
            }
            return data;
        };
    }
    ();
})();
zeng.formatDate = function (e, r, p) {
    if (!e || !e.getFullYear || isNaN(e)) {
        return "";
    }
    var b = e.toString();
    var a = zeng.dateInfo;
    if (!a) {
        a = zeng.dateInfo;
    }
    if (typeof (a) !== "undefined") {
        var j = typeof (a.patterns[r]) !== "undefined" ? a.patterns[r] : r;
        var k = e.getFullYear();
        var i = e.getMonth();
        var l = e.getDate();
        if (r == "yyyy-MM-dd") {
            i = i + 1 < 10 ? "0" + (i + 1) : i + 1;
            l = l < 10 ? "0" + l : l;
            return k + "-" + i + "-" + l;
        }
        if (r == "MM/dd/yyyy") {
            i = i + 1 < 10 ? "0" + (i + 1) : i + 1;
            l = l < 10 ? "0" + l : l;
            return i + "/" + l + "/" + k;
        }
        b = j.replace(/yyyy/g, k);
        b = b.replace(/yy/g, (k + "").substring(2));
        var o = e.getHalfYear();
        b = b.replace(/hy/g, a.halfYearLong[o]);
        var c = e.getQuarter();
        b = b.replace(/Q/g, a.quarterLong[c]);
        b = b.replace(/q/g, a.quarterShort[c]);
        b = b.replace(/MMMM/g, a.monthsLong[i].escapeDateTimeTokens());
        b = b.replace(/MMM/g, a.monthsShort[i].escapeDateTimeTokens());
        b = b.replace(/MM/g, i + 1 < 10 ? "0" + (i + 1) : i + 1);
        b = b.replace(/(\\)?M/g, function (t, s) {
            return s ? t : i + 1;
        });
        var d = e.getDay();
        b = b.replace(/dddd/g, a.daysLong[d].escapeDateTimeTokens());
        b = b.replace(/ddd/g, a.daysShort[d].escapeDateTimeTokens());
        b = b.replace(/dd/g, l < 10 ? "0" + l : l);
        b = b.replace(/(\\)?d/g, function (t, s) {
            return s ? t : l;
        });
        var g = e.getHours();
        var n = g > 12 ? g - 12 : g;
        if (a.clockType == 12) {
            if (g > 12) {
                g -= 12;
            }
        }
        b = b.replace(/HH/g, g < 10 ? "0" + g : g);
        b = b.replace(/(\\)?H/g, function (t, s) {
            return s ? t : g;
        });
        b = b.replace(/hh/g, n < 10 ? "0" + n : n);
        b = b.replace(/(\\)?h/g, function (t, s) {
            return s ? t : n;
        });
        var f = e.getMinutes();
        b = b.replace(/mm/g, f < 10 ? "0" + f : f);
        b = b.replace(/(\\)?m/g, function (t, s) {
            return s ? t : f;
        });
        var q = e.getSeconds();
        b = b.replace(/ss/g, q < 10 ? "0" + q : q);
        b = b.replace(/(\\)?s/g, function (t, s) {
            return s ? t : q;
        });
        b = b.replace(/fff/g, e.getMilliseconds());
        b = b.replace(/tt/g, e.getHours() > 12 || e.getHours() == 0 ? a.tt.PM : a.tt.AM);
        var e = e.getDate();
        var h = "";
        if (e <= 10) {
            h = a.ten.Early;
        } else {
            if (e <= 20) {
                h = a.ten.Mid;
            } else {
                h = a.ten.Late;
            }
        }
        b = b.replace(/ten/g, h);
    }
    return b.replace(/\\/g, "");
};
zeng.dateInfo = {
    monthsLong: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
    monthsShort: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
    daysLong: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
    daysShort: ["日", "一", "二", "三", "四", "五", "六"],
    quarterLong: ["一季度", "二季度", "三季度", "四季度"],
    quarterShort: ["Q1", "Q2", "Q2", "Q4"],
    halfYearLong: ["上半年", "下半年"],
    patterns: {
        d: "yyyy-M-d",
        D: "yyyy年M月d日",
        f: "yyyy年M月d日 H:mm",
        F: "yyyy年M月d日 H:mm:ss",
        g: "yyyy-M-d H:mm",
        G: "yyyy-M-d H:mm:ss",
        m: "MMMd日",
        o: "yyyy-MM-ddTHH:mm:ss.fff",
        s: "yyyy-MM-ddTHH:mm:ss",
        t: "H:mm",
        T: "H:mm:ss",
        U: "yyyy年M月d日 HH:mm:ss",
        y: "yyyy年MM月"
    },
    tt: {
        AM: "上午",
        PM: "下午"
    },
    ten: {
        Early: "上旬",
        Mid: "中旬",
        Late: "下旬"
    },
    today: "今天",
    clockType: 24
};

zeng.regClass = function (a, b) {
    b = b.toLowerCase();
    if (!zeng.classes[b]) {
        zeng.classes[b] = a;
        a.prototype.type = b;
    }
    var c = a.prototype.uiCls;
    if (!zeng.isNull(c) && !zeng.uiClasses[c]) {
        zeng.uiClasses[c] = a;
    }
};
zeng.extend = function (e, b, f) {
    if (typeof b != "function") {
        return this;
    }
    var g = e,
    d = g.prototype,
    a = b.prototype;
    if (g.superclass == a) {
        return;
    }
    g.superclass = a;
    g.superclass.constructor = b;
    for (var c in a) {
        d[c] = a[c];
    }
    if (f) {
        for (var c in f) {
            d[c] = f[c];
        }
    }
    return g;
};

/**
 * 打包提交到服务器的数据
 * @param servicesName_para 后台服务名称
 * @param submitData_para 提交的数据(支持Json格式的字符串或对象)
 * @returns {*} 服务器的数据
 */
zeng.packetSubmitData = function (servicesName_para,submitData_para){
    if(zeng.isEmpty(servicesName_para) || zeng.isEmpty(submitData_para)){
        return null;
    }
    return {serversName:servicesName_para,submitData:zeng.JSON.encode(submitData_para)};
};

/**
 * 关闭弹出窗口
 */
zeng.closeWindow = function(){
    if (window.CloseOwnerWindow)
        return window.CloseOwnerWindow('close');
    else
        window.close();

};
zeng._doOpen = function (c) {
    if (typeof c == "string") {
        c = {
            url : c
        }
    }
    c = zeng.copyTo({
        width : 700,
        height : 400,
        allowResize : true,
        allowModal : true,
        closeAction : "destroy",
        title : "",
        titleIcon : "",
        iconCls : "",
        iconStyle : "",
        bodyStyle : "padding: 0",
        url : "",
        showCloseButton : true,
        showFooter : false
    }, c);
    c.closeAction = "destroy";
    var i = c.onload;
    delete c.onload;
    var g = c.ondestroy;
    delete c.ondestroy;
    var b = c.url;
    delete c.url;
    var e = mini.getViewportBox();
    if (c.width && String(c.width).indexOf("%") != -1) {
        var a =
            parseInt(c.width);
        c.width = parseInt(e.width * (a / 100))
    }
    if (c.height && String(c.height).indexOf("%") != -1) {
        var d = parseInt(c.height);
        c.height = parseInt(e.height * (d / 100))
    }
    var f = new mini.Window();
    f.set(c);
    f.load(b, i, g);
    f.show();
    return f
};


zeng.open = function (b) {
    if (!b) {
        return
    }
    var a = b.url;
    if (!a) {
        a = ""
    }
    var f = a.split("#");
    var a = f[0];
    if (a && a.indexOf("_winid") == -1) {
        var c = "_winid=" + mini._WindowID;
        if (a.indexOf("?") == -1) {
            a += "?" + c
        } else {
            a += "&" + c
        }
        if (f[1]) {
            a = a + "#" + f[1]
        }
    }
    b.url = a;
    b.Owner = window;
    var g = [];
    function d(i) {
        try {
            if (i.mini) {
                g.push(i)
            }
            if (i.parent && i.parent != i) {
                d(i.parent)
            }
        } catch (h) {}

    }
    d(window);
    var e = g[g.length - 1];
    return e.mini._doOpen(b)
};
zeng.GetQueryString = function (name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
};