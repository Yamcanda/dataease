package io.dataease.controller.dataset.api;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;

import io.dataease.controller.ResultHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "仪表板：数据集管理")
@ApiSupport(order = 200)
@RequestMapping("/api/dataset")
public interface DataSetApi {

	@ApiOperation("执行sql")
    @PostMapping("/{id}")
    public ResultHolder sqlExecute(@PathVariable("id") String id, @RequestBody Map<String, Object> params) throws Exception;
	
}
