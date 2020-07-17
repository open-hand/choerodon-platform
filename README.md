## 使用说明
平台功能基础服务，主要涵盖平台开发支持功能、平台主数据模块、系统管理模块等

## 特征
平台功能基础服务，主要涵盖平台开发支持功能、平台主数据模块、系统管理模块等

## 服务配置 

- `服务配置参数`
```
hzero:
  platform:
    init-cache: ${HZERO_PLATFORM_INIT_CACHE:true} # 是否执行Redis初始化
    http-protocol: ${HZERO_PLATFORM_HTTP_PROTOCOL:http} # 请求协议，可选值：http，https，用于值集、弹性域、个性化等功能
    role-template-codes: # 如果角色继承自列表中的角色，那么该角色可以看到分配到列表中角色的卡片
      - role/organization/default/administrator
    regist-entity: 
      enable: true # 开启实体类的注册
  data:
    permission:
      db-owner: ${HZERO_DB_OWNER:} # 数据库所有者模式，例如在MSSQL下数据库前缀拼接规则为：db-prefix.db-owner.table-name
```

### 数据初始化

在`src/main/resources/script/db`下已有数据库初始化excel文件与groovy脚本示例，
此模板在部署时有一个初始化数据的阶段，所以部署此模板创建好的应用之前，应该去数据库中
创建一个数据库。并且部署时需要将对应的数据库名称填写正确


部署时需要填写数据库，如下所示
```yml
hzero:
   platform:
     init-cache: ${HZERO_PLATFORM_INIT_CACHE:true} # 是否执行Redis初始化
     http-protocol: ${HZERO_PLATFORM_HTTP_PROTOCOL:http} # 请求协议，可选值：http，https，用于值集、弹性域、个性化等功能
     role-template-codes: # 如果角色继承自列表中的角色，那么该角色可以看到分配到列表中角色的卡片
       - role/organization/default/administrator
     regist-entity: 
       enable: true # 开启实体类的注册
   data:
     permission:
       db-owner: ${HZERO_DB_OWNER:} # 数据库所有者模式，例如在MSSQL下数据库前缀拼接规则为：db-prefix.db-owner.table-name
```
