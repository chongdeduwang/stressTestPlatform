package io.renren.modules.test.controller;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.test.entity.DataEntity;
import io.renren.modules.test.entity.DataManEntity;
import io.renren.modules.test.service.DataManageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order/manage")
public class DataManageController {

    @Resource
    DataManageService dataManageService;


    /**
     * 性能测试用例列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:manage:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<DataManEntity> autoDataList = dataManageService.queryList(query);
        int total = dataManageService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(autoDataList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 性能测试用例信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:manage:info")
    public R info(@PathVariable("id") Long id) {
        DataManEntity dataInfo = dataManageService.queryObject(id);

        return R.ok().put("dataInfo",dataInfo );
    }


    /**
     * 创建数据实例
     *
     */
    @RequestMapping("/createDataInstance")
    @RequiresPermissions("order:manage")
    public R createNewDataInstance(@RequestBody  DataEntity dataEntity){



        return R.ok().put("page", null);
    }
//    @RequestMapping("")


}
