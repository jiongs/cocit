/**
 * 
 * <P>
 * <B>Relationship:</B>
 * <UL>
 * <LI>Reference: calendar, panel
 * <LI>ReferencedBy:
 * <LI>SubClass: datetimebox
 * <LI>SuperClass: combo
 * </UL>
 * 
 * <pre>
 * 	[div class='Cb']
 * 		[div class='CbB combodialog']
 * 			[input class='combodialog-f Cb-f CbT' autocomplete='off' /]
 * 			[input class='CbA' type='text' /]
 * 		[/div]
 * 	[/div]
 * </pre>
 * 
 * <p>
 * <UL>
 * <LI>combodialog:
 * <LI>combodialog-panel:
 * <LI>combodialog-calendar-inner:
 * <LI>calendar:
 * <LI>calendar-noborder:
 * <LI>combodialog-button:
 * <LI>combodialog-today:
 * <LI>combodialog-close:
 * </UL>
 */
(function($, jCocit) {
	function _init(selfHTML) {
		var state = $d(selfHTML, "combodialog");
		var opts = state.options;
		$ac("combodialog-f", $(selfHTML));
		var $combo = $(selfHTML).combo($.extend({}, opts, {
			onInitPanel : function() {
				return false;
			},
			onShowPanel : function() {
				return false;
			},
			onBeforeShowPanel : function() {
				if (jCocit.dialog && opts.url) {
					if (!opts.dialogID)
						opts.dialogID = "combodialog_" + new Date().getTime();

					jCocit.dialog.open(opts.url, opts.dialogID, {
						title : opts.dialogTitle,
						width : opts.panelWidth,
						height : opts.panelHeight,
						modal : true,
						logoCls : opts.iconCls || 'icon-logo',
						buttons : [ {
							text : $.fn.combodialog.defaults.ok,
							onClick : function(data) {
								opts.onSelect.call(this, selfHTML, data);
								$(this).dialog('close');
							}
						}, {
							text : $.fn.combodialog.defaults.cancel,
							onClick : function(data) {
								$(this).dialog('close');
							}
						} ],
					});
				}
				return false;
			}
		}));
		$ac("combodialog", $p($(selfHTML).combo("textbox")));
		setValue(selfHTML, opts.value);

	}

	function _doQuery(selfHTML, keyword) {
	}

	function _doEnter(selfHTML) {
	}

	function setValue(selfHTML, strDate) {
	}

	$.fn.combodialog = function(options, args) {
		if (typeof options == "string") {
			var fn = $.fn.combodialog.methods[options];
			if (fn) {
				return fn(this, args);
			} else {
				return this.combo(options, args);
			}
		}
		options = options || {};
		return this.each(function() {
			var state = $d(this, "combodialog");
			if (state) {
				$.extend(state.options, options);
			} else {
				$d(this, "combodialog", {
					options : $.extend({}, $.fn.combodialog.defaults, $.fn.combodialog.parseOptions(this), options)
				});
			}
			_init(this);
		});
	};

	$.fn.combodialog.methods = {
		options : function(jq) {
			var opts = $d(jq[0], "combodialog").options;
			opts.originalValue = jq.combo("options").originalValue;
			return opts;
		},
		dialog : function(jq) {
			return $d(jq[0], "combodialog").dialog;
		},
		setValue : $X(setValue)
	};

	$.fn.combodialog.parseOptions = function(selfHTML) {
		return $.extend({}, $.fn.combo.parseOptions(selfHTML), {});
	};

	$.fn.combodialog.defaults = $.extend({}, $.fn.combo.defaults, {
		panelWidth : 800,
		panelHeight :600,
		dialogTitle : "",
		ok : "Ok",
		cancel : "Cancel",
		/**
		 * This method will be invoked when click 'OK' button.
		 * args: comboHTML, buttonData
		 */
		onSelect : $n,
		keyHandler : {
			doKey : $n,
			doEnter : function() {
				_doEnter(this);
			},
			doQuery : function(keyword) {
				_doQuery(this, keyword);
			}
		}
	});
})(jQuery, jCocit);
