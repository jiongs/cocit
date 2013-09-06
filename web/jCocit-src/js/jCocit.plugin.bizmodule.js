(function($, jCocit) {
	function _init(mdlDIV) {

	}
	function doEdit(opts, dataID) {
		var pathArgs = opts.pathArgs;
		var url = "/coc/getBizFormModel/" + pathArgs + "/" + dataID + "?_windowWidth=890&_windowHeight=600";
		jCocit.dialog.open(url, "dialog_" + new Date().getTime(), {
			title : opts.text,
			width : 900,
			height : 640,
			modal : true,
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				onClick : function(data) {
					$(this).dialog('close');
				}
			}, {
				text : '应用',
				iconCls : 'icon-ok',
				onClick : function(data) {
				}
			}, {
				text : '取消',
				onClick : function(data) {
					$(this).dialog('close');
				}
			} ],
		});
	}

	jCocit.bizmodule = {
		doAction : function(opts) {
			switch (opts.opCode) {
			case 101:
				doEdit(opts, "");

				break;
			case 102:
				var gridID = "#datagrid_" + opts.bizToken;
				var selectedRow = $(gridID).datagrid("getSelected");
				doEdit(opts, selectedRow.id);

				break;
			default:
				Jalert("不支持的操作！{bizToken: '" + opts.bizToken + "', pathArgs: '" + opts.pathArgs + "', opCode:" + opts.opCode + "}");
			}
		},
		doSearch : function(value, name) {
			Jalert("value: " + value + ", name: " + name);
		},
		doSetting : function(opts) {
			Jalert("Setting: title: " + opts.title);
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