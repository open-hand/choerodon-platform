package io.choerodon.platform.app.service;

import java.util.Map;

public interface OnlineUserC7nService {
    Map<String, Integer> getCurrentCount();

    Map<String, Integer> getCurrentCountPerTwoHour();
}
