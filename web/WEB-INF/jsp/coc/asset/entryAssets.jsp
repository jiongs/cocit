<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<style>
<!--

.entry-assets-form .spinner,.entry-assets-form .input,.entry-assets-form .CbB {
    border-color: #c5dbec;
}
.entry-assets-form .spinner-arrow-up,.entry-assets-form .spinner-arrow-down{
    width: 18px;
}
.entry-assets-form .spinner-text{
    border-color: transparent;
}
-->
</style>

<form class="entity-form entry-assets-form">
	<input name="entity.id" type="hidden" value="">
	<div class="entity-groups">
		<div class="entity-group">
			<div class="entity-group-header">基本信息</div>
			<table valign="top" width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<th class="entity-field-header">设备名称</th>
					<td class="entity-field-box"><input name="entity.name" value="" class="input" /> <span class="icon-mode-M">&nbsp;&nbsp;&nbsp;</span></td>
					<th class="entity-field-header">设备编号</th>
					<td class="entity-field-box"><input name="entity.code" value="" class="input" /> <span class="icon-mode-M">&nbsp;&nbsp;&nbsp;</span></td>
				</tr>
				<tr>
					<th class="entity-field-header">资产类型</th>
					<td class="entity-field-box"><input name="entity.type.id" class="jCocit-ui jCocit-combogrid"
						data-options="value:'',text:'',width:202,
panelWidth: 600,
panelHeight: 300,
singleSelect: false,
idField: 'id',
textField: 'name',
url: '/coc/getEntityGridData/0:43:',
rownumbers: true,
pagination: true,
mode: 'remote',
pageSize: 20,
columns: [[
{field:'id',title:'ID',width:80,align:'right',checkbox:true},
{field:'name',title:'名称',width:150,sortable:true,align:'left'},
{field:'code',title:'编号',width:150,sortable:true,align:'left'},
{field:'desc',title:'描述',width:200,sortable:true,align:'left'},
]],
fitColumns: true
" />
					</td>
					<th class="entity-field-header">资产分类</th>
					<td class="entity-field-box"><input name="entity.category.id" class="jCocit-ui jCocit-combotree" style="width: 202px;" data-options="value:'',text:'',
					onlyLeafValue:true,
url: '/coc/getEntityTreeData/0:9:'
" /></td>
				</tr>
				<tr>
					<th class="entity-field-header">设备来源</th>
					<td class="entity-field-box"><input name="entity.origin.id" class="jCocit-ui jCocit-combogrid"
						data-options="value:'',text:'',width:202,
panelWidth: 600,
panelHeight: 300,
singleSelect: false,
idField: 'id',
textField: 'name',
url: '/coc/getEntityGridData/0:7:',
rownumbers: true,
pagination: true,
mode: 'remote',
pageSize: 20,
columns: [[
{field:'id',title:'ID',width:80,align:'right',checkbox:true},
{field:'name',title:'名称',width:150,sortable:true,align:'left'},
{field:'code',title:'编号',width:150,sortable:true,align:'left'},
{field:'desc',title:'描述',width:200,sortable:true,align:'left'},
]],
fitColumns: true
" />
						<span class="icon-mode-M">&nbsp;&nbsp;&nbsp;</span></td>
					<th class="entity-field-header">设备规格</th>
					<td class="entity-field-box"><input name="entity.specification.id" class="jCocit-ui jCocit-combogrid"
						data-options="value:'',text:'',width:202,
panelWidth: 600,
panelHeight: 300,
singleSelect: false,
idField: 'id',
textField: 'name',
url: '/coc/getEntityGridData/0:25:',
rownumbers: true,
pagination: true,
mode: 'remote',
pageSize: 20,
columns: [[
{field:'id',title:'ID',width:80,align:'right',checkbox:true},
{field:'name',title:'名称',width:150,sortable:true,align:'left'},
{field:'code',title:'编号',width:150,sortable:true,align:'left'},
{field:'category',title:'资产分类',width:150,sortable:true,align:'left'},
]],
fitColumns: true
" />
					</td>
				</tr>
				<tr>
					<th class="entity-field-header">设备型号</th>
					<td class="entity-field-box"><input name="entity.model.id" class="jCocit-ui jCocit-combogrid"
						data-options="value:'',text:'',width:202,
