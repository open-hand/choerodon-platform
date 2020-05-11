package io.choerodon.platform.app.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.undertow.security.idm.Account;
import org.hzero.platform.domain.entity.Lov;
import org.hzero.platform.domain.entity.LovValue;
import org.hzero.platform.domain.repository.LovValueRepository;
import org.hzero.platform.infra.mapper.LovValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.choerodon.platform.app.service.LovC7nService;

/**
 * @author scp
 * @date 2020/5/10
 * @description
 */
@Service
public class LovC7nServiceImpl implements LovC7nService {

    @Autowired
    private LovValueMapper lovValueMapper;

    @Override
    public Map<String, String> getMeaningsByCode(String lovCode) {
        LovValue lovValue = new LovValue();
        lovValue.setLovCode(lovCode);
        List<LovValue> lovValueList = lovValueMapper.select(lovValue);
        return lovValueList.stream().collect(Collectors.toMap(LovValue::getValue, LovValue::getMeaning));
    }
}
