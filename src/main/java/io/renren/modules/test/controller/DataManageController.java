package io.renren.modules.test.controller;


import com.mongodb.util.JSON;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.test.entity.DataEntity;
import io.renren.modules.test.entity.DataManEntity;
import io.renren.modules.test.service.DataManageService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONString;
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
     * 数据信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:manage:info")
    public R info(@PathVariable("id") Long id) {
        DataManEntity dataInfo = dataManageService.queryObject(id);

        return R.ok().put("dataInfo",dataInfo );
    }



//    @RequestMapping("")


    /**
     * 保存数据实例
     */
    @SysLog("保存数据实例")
    @RequestMapping("/save")
    @RequiresPermissions("order:manage:save")
    public R createNewDataInstance(@RequestBody DataManEntity dataManEntity) {
        String content =dataManEntity.getContent();
        System.out.println(content);
        dataManEntity.setContent(content);

        ValidatorUtils.validateEntity(dataManEntity);
        dataManageService.save(dataManEntity);
//        ValidatorUtils.validateEntity(stressTestCase);
//        // 生成用例时即生成用例的文件夹名，上传附件时才会将此名称落地成为文件夹。
//        if (StringUtils.isEmpty(stressTestCase.getCaseDir())) {
//            Date caseAddTime = new Date();
//            String caseAddTimeStr = DateUtils.format(caseAddTime, DateUtils.DATE_TIME_PATTERN_4DIR);
//            //random使用时间种子的随机数,避免了轻度并发造成文件夹重名.
//            String caseFilePath = caseAddTimeStr + new Random(System.nanoTime()).nextInt(1000);
//            stressTestCase.setCaseDir(caseFilePath);
//        }
//
//        stressTestService.save(stressTestCase);

        return R.ok();
    }
    @SysLog("生成单据")
    @RequestMapping("/start")
    @RequiresPermissions("order:manage:start")
    public R start(@RequestBody DataManEntity dataManEntity){




        return R.ok();
    }




}
