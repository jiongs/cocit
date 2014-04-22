<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<style>
<!--

.add-system-form .spinner,.add-system-form .input,.add-system-form .CbB {
    border-color: #c5dbec;
}
.add-system-form .spinner-arrow-up,.add-system-form .spinner-arrow-down{
    width: 18px;
}
.add-system-form .spinner-text{
    border-color: transparent;
}
-->
</style>

<form class="entity-form add-system-form">
	<input name="entity.id" type="hidden" value="">
	<div class="entity-groups">
		<div class="entity-group">
			<div class="entity-group-header">模块信息</div>
			<table valign="top" width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<th class="entity-field-header">模块名称</th>
					<td class="entity-field-box"><input name="entity.name" value="" class="input" /> <span class="icon-mode-M">&nbsp;&nbsp;&nbsp;</span></td>
					<th class="entity-field-header">模块编号</th>
					<td class="entity-field-box"><input name="entity.code" value="" class="input" /> <span class="icon-mode-M">&nbsp;&nbsp;&nbsp;</span></td>
				</tr>
				<tr>
					<th class="entity-field-header">模块分类</th>
					<td class="entity-field-box"><input name="entity.category.id" class="jCocit-ui jCocit-combotree" style="width: 202px;" data-options="
						value:'',
						text:'',
						onlyLeafValue:false,
						styleName: 'folder-tree',
						url: '/coc/getEntityTreeData/_biz_catalog:_biz_catalog:'
						" /> <span class="icon-mode-M">&nbsp;&nbsp;&nbsp;</span>
					</td>
					<th class="entity-field-header">路径前缀</th>
					<td class="entity-field-box"><select name="entity.pathPrefix" class="select" >
							<option value=""></option>
							<option value="/coc">/coc</option>
						</select>
					</td>
				</tr>
				<tr>
					<th class="entity-field-header">窗体模版</th>
					<td class="entity-field-box"><input name="entity.template" value="" class="input" /></td>
					<th class="entity-field-header">排序表达式</th>
					<td class="entity-field-box"><input name="entity.sortExpr" value="" class="input" /></td>
				</tr>
				<tr>
					<th class="entity-field-header">映射数据表</th>
					<td class="entity-field-box"><input name="entity.mappingTable" value="" class="input" /></td>
					<th class="entity-field-header">映射实体类</th>
					<td class="entity-field-box"><input name="entity.mappingClass" value="" class="input" /></td>
				</tr>
			</table>
		</div>
	</div>
</form>