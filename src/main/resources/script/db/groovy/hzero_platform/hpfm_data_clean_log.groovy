package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_data_clean_log.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2020-11-26-hpfm_data_clean_log") {
        def weight = 1
        if (helper.isSqlServer()) {
            weight = 2
        } else if (helper.isOracle()) {
            weight = 3
        }
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'hpfm_data_clean_log_s', startValue: "1")
        }
        createTable(tableName: "hpfm_data_clean_log", remarks: "数据清理日志") {
            column(name: "data_clean_log_id", type: "bigint", autoIncrement: true, remarks: "数据清理日志ID，主键") { constraints(primaryKey: true) }
            column(name: "data_clean_id", type: "bigint", remarks: "数据清理配置ID") { constraints(nullable: "false") }
            column(name: "clean_strategy", type: "varchar(" + 30 * weight + ")", remarks: "清理策略") { constraints(nullable: "false") }
            column(name: "execution_time", type: "datetime", remarks: "执行时间") { constraints(nullable: "false") }
            column(name: "execution_result", type: "varchar(" + 30 * weight + ")", remarks: "执行结果") { constraints(nullable: "false") }
            column(name: "execution_type", type: "varchar(" + 30 * weight + ")", remarks: "执行类型") { constraints(nullable: "false") }
            column(name: "return_message", type: "longtext", remarks: "返回消息")
            column(name: "tenant_id", type: "bigint", remarks: "租户ID") { constraints(nullable: "false") }
            column(name: "job_id", type: "bigint", remarks: "任务ID")
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}
            column(name: "created_by", type: "bigint", defaultValue: "-1", remarks: "") { constraints(nullable: "false") }
            column(name: "last_updated_by", type: "bigint", defaultValue: "-1", remarks: "") { constraints(nullable: "false") }
            column(name: "creation_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "") { constraints(nullable: "false") }
            column(name: "last_update_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "") { constraints(nullable: "false") }
        }

        createIndex(tableName: "hpfm_data_clean_log", indexName: "hpfm_data_clean_log_n1") {
            column(name: "data_clean_id")
        }

    }
}