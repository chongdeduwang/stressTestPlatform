package io.renren.modules.test.service.impl;

import io.renren.modules.test.dao.DataManageDao;
import io.renren.modules.test.entity.DataManEntity;
import io.renren.modules.test.service.DataManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("DataManageService")
public class DataManageServiceImpl implements DataManageService {


    @Resource
    private DataManageDao dataManageDao;





    @Override
    public DataManEntity queryObject(Long dataId) {
        return dataManageDao.queryObject(dataId);
    }

    @Override
    public List<DataManEntity> queryList(Map<String, Object> map) {
        return dataManageDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return dataManageDao.queryTotal(map);
    }

    @Override
    public void save(DataManEntity dataManEntity) {
        dataManageDao.save(dataManEntity);
    }

    @Override
    public void update(DataManEntity dataManEntity) {
        dataManageDao.update(dataManEntity);
    }

    @Override
    public void deleteBatch(Long[] caseIds) {
        dataManageDao.deleteBatch(caseIds);
    }

    @Override
    public int updateBatch(Long[] caseIds, int status) {
        Map <String,Object> map = new HashMap<>();
        map.put("list",caseIds);
        map.put("status",status);

        return dataManageDao.update(map);
    }
}
