package io.choerodon.platform.infra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author scp
 * @date 2020/5/10
 * @description
 */
@Configuration
public class C7nSwaggerApiConfig {
    public static final String CHOERODON_LOV = "Choerodon lov";


    @Autowired
    public C7nSwaggerApiConfig(Docket docket) {
        docket.tags(
                new Tag(CHOERODON_LOV, "Choerodon LOV")
        );
    }
}
