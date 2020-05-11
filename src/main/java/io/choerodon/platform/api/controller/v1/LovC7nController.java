package io.choerodon.platform.api.controller.v1;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.choerodon.platform.app.service.LovC7nService;
import io.choerodon.platform.infra.config.C7nSwaggerApiConfig;
import io.choerodon.swagger.annotation.Permission;

/**
 * @author scp
 * @date 2020/5/10
 * @description
 */
@Api(tags = C7nSwaggerApiConfig.CHOERODON_LOV)
@RestController
@RequestMapping("/choerodon/v1/lov")
public class LovC7nController {

    @Autowired
    private LovC7nService lovC7nService;

    @ApiOperation(value = "根据lov code 查询meaning")
    @Permission(permissionLogin = true)
    @GetMapping(value = "/meanings")
    public ResponseEntity<Map> getMeaningsByCode(@RequestParam String lovCode) {
        return ResponseEntity.ok(lovC7nService.getMeaningsByCode(lovCode));
    }
}
