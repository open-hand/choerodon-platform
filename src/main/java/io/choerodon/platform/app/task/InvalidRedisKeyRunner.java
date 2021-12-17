package io.choerodon.platform.app.task;

import io.choerodon.core.exception.CommonException;
import io.choerodon.platform.infra.mapper.HpfmGroupC7nMapper;
import org.hzero.core.redis.RedisHelper;
import org.hzero.platform.domain.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by wangxiang on 2020/8/3
 */
@Component
public class InvalidRedisKeyRunner implements CommandLineRunner {

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private HpfmGroupC7nMapper hpfmGroupC7nMapper;

    private static final String REDIS_ERROR = "error.invalid.redis.key";

    /**
     * 消息类别的缓存key
     */
    private static final String LOV_SUBCATEGORY_KEY = "hpfm:lov:values:HMSG.TEMP_SERVER.SUBCATEGORY";

    /**
     * 模板的key
     *
     * @param args
     * @throws Exception
     */
    private static final String TEMPLATE_KEY = "hmsg:message:template:*";

    /**
     * 重新加载groupId的key
     */
    private static final String GROUP_SEQUENCE_KEY = "hpfm:code-rule:sequence:T.0.HPFM.GROUP.GLOBAL.GLOBAL";

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            try {
                Set<String> keySet = new HashSet<>();
                keySet.add(LOV_SUBCATEGORY_KEY);
                keySet.add(GROUP_SEQUENCE_KEY);
                Set keys = redisHelper.keys(TEMPLATE_KEY);
                keySet.addAll(keys);
                redisHelper.delKeys(keySet);

                Group group = hpfmGroupC7nMapper.selectMaxIdGroup();
                if (!Objects.isNull(group)) {
                    int dbMaxGroupId = Integer.parseInt(StringUtils.split(group.getGroupNum(), "BG")[1]);
                    redisHelper.strSet(GROUP_SEQUENCE_KEY, String.valueOf(dbMaxGroupId));
                }
            } catch (Exception e) {
                throw new CommonException(REDIS_ERROR, e);
            }
        }).start();
    }
}
