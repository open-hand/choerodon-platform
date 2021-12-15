package script.db

databaseChangeLog(logicalFilePath: 'script/db/xcor_perm_group_assign.groovy') {
    changeSet(author: "terry", id: "2020-04-07-xcor_perm_group_assign") {
        def weight = 1
        if (helper.isSqlServer()) {
            weight = 2
        } else if (helper.isOracle()) {
            weight = 3
        }
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'xcor_perm_group_assign_s', startValue: "1")
        }
        createTable(tableName: "xcor_perm_group_assign", remarks: "权限组分配表") {
            column(name: "assign_id", type: "bigint", autoIncrement: true, remarks: "") { constraints(primaryKey: true) }
            column(name: "assign_type", type: "varchar(" + 30 * weight + ")", defaultValue: "USER", remarks: "分配类型 USER:用户 ROLE:角色") { constraints(nullable: "false") }
            column(name: "group_id", type: "bigint", remarks: "权限组ID，关联权限组表HDP_PERMISSION_GROUPS") { constraints(nullable: "false") }
            column(name: "assign_object_id", type: "bigint", remarks: "分配对象ID,USER_ID/ROLE_ID") { constraints(nullable: "false") }
            column(name: "assign_description", type: "varchar(" + 240 * weight + ")", remarks: "分配说明")
            column(name: "enabled_flag", type: "tinyint", defaultValue: "1", remarks: "是否启用 1:启用 0：不启用") { constraints(nullable: "false") }
            column(name: "tenant_id", type: "bigint", defaultValue: "0", remarks: "租户ID") { constraints(nullable: "false") }
            column(name: "object_version_number", type: "bigint", defaultValue: "1", remarks: "行版本号，用来处理锁") { constraints(nullable: "false") }
            column(name: "creation_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "") { constraints(nullable: "false") }
            column(name: "created_by", type: "bigint", defaultValue: "-1", remarks: "") { constraints(nullable: "false") }
            column(name: "last_updated_by", type: "bigint", defaultValue: "-1", remarks: "") { constraints(nullable: "false") }
            column(name: "last_update_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "") { constraints(nullable: "false") }

        }

        addUniqueConstraint(columnNames: "assign_object_id,group_id,assign_type", tableName: "xcor_perm_group_assign", constraintName: "xcor_perm_group_assign_u1")
    }

    changeSet(author: "tianle.liu@hand-china.com", id: "xcor_perm_group_assign-2021-06-25-version-2") {
        modifyDataType(tableName: "xcor_perm_group_assign", columnName: "created_by", newDataType: "bigint")
        //mysql在修改列类型时候会清空非空约束和默认值
        if (helper.isMysql()) {
            addNotNullConstraint(tableName: "xcor_perm_group_assign", columnName: "created_by", columnDataType: "bigint")
            addDefaultValue(tableName: "xcor_perm_group_assign", columnName: "created_by", columnDataType: "bigint", defaultValue: "-1")
        }
        modifyDataType(tableName: "xcor_perm_group_assign", columnName: "last_updated_by", newDataType: "bigint")
        //mysql在修改列类型时候会清空非空约束和默认值
        if (helper.isMysql()) {
            addNotNullConstraint(tableName: "xcor_perm_group_assign", columnName: "last_updated_by", columnDataType: "bigint")
            addDefaultValue(tableName: "xcor_perm_group_assign", columnName: "last_updated_by", columnDataType: "bigint", defaultValue: "-1")
        }
    }
}