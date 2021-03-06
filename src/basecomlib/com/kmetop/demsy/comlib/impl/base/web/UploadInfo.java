package com.kmetop.demsy.comlib.impl.base.web;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZ_DEL;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_ADMIN;
import static com.kmetop.demsy.comlib.LibConst.BIZSYS_ADMIN_MODULE;
import static com.kmetop.demsy.comlib.LibConst.BIZSYS_ADMIN_UPLOAD;
import static com.kmetop.demsy.comlib.LibConst.BIZSYS_BZUDF_FIELD;
import static com.kmetop.demsy.comlib.LibConst.ORDER_SYSADMIN_UPLOAD;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.biz.IBizField;
import com.kmetop.demsy.comlib.biz.field.Upload;
import com.kmetop.demsy.comlib.impl.BizEntity;
import com.kmetop.demsy.comlib.impl.base.security.Module;
import com.kmetop.demsy.comlib.impl.sft.system.AbstractSystemData;
import com.kmetop.demsy.comlib.security.IModule;
import com.kmetop.demsy.comlib.web.IUploadInfo;

@Entity
@CocTable(name = "上传文件日志", code = BIZSYS_ADMIN_UPLOAD, catalog = BIZCATA_ADMIN, orderby = ORDER_SYSADMIN_UPLOAD, buildin = false//
, actions = { @CocOperation(name = "删除", typeCode = TYPE_BZ_DEL, mode = "d") //
		, @CocOperation(name = "查看", typeCode = TYPE_BZFORM_EDIT, mode = "v") //
}//
, groups = { @CocGroup(name = "基本信息", code = "basic"//
, fields = { @CocField(name = "上传名称", property = "localName")//
		, @CocField(name = "文件路径", property = "path") //
		, @CocField(name = "文件大小", property = "contentLength", pattern = "#,###") //
		, @CocField(name = "文件标题", property = "name") //
		, @CocField(name = "上传模块", property = "moduleID", fkTable = BIZSYS_ADMIN_MODULE) //
		, @CocField(name = "上传字段", property = "bzfield", fkTable = BIZSYS_BZUDF_FIELD) //
		, @CocField(name = "上传时间", property = "created", mode = "*:P") //
		, @CocField(name = "上传帐号", property = "createdBy", mode = "*:P") //
		, @CocField(name = "存储路径", property = "abstractPath") //
		, @CocField(name = "内容类型", property = "contentType") //
		, @CocField(name = "上传工具", property = "extName") //
}) }// end groups
)
public class UploadInfo extends BizEntity implements IUploadInfo {
	private String name;

	private Upload path;

	@Column(length = 10)
	private String extName;

	@ManyToOne
	private Module module;

	@ManyToOne
	private AbstractSystemData bzfield;

	private Long contentLength;

	@Column(length = 128)
	private String localName;

	@Column(length = 64)
	private String contentType;

	public String getAbstractPath() {
		return Demsy.contextDir + path;
	}

	public Upload getPath() {
		return path;
	}

	public String getExtName() {
		return extName;
	}

	public Long getContentLength() {
		return contentLength;
	}

	public String getLocalName() {
		return localName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setPath(Upload path) {
		this.path = path;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public String getUploadBy() {
		return this.getCreatedBy();
	}

	@Override
	public Date getUploadDate() {
		return this.getCreated();
	}

	public IModule getModule() {
		return module;
	}

	public void setModule(IModule module) {
		this.module = (Module) module;
	}

	public IBizField getBzfield() {
		return bzfield;
	}

	public void setBzfield(IBizField bzfield) {
		this.bzfield = (AbstractSystemData) bzfield;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
