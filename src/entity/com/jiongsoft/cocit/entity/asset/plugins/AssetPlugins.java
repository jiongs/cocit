package com.jiongsoft.cocit.entity.asset.plugins;

import java.util.ArrayList;
import java.util.List;

import com.jiongsoft.cocit.ActionContext;
import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.action.ActionHelper;
import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.asset.Asset;
import com.jiongsoft.cocit.entity.hr.Department;
import com.jiongsoft.cocit.entity.hr.Employee;
import com.jiongsoft.cocit.entity.plugin.BasePlugin;
import com.jiongsoft.cocit.orm.Orm;
import com.jiongsoft.cocit.service.TableService;
import com.jiongsoft.cocit.util.CocException;

public class AssetPlugins {

	public static class AddAssets extends BasePlugin<ActionHelper> {

		@Override
		public void before(ActionEvent<ActionHelper> event) {
			ActionHelper helper = event.getEntity();
			Orm orm = helper.orm;
			TableService tableService = helper.table;
			String opMode = helper.opMode;
			ActionContext context = Cocit.getActionContext();

			Asset asset = (Asset) helper.entity;
			asset.setSoftID(context.getSoftID());

			String[] usedByDepartments = context.getParameterValues("item.usedByDepartment.id");
			String[] usedByPersons = context.getParameterValues("item.usedByPerson.id");
			String[] numbers = context.getParameterValues("item.number");
			String[] barCodes = context.getParameterValues("item.barCode");

			Asset item;
			int numCount = 0;

			List<Asset> list = new ArrayList();
			for (int i = 0; i < usedByPersons.length; i++) {
				String personStr = usedByPersons[i];
				String orgStr = usedByDepartments[i];
				String numStr = numbers[i];
				String barCode = barCodes[i];

				Employee person = null;
				Department org = null;
				int num = 1;
				try {
					Long personID = Long.parseLong(personStr);
					person = orm.load(Employee.class, personID);
				} catch (Throwable e) {
				}
				try {
					Long orgID = Long.parseLong(orgStr);
					org = orm.load(Department.class, orgID);
				} catch (Throwable e) {
				}
				try {
					num = Integer.parseInt(numStr);
				} catch (Throwable e) {
				}

				/*
				 * 使用部门和使用人都不存在，则忽略。
				 */
				if (person == null && org == null) {
					continue;
				}

				item = asset.clone();
				if (person != null) {
					item.setUsedByPerson(person);
					item.setDepositAddress(person.getOfficeAddress());
				}

				if (org != null) {
					item.setUsedByDepartment(org);
				}

				item.setNumber(num);
				item.setBarCode(barCode);

				tableService.validateEntityData(opMode, item);

				numCount += num;

				list.add(item);
			}

			Integer totalNumber = asset.getNumber();
			if (totalNumber != null) {
				if (numCount > totalNumber) {
					throw new CocException("设备数量不一致！");
				}
				if (numCount < totalNumber) {
					asset.setNumber(totalNumber - numCount);
					tableService.validateEntityData(opMode, asset);
					list.add(asset);
				}
			}

			if (list.size() == 0) {
				throw new CocException("非法设备数量！");
			}

			orm.save(list);
		}
	}
}
