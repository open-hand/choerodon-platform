package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_plugin.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2020-11-12-hpfm_plugin") {
        def weight = 1
        if (helper.isSqlServer()) {
            weight = 2
        } else if (helper.isOracle()) {
            weight = 3
        }
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'hpfm_plugin_s', startValue: "1")
        }
        createTable(tableName: "hpfm_plugin", remarks: "插件中心") {
            column(name: "plugin_id", type: "bigint", autoIncrement: true, remarks: "插件Id") {
                constraints(primaryKey: true)
            }
            column(name: "plugin_code", type: "varchar(" + 60 * weight + ")", remarks: "插件编码") {
                constraints(nullable: "false")
            }
            column(name: "plugin_name", type: "varchar(" + 120 * weight + ")", remarks: "插件名称，上传插件的文件名") {
                constraints(nullable: "false")
            }
            column(name: "description", type: "varchar(" + 480 * weight + ")", remarks: "描述")
            column(name: "plugin_version", type: "varchar(" + 30 * weight + ")", remarks: "插件版本") {
                constraints(nullable: "false")
            }
            column(name: "plugin_path", type: "varchar(" + 480 * weight + ")", remarks: "插件文件路径") {
                constraints(nullable: "false")
            }
            column(name: "plugin_purpose", type: "varchar(" + 30 * weight + ")", remarks: "插件用途,HPFM.PLUGIN_PURPOSE") {
                constraints(nullable: "false")
            }
            column(name: "plugin_type", type: "varchar(" + 30 * weight + ")", remarks: "插件类型,HPFM.PLUGIN_TYPE") {
                constraints(nullable: "false")
            }
            column(name: "plugin_fingerprint", type: "varchar(" + 60 * weight + ")", remarks: "插件指纹") {
                constraints(nullable: "false")
            }
            column(name: "tenant_id", type: "bigint", defaultValue: "0", remarks: "租户ID,hpfm_tenant.tenant_id") {
                constraints(nullable: "false")
            }
            column(name: "enabled_flag", type: "tinyint", defaultValue: "1", remarks: "是否启用。1启用，0未启用") {
                constraints(nullable: "false")
            }
            column(name: "object_version_number", type: "bigint", defaultValue: "1", remarks: "行版本号，用来处理锁") {
                constraints(nullable: "false")
            }
            column(name: "creation_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "") {
                constraints(nullable: "false")
            }
            column(name: "created_by", type: "bigint", defaultValue: "-1", remarks: "") {
                constraints(nullable: "false")
            }
            column(name: "last_updated_by", type: "bigint", defaultValue: "-1", remarks: "") {
                constraints(nullable: "false")
            }
            column(name: "last_update_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "") {
                constraints(nullable: "false")
            }
        }
        addUniqueConstraint(columnNames: "plugin_code,tenant_id", tableName: "hpfm_plugin", constraintName: "hpfm_plugin_u1")
    }
}
