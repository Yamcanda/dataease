package io.dataease.plugins.common.base.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Schema(description = "sql执行参数")
@Data
public class DataSetSqlExecuteQuery implements Serializable {
    @Schema(description = "数据集信息表id")
    private String id;

    @Schema(description = "参数")
    private Map<String, String> params;
}
