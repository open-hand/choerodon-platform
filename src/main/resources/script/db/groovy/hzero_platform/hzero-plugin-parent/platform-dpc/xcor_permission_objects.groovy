package script.db

databaseChangeLog(logicalFilePath: 'script/db/xcor_permission_objects.groovy') {
    def weight = 1
    if (helper.isSqlServer()) {
        weight = 2
    } else if (helper.isOracle()) {
        weight = 3
    }
    changeSet(author: "terry", id: "2020-04-07-xcor_permission_objects") {
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'xcor_permission_objects_s', startValue: "1")
        }
        createTable(tableName: "xcor_permission_objects", remarks: "权限对象表") {
            column(name: "object_id", type: "bigint", autoIncrement: true, remarks: "") { constraints(primaryKey: true) }
            column(name: "object_code", type: "varchar(" + 30 * weight + ")", remarks: "权限对象代码") { constraints(nullable: "false") }
            column(name: "object_alias", type: "varchar(" + 30 * weight + ")", remarks: "权限对象别名，废弃")
            column(name: "object_name", type: "varchar(" + 80 * weight + ")", remarks: "权限对象名称") { constraints(nullable: "false") }
            column(name: "object_description", type: "varchar(" + 240 * weight + ")", remarks: "权限对象说明")
            column(name: "parent_object_id", type: "bigint", remarks: "父权限对象ID")
            column(name: "parent_object_code", type: "varchar(" + 30 * weight + ")", remarks: "父权限对象代码")
            column(name: "enabled_flag", type: "tinyint", defaultValue: "1", remarks: "是否启用 1:启用 0：不启用") { constraints(nullable: "false") }
            column(name: "tenant_id", type: "bigint", defaultValue: "0", remarks: "租户ID") { constraints(nullable: "false") }
            column(name: "object_version_number", type: "bigint", defaultValue: "1", remarks: "行版本号，用来处理锁") { constraints(nullable: "false") }
            column(name: "creation_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "") { constraints(nullable: "false") }
            column(name: "created_by", type: "bigint", defaultValue: "-1", remarks: "") { constraints(nullable: "false") }
            column(name: "last_updated_by", type: "bigint", defaultValue: "-1", remarks: "") { constraints(nullable: "false") }
            column(name: "last_update_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "") { constraints(nullable: "false") }

        }

        addUniqueConstraint(columnNames: "object_code,tenant_id", tableName: "xcor_permission_objects", constraintName: "xcor_permission_objects_u1")
        addUniqueConstraint(columnNames: "object_name,tenant_id", tableName: "xcor_permission_objects", constraintName: "xcor_permission_objects_u2")
    }

    changeSet(author: "lei.song@hand-china.com", id: "2021-04-09-1-xcor_permission_objects") {
        addColumn(tableName: "xcor_permission_objects") {
            column(name: "create_mode", type: "varchar(" + 30 * weight + ")", remarks: "创建方式 HDSP.XCOR.VALUE_CREATE_MODE", defaultValue: "MANUAL") { constraints(nullable: "false") }
        }
        addColumn(tableName: "xcor_permission_objects") {
            column(name: "lov_code", type: "varchar(" + 60 * weight + ")", remarks: "创建方式为值集导入时这里是值集编码") { constraints(nullable: "true") }
        }
        addColumn(tableName: "xcor_permission_objects") {
            column(name: "lov_type", type: "varchar(" + 30 * weight + ")", remarks: "值集类型。IDP（独立值集）,LOV（值集视图）HDSP.XCOR.LOV_TYPE") { constraints(nullable: "true") }
        }
    }

    changeSet(author: "tianle.liu@hand-china.com", id: "xcor_permission_objects-2021-06-25-version-2") {
        modifyDataType(tableName: "xcor_permission_objects", columnName: "created_by", newDataType: "bigint")
        //mysql在修改列类型时候会清空非空约束和默认值
        if (helper.isMysql()) {
            addNotNullConstraint(tableName: "xcor_permission_objects", columnName: "created_by", columnDataType: "bigint")
            addDefaultValue(tableName: "xcor_permission_objects", columnName: "created_by", columnDataType: "bigint", defaultValue: "-1")
        }
        modifyDataType(tableName: "xcor_permission_objects", columnName: "last_updated_by", newDataType: "bigint")
        //mysql在修改列类型时候会清空非空约束和默认值
        if (helper.isMysql()) {
            addNotNullConstraint(tableName: "xcor_permission_objects", columnName: "last_updated_by", columnDataType: "bigint")
            addDefaultValue(tableName: "xcor_permission_objects", columnName: "last_updated_by", columnDataType: "bigint", defaultValue: "-1")
        }
    }
}