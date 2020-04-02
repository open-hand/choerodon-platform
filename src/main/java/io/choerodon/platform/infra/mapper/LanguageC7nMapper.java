package io.choerodon.platform.infra.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hzero.platform.domain.entity.Language;

import io.choerodon.mybatis.common.BaseMapper;

/**
 * @author scp
 * @date 2020/4/2
 * @description
 */
public interface LanguageC7nMapper extends BaseMapper<Language> {

    List<Language> fulltextSearch(@Param("language") Language language,
                                  @Param("param") String param);
}

