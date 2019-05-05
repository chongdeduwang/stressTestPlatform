package io.renren.modules.test.service.impl;

import io.renren.modules.test.dao.DataTemplateDao;
import io.renren.modules.test.entity.TemplateEntity;
import io.renren.modules.test.service.DataTemplateManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("TemplManageService")
public class TemplateManageServiceImpl implements DataTemplateManageService {


    @Resource
    private DataTemplateDao templateManageDao;


    @Override
    public TemplateEntity queryObject(String templateType,int status) {
        return templateManageDao.queryObject(templateType,status);
    }

    @Override
    public List<TemplateEntity> queryList(Map<String, Object> map) {
        return templateManageDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return templateManageDao.queryTotal(map);
    }

    @Override
    public void save(TemplateEntity templateEntity) {
        templateManageDao.save(templateEntity);
    }

    @Override
    public void update(TemplateEntity templateEntity) {
        templateManageDao.update(templateEntity);
    }

    @Override
    public void deleteBatch(Long[] templateIds) {
        templateManageDao.deleteBatch(templateIds);
    }

}
