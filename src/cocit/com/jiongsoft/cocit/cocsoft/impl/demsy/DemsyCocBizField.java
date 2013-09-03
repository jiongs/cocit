package com.jiongsoft.cocit.cocsoft.impl.demsy;

import static com.kmetop.demsy.Demsy.moduleEngine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nutz.json.Json;

import com.jiongsoft.cocit.cocsoft.CocBizField;
import com.jiongsoft.cocit.cocsoft.CocBizTable;
import com.jiongsoft.cocit.utils.KeyValue;
import com.jiongsoft.cocit.utils.Log;
import com.jiongsoft.cocit.utils.StringUtil;
import com.kmetop.demsy.comlib.biz.IBizFieldType;
import com.kmetop.demsy.comlib.entity.ISoftConfig;
import com.kmetop.demsy.comlib.impl.sft.dic.Dic;
import com.kmetop.demsy.comlib.impl.sft.dic.DicCategory;
import com.kmetop.demsy.comlib.impl.sft.system.AbstractSystemData;
import com.kmetop.demsy.comlib.impl.sft.system.SFTSystem;
import com.kmetop.demsy.lang.Str;

class DemsyCocBizField implements CocBizField {
	private AbstractSystemData entity;

	DemsyCocBizField(AbstractSystemData entity) {
		this.entity = entity;
	}

	@Override
	public boolean is(String propName) {
		Object obj = entity.get(propName);
		if (obj == null)
			return false;

		try {
			return Boolean.valueOf(obj.toString());
		} catch (Throwable e) {
			return false;
		}
	}

	@Override
	public Long getID() {
		return entity.getId();
	}

	@Override
	public String getName() {
		return entity.getName();
	}

	@Override
	public boolean isDisabled() {
		return entity.isDisabled();
	}

	@Override
	public String getInfo() {
		return entity.getDesc();
	}

	@Override
	public Date getCreatedDate() {
		return entity.getCreated();
	}

	@Override
	public String getCreatedUser() {
		return entity.getCreatedBy();
	}

	@Override
	public Date getLatestModifiedDate() {
		return entity.getUpdated();
	}

	@Override
	public String getLatestModifiedUser() {
		return entity.getUpdatedBy();
	}

	@Override
	public <T> T get(String propName) {
		return (T) entity.get(propName);
	}

	@Override
	public String getPropName() {
		return entity.getPropName();
	}

	@Override
	public Integer getScale() {
		return entity.getScale();
	}

	@Override
	public Integer getPrecision() {
		return entity.getPrecision();
	}

	@Override
	public byte getType() {
		IBizFieldType type = entity.getType();

		if (type.isBoolean())
			return TYPE_BOOL;
		if (type.isDate())
			return TYPE_DATE;
		if (type.isNumber())
			return TYPE_NUMBER;
		if (type.isRichText())
			return TYPE_RICH_TEXT;
		if (type.isString())
			return TYPE_STRING;
		if (type.isUpload())
			return TYPE_UPLOAD;
		if (type.isSystem())
			return TYPE_FK;
		if (type.isString() && this.getPrecision() > 255)
			return TYPE_TEXT;

		return TYPE_STRING;
	}

	@Override
	public String getEditMode(String actionMode) {
		String mode = entity.getMode();
		if (actionMode == null || actionMode.trim().length() == 0) {
			actionMode = "v";
		}
		if (mode == null || mode.trim().length() == 0) {
			return "";
		}
		mode = mode.trim();
		String[] dataModes = Str.toArray(mode, " ");
		String defaultActionMode = "*";
		String defaultMode = "";
		for (String dataMode : dataModes) {
			if (dataMode == null) {
				continue;
			}
			dataMode = dataMode.trim();
			int index = dataMode.indexOf(":");
			if (index < 0) {
				continue;
			}
			String actMode = dataMode.substring(0, index);
			if (actMode.equals(actionMode)) {
				return dataMode.substring(index + 1);
			}
			if (defaultActionMode.equals(actMode)) {
				defaultMode = dataMode.substring(index + 1);
			}
		}
		return defaultMode;
	}

	@Override
	public String getPattern() {
		return entity.getPattern();
	}

	@Override
	public boolean isPassword() {
		return entity.isPassword();
	}

	@Override
	public boolean isToString() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public KeyValue[] getOptions() {
		String str = entity.getOptions();

		if (!StringUtil.isNil(str)) {

			// 转换字符串为 KeyValue[]。
			return convertStringToOptions(str);

		} else if (getType() == TYPE_BOOL) {

			// 转换Bool值为 KeyValue[]。
			return new KeyValue[] { KeyValue.make("是", "1"), KeyValue.make("否", "0") };

		} else if (entity.getType().isV1Dic()) {

			// 转换 SFT V1版本的字典数据为 KeyValue[]
			DicCategory dicc = entity.getDicCategory();

			List<KeyValue> oplist = new ArrayList();
			if (dicc != null && !dicc.isDisabled()) {
				List<Dic> list = dicc.getDics();
				for (Dic dic : list) {
					if (!dic.isDisabled() && !dic.isDisabled())
						oplist.add(KeyValue.make(dic.getName(), dic.getId()));
				}
			}

			KeyValue[] ret = new KeyValue[oplist.size()];
			ret = oplist.toArray(ret);

			return ret;

		}

		return null;
	}

	private static KeyValue[] convertStringToOptions(String str) {
		str = str.trim();
		if (str.startsWith("[") && str.endsWith("]")) {
			try {
				return Json.fromJson(KeyValue[].class, str);
			} catch (Throwable e) {
				Log.error("解析字段选项出错：%s", str);
			}
		} else if (str.startsWith("{") && str.endsWith("}")) {
			String key = str.substring(1, str.length() - 1);
			ISoftConfig config = moduleEngine.getSoftConfig(key);
			if (config != null && !StringUtil.isNil(config.getValue())) {
				return convertStringToOptions(config.getValue());
			}
		} else {
			String[] strs = StringUtil.toArray(str, ",;，；\r\t\n");
			KeyValue[] options = new KeyValue[strs.length];
			int i = 0;
			for (String item : strs) {
				item = item.trim();
				int idx = item.indexOf(":");
				if (idx < 0) {
					idx = item.indexOf("：");
				}
				if (idx > -1) {
					options[i++] = KeyValue.make(item.substring(idx + 1).trim(), item.substring(0, idx).trim());
				} else {
					options[i++] = KeyValue.make(item, item);
				}
			}
			return options;
		}

		return new KeyValue[0];
	}

	@Override
	public boolean isGridField() {
		return entity.isGridField();
	}

	@Override
	public int getGridOrder() {
		return entity.getGridOrder();
	}

	@Override
	public int getGridWidth() {
		return entity.getGridWidth();
	}

	@Override
	public CocBizTable getFkDataTable() {
		SFTSystem sys = entity.getRefrenceSystem();
		if (sys == null)
			return null;

		return new DemsyCocBizTable(sys);
	}

	@Override
	public boolean isFkDataTableChild() {
		return entity.isMappingToMaster();
	}

	@Override
	public boolean isDisabledNavi() {
		return !entity.isDisabledNavi();
	}

	@Override
	public boolean isFkForManyToMany() {
		return entity.isSysMultiple();
	}

	@Override
	public boolean isCascading() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getUploadType() {
		String types = entity.getUploadType();
		return StringUtil.toArray(types, "|,; ");
	}

	@Override
	public int getSequence() {
		return entity.getOrderby();
	}

}
