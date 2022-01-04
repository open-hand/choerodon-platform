package script.db

databaseChangeLog(logicalFilePath: 'script/db/hiam_user_config.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hiam_user_config") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_user_config_s', startValue:"1")
        }
        createTable(tableName: "hiam_user_config", remarks: "用户默认配置") {
            column(name: "user_config_id", type: "bigint", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)}
            column(name: "user_id", type: "bigint",  remarks: "用户ID，iam_user.id")  {constraints(nullable:"false")}
            column(name: "tenant_id", type: "bigint",  remarks: "租户ID，hpfm_tenant.tenant_id")  {constraints(nullable:"false")}
            column(name: "default_company_id", type: "bigint",  remarks: "默认公司ID，hpfm_company.company_id")
            column(name: "default_role_id", type: "bigint",  remarks: "默认角色ID，iam_role.id")
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}

        }

        addUniqueConstraint(columnNames:"user_id,tenant_id",tableName:"hiam_user_config",constraintName: "hiam_user_config_u1")
    }
    changeSet(author: "xiaoyu.zhao@hand-china.com", id: "2019-07-31-hiam_user_config") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        addColumn(tableName: 'hiam_user_config') {
            column(name: "menu_layout", type: "varchar(" + 30 * weight + ")", remarks: "菜单布局，包含左右布局和水平布局两种")
        }
        addColumn(tableName: 'hiam_user_config') {
            column(name: "menu_layout_theme", type: "varchar(" + 30 * weight + ")", remarks: "菜单布局主题")
        }
        addColumn(tableName: 'hiam_user_config') {
            column(name: "role_merge_flag", type: "tinyint", remarks: "角色合并标识")
        }

    }
	changeSet(author: "wanshun.zhang@hand-china.com", id: "2019-10-10-hiam_user_config") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        addColumn(tableName: 'hiam_user_config') {
            column(name: "popout_reminder_flag", type: "tinyint", remarks: "弹框提醒标识")
        }

    }

    changeSet(author: "xiaoyu.zhao@hand-china.com", id: "2021-02-22-hiam_user_config") {
        addColumn(tableName: 'hiam_user_config') {
            column(name: "multi_tab_flag", type: "tinyint", remarks: "多tab窗口标识")
        }
    }

    changeSet(author: "hzero@hand-china.com", id: "2021-06-22-hiam_user_config") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        addColumn(tableName: 'hiam_user_config') {
            column(name: "default_language", type: "varchar(" + 16 * weight + ")", remarks: "默认语言")
        }
    }
    changeSet(author: "hzero@hand-china.com", id: "2021-06-23-hiam_user_config") {
        addColumn(tableName: 'hiam_user_config') {
            column(name: "switch_tr_flag", type: "tinyint", defaultValue: "0", remarks: "默认语言") {constraints(nullable:"false")}
        }
    }
    changeSet(author: "hzero@hand-china.com", id: "2021-07-12-hiam_user_config") {
        addDefaultValue (tableName: 'hiam_user_config', columnName: 'role_merge_flag', columnDataType: 'tinyint', defaultValue: '0')
    }
    changeSet(author: "hzero@hand-china.com", id: "2021-10-11-hiam_user_config") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        addColumn (tableName: "hiam_user_config") {
            column (name: "ext_theme_config", type: "varchar(" + 480 * weight + ")", remarks: "额外主题配置")
        }
    }
}