panelWidth: 600,
panelHeight: 300,
singleSelect: false,
idField: 'id',
textField: 'name',
url: '/coc/getEntityGridData/0:11:',
rownumbers: true,
pagination: true,
mode: 'remote',
pageSize: 20,
columns: [[
{field:'id',title:'ID',width:80,align:'right',checkbox:true},
{field:'name',title:'名称',width:150,sortable:true,align:'left'},
{field:'code',title:'编号',width:150,sortable:true,align:'left'},
{field:'category',title:'资产分类',width:150,sortable:true,align:'left'},
]],
fitColumns: true
" />
					</td>
					<th class="entity-field-header">设备用途</th>
					<td class="entity-field-box"><input name="entity.purpose.id" class="jCocit-ui jCocit-combogrid"
						data-options="value:'',text:'',width:202,
panelWidth: 600,
panelHeight: 300,
singleSelect: false,
idField: 'id',
textField: 'name',
url: '/coc/getEntityGridData/0:6:',
rownumbers: true,
pagination: true,
mode: 'remote',
pageSize: 20,
columns: [[
{field:'id',title:'ID',width:80,align:'right',checkbox:true},
{field:'name',title:'名称',width:150,sortable:true,align:'left'},
{field:'code',title:'编号',width:150,sortable:true,align:'left'},
{field:'desc',title:'描述',width:200,sortable:true,align:'left'},
]],
fitColumns: true
" />
						<span class="icon-mode-M">&nbsp;&nbsp;&nbsp;</span></td>
				</tr>
				<tr>
					<th class="entity-field-header">供货商</th>
					<td class="entity-field-box"><input name="entity.supplier.id" class="jCocit-ui jCocit-combogrid"
						data-options="value:'',text:'',width:202,
panelWidth: 600,
panelHeight: 300,
singleSelect: false,
idField: 'id',
textField: 'name',
url: '/coc/getEntityGridData/0:26:',
rownumbers: true,
pagination: true,
mode: 'remote',
pageSize: 20,
columns: [[
{field:'id',title:'ID',width:80,align:'right',checkbox:true},
{field:'name',title:'名称',width:150,sortable:true,align:'left'},
{field:'code',title:'编号',width:150,sortable:true,align:'left'},
{field:'address',title:'单位地址',width:200,sortable:true,align:'left'},
]],
fitColumns: true
" />
						<span class="icon-mode-M">&nbsp;&nbsp;&nbsp;</span></td>
					<th class="entity-field-header">计量单位</th>
					<td class="entity-field-box"><input name="entity.uom.id" class="jCocit-ui jCocit-combogrid"
						data-options="value:'',text:'',width:202,
panelWidth: 600,
panelHeight: 300,
singleSelect: false,
idField: 'id',
textField: 'name',
url: '/coc/getEntityGridData/0:19:',
rownumbers: true,
pagination: true,
mode: 'remote',
pageSize: 20,
columns: [[
{field:'id',title:'ID',width:80,align:'right',checkbox:true},
{field:'name',title:'名称',width:150,sortable:true,align:'left'},
{field:'code',title:'编号',width:150,sortable:true,align:'left'},
{field:'desc',title:'描述',width:200,sortable:true,align:'left'},
]],
fitColumns: true
" />
					</td>
				</tr>
				<tr>
					<th class="entity-field-header">购置日期</th>
					<td class="entity-field-box"><input name="entity.buyDate" value="" class="jCocit-ui jCocit-combodate" data-options="width:202" /></td>
					<th class="entity-field-header">存放地点</th>
					<td class="entity-field-box"><input name="entity.depositAddress.id" class="jCocit-ui jCocit-combotree" style="width: 202px;" data-options="value:'',text:'',
					onlyLeafValue:true,
