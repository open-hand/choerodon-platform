package script.db
databaseChangeLog(logicalFilePath: 'script/db/hiam_role_auth_data_line.groovy') {
    def weight_c = 1
    if(helper.isOracle()){
        weight_c = 2
    }
    if(helper.isOracle()){
        weight_c = 3
    }
    changeSet(author: "qingsheng.chen@hand-china.com", id: "2019-06-14-hiam_role_auth_data_line") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_role_auth_data_line_s', startValue:"1")
        }
        createTable(tableName: "hiam_role_auth_data_line", remarks: "角色单据权限管理行") {
            column(name: "auth_data_line_id", type: "bigint", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)}
            column(name: "auth_data_id", type: "bigint",  remarks: "权限ID，hiam_role_auth_data.auth_data_id")  {constraints(nullable:"false")}
            column(name: "tenant_id", type: "bigint",  remarks: "租户ID，HPFM.HPFM_TENANT")  {constraints(nullable:"false")}
            column(name: "data_id", type: "bigint",  remarks: "数据ID")  {constraints(nullable:"false")}
            column(name: "data_code", type: "varchar(" + 80 * weight + ")",  remarks: "数据代码/编码")
            column(name: "data_name", type: "varchar(" + 360 * weight + ")",  remarks: "数据名称")
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}
        }
        addUniqueConstraint(columnNames:"auth_data_id,data_id",tableName:"hiam_role_auth_data_line",constraintName: "hiam_role_auth_data_line_u1")
    }
    changeSet(author: 'jiangzhou.bo@hand-china.com', id: '2020-03-16-hiam_role_auth_data_line') {
        addColumn(tableName: 'hiam_role_auth_data_line') {
            column(name: 'data_source',  type: 'varchar(30)', defaultValue: "DEFAULT", remarks: '数据来源') {constraints(nullable: "false")}
        }
    }
    changeSet(author: "Admin@hand-china.com", id: "hiam_role_auth_data_line-2021-05-18-version-2") {
        addColumn (tableName: "hiam_role_auth_data_line") {
            column (name: "queried_flag", type: "tinyint", remarks: "用于查询的数据标识，默认为1，0-禁用，1-启用", afterColumn: "last_update_date", defaultValue: "1") {
                constraints (nullable: "false")
            }
        }
        addColumn (tableName: "hiam_role_auth_data_line") {
            column (name: "updated_flag", type: "tinyint", remarks: "用于更新的数据标识，默认为1，0-禁用，1-启用", afterColumn: "queried_flag", defaultValue: "1") {
                constraints (nullable: "false")
            }
        }
        addColumn (tableName: "hiam_role_auth_data_line") {
            column (name: "created_flag", type: "tinyint", remarks: "用于新建的数据标识，默认为1，0-禁用，1-启用", afterColumn: "updated_flag", defaultValue: "1") {
                constraints (nullable: "false")
            }
        }
        addColumn (tableName: "hiam_role_auth_data_line") {
            column (name: "removed_flag", type: "tinyint", remarks: "用于删除的数据标识，默认为1，0-禁用，1-启用", afterColumn: "created_flag", defaultValue: "1") {
                constraints (nullable: "false")
            }
        }
    }
    changeSet(author: "Admin@hand-china.com", id: "hiam_role_auth_data_line-2021-06-22-version-3") {
        dropNotNullConstraint (tableName: "hiam_role_auth_data_line", columnName: "data_source", columnDataType: "varchar(" + 30* weight_c + ")")
        //mysql在删除非空约束时候会清空默认值
        if (helper.isMysql()) {
            addDefaultValue (tableName: "hiam_role_auth_data_line", columnName: "data_source", columnDataType: "varchar(" + 30* weight_c + ")", defaultValue: "DEFAULT")
        }
    }
    changeSet(author: "Admin@hand-china.com", id: "hiam_role_auth_data_line-2021-08-16-version-4") {
        dropUniqueConstraint(tableName: 'hiam_role_auth_data_line', constraintName: 'hiam_role_auth_data_line_u1')

        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hiam_role_auth_data_line", columnName: "data_id", remarks: "数据标识")
        }
        if (helper.isMysql()) {
            renameColumn (tableName: "hiam_role_auth_data_line", oldColumnName: "data_id", newColumnName: "data_id", columnDataType: "varchar(" + 120* weight_c + ")", remarks: "数据标识")
            //mysql在修改列名时候会清空非空约束和默认值
            if (helper.isMysql()) {
                addNotNullConstraint (tableName: "hiam_role_auth_data_line", columnName: "data_id", columnDataType: "varchar(" + 120* weight_c + ")")
            }
        }
        modifyDataType (tableName: "hiam_role_auth_data_line", columnName: "data_id", newDataType: "varchar(" + 120* weight_c + ")")
        //mysql在修改列类型时候会清空非空约束和默认值
        if (helper.isMysql()) {
            addNotNullConstraint (tableName: "hiam_role_auth_data_line", columnName: "data_id", columnDataType: "varchar(" + 120* weight_c + ")")
        }

        addUniqueConstraint(columnNames:"auth_data_id,data_id",tableName:"hiam_role_auth_data_line",constraintName: "hiam_role_auth_data_line_u1")
    }
}
