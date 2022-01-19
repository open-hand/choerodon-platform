package io.choerodon.platform.app.task;

import java.util.Map;

import org.hzero.platform.app.service.AuditLoginService;
import org.hzero.platform.infra.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.choerodon.asgard.schedule.annotation.JobParam;
import io.choerodon.asgard.schedule.annotation.JobTask;

/**
 * Created by scp on 2022/1/19
 */
@Component
public class CleanTask {
    private final String CLEAN_STRATEGY = "cleanStrategy";

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private AuditLoginService auditLoginService;

    /**
     * 清除消息发送记录
     *
     * @param data
     * @return map
     */
    @JobTask(code = "cleanAuditLogRecordHzero", maxRetryCount = 0, description = "hzero提供的清除消息发送记录，默认保留三个月记录数据",
            params = {@JobParam(name = CLEAN_STRATEGY, description = "清理策略，默认三个月前数据", defaultValue = Constants.ClearLogType.THREE_MONTH)})
    public Map<String, Object> cleanAuditLogRecordHzero(Map<String, Object> data) {
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>begin clearing records hzero<<<<<<<<<<<<<<<<<<<<<<<<<<");
        try {
            auditLoginService.clearLog(String.valueOf(data.get(CLEAN_STRATEGY)), null);
        } catch (Exception e) {
            LOGGER.error("error.clean.records", e);
        }
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>end clearing records hzero<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return data;
    }


}
