package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_data_clean_tl.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2020-11-26-hpfm_data_clean_tl") {
        def weight = 1
        if (helper.isSqlServer()) {
            weight = 2
        } else if (helper.isOracle()) {
            weight = 3
        }
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'hpfm_data_clean_tl_s', startValue: "1")
        }
        createTable(tableName: "hpfm_data_clean_tl", remarks: "数据清理配置多语言") {
            column(name: "data_clean_id", type: "bigint", remarks: "数据清理配置ID") { constraints(nullable: "false") }
            column(name: "lang", type: "varchar(" + 30 * weight + ")", remarks: "语言") { constraints(nullable: "false") }
            column(name: "config_name", type: "varchar(" + 60 * weight + ")", remarks: "配置名称") { constraints(nullable: "false") }
            column(name: "tenant_id", type: "bigint", remarks: "租户ID") { constraints(nullable: "false") }
        }


        addUniqueConstraint(columnNames: "data_clean_id,lang", tableName: "hpfm_data_clean_tl", constraintName: "hpfm_data_clean_tl_u1")
    }
}