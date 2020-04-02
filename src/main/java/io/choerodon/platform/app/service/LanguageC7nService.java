package io.choerodon.platform.app.service;


import org.hzero.platform.domain.entity.Language;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * @author superlee
 */
public interface LanguageC7nService {

    Page<Language> pagingQuery(PageRequest pageRequest, Language languageDTO, String param);

}
