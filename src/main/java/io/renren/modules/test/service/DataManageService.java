package io.renren.modules.test.service;

import io.renren.modules.test.entity.DataManEntity;

import java.util.List;
import java.util.Map;

public interface DataManageService {


    /**
     * 根据ID，查询性能测试用例
     */
    DataManEntity queryObject(Long dataId);

    /**
     * 查询性能测试用例列表
     */
    List<DataManEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存性能测试用例
     */
    void save(DataManEntity dataManEntity);

    /**
     * 更新性能测试用例信息
     */
    void update(DataManEntity dataManEntity);

    /**
     * 批量删除
     */
    void deleteBatch(Long[] dataIds);

    /**
     * 批量更新性能测试用例信息
     */
    int updateBatch(Long[] dataIds, int status);
}
