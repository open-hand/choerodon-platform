package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_cusz_filter_field.groovy') {

    def weight = 1
    if (helper.isSqlServer()) {
        weight = 2
    } else if (helper.isOracle()) {
        weight = 3
    }

    changeSet(author: "peng.yu01@hand-china.com", id: "2020-01-14_hpfm_cusz_filter_field") {
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'hpfm_cusz_filter_field_s', startValue: "1")
        }
        createTable(tableName: "hpfm_cusz_filter_field") {
            column(name: "filter_field_id", type: "bigint", autoIncrement: "true", remarks: "表ID，主键，供其他表做外键") {
                constraints(primaryKey: true)
            }
            column(name: "tenant_id", type: "bigint", defaultValue: 0, remarks: "租户ID") {
                constraints(nullable: false)
            }
            column(name: "filter_id", type: "bigint", remarks: "hpfm_cusz_filter表主键") {
                constraints(nullable: false)
            }
            column(name: "field_alias", type: "varchar(" + 255 * weight + ")", remarks: "字段别名，对应hpfm_cusz_unit_field field_alias") {
                constraints(nullable: false)
            }
            column(name: "fixed_flag", type: "tinyint", defaultValue: 0, remarks: "固定标识") {
                constraints(nullable: false)
            }
            column(name: "order_seq", type: "int", defaultValue: 0, remarks: "排列顺序") {
                constraints(nullable: false)
            }
            column(name: "default_value", type: "varchar(" + 255 * weight + ")", remarks: "条件默认值") {
                constraints(nullable: true)
            }
            column(name: "merge_flag", type: "tinyint", defaultValue: 0, remarks: "是否聚合标识") {
                constraints(nullable: false)
            }
            column(name: "comparison", type: "varchar(" + 30 * weight + ")",  remarks: "比较符，用户级使用") {
                constraints(nullable: true)
            }
            column(name: "object_version_number", type: "bigint", defaultValue: "1", remarks: "行版本号，用来处理锁") {
                constraints(nullable: false)
            }
            column(name: "created_by", type: "bigint", defaultValue: "-1")
            column(name: "creation_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "last_updated_by", type: "bigint", defaultValue: "-1")
            column(name: "last_update_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP")
        }
        addUniqueConstraint(columnNames: "filter_id,field_alias,tenant_id", tableName: "hpfm_cusz_filter_field", constraintName: "hpfm_cusz_filter_field_U1")
    }
}
