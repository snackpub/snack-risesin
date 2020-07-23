package com.risesin.system.dao.dict;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.dict.entity.SysBankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * sysBankCard数据访问接口
 *
 * @author Administrator
 */
@Repository
public interface SysBankCardDao extends JpaRepository<SysBankCard, Integer>, JpaSpecificationExecutor<SysBankCard>, BaseDao<SysBankCard, Integer> {


    /**
     * 根据银行卡号获取信息
     *
     * @param card 银行卡号
     * @return SysBankCard
     */
    @Query("from SysBankCard where card=:card")
    SysBankCard getBankCard(@Param("card") String card);

}
