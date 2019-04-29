package io.renren.modules.test.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.test.entity.TemplateEntity;
import io.renren.modules.test.service.TemplateManageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/template/manage")
public class TemplateManageController {

    @Resource
    private TemplateManageService templateManageService;


    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){

        //查询列表数据
        Query query = new Query(params);
        List<TemplateEntity> autoDataList = templateManageService.queryList(query);
        int total = templateManageService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(autoDataList, total, query.getLimit(), query.getPage());


        return R.ok().put("template",pageUtil);
    }


}
