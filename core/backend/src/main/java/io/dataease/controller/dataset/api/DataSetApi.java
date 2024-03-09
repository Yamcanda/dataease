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

@Api(tags = "数据集：数据查询及更新接口")
@ApiSupport(order = 200)
@RequestMapping("/api/dataset")
public interface DataSetApi {

	@ApiOperation("数据获取及更新")
    @PostMapping("/{id}")
    ResultHolder fetchOrUpdate(@PathVariable("id") String id, @RequestBody Map<String, Object> params) throws Exception;
	
}
