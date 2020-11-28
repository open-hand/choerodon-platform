package io.choerodon.platform.infra.mapper;

import org.hzero.platform.domain.entity.Group;

/**
 * Created by wangxiang on 2020/11/28
 */
public interface HpfmGroupC7nMapper {
    Group selectMaxIdGroup();
}
