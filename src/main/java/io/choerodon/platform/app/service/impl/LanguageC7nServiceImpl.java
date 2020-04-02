package io.choerodon.platform.app.service.impl;

import org.hzero.platform.domain.entity.Language;
import org.springframework.stereotype.Service;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.platform.app.service.LanguageC7nService;
import io.choerodon.platform.infra.mapper.LanguageC7nMapper;


/**
 * @author superlee
 */
@Service
public class LanguageC7nServiceImpl implements LanguageC7nService {

    private LanguageC7nMapper languageC7nMapper;

    public LanguageC7nServiceImpl(LanguageC7nMapper languageC7nMapper) {
        this.languageC7nMapper = languageC7nMapper;
    }

    @Override
    public Page<Language> pagingQuery(PageRequest pageRequest, Language languageDTO, String param) {
        return PageHelper.doPageAndSort(pageRequest, () -> languageC7nMapper.fulltextSearch(languageDTO, param));
    }

}
