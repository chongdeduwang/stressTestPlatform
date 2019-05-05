package io.renren.modules.test.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.test.entity.TemplateEntity;
import io.renren.modules.test.service.DataTemplateManageService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/template/manage")
public class DataTemplManageController {

    @Resource
    private DataTemplateManageService templManageService;


    @RequestMapping("/{templateType}/{orderMode}")
    public R list(@PathVariable("templateType") String templateType,@PathVariable("orderMode") int orderMode){
        TemplateEntity templateEntity = templManageService.queryObject(templateType,orderMode);


        return R.ok().put("template",templateEntity);
    }


}
