package io.dataease.dto.dataset;

import java.io.Serializable;

import lombok.Data;

/**
 * dataSet api 更新处理
 * 参考：update tableName set [fieldName] = [fieldVal] where [conditionName] = '返回数据获取'
 * 
 */
@Data
public class DataSetApiUpdateDTO implements Serializable {

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
	/**
	 * 参数条件
	 */
	private String conditionName;
	
}
