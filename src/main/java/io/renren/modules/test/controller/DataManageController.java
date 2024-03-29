package io.renren.modules.test.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
//import io.renren.config.DynamicRoutingDataSource;
import io.renren.datasources.DynamicDataSource;
import io.renren.modules.test.bean.PickAutoTemplate;
import io.renren.modules.test.bean.PickCustomTemplate;
import io.renren.modules.test.bean.RepAutoTemplate;
import io.renren.modules.test.bean.RepCustomTemplate;
import io.renren.modules.test.entity.DataEntity;
import io.renren.modules.test.entity.DataManEntity;
import io.renren.modules.test.entity.DataSourceEntity;
import io.renren.modules.test.entity.OrderManEntity;
import io.renren.modules.test.service.DataManageService;
import io.renren.modules.test.utils.JDBCUtils;
import io.swagger.models.auth.In;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.beans.Introspector;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/order/manage")
public class DataManageController {

    @Resource
    DataManageService dataManageService;

    @Autowired
    private DynamicDataSource dynamicDataSource;

    private Map<Long, PickAutoTemplate> pickAutoTemplate = new ConcurrentHashMap<>();
    private Map<Long, RepAutoTemplate> repAutoTemplate = new ConcurrentHashMap<>();
    private Map<Long, PickCustomTemplate> pickCustomTemplate = new ConcurrentHashMap<>();
    private Map<Long, RepCustomTemplate> repCustomTemplate = new ConcurrentHashMap<>();

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

        return R.ok().put("dataInfo", dataInfo);
    }


//    @RequestMapping("")


    /**
     * 保存数据实例
     */
    @SysLog("保存数据实例")
    @RequestMapping("/save")
    @RequiresPermissions("order:manage:save")
    public R createNewDataInstance(@RequestBody DataManEntity dataManEntity) {
        String content = dataManEntity.getContent();
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

    /**
     * 修改数据实例
     */
    @SysLog("修改数据实例")
    @RequestMapping("/update")
    @RequiresPermissions("order:manage:update")
    public R update(@RequestBody DataManEntity dataManEntity) {
        ValidatorUtils.validateEntity(dataManEntity);


        dataManageService.update(dataManEntity);

        return R.ok();
    }


    //    @SysLog("生成单据")
    @RequestMapping("/start")
    @RequiresPermissions("order:manage:start")
    public R start(@RequestBody OrderManEntity orderManEntity) throws SQLException {
        DataManEntity data = orderManEntity.getDataManEntity();

        DataSourceEntity db = orderManEntity.getDataSourceEntity();
        //getDataBaseInfo
        JDBCUtils jdbcUtils = new JDBCUtils(db.getUrl(), db.getUser(), db.getPassword());
        Connection cn = jdbcUtils.getConnection();

        JSONObject jsonObject = JSONObject.parseObject(data.getContent());
        PickAutoTemplate pickATemplate = JSON.toJavaObject(jsonObject, PickAutoTemplate.class);

        new Thread(() -> pickATemplate.generateOrder(data.getId(), orderManEntity.getInterfaceUrl(), cn)).start();


        pickAutoTemplate.put(data.getId(), pickATemplate);

        data.setStatus(1);
        dataManageService.update(data);

        return R.ok();
    }

    @RequestMapping("/stop")
    @RequiresPermissions("order:manage:stop")
    public R stop(@RequestBody DataManEntity data) throws SQLException {


        long id = data.getId();
        pickAutoTemplate.get(id).stopGenerate(id);
        data.setStatus(0);
        dataManageService.update(data);
        pickAutoTemplate.remove(id);
        return R.ok();
    }
    /**
     * 删除数据实例
     */
    @SysLog("删除数据实例")
    @RequestMapping("/delete")
    @RequiresPermissions("order:manage:delete")
    public R delete(@RequestBody Long[] dataIds) {
        dataManageService.deleteBatch(dataIds);
        return R.ok();
    }



}
