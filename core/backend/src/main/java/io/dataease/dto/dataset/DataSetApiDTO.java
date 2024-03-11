package io.dataease.dto.dataset;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * dataSet api 统一处理参数
 * 
 */
@Data
public class DataSetApiDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * token 验证，用于验证，是否可以调用接口
	 */
	private String token;
	/**
	 * api 参数
	 */
	private Map<String, Object> params;
	/**
	 * 更新参数：当原接口执行后需要更新时，需要填充参数
	 */
	private List<DataSetApiUpdateDTO> apiUpdateDto;
	
}
