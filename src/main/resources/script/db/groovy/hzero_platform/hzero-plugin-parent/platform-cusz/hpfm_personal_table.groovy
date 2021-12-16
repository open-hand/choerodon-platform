package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_personal_table.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2021-04-16-hpfm_personal_table") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_personal_table_s', startValue:"1")
        }
        createTable(tableName: "hpfm_personal_table", remarks: "个性化表数据") {
            column(name: "id", type: "bigint", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)}
            column(name: "user_id", type: "bigint",  remarks: "用户id")  {constraints(nullable:"false")}
            column(name: "tenant_id", type: "bigint",  remarks: "租户id")  {constraints(nullable:"false")}
            column(name: "code", type: "varchar(" + 100 * weight + ")",  remarks: "功能编码")  {constraints(nullable:"false")}  
            column(name: "data_json", type: "longtext",  remarks: "数据")   
            column(name: "object_version_number", type: "bigint", defaultValue: "1", remarks: "行版本号，用来处理锁") {constraints(nullable: false)}
            column(name: "created_by", type: "bigint", defaultValue: "-1")
            column(name: "creation_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "last_updated_by", type: "bigint", defaultValue: "-1")
            column(name: "last_update_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP")
        }

        addUniqueConstraint(columnNames: "user_id,code,tenant_id", tableName: "hpfm_personal_table", constraintName: "hpfm_code_unique")
    }
}