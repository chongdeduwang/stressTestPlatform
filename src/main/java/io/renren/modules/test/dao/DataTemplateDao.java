package io.renren.modules.test.dao;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.modules.test.entity.TemplateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DataTemplateDao extends BaseDao<TemplateEntity> {
    TemplateEntity queryObject(@Param("templateType") String templateType, @Param("orderMode") int orderMode);
}
