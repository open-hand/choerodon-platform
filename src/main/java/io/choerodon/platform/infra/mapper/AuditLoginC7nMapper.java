package io.choerodon.platform.infra.mapper;

import org.apache.ibatis.annotations.Param;
import org.hzero.platform.domain.entity.AuditLogin;

import java.util.Date;
import java.util.List;

public interface AuditLoginC7nMapper {
    Integer getNumberOfVisitors(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<AuditLogin> getAuditLoginBetweenTime(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
