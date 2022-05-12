package io.choerodon.platform.api.controller.v1;

import io.swagger.annotations.ApiOperation;
import org.hzero.core.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.platform.app.service.ConfigC7nService;
import io.choerodon.swagger.annotation.Permission;

@RestController
@RequestMapping("/choerodon/v1")
public class ConfigC7nController {
    @Autowired
    private ConfigC7nService configC7nService;

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "更改系统默认语言")
    @PutMapping(value = "/default_language")
    public ResponseEntity<Void> updateDefaultLanguage(@RequestParam String language) {
        configC7nService.updateDefaultLanguage(language);
        return Results.success();
    }

}
