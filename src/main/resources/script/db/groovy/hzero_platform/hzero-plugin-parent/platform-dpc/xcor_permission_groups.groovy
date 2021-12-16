package script.db

databaseChangeLog(logicalFilePath: 'script/db/xcor_permission_groups.groovy') {
    changeSet(author: "terry", id: "2020-04-07-xcor_permission_groups") {
        def weight = 1
        if (helper.isSqlServer()) {
            weight = 2
        } else if (helper.isOracle()) {
            weight = 3
        }
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'xcor_permission_groups_s', startValue: "1")
        }
        createTable(tableName: "xcor_permission_groups", remarks: "权限组表") {
            column(name: "group_id", type: "bigint", autoIncrement: true, remarks: "") { constraints(primaryKey: true) }
            column(name: "group_code", type: "varchar(" + 80 * weight + ")", remarks: "权限组代码") { constraints(nullable: "false") }
            column(name: "group_name", type: "varchar(" + 240 * weight + ")", remarks: "权限组") { constraints(nullable: "false") }
            column(name: "group_description", type: "varchar(" + 240 * weight + ")", remarks: "权限组说明")
            column(name: "enabled_flag", type: "tinyint", defaultValue: "1", remarks: "是否启用 1:启用 0：不启用") { constraints(nullable: "false") }
            column(name: "tenant_id", type: "bigint", defaultValue: "0", remarks: "租户ID") { constraints(nullable: "false") }
            column(name: "object_version_number", type: "bigint", defaultValue: "1", remarks: "行版本号，用来处理锁") { constraints(nullable: "false") }
            column(name: "creation_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "") { constraints(nullable: "false") }
            column(name: "created_by", type: "bigint", defaultValue: "-1", remarks: "") { constraints(nullable: "false") }
            column(name: "last_updated_by", type: "bigint", defaultValue: "-1", remarks: "") { constraints(nullable: "false") }
            column(name: "last_update_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "") { constraints(nullable: "false") }

        }

        addUniqueConstraint(columnNames: "group_code,tenant_id", tableName: "xcor_permission_groups", constraintName: "xcor_permission_groups_u1")
        addUniqueConstraint(columnNames: "group_name,tenant_id", tableName: "xcor_permission_groups", constraintName: "xcor_permission_groups_u2")
    }

    changeSet(author: "tianle.liu@hand-china.com", id: "xcor_permission_groups-2021-06-25-version-2") {
        modifyDataType(tableName: "xcor_permission_groups", columnName: "created_by", newDataType: "bigint")
        //mysql在修改列类型时候会清空非空约束和默认值
        if (helper.isMysql()) {
            addNotNullConstraint(tableName: "xcor_permission_groups", columnName: "created_by", columnDataType: "bigint")
            addDefaultValue(tableName: "xcor_permission_groups", columnName: "created_by", columnDataType: "bigint", defaultValue: "-1")
        }
        modifyDataType(tableName: "xcor_permission_groups", columnName: "last_updated_by", newDataType: "bigint")
        //mysql在修改列类型时候会清空非空约束和默认值
        if (helper.isMysql()) {
            addNotNullConstraint(tableName: "xcor_permission_groups", columnName: "last_updated_by", columnDataType: "bigint")
            addDefaultValue(tableName: "xcor_permission_groups", columnName: "last_updated_by", columnDataType: "bigint", defaultValue: "-1")
        }
    }
}