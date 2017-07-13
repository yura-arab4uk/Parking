package com.parking.model.business.impl;

import java.sql.Timestamp;

public class SqlWizard {

	public String update(String tableName) {
		String sql = "update "+tableName+" set ";
		return sql;
	}

	public String insert(String tableName) {
		String sql = "insert into "+tableName+" values()";
		return sql;
	}

	public String remove(String tableName,Long id) {
		String sql = "update "+tableName+" set enabled=0 where id="+id;
		return sql;
	}

	public String selectFrom(String tableName) {
		String sql = "SELECT * FROM " + tableName +" WHERE true ";
		return sql;
	}

	private String addQuotes(Object value){
		if ((value instanceof String && !value.equals("NULL")&&!((String)value).startsWith("(")) | value instanceof Timestamp)
			value ="'"+ value+"'";
		else
			value = value+"";
		return (String)value;
	}

	public String insValues(String sql, Object... values) {

		String sqlValues="";
		for (int i = 0; i < values.length; i += 1) {
			if (values[i] != null && values[i] != "") {
				sqlValues += addQuotes(values[i])+",";
			}
		}
		sqlValues=sqlValues.substring(0,sqlValues.length()-1);
		sql = sql.replace("()","("+sqlValues+")");
		return sql;
	}

	public String addValues(String sql, Object... values) {

		for (int i = 1; i < values.length; i += 2) {
            if (values[i - 1] != null && values[i - 1] != "") {
				sql = sql + " and " + values[i - 1] + "=" + addQuotes(values[i]);
			}

		}
		return sql;
	}

	public String addIfNotNull(String sql,Object field,Object value) {
		if (value!=null) {
			sql+=","+field+"=";
			sql+=addQuotes(value);
		}
		return sql;
	}
	
	public String addWhereId(String sql,Long id) {
			sql+=" WHERE id="+id;
		return sql;
	}
	
}