package io.renren.modules.test.service.impl;

import io.renren.modules.test.dao.TemplateManageDao;
import io.renren.modules.test.entity.TemplateEntity;
import io.renren.modules.test.service.TemplateManageService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class TemplateManageServiceImpl implements TemplateManageService {


    @Resource
    private TemplateManageDao templateManageDao;


    @Override
    public TemplateEntity queryObject(Long templateId) {
        return templateManageDao.queryObject(templateId);
    }

    @Override
    public List<TemplateEntity> queryList(Map<String, Object> map) {
        return templateManageDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return queryTotal(map);
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
