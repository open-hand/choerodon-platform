package io.choerodon.platform.api.controller.v1;

import io.swagger.annotations.ApiOperation;
import org.hzero.core.util.Results;
import org.hzero.platform.app.service.AuditLoginService;
import org.hzero.platform.infra.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.choerodon.core.iam.InitRoleCode;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;

@RestController
@RequestMapping("/choerodon/v1/clean")
public class CleanController {

    @Autowired
    private AuditLoginService auditLoginService;

    /**
     * 清理登录日志
     */
    @Permission(level = ResourceLevel.SITE, roles = {InitRoleCode.SITE_DEVELOPER})
    @ApiOperation(value = "清理登录日志")
    @DeleteMapping(value = "/login_record")
    public ResponseEntity<Void> cleanAuditLogRecord(@RequestParam(required = false, defaultValue = Constants.ClearLogType.THREE_MONTH) String cleanStrategy) {
        auditLoginService.clearLog(cleanStrategy, null);
        return Results.success();
    }

}
