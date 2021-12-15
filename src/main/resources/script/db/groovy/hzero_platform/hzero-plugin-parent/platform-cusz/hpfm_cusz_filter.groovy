package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_cusz_filter.groovy') {

    def weight = 1
    if (helper.isSqlServer()) {
        weight = 2
    } else if (helper.isOracle()) {
        weight = 3
    }

    changeSet(author: "peng.yu01@hand-china.com", id: "2021-02-04_hpfm_cusz_filter") {
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'hpfm_cusz_filter_s', startValue: "1")
        }
        createTable(tableName: "hpfm_cusz_filter") {
            column(name: "filter_id", type: "bigint", autoIncrement: "true", remarks: "表ID，主键，供其他表做外键") {
                constraints(primaryKey: true)
            }
            column(name: "tenant_id", type: "bigint", defaultValue: 0, remarks: "租户ID") {
                constraints(nullable: false)
            }
            column(name: "user_id", type: "bigint", defaultValue: -1, remarks: "用户ID") {
                constraints(nullable: false)
            }
            column(name: "unit_id", type: "bigint", remarks: "单元ID") {
                constraints(nullable: false)
            }
            column(name: "filter_code", type: "varchar(" + 255 * weight + ")", remarks: "筛选器编码") {
                constraints(nullable: false)
            }
            column(name: "filter_name", type: "varchar(" + 255 * weight + ")", remarks: "筛选器名称") {
                constraints(nullable: false)
            }
            column(name: "default_flag", type: "tinyint", defaultValue: 0, remarks: "默认标识") {
                constraints(nullable: false)
            }
            column(name: "enabled_flag", type: "tinyint", defaultValue: 1, remarks: "启用标识")
            column(name: "object_version_number", type: "bigint", defaultValue: "1", remarks: "行版本号，用来处理锁") {
                constraints(nullable: false)
            }
            column(name: "created_by", type: "bigint", defaultValue: "-1")
            column(name: "creation_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "last_updated_by", type: "bigint", defaultValue: "-1")
            column(name: "last_update_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP")
        }
        addUniqueConstraint(columnNames: "filter_code,tenant_id", tableName: "hpfm_cusz_filter", constraintName: "hpfm_cusz_filter_U1")
        createIndex(tableName: "hpfm_cusz_filter", indexName: "hpfm_cusz_filter_n1") { column(name: "unit_id") }
    }

    changeSet(author: "peng.yu01@hand-china.com", id: "2021-02-04_hpfm_cusz_filter_tl") {
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'hpfm_cusz_filter_tl_s', startValue: "1")
        }
        createTable(tableName: "hpfm_cusz_filter_tl") {
            column(name: "filter_id", type: "bigint", remarks: "hpfm_cusz_filter 主键") {
                constraints(nullable: false)
            }
            column(name: "filter_name", type: "varchar(" + 255 * weight + ")", remarks: "筛选器名称") {
                constraints(nullable: false)
            }
            column(name: "lang", type: "varchar(" + 30 * weight + ")", remarks: "语言") {
                constraints(nullable: false)
            }
        }
        addUniqueConstraint(columnNames: "filter_id,lang", tableName: "hpfm_cusz_filter_tl", constraintName: "hpfm_cusz_filter_tl_U1")
    }


}
