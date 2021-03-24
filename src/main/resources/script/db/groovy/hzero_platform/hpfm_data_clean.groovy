package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_data_clean.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2020-11-26-hpfm_data_clean") {
        def weight = 1
        if (helper.isSqlServer()) {
            weight = 2
        } else if (helper.isOracle()) {
            weight = 3
        }
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'hpfm_data_clean_s', startValue: "1")
        }
        createTable(tableName: "hpfm_data_clean", remarks: "数据清理配置") {
            column(name: "data_clean_id", type: "bigint", autoIncrement: true, remarks: "数据清理配置ID，主键") { constraints(primaryKey: true) }
            column(name: "config_code", type: "varchar(" + 30 * weight + ")", remarks: "配置编码") { constraints(nullable: "false") }
            column(name: "config_name", type: "varchar(" + 60 * weight + ")", remarks: "配置名称") { constraints(nullable: "false") }
            column(name: "description", type: "varchar(" + 480 * weight + ")", remarks: "描述")
            column(name: "service", type: "varchar(" + 30 * weight + ")", remarks: "服务") { constraints(nullable: "false") }
            column(name: "request_method", type: "varchar(" + 30 * weight + ")", remarks: "请求方式") { constraints(nullable: "false") }
            column(name: "request_url", type: "varchar(" + 1200 * weight + ")", remarks: "请求路径") { constraints(nullable: "false") }
            column(name: "request_param", type: "longtext", remarks: "请求参数")
            column(name: "job_id", type: "bigint", remarks: "定时任务ID")
            column(name: "tenant_id", type: "bigint", defaultValue: "0", remarks: "租户ID") { constraints(nullable: "false") }
            column(name: "enabled_flag", type: "tinyint", defaultValue: "1", remarks: "启用标识") { constraints(nullable: "false") }
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}
            column(name: "created_by", type: "bigint", defaultValue: "-1", remarks: "") { constraints(nullable: "false") }
            column(name: "last_updated_by", type: "bigint", defaultValue: "-1", remarks: "") { constraints(nullable: "false") }
            column(name: "creation_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "") { constraints(nullable: "false") }
            column(name: "last_update_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "") { constraints(nullable: "false") }
        }


        addUniqueConstraint(columnNames: "config_code,tenant_id", tableName: "hpfm_data_clean", constraintName: "hpfm_data_clean_u1")
    }
}