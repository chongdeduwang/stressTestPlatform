package io.renren.modules.test.dao;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.modules.test.entity.TemplateEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TemplateManageDao extends BaseDao<TemplateEntity> {

}
