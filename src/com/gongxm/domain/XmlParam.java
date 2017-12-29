package com.gongxm.domain;

import java.util.List;

public class XmlParam {
	private boolean comment;
	private boolean decimal;
	private String dbType;
	private String conUrl;
	private String user;
	private String password;
	private String dirPath;
	private String pojoPackage;
	private String mapperPackage;
	private List<String> tableList;
	private List<TypeItem> typeList;

	public XmlParam() {
	}

	public XmlParam(int comment, int decimal, String dbType, String conUrl, String user, String password,
			String pojoPackage, String mapperPackage, String dirPath, List<String> tableList, List<TypeItem> typeList) {
		super();
		this.comment = comment == 0; // 0:ÊÇ 1:·ñ
		this.decimal = decimal == 1; // 0:Integer->false 1:Decimal->true
		this.dbType = dbType;
		this.conUrl = conUrl;
		this.user = user;
		this.password = password;
		this.pojoPackage = pojoPackage;
		this.mapperPackage = mapperPackage;
		this.dirPath=dirPath;
		this.tableList = tableList;
		this.typeList = typeList;
	}

	public String getDirPath() {
		return dirPath;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	public boolean getComment() {
		return comment;
	}

	public void setComment(boolean comment) {
		this.comment = comment;
	}

	public boolean getDecimal() {
		return decimal;
	}

	public void setDecimal(boolean decimal) {
		this.decimal = decimal;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getConUrl() {
		return conUrl;
	}

	public void setConUrl(String conUrl) {
		this.conUrl = conUrl;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPojoPackage() {
		return pojoPackage;
	}

	public void setPojoPackage(String pojoPackage) {
		this.pojoPackage = pojoPackage;
	}

	public String getMapperPackage() {
		return mapperPackage;
	}

	public void setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
	}

	public List<String> getTableList() {
		return tableList;
	}

	public void setTableList(List<String> tableList) {
		this.tableList = tableList;
	}

	public List<TypeItem> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<TypeItem> typeList) {
		this.typeList = typeList;
	}

}
