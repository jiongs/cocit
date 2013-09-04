(function($, jCocit) {
	function _init(mdlDIV) {

	}

	jCocit.bizmodule = {
		doAction : function(opts) {
			alert("doAction: moduleID=" + opts.moduleID + ", tableID=" + opts.tableID + ", opID=" + opts.opID + ", opCode=" + opts.opCode + ", opMode=" + opts.opMode);

			jCocit.dialog.open("/coc/getBizTableModel/7:65:", "dialog_" + new Date().getTime(), {
				title : "jCocit Dialog",
				width : 800,
				height : 600
			});
		}

	};
	$.fn.bizmodule = function(options, args) {
		if (typeof options == "string") {
			var fn = $.fn.bizmodule.methods[options];
			if (fn)
				return fn(this, args);
		}
		options = options || {};
		return this.each(function() {
			var state = $d(this, "bizmodule");
			if (state) {
				$.extend(state.options, options);
			} else {
				$d(this, "bizmodule", {
					options : $.extend({}, $.fn.bizmodule.defaults, $.fn.bizmodule.parseOptions(this), options)
				});
			}
			_init(this);
		});
	};

	$.fn.bizmodule.parseOptions = function(html) {
		return $.extend({}, jCocit.parseOptions(html, []));
	};

	$.fn.bizmodule.methods = {
		options : function(jq) {
			return $d(jq[0], "bizmodule").options;
		}
	};

	$.fn.bizmodule.defaults = {};
})(jQuery, jCocit);