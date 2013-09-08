(function($, jCocit) {
	jCocit.bizmodule = {
		/**
		 * 处理功能操作
		 */
		doAction : function(opts) {
			switch (opts.opCode) {
			case 101:// insert
				doEdit(opts, "");

				break;
			case 102:// edit
				var gridID = "#datagrid_" + opts.bizToken;
				var row = $(gridID).datagrid("getSelected");
				if (row) {
					doEdit(opts, row.id);
				} else {
					Jwarn("请先选中一条记录！");
				}

				break;

			case 299:// delete
				var gridID = "#datagrid_" + opts.bizToken;
				var rows = $(gridID).datagrid("getChecked");
				if (rows.length == 0) {
					var row = $(gridID).datagrid("getSelected");
					if (row)
						rows[0] = row;
				}
				if (rows.length == 0) {
					Jwarn("请先选中一条或复选多条记录！");
				} else {
					Jconfirm("你确定要删除选中的 " + rows.length + " 条记录吗？", "", function(ok) {
						if (ok) {
							var ids = new Array();
							for (i = 0; i < rows.length; i++) {
								ids[ids.length] = rows[i].id;
							}
							doDelete(opts, ids.join(","), null, function() {
								$("#datagrid_" + opts.bizToken).datagrid("reload");
							})
						}
					});
				}

				break;
			default:
				Jalert("不支持该操作！{opCode: '" + opts.opCode + "', pathArgs: '" + opts.pathArgs + "', token:" + opts.bizToken + "}");
			}
		},
		doSetting : function(opts) {
			Jalert("不支持该操作！" + opts.title);
		},
		/**
		 * 在SearchBox框上执行查询操作时将调用该方法：刷新token对应的业务表Grid数据
		 */
		doSearch : function(value, name) {
			doGridRefresh($(this).searchbox("options").bizToken);
		},
		/**
		 * 在导航树上执行“Select/Check”操作时将调用该方法：刷新token对应的业务表Grid数据。
		 */
		doTreeSelect : function(node) {
			doGridRefresh($(this).tree("options").bizToken);
		},
		/**
		 * 在Grid上执行“Select/UnSelect/Check/UnCheck/CheckAll/UnCheckAll”等操作时将调用该方法：刷新子业务表的Grid数据。
		 */
		doGridSelect : function(rowIndex, row) {
			// 查找当前GRID
			var gridOptions = $(this).datagrid("options");
			// 查找子业务表Tabs
			var $childrenTabs = $("#childrentabs_" + gridOptions.bizToken);
			if ($childrenTabs.length > 0) {
				// 查找选中的子业务表Tab
				var $childTab = $childrenTabs.tabs("getSelected");
				// 查找子业务表Grid
				var $childGrid = $(".jCocit-datagrid", $childTab);
				// 刷新子业务表Grid数据
				doGridRefresh($childGrid.datagrid("options").bizToken);
			}
		},
		/**
		 * Grid数据刷新之前将调用该方法：准备Grid查询参数。
		 * <UL>
		 * <LI>主要用于切换到尚未加载的业务子表Tab选项时，这是Grid尚未加载，只有等待Grid加载数据的时候才会调用该方法；
		 * <LI>如果Tab项已经加载，则应该调用onTabsSelect方法；
		 * </UL>
		 */
		doGridBeforeLoad : function(queryParams) {
			// 查找当前GRID
			var gridOptions = $(this).datagrid("options");
			prepareGridQueryParams(gridOptions.bizToken, queryParams);
		},
		doGridHeaderContextMenu : function(e, field) {
			var $grid = $(this);
			var gridOptions = $grid.datagrid("options");

			// 查找Grid表头环境菜单
			var contextMenuID = "gridContextMenu_" + gridOptions.bizToken;
			var $contextMenu = $("#" + contextMenuID);

			// 创建Grid表头环境菜单
			if ($contextMenu.length == 0) {
				$contextMenu = $('<div id="' + contextMenuID + '"/>').appendTo('body');
				$contextMenu.menu({
					hideOnMouseLeave : true,
					hideOnMouseClick : false,
					onClick : function(item) {
						if (item.iconCls == 'icon-check') {
							$grid.datagrid('hideColumn', item.name);
							$contextMenu.menu('setIcon', {
								target : item.target,
								iconCls : 'icon-uncheck'
							});
						} else {
							$grid.datagrid('showColumn', item.name);
							$contextMenu.menu('setIcon', {
								target : item.target,
								iconCls : 'icon-check'
							});
						}
					}
				});
				var fields = $grid.datagrid('getColumnFields');
				for ( var i = 2; i < fields.length; i++) {
					var field = fields[i];
					var col = $grid.datagrid('getColumnOption', field);
					$contextMenu.menu('append', {
						text : col.title,
						name : field,
						iconCls : 'icon-check'
					});
				}
			}
			$contextMenu.menu('show', {
				left : e.pageX,
				top : e.pageY
			});
			e.preventDefault();
		},
		/**
		 * 切换业务子表Tab项时将调用该方法，如果Tab中的Grid已经存在，则刷新Grid数据。
		 */
		doTabsSelect : function(tabTitle, tabIndex) {
			// 查找选中的子业务表Tab
			var $childTab = $(this).tabs("getSelected");
			// 查找子业务表Grid
			var $childGrid = $(".jCocit-datagrid", $childTab);
			// 刷新子业务表Grid数据
			if ($childGrid.length)
				doGridRefresh($childGrid.datagrid("options").bizToken);
		}
	};

	/**
	 * 准备Grid查询参数。
	 * <UL>
	 * <LI>通过token查找Grid、Tree、Searchbox对象；
	 * <LI>queryParams：即为Grid查询参数JSON对象，查询条件直接放入该对象中；
	 * </UL>
	 */
	function prepareGridQueryParams(token, queryParams) {
		// 根据令牌查找导航树、Grid、搜索框对象
		var $tree = $("#tree_" + token);
		var $grid = $("#datagrid_" + token);
		var $searchbox = $("#searchbox_" + token);

		/*
		 * Navi Tree JSON Expression
		 */
		// 获取导航树中选中（包括单选和多选）的节点
		if ($tree.length > 0) {
			var nodes = $tree.tree("getChecked");
			var node = $tree.tree("getSelected");
			if (node)
				nodes.push(node);

			// 生成导航树过滤表达式。导航树节点ID格式为“字段名:字段值”。
			var naviTreeExpr = {};
			var nodeID, idx, fld, val, arr;
			for (i = 0; i < nodes.length; i++) {
				nodeID = nodes[i].id;
				idx = nodeID.indexOf(":");
				fld = "";
				val = "";
				if (idx > 0) {
					fld = nodeID.substring(0, idx);
					val = nodeID.substring(idx + 1);
				}

				if (fld.length > 0 && val.length > 0) {
					arr = naviTreeExpr[fld];
					if (!arr) {
						arr = [];
						naviTreeExpr[fld] = arr;
					}
					arr.push(val);
				}
			}
			queryParams["queryNaviTreeJsonExpr"] = $.toJsonString(naviTreeExpr);
		}

		/*
		 * Search Box JSON Expression
		 */
		if ($searchbox.length > 0) {
			var searchBoxField = $searchbox.searchbox("getName");// 字段名
			var searchBoxValue = $searchbox.searchbox("getValue");// 字段值
			// 生成关键字查询表达式
			if (searchBoxField != null && searchBoxField.trim().length > 0) {
				var searchBoxExpr = {};
				searchBoxExpr[searchBoxField] = searchBoxValue;
				queryParams["querySearchBoxJsonExpr"] = $.toJsonString(searchBoxExpr);
			} else {
				queryParams["querySearchBoxJsonExpr"] = "";
			}
		}

		/*
		 * Main Grid JSON Expression
		 */
		// 查找Grid所在的Tab
		var $gridTab = $grid.closest(".jCocit-gridtab");
		if ($gridTab.length > 0) {// 找到Grid所在的Tab

			// 获取Tab属性；获取父Grid令牌
			var gridTabOptions = $gridTab.panel("options");
			var parentToken = gridTabOptions.bizToken;

			if (parentToken) {
				// 查找父Grid
				var $parentGrid = $("#datagrid_" + parentToken);
				if ($parentGrid.length > 0) {
					// 获取父Grid中选中（包括seleted和checked）的行
					var row, parentGridExpr = {}, idArray = [];
					var rows = $parentGrid.datagrid("getChecked");
					row = $parentGrid.datagrid("getSelected");
					if (row)
						rows.push(row);

					// 创建父Grid中选中行的ID数组
					for (i = 0; i < rows.length; i++) {
						row = rows[i];
						idArray.push(row.id);
					}
					// 创建父Grid过滤表达式
					if (idArray.length > 0) {
						parentGridExpr[gridTabOptions.fkfield] = idArray;
						queryParams["queryParentGridJsonExpr"] = $.toJsonString(parentGridExpr);
					} else {
						queryParams["queryParentGridJsonExpr"] = "";
					}
				}
			}
		}
	}
	/**
	 * 刷新Grid数据，通过token查找Grid对象
	 */
	function doGridRefresh(token) {
		var $grid = $("#datagrid_" + token);
		if ($grid.length > 0)
			$grid.datagrid("reload");
	}
	function doDelete(opts, dataID, data, callback) {
		$.doAjax({
			type : "POST",
			dataType : "json",
			url : "/coc/deleteBizData/" + opts.pathArgs + "/" + dataID,
			data : data,
			success : callback
		});

	}
	function doSave(opts, dataID, data, callback) {
		$.doAjax({
			type : "POST",
			dataType : "json",
			url : "/coc/saveBizFormData/" + opts.pathArgs + "/" + dataID,
			data : data,
			success : callback
		});

	}
	/**
	 * 执行getBizFormUI操作之前将调用该方法准备业务表单数据。
	 */
	function prepareBizFormUIParams(token, formData) {
		// 根据令牌查找导航树、Grid、搜索框对象
		var $tree = $("#tree_" + token);
		var $grid = $("#datagrid_" + token);

		/*
		 * Navi Tree JSON Expression
		 */
		// 获取导航树中选中的节点
		if ($tree.length > 0) {
			var node = $tree.tree("getSelected");
			if (node) {
				var nodeID = node.id;
				var idx = nodeID.indexOf(":");
				if (idx > 0) {
					var fld = nodeID.substring(0, idx);
					var val = nodeID.substring(idx + 1);
					if (fld.length > 0 && val.length > 0)
						formData["data." + fld] = val;

				}
			}
		}

		/*
		 * Main Grid JSON Expression
		 */
		// 查找Grid所在的Tab
		var $gridTab = $grid.closest(".jCocit-gridtab");
		if ($gridTab.length > 0) {// 找到Grid所在的Tab

			// 获取Tab属性；获取父Grid令牌
			var gridTabOptions = $gridTab.panel("options");
			var parentToken = gridTabOptions.bizToken;

			if (parentToken) {
				// 查找父Grid
				var $parentGrid = $("#datagrid_" + parentToken);
				if ($parentGrid.length > 0) {
					// 获取父Grid中选中的行
					var row = $parentGrid.datagrid("getSelected");
					if (row) {
						formData["data." + gridTabOptions.fkfield + ".id"] = row.id;
						formData["data." + gridTabOptions.fkfield + ".name"] = row.name;
					}

				}
			}
		}
	}
	function doEdit(opts, dataID) {
		var data = {};
		prepareBizFormUIParams(opts.bizToken, data);
		var loadFormUrl = "/coc/getBizFormUI/" + opts.pathArgs + "/" + dataID + "?_windowWidth=890&_windowHeight=600&" + $.param(data);
		jCocit.dialog.open(loadFormUrl, "dialog_" + opts.bizToken + "_" + opts.opCode, {
			title : opts.text,
			width : 900,
			height : 640,
			logoCls : opts.iconCls || 'icon-logo',
			buttons : [ {
				text : '确定',
				onClick : function(data) {
					var $form = $("form", this);
					var $dialog = $(this);
					doSave(opts, dataID, $form.serialize(), function() {
						$dialog.dialog('close');
						$("#datagrid_" + opts.bizToken).datagrid("reload");
					});
				}
			}, {
				text : '应用',
				onClick : function(data) {
					var $form = $("form", this);
					doSave(opts, dataID, $form.serialize(), function() {
						$("#datagrid_" + opts.bizToken).datagrid("reload");
					});
				}
			}, {
				text : '取消',
				onClick : function(data) {
					$(this).dialog('close');
				}
			} ],
		});
	}

	function _init(mdlDIV) {
	}
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