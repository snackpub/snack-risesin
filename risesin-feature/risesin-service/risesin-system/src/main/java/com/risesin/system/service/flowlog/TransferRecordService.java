package com.risesin.system.service.flowlog;

import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.flowlog.TransferRecordDao;
import com.risesin.systemapi.flowlog.entity.TransferRecord;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * transferRecord服务层
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class TransferRecordService {

    @Autowired
    private TransferRecordDao transferRecordDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<TransferRecord> findAll() {
        return transferRecordDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<TransferRecord> findSearch(Map whereMap, int page, int size) {
        Specification<TransferRecord> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return transferRecordDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap 查询参数
     * @return list
     */
    public List<TransferRecord> findSearch(Map whereMap) {
        Specification<TransferRecord> specification = createSpecification(whereMap);
        return transferRecordDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id 主键ID
     * @return TransferRecord
     */
    public TransferRecord findById(Integer id) {
        return SqlHelper.optional(transferRecordDao.findById(id));
    }

    /**
     * 增加
     *
     * @param transferRecord 对象
     */
    public boolean add(TransferRecord transferRecord) {
        transferRecordDao.save(transferRecord);
        return true;
    }

    /**
     * 修改
     *
     * @param transferRecord
     */
    public void update(TransferRecord transferRecord) {
        transferRecordDao.save(transferRecord);
    }

    /**
     * 批量删除
     */
    public boolean deleteById(List<Integer> ids) {
        return transferRecordDao.removeBatch(TransferRecord.class, ids, SqlConstant.DELETE);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<TransferRecord> createSpecification(Map searchMap) {

        return new Specification<TransferRecord>() {

            @Override
            public Predicate toPredicate(Root<TransferRecord> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 经手人(平台加前缀sys_，助贷端加前缀loan_)
                if (searchMap.get("handleBy") != null && !"".equals(searchMap.get("handleBy"))) {
                    predicateList.add(cb.like(root.get("handleBy").as(String.class), "%" + (String) searchMap.get("handleBy") + "%"));
                }
                // 经手人姓名
                if (searchMap.get("handleByName") != null && !"".equals(searchMap.get("handleByName"))) {
                    predicateList.add(cb.like(root.get("handleByName").as(String.class), "%" + (String) searchMap.get("handleByName") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    public List getActionPlanId(String sysUserId) {

        return transferRecordDao.getActionPlanId(sysUserId);
    }
}
