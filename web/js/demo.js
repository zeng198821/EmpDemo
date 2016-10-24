/**
 * Created by zeng on 2016-10-22.
 */
__CreateJSPath = function (js) {
    var scripts = document.getElementsByTagName("script");
    var path = "";
    for (var i = 0, l = scripts.length; i < l; i++) {
        var src = scripts[i].src;
        if (src.indexOf(js) != -1) {
            //var ss = src.split(js);
            //path = ss[0];
            path = "/"+src.split('/')[3]+'/';
            break;
        }
    }
    var href = location.href;
    href = href.split("#")[0];
    href = href.split("?")[0];
    var ss = href.split("/");
    ss.length = ss.length - 1;
    href = ss.join("/");
    if (path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") != 0) {
        path = href + "/" + path;
    }
    return path;
}

//bootPath
var bootPATH = __CreateJSPath("demo.js");

//debugger,此变量用来区别ajax请求是否弹出alert来提示交互错误
mini_debugger = true;

window['nui_model']=window['nui_model']||'min';

document.write('<link href="' + bootPATH + 'css/bootstrap.min.css" rel="stylesheet"  media="screen" type="text/css" />');
document.write('<link href="' + bootPATH + 'css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen" type="text/css" />');

//Jquery
document.write('<script src="' + bootPATH + 'js/jquery-1.11.2.min.js" type="text/javascript"></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'js/extend.js" type="text/javascript"></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'js/bootstrap.min.js" type="text/javascript"></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'js/bootstrap-datetimepicker.js" type="text/javascript"></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></sc' + 'ript>');