url: '/coc/getEntityTreeData/0:27:'
" /> <span class="icon-mode-M">&nbsp;&nbsp;&nbsp;</span></td>
				</tr>
				<tr>
					<th class="entity-field-header">设备单价</th>
					<td class="entity-field-box"><input name="entity.unitPrice" value="" style="text-align: right;" class="input jCocit-ui jCocit-numberbox" data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'￥ ',suffix:' 元'" /></td>
					<th class="entity-field-header">附件单价</th>
					<td class="entity-field-box"><input name="entity.attachmentUnitPrice" style="text-align: right;" value="" class="input jCocit-ui jCocit-numberbox" data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'￥ ',suffix:' 元'" /></td>
				</tr>
				<tr>
					<th class="entity-field-header">设备数量</th>
					<td class="entity-field-box"><input name="entity.number" style="text-align: right; width: 202px;" value="" class="input jCocit-ui jCocit-spinnernumber" data-options="min:1,precision:null,groupSeparator:','" /></td>
					<th class="entity-field-header">附件数量</th>
					<td class="entity-field-box"><input name="entity.attachmentNumber" style="text-align: right; width: 202px;" value="" class="input jCocit-ui jCocit-spinnernumber" data-options="min:0,precision:null,groupSeparator:','" /></td>
				</tr>
				<tr>
					<th class="entity-field-header">设备金额</th>
					<td class="entity-field-box"><input name="entity.totalPrice" style="text-align: right; width: 202px;" value="" class="input jCocit-ui jCocit-numberbox" data-options="precision:null,groupSeparator:','" /></td>
					<th class="entity-field-header">附件总额</th>
					<td class="entity-field-box"><input name="entity.attachmentTotalPrice" style="text-align: right;" value="" class="input jCocit-ui jCocit-numberbox" data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'￥ ',suffix:' 元'" /></td>
				</tr>
			</table>
		</div>
		<div class="entity-group" style="margin-top: 10px;">
			<div class="entity-group-header">设备领用明细</div>
			<table valign="top" border="0" cellpadding="0" cellspacing="0">
				<tr class="entity-field-header">
					<th class="entity-field-header" style="width: 265px; text-align:center;">使用部门</th>
					<th class="entity-field-header" style="width: 265px; text-align:center;">领用人</th>
					<th class="entity-field-header" style="width: 60px; text-align:center;">设备数量</th>
					<th class="entity-field-header" style="width: 120px; text-align:center;">条码</th>
				</tr>
				<tr>
					<td class="entity-field-box"><input name="item.usedByDepartment.id" class="jCocit-ui jCocit-combotree" style="width: 265px;" data-options="
						value:'',
						text:'',
						onlyLeafValue:true,
	                    url: '/coc/getEntityTreeData/0:28:'
                    " />
					<td class="entity-field-box"><input name="item.usedByPerson.id" class="jCocit-ui jCocit-combodialog" style="width: 265px;" data-options="
		                url: '/coc/getEntitySelectionTableUI/hr_employee:29:?_uiWidth=680&_uiHeight=430',
		                dialogTitle: '选择员工',
		                panelWidth : 700,
		                panelHeight :500,
				        ok : '选择',
				        cancel : '取消'
                    " />
					</td>
					<td class="entity-field-box"><input name="item.number" value="1" style="text-align: right; width:60px;" class="input jCocit-ui jCocit-spinnernumber" data-options="min:1,precision:null,groupSeparator:','" /></td>
					<td class="entity-field-box"><input name="item.barCode" value="" style="width:120px;" class="input" /></td>
				</tr>
                <tr>
                    <td class="entity-field-box"><input name="item.usedByDepartment.id" class="jCocit-ui jCocit-combotree" style="width: 265px;" data-options="
                        value:'',
                        text:'',
                        onlyLeafValue:true,
                        url: '/coc/getEntityTreeData/0:28:'
                    " />
                    <td class="entity-field-box"><input name="item.usedByPerson.id" class="jCocit-ui jCocit-combodialog" style="width: 265px;" data-options="
                        url: '/coc/getEntitySelectionTableUI/hr_employee:29:?_uiWidth=680&_uiHeight=430',
                        dialogTitle: '选择员工',
                        panelWidth : 700,
                        panelHeight :500,
                        ok : '选择',
                        cancel : '取消'
                    " />
                    </td>
                    <td class="entity-field-box"><input name="item.number" value="1" style="text-align: right; width:60px;" class="input jCocit-ui jCocit-spinnernumber" data-options="min:1,precision:null,groupSeparator:','" /></td>
                    <td class="entity-field-box"><input name="item.barCode" value="" style="width:120px;" class="input" /></td>
                </tr>
                <tr>
                    <td class="entity-field-box"><input name="item.usedByDepartment.id" class="jCocit-ui jCocit-combotree" style="width: 265px;" data-options="
                        value:'',
                        text:'',
                        onlyLeafValue:true,
                        url: '/coc/getEntityTreeData/0:28:'
                    " />
                    <td class="entity-field-box"><input name="item.usedByPerson.id" class="jCocit-ui jCocit-combodialog" style="width: 265px;" data-options="
                        url: '/coc/getEntitySelectionTableUI/hr_employee:29:?_uiWidth=680&_uiHeight=430',
                        dialogTitle: '选择员工',
                        panelWidth : 700,
                        panelHeight :500,
                        ok : '选择',
                        cancel : '取消'
                    " />
                    </td>
                    <td class="entity-field-box"><input name="item.number" value="1" style="text-align: right; width:60px;" class="input jCocit-ui jCocit-spinnernumber" data-options="min:1,precision:null,groupSeparator:','" /></td>
                    <td class="entity-field-box"><input name="item.barCode" value="" style="width:120px;" class="input" /></td>
                </tr>
                <tr>
                    <td class="entity-field-box"><input name="item.usedByDepartment.id" class="jCocit-ui jCocit-combotree" style="width: 265px;" data-options="
                        value:'',
                        text:'',
                        onlyLeafValue:true,
                        url: '/coc/getEntityTreeData/0:28:'
                    " />
                    <td class="entity-field-box"><input name="item.usedByPerson.id" class="jCocit-ui jCocit-combodialog" style="width: 265px;" data-options="
                        url: '/coc/getEntitySelectionTableUI/hr_employee:29:?_uiWidth=680&_uiHeight=430',
                        dialogTitle: '选择员工',
                        panelWidth : 700,
                        panelHeight :500,
                        ok : '选择',
                        cancel : '取消'
                    " />
                    </td>
                    <td class="entity-field-box"><input name="item.number" value="1" style="text-align: right; width:60px;" class="input jCocit-ui jCocit-spinnernumber" data-options="min:1,precision:null,groupSeparator:','" /></td>
                    <td class="entity-field-box"><input name="item.barCode" value="" style="width:120px;" class="input" /></td>
                </tr>
                <tr>
                    <td class="entity-field-box"><input name="item.usedByDepartment.id" class="jCocit-ui jCocit-combotree" style="width: 265px;" data-options="
                        value:'',
                        text:'',
                        onlyLeafValue:true,
                        url: '/coc/getEntityTreeData/0:28:'
                    " />
                    <td class="entity-field-box"><input name="item.usedByPerson.id" class="jCocit-ui jCocit-combodialog" style="width: 265px;" data-options="
                        url: '/coc/getEntitySelectionTableUI/hr_employee:29:?_uiWidth=680&_uiHeight=430',
                        dialogTitle: '选择员工',
                        panelWidth : 700,
                        panelHeight :500,
                        ok : '选择',
                        cancel : '取消'
                    " />
                    </td>
                    <td class="entity-field-box"><input name="item.number" value="1" style="text-align: right; width:60px;" class="input jCocit-ui jCocit-spinnernumber" data-options="min:1,precision:null,groupSeparator:','" /></td>
                    <td class="entity-field-box"><input name="item.barCode" value="" style="width:120px;" class="input" /></td>
                </tr>
                <tr>
                    <td class="entity-field-box"><input name="item.usedByDepartment.id" class="jCocit-ui jCocit-combotree" style="width: 265px;" data-options="
                        value:'',
                        text:'',
                        onlyLeafValue:true,
                        url: '/coc/getEntityTreeData/0:28:'
                    " />
                    <td class="entity-field-box"><input name="item.usedByPerson.id" class="jCocit-ui jCocit-combodialog" style="width: 265px;" data-options="
                        url: '/coc/getEntitySelectionTableUI/hr_employee:29:?_uiWidth=680&_uiHeight=430',
                        dialogTitle: '选择员工',
                        panelWidth : 700,
                        panelHeight :500,
                        ok : '选择',
                        cancel : '取消'
                    " />
                    </td>
                    <td class="entity-field-box"><input name="item.number" value="1" style="text-align: right; width:60px;" class="input jCocit-ui jCocit-spinnernumber" data-options="min:1,precision:null,groupSeparator:','" /></td>
                    <td class="entity-field-box"><input name="item.barCode" value="" style="width:120px;" class="input" /></td>
                </tr>
                <tr>
                    <td class="entity-field-box"><input name="item.usedByDepartment.id" class="jCocit-ui jCocit-combotree" style="width: 265px;" data-options="
                        value:'',
                        text:'',
                        onlyLeafValue:true,
                        url: '/coc/getEntityTreeData/0:28:'
                    " />
                    <td class="entity-field-box"><input name="item.usedByPerson.id" class="jCocit-ui jCocit-combodialog" style="width: 265px;" data-options="
                        url: '/coc/getEntitySelectionTableUI/hr_employee:29:?_uiWidth=680&_uiHeight=430',
                        dialogTitle: '选择员工',
                        panelWidth : 700,
                        panelHeight :500,
                        ok : '选择',
                        cancel : '取消'
                    " />
                    </td>
                    <td class="entity-field-box"><input name="item.number" value="1" style="text-align: right; width:60px;" class="input jCocit-ui jCocit-spinnernumber" data-options="min:1,precision:null,groupSeparator:','" /></td>
                    <td class="entity-field-box"><input name="item.barCode" value="" style="width:120px;" class="input" /></td>
                </tr>
                <tr>
                    <td class="entity-field-box"><input name="item.usedByDepartment.id" class="jCocit-ui jCocit-combotree" style="width: 265px;" data-options="
                        value:'',
                        text:'',
                        onlyLeafValue:true,
                        url: '/coc/getEntityTreeData/0:28:'
                    " />
                    <td class="entity-field-box"><input name="item.usedByPerson.id" class="jCocit-ui jCocit-combodialog" style="width: 265px;" data-options="
                        url: '/coc/getEntitySelectionTableUI/hr_employee:29:?_uiWidth=680&_uiHeight=430',
                        dialogTitle: '选择员工',
                        panelWidth : 700,
                        panelHeight :500,
                        ok : '选择',
                        cancel : '取消'
                    " />
                    </td>
                    <td class="entity-field-box"><input name="item.number" value="1" style="text-align: right; width:60px;" class="input jCocit-ui jCocit-spinnernumber" data-options="min:1,precision:null,groupSeparator:','" /></td>
                    <td class="entity-field-box"><input name="item.barCode" value="" style="width:120px;" class="input" /></td>
                </tr>
                <tr>
                    <td class="entity-field-box"><input name="item.usedByDepartment.id" class="jCocit-ui jCocit-combotree" style="width: 265px;" data-options="
                        value:'',
                        text:'',
                        onlyLeafValue:true,
                        url: '/coc/getEntityTreeData/0:28:'
                    " />
                    <td class="entity-field-box"><input name="item.usedByPerson.id" class="jCocit-ui jCocit-combodialog" style="width: 265px;" data-options="
                        url: '/coc/getEntitySelectionTableUI/hr_employee:29:?_uiWidth=680&_uiHeight=430',
                        dialogTitle: '选择员工',
                        panelWidth : 700,
                        panelHeight :500,
                        ok : '选择',
                        cancel : '取消'
                    " />
                    </td>
                    <td class="entity-field-box"><input name="item.number" value="1" style="text-align: right; width:60px;" class="input jCocit-ui jCocit-spinnernumber" data-options="min:1,precision:null,groupSeparator:','" /></td>
                    <td class="entity-field-box"><input name="item.barCode" value="" style="width:120px;" class="input" /></td>
                </tr>
                <tr>
                    <td class="entity-field-box"><input name="item.usedByDepartment.id" class="jCocit-ui jCocit-combotree" style="width: 265px;" data-options="
                        value:'',
                        text:'',
                        onlyLeafValue:true,
                        url: '/coc/getEntityTreeData/0:28:'
                    " />
                    <td class="entity-field-box"><input name="item.usedByPerson.id" class="jCocit-ui jCocit-combodialog" style="width: 265px;" data-options="
                        url: '/coc/getEntitySelectionTableUI/hr_employee:29:?_uiWidth=680&_uiHeight=430',
                        dialogTitle: '选择员工',
                        panelWidth : 700,
                        panelHeight :500,
                        ok : '选择',
                        cancel : '取消'
                    " />
                    </td>
                    <td class="entity-field-box"><input name="item.number" value="1" style="text-align: right; width:60px;" class="input jCocit-ui jCocit-spinnernumber" data-options="min:1,precision:null,groupSeparator:','" /></td>
                    <td class="entity-field-box"><input name="item.barCode" value="" style="width:120px;" class="input" /></td>
                </tr>
			</table>
		</div>
	</div>
</form>