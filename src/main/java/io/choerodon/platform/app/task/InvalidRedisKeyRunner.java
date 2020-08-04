package io.choerodon.platform.app.task;

import org.hzero.core.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by wangxiang on 2020/8/3
 */
@Component
public class InvalidRedisKeyRunner implements CommandLineRunner {
    @Autowired
    private RedisHelper redisHelper;

    /**
     * 消息类别的缓存key
     */
    private static final String LOV_KEY = "hpfm:lov:values:HMSG.TEMP_SERVER.SUBCATEGORY";


    @Override
    public void run(String... args) throws Exception {
        redisHelper.delKey(LOV_KEY);
    }
}
