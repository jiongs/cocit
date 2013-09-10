package com.kmetop.demsy.orm.nutz;

import java.lang.reflect.Field;

import org.nutz.dao.Dao;
import org.nutz.dao.entity.Link;
import org.nutz.dao.impl.DemsyInsertInvoker;
import org.nutz.lang.Each;
import org.nutz.lang.ExitLoop;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;

import com.kmetop.demsy.lang.Obj;

public class SaveManyInvoker extends DemsyInsertInvoker {

	public SaveManyInvoker(Dao dao, Object mainObj, Mirror<?> mirror) {
		super(dao, mainObj, mirror);
	}

	public void invoke(final Link link, Object many) {
		Object first = Lang.first(many);
		if (null != first) {
			Field refer = link.getReferField();
			if (null == refer) {
				Lang.each(many, new Each<Object>() {
					public void invoke(int index, Object ta, int size) throws ExitLoop {
						((IExtDao) dao).save(ta, null, true);
					}
				});
			} else {
				final Mirror<?> mta = Mirror.me(first.getClass());
				Lang.each(many, new Each<Object>() {
					public void invoke(int index, Object ta, int size) throws ExitLoop {
						mta.setValue(ta, link.getTargetField(), mainObj);
						/**
						 * 保存一对多数据时：先查看批量操作的数据状态码，如批量添加业务子表数据，包括删除、添加、修改等。
						 * <p>
						 * 1 - 删除
						 */
						byte statusCode = Obj.getValue(ta, "statusForJsonData");
						if (statusCode == 1) {
							if (!Obj.isEmpty(ta))
								((IExtDao) dao).delete(ta);
						} else {
							((IExtDao) dao).save(ta, null, true);
						}
					}
				});
			}
		}
	}
}
