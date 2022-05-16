package io.choerodon.platform.app.service.impl;

import java.util.Collections;

import org.hzero.platform.domain.entity.Config;
import org.hzero.platform.domain.repository.ConfigRepository;
import org.hzero.platform.infra.mapper.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.choerodon.platform.app.service.ConfigC7nService;

/**
 * @author scp
 * @since 2022/5/12
 */
@Component
public class ConfigC7nServiceImpl implements ConfigC7nService {
    @Autowired
    private ConfigMapper configMapper;
    @Autowired
    private ConfigRepository configRepository;

    @Override
    public void updateConfig(String code, String value) {
        Config query = new Config();
        query.setTenantId(0L);
        query.setConfigCode(code);
        Config result = configMapper.selectOne(query);
        if (result == null) {
            result = new Config();
            result.setConfigCode(code);
            result.setTenantId(0L);
            result.setCategory("system");
        }
        result.setConfigValue(value);
        configRepository.insertOrUpdateConfig(Collections.singletonList(result), 0L);
    }
}
