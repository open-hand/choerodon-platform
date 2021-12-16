package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_import_task.groovy') {
    def weight_c = 1
    if (helper.isSqlServer()) {
        weight_c = 2
    }
    if (helper.isOracle()) {
        weight_c = 3
    }
    changeSet(author: "hzero@hand-china.com", id: "hpfm_import_task-2021-08-24-version-1") {
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'hpfm_import_task_s', startValue: "1")
        }
        createTable(tableName: "hpfm_import_task", remarks: "导入记录") {
            column(name: "import_task_id", type: "bigint", autoIncrement: true, startWith: "1", incrementBy: "1", remarks: "主键ID，供其他表做外键") { constraints(primaryKey: true) }
            column(name: "template_code", type: "varchar(" + 30 * weight_c + ")", remarks: "模板编码") { constraints(nullable: "false") }
            column(name: "template_source", type: "varchar(" + 60 * weight_c + ")", remarks: "模板来源，值集HIMP.TEMPLATE_SOURCE") { constraints(nullable: "false") }
            column(name: "template_category", type: "varchar(" + 120 * weight_c + ")", remarks: "导入模板类别编码（导入场景），值集HMDE.BUSINESS_OBJECT.IMPORT.TEMPLATE_TYPE")
            column(name: "batch_num", type: "varchar(" + 120 * weight_c + ")", remarks: "批次号") { constraints(nullable: "false") }
            column(name: "status", type: "varchar(" + 30 * weight_c + ")", remarks: "状态") { constraints(nullable: "false") }
            column(name: "file_name", type: "varchar(" + 240 * weight_c + ")", remarks: "文件名")
            column(name: "file_url", type: "varchar(" + 480 * weight_c + ")", remarks: "文件url")
            column(name: "service_path", type: "varchar(" + 120 * weight_c + ")", remarks: "服务路由")
            column(name: "data_count", type: "int", remarks: "导入数据总量") { constraints(nullable: "false") }
            column(name: "success_count", type: "int", remarks: "导入成功数量")
            column(name: "tenant_id", type: "bigint", defaultValue: "0", remarks: "租户ID，hpfm_tenant.tenant_id") { constraints(nullable: "false") }
            column(name: "object_version_number", type: "bigint", remarks: "行版本号，用来处理锁") { constraints(nullable: "false") }
            column(name: "created_by", type: "bigint", defaultValue: "-1", remarks: "创建人") { constraints(nullable: "false") }
            column(name: "last_updated_by", type: "bigint", defaultValue: "-1", remarks: "最近更新人") { constraints(nullable: "false") }
            column(name: "creation_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "创建时间") { constraints(nullable: "false") }
            column(name: "last_update_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "最近更新时间") { constraints(nullable: "false") }
        }
        createIndex(tableName: "hpfm_import_task", indexName: "hpfm_import_task_n1") {
            column(name: "created_by")
        }
        addUniqueConstraint(columnNames: "batch_num", tableName: "hpfm_import_task", constraintName: "hpfm_import_task_u1")
    }
}
