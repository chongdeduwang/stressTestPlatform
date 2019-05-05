package io.renren.modules.test.service;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.modules.test.entity.TemplateEntity;

import java.util.List;
import java.util.Map;

public interface DataTemplateManageService {
    /**
     * 根据ID，查询性能测试用例
     */
    TemplateEntity queryObject(String templateType,int status);

    /**
     * 查询模板列表
     */
    List<TemplateEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存模板
     */
    void save(TemplateEntity templateEntity);

    /**
     * 更新模板信息
     */
    void update(TemplateEntity templateEntity);

    /**
     * 批量删除
     */
    void deleteBatch(Long[] templateIds);

}
