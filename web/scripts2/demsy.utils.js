/*
 * 字符串函数扩展
 */
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g, '');
}
String.prototype.toProp = function() {
	var idx = this.indexOf("-");
	if (idx > -1) {
		var str = this.substring(0, idx) + this.substring(idx + 1, idx + 2).toUpperCase() + this.substring(idx + 2);
		return str.toProp();
	}

	return this;
}
/*
 * 扩展后支持参数传递的的定时调度函数
 */
var __sti = setInterval;
window.setInterval = function(callback, timeout, param) {
	var args = Array.prototype.slice.call(arguments, 2);
	var _cb = function() {
		callback.apply(null, args);
	}
	__sti(_cb, timeout);
}

var __sto = setTimeout;
window.setTimeout = function(callback, timeout, param) {
	var args = Array.prototype.slice.call(arguments, 2);
	var _cb = function() {
		if (callback.apply)
			callback.apply(null, args);
	}
	__sto(_cb, timeout);
}

function checkHeartbeat_demsy() {
	$.ajax({
		type : 'POST',
		url : "/coc/chkHeartbeat/" + new Date().getTime(),
		async : true,
		data : "",
		dataType : "json",
		success : function(json) {
			__sto(checkHeartbeat_demsy, 180000);// 3*60*1000
		},
		error : function(jqXHR, statusText, responseError) {
			//alert("访问服务器出错！" + responseError);
		}
	});
}

/**
 * Cache data specified by name and value into the browser cookie.
 * <p>
 * <b>Parameters:</b>
 * <UL>
 * <LI>name: cached data key.
 * <LI>value: cached data value.
 * <LI>hours: cached period of validity, time unit is hour.
 * </UL>
 */
function setCookie(name, value, hours) {
	var expires = "";
	if (typeof hours == "undefined" || hours == null) {
		hours = 30 * 24;
	}
	var d = new Date();
	d.setTime(d.getTime() + 60 * 60 * 1000 * hours);
	expires = "; expires=" + d.toGMTString();
	
	document.cookie = escape(name) + "=" + escape(value) + ";path=/" + expires;
}

/**
 * Get cached data from the browser cookie.
 * <p>
 * <b>Parameters:</b>
 * <UL>
 * <LI>name: cached data key.
 * </UL>
 */
function getCookie(name) {
	var m = document.cookie.match(new RegExp("(^|; )(" + escape(name) + ")\\=([^;]*)(;|$)", "i"));
	return m == null ? null : unescape(m[3]);
}

/**
 * Remove cached data from the browser cookie.
 * <p>
 * <b>Parameters:</b>
 * <UL>
 * <LI>name: cached data key.
 * </UL>
 */
function removeCookie(name) {
	if (getCookie(name) != null)
		setCookie(name, "", -1);
}
