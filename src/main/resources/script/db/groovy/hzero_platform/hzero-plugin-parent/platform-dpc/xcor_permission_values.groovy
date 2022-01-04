package script.db

databaseChangeLog(logicalFilePath: 'script/db/xcor_permission_values.groovy') {
    changeSet(author: "terry", id: "2020-04-07-xcor_permission_values") {
        def weight = 1
        if (helper.isSqlServer()) {
            weight = 2
        } else if (helper.isOracle()) {
            weight = 3
        }
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'xcor_permission_values_s', startValue: "1")
        }
        createTable(tableName: "xcor_permission_values", remarks: "权限值表") {
            column(name: "value_id", type: "bigint", autoIncrement: true, remarks: "") { constraints(primaryKey: true) }
            column(name: "object_id", type: "bigint", remarks: "权限对象ID") { constraints(nullable: "false") }
            column(name: "parent_value_id", type: "bigint", remarks: "父权限值ID")
            column(name: "parent_value_code", type: "varchar(" + 80 * weight + ")", remarks: "父权限值代码")
            column(name: "value_code", type: "varchar(" + 80 * weight + ")", remarks: "权限值代码") { constraints(nullable: "false") }
            column(name: "value_name", type: "varchar(" + 240 * weight + ")", remarks: "权限值名称") { constraints(nullable: "false") }
            column(name: "value_description", type: "varchar(" + 240 * weight + ")", remarks: "权限值说明")
            column(name: "summary_flag", type: "varchar(" + 1 * weight + ")", remarks: "权限值汇总 Y/N")
            column(name: "hierarchy_level", type: "varchar(" + 30 * weight + ")", remarks: "权限值层级")
            column(name: "enabled_flag", type: "tinyint", defaultValue: "1", remarks: "是否启用 1:启用 0：不启用") { constraints(nullable: "false") }
            column(name: "tenant_id", type: "bigint", defaultValue: "0", remarks: "租户ID") { constraints(nullable: "false") }
            column(name: "object_version_number", type: "bigint", defaultValue: "1", remarks: "行版本号，用来处理锁") { constraints(nullable: "false") }
            column(name: "creation_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "") { constraints(nullable: "false") }
            column(name: "created_by", type: "bigint", defaultValue: "-1", remarks: "") { constraints(nullable: "false") }
            column(name: "last_updated_by", type: "bigint", defaultValue: "-1", remarks: "") { constraints(nullable: "false") }
            column(name: "last_update_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "") { constraints(nullable: "false") }

        }

        addUniqueConstraint(columnNames: "value_code,object_id,tenant_id", tableName: "xcor_permission_values", constraintName: "xcor_permission_values_u1")
    }

    changeSet(author: "tianle.liu@hand-china.com", id: "xcor_permission_values-2021-06-25-version-2") {
        modifyDataType(tableName: "xcor_permission_values", columnName: "created_by", newDataType: "bigint")
        //mysql在修改列类型时候会清空非空约束和默认值
        if (helper.isMysql()) {
            addNotNullConstraint(tableName: "xcor_permission_values", columnName: "created_by", columnDataType: "bigint")
            addDefaultValue(tableName: "xcor_permission_values", columnName: "created_by", columnDataType: "bigint", defaultValue: "-1")
        }
        modifyDataType(tableName: "xcor_permission_values", columnName: "last_updated_by", newDataType: "bigint")
        //mysql在修改列类型时候会清空非空约束和默认值
        if (helper.isMysql()) {
            addNotNullConstraint(tableName: "xcor_permission_values", columnName: "last_updated_by", columnDataType: "bigint")
            addDefaultValue(tableName: "xcor_permission_values", columnName: "last_updated_by", columnDataType: "bigint", defaultValue: "-1")
        }
    }
}