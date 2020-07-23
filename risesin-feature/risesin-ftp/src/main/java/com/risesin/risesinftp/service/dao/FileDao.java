package com.risesin.risesinftp.service.dao;

import com.risesin.core.base.BaseDao;
import com.risesin.risesinftp.service.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/24
 * @DESCRIPTION 文件dao
 * @since 1.0.0
 */
public interface FileDao extends JpaRepository<File, String>, JpaSpecificationExecutor<File>, BaseDao<File, String> {
}
