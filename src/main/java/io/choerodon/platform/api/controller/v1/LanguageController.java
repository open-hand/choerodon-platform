package io.choerodon.platform.api.controller.v1;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import org.hzero.core.util.Results;
import org.hzero.platform.app.service.HpfmLanguageService;
import org.hzero.platform.domain.entity.Language;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import io.choerodon.core.base.BaseController;
import io.choerodon.core.domain.Page;
import io.choerodon.core.exception.NotFoundException;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.platform.app.service.LanguageC7nService;
import io.choerodon.swagger.annotation.CustomPageRequest;
import io.choerodon.swagger.annotation.Permission;

/**
 * @author superlee
 */
@RestController
@RequestMapping("/v1/languages")
public class LanguageController extends BaseController {

    private HpfmLanguageService languageService;
    private LanguageC7nService LanguageC7nService;

    public LanguageController(HpfmLanguageService languageService,
                              LanguageC7nService LanguageC7nService) {
        this.languageService = languageService;
        this.LanguageC7nService = LanguageC7nService;
    }

    /**
     * 修改 Language
     *
     * @return 返回信息
     */
    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "修改Language")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Language> update(@PathVariable Long id,
                                           @RequestBody @Valid Language language) {
        language.setId(id);
        this.validObject(language);
        return Results.success(languageService.updateLanguage(language));
    }

    /**
     * 分页查询 Language
     *
     * @param language 请求参数封装对象
     * @return 返回信息
     */
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @ApiOperation(value = "分页查询Language")
    @GetMapping
    @CustomPageRequest
    public ResponseEntity<Page<Language>> pagingQuery(@ApiIgnore
                                                      @SortDefault(value = "id", direction = Sort.Direction.DESC) PageRequest pageRequest,
                                                      Language language,
                                                      @RequestParam(required = false) String param) {
        return ResponseEntity.ok(LanguageC7nService.pagingQuery(pageRequest, language, param));
    }

    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @ApiOperation(value = "查询language列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Language>> listAll() {
        return ResponseEntity.ok(languageService.listLanguage());
    }


    /**
     * 根据language code单个查询
     *
     * @param code Language
     * @return 返回信息
     */
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @ApiOperation(value = "通过code查询Language")
    @GetMapping(value = "/code")
    public ResponseEntity<Language> queryByCode(@RequestParam(name = "value") String code) {
        return Optional.ofNullable(languageService.queryLanguage(code)).map(Results::success).orElseThrow(NotFoundException::new);
    }

}
