package io.renren.modules.test.dao;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.modules.test.entity.DataManEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface DataManageDao extends BaseDao<DataManEntity> {
    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> map);
}
