# choerodon-platform
平台管理

## Introduction
平台管理，作为平台的基础管理服务，主要为平台提供一些基础能力，如系统配置、开发配置等。
此服务是对[hzero-platform](https://github.com/open-hand/hzero-platform.git)的二开，定制化开发了统计在线人数功能。

## Features
- 系统配置：用于配置系统环境的标题、LOGO和布局等。
- 配置维护：用于配置参数在不同角色或用户下的具体展现，例如币种，在A角色下为CNY，在B角色下可能需要展示为USD
- 编码规则：用于维护编码的生成策略，在应用时会根据规则生成相应的字符串内容。
- 数据权限规则：用于控制用户的可访问数据，在系统使用过程中可以通过调整配置来实现实时、动态、灵活的调整用户、角色或者其他维度的可访问数据，同时支持配置数据库前缀来实现不同数据库之间的跨库访问。
- 卡片管理：用于维护平台的工作台卡片信息，可通过配置卡片并辅以前端开发来自定义实现工作台卡片的样式和内容。
- 数据层级配置：结合数据权限规则功能，用于为用户的数据权限提供更加丰富多层级的控制。默认数据权限规则可以控制租户、角色、用户等层级，可通过该功能进行扩展。
- 值集配置：用于维护值集信息。
- 值集视图配置：用于配置值集返回数据的展示形式，支持在线预览。
- 数据源设置：用于维护服务所需的数据源信息。
- 平台多语言：用于维护平台的多语言信息，目前支持中文、英文和日文三种语言。
- 在线人数统计：用于展示平台当前在线人数

## Documentation
- 更多详情请参考`hzero-platform`[中文文档](http://open.hand-china.com/document-center/doc/application/10035/10153?doc_id=5148)

## Data initialization

- 创建数据库，本地创建 `hzero_platform` 数据库和默认用户，示例如下：

  ```sql
  CREATE USER 'choerodon'@'%' IDENTIFIED BY "123456";
  CREATE DATABASE hzero_platform DEFAULT CHARACTER SET utf8;
  GRANT ALL PRIVILEGES ON hzero_platform.* TO choerodon@'%';
  FLUSH PRIVILEGES;
  ```

- 初始化 `hzero_platform` 数据库，运行项目根目录下的 `init-database.sh`，该脚本默认初始化数据库的地址为 `localhost`，若有变更需要修改脚本文件

  ```sh
  sh init-database.sh
  ```

 
## Changelog

* [更新日志](./CHANGELOG.zh-CN.md)

## Dependencies
* 依赖服务
```xml
<dependency>
    <groupId>org.hzero</groupId>
    <artifactId>hzero-platform-saas</artifactId>
    <version>${hzero.service.version}</version>
</dependency>
```

## Contributing

欢迎参与项目贡献！比如提交PR修复一个bug，或者新建Issue讨论新特性或者变更。

Copyright (c) 2020-present, CHOERODON
