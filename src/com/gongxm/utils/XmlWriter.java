package com.gongxm.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.gongxm.domain.DbType;
import com.gongxm.domain.TypeItem;
import com.gongxm.domain.XmlParam;

public class XmlWriter {
	
	public static InputStream createXml(XmlParam param) throws IOException {
	
		String dirPath = param.getDirPath();
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<!DOCTYPE generatorConfiguration\r\n" + 
				"  PUBLIC \"-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN\"\r\n" + 
				"  \"http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd\">");
		sb.append("<generatorConfiguration>\r\n" + 
				"	<context id=\"mybatis\" targetRuntime=\"MyBatis3\">\r\n" + 
				"		<commentGenerator>");
		sb.append("<property name=\"suppressAllComments\" value=\""+param.getComment()+"\" />");
		sb.append("</commentGenerator>");
		String dbType = param.getDbType();
		if(DbType.MySQL.toString().equals(dbType)) {
			sb.append("<jdbcConnection driverClass=\"com.mysql.jdbc.Driver\"\r\n" + 
					"			connectionURL=\""+param.getConUrl()+"\" userId=\""+param.getUser()+"\"\r\n" + 
					"			password=\""+param.getPassword()+"\">\r\n" + 
					"		</jdbcConnection>");
		}else if(DbType.Oracle.toString().equals(dbType)) {
			sb.append("<jdbcConnection driverClass=\"oracle.jdbc.OracleDriver\"\r\n" + 
					"			connectionURL=\""+param.getConUrl()+"\" \r\n" + 
					"			userId=\""+param.getUser()+"\"\r\n" + 
					"			password=\""+param.getPassword()+"\">\r\n" + 
					"		</jdbcConnection>");
		}
		sb.append("<javaTypeResolver>\r\n" + 
				"			<property name=\"forceBigDecimals\" value=\""+param.getDecimal()+"\" />\r\n" + 
				"		</javaTypeResolver>");
		sb.append("<javaModelGenerator targetPackage=\""+param.getPojoPackage()+"\"\r\n" + 
				"			targetProject=\""+dirPath+"\">");
		sb.append("<property name=\"enableSubPackages\" value=\"false\" />");
		sb.append("<property name=\"trimStrings\" value=\"true\" />\r\n" + 
				"		</javaModelGenerator>");
		sb.append("<sqlMapGenerator targetPackage=\""+param.getMapperPackage()+"\" \r\n" + 
				"			targetProject=\""+dirPath+"\">\r\n" + 
				"			<property name=\"enableSubPackages\" value=\"false\" />\r\n" + 
				"		</sqlMapGenerator>");
		sb.append("<javaClientGenerator type=\"XMLMAPPER\"\r\n" + 
				"			targetPackage=\""+param.getMapperPackage()+"\" \r\n" + 
				"			targetProject=\""+dirPath+"\">\r\n" + 
				"			<property name=\"enableSubPackages\" value=\"false\" />\r\n" + 
				"		</javaClientGenerator>");
		
		List<String> tableList = param.getTableList();
		if(tableList!=null&&tableList.size()>0) {
			for (String table : tableList) {
				sb.append("<table schema=\"\" tableName=\""+table+"\"></table>");
			}
		}
		
		List<TypeItem> typeList = param.getTypeList();
		if(typeList!=null&&typeList.size()>0) {
			for (TypeItem item : typeList) {
				sb.append("<table schema=\"\" tableName=\""+item.getTable()+"\">\r\n" + 
						"			<columnOverride column=\""+item.getField()+"\" javaType=\""+item.getJavaType()+"\" />\r\n" + 
						"		</table>");
			}
		}
		sb.append("</context>\r\n" + 
				"</generatorConfiguration>");
		return new ByteArrayInputStream(sb.toString().getBytes());
	}

}
