package io.dataease.controller.dataset.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.dataease.controller.ResultHolder;
import io.dataease.controller.dataset.api.DataSetApi;
import io.dataease.controller.request.dataset.DataSetExportRequest;
import io.dataease.plugins.common.base.domain.DatasetTable;
import io.dataease.plugins.common.dto.dataset.SqlVariableDetails;
import io.dataease.service.dataset.DataSetTableService;

@RestController
public class DataSetServer implements DataSetApi {

	@Resource
    private DataSetTableService dataSetTableService;
	
	@Override
	public ResultHolder fetchOrUpdate(String id, Map<String, Object> params) throws Exception {
		DatasetTable datasetTable = dataSetTableService.get(id);
        if (datasetTable == null){
            return ResultHolder.error("找不到对应的 sql 数据集！");
        }

        // 对参数进行转换：
        // 如果参数为空、字符串、数字 不做处理
        // 如果参数为数组，根据
        Map<String, String> paramMap = new HashMap<>();

        if(!ObjectUtils.isEmpty(params)) {
            params.forEach((k, v) -> {
                if (v == null) {
                    return;
                }

                if (v instanceof List) {
                    String requestStr = "";
                    List list = (List) v;
                    Object first = list.get(0);
                    boolean isString = first instanceof String;
                    for (int i = 0; i < list.size(); i++) {
                        Object o = list.get(i);
                        // 去掉数组中的 null 值，使用IN操作符时，null实际上代表未知值，不是一个有效的值
                        if (o == null) {
                            continue;
                        }

                        if (i != 0) {
                            requestStr += ",";
                        }

                        if (isString) {
                            requestStr += "'" + o + "'";
                        } else {
                            requestStr += "" + o;
                        }
                    }

                    paramMap.put(k, requestStr);
                } else {
                    paramMap.put(k, String.valueOf(v));
                }
            });
        }

        DataSetExportRequest dataSetExportRequest = new DataSetExportRequest();
        dataSetExportRequest.setId(id);
        dataSetExportRequest.setInfo(datasetTable.getInfo());
        List<SqlVariableDetails> sqlVariables = new Gson().fromJson(datasetTable.getSqlVariableDetails(), new TypeToken<List<SqlVariableDetails>>() {}.getType());

        // 遍历所有参数 替换为传入值
        if(!ObjectUtils.isEmpty(sqlVariables)) {
            for (SqlVariableDetails variable : sqlVariables) {
                // 参数名
                String variableName = variable.getVariableName();
                String paramValue = paramMap.get(variableName);
                variable.setDefaultValue(paramValue);
            }

            dataSetExportRequest.setSqlVariableDetails(new Gson().toJson(sqlVariables));
        }

        dataSetExportRequest.setDataSourceId(datasetTable.getDataSourceId());
        dataSetExportRequest.setMode(0);
        dataSetExportRequest.setType("sql");

        return dataSetTableService.sqlExecute(dataSetExportRequest, true);
	}

}
