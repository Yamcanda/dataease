package io.dataease.dto.dataset;

import java.io.Serializable;

import lombok.Data;

/**
 * dataSet api 更新字段处理
 * 参考：update tableName set [fieldName] = [fieldVal]
 * 
 */
@Data
public class FieldNamesUpdateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 参数字段名称
	 */
	private String fieldName;
	/**
	 * 参数字段值
	 */
	private String fieldVal;
	
}
