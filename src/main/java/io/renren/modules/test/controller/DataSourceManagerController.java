package io.renren.modules.test.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.R;
import io.renren.modules.test.entity.DataSourceEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dbsource/manage")
public class DataSourceManagerController {
    @SysLog("添加数据源")
    @RequestMapping("/add")
    @RequiresPermissions("dbsource:manage:add")
    public R addDatasource(@RequestBody DataSourceEntity dbsource){
        return R.ok();

    }




}
