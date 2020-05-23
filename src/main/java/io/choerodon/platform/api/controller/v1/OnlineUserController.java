package io.choerodon.platform.api.controller.v1;

import io.choerodon.core.iam.InitRoleCode;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.platform.app.service.OnlineUserC7nService;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/choerodon/v1/online")
public class OnlineUserController {

    @Autowired
    private OnlineUserC7nService onlineUserC7nService;

    /**
     * 当前在线人数和访问人数
     */
    @Permission(level = ResourceLevel.SITE, roles = {InitRoleCode.SITE_DEVELOPER})
    @ApiOperation(value = "当前在线人数和访问人数")
    @GetMapping(value = "/current", produces = "application/json")
    public ResponseEntity<Map<String, Integer>> getCurrentCount() {
        return new ResponseEntity<>(onlineUserC7nService.getCurrentCount(), HttpStatus.OK);
    }

    /**
     * 每2小时在线人数统计
     */
    @Permission(level = ResourceLevel.SITE, roles = {InitRoleCode.SITE_DEVELOPER})
    @ApiOperation(value = "每2小时在线人数统计")
    @GetMapping(value = "/current/list", produces = "application/json")
    public ResponseEntity<Map<String, Integer>> getCurrentCountPerHour() {
        return new ResponseEntity<>(onlineUserC7nService.getCurrentCountPerTwoHour(), HttpStatus.OK);
    }

}
