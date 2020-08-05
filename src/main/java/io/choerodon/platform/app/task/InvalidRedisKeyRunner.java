package io.choerodon.platform.app.task;

import java.util.HashSet;
import java.util.Set;
import org.hzero.core.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by wangxiang on 2020/8/3
 */
@Component
public class InvalidRedisKeyRunner implements CommandLineRunner {
    @Autowired
    private RedisTemplate redisTemplate;

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

    @Override
    public void run(String... args) throws Exception {
        Set<String> keySet = new HashSet<>();
        keySet.add(LOV_SUBCATEGORY_KEY);
        Set keys = redisTemplate.keys(TEMPLATE_KEY);
        keySet.addAll(keys);
        redisTemplate.delete(keySet);
    }
}
