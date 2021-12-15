package script.db
databaseChangeLog(logicalFilePath: 'script/db/hiam_api_authority.groovy') {
    def weight_c = 1
    if(helper.isOracle()){
    weight_c = 2
    }
    if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "Admin@hand-china.com", id: "hiam_api_authority-2021-05-07-version-1"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_api_authority_s', startValue:"1")
        }
        createTable(tableName: "hiam_api_authority", remarks: "接口数据权限表") {
            column(name: "api_authority_id", type: "bigint",autoIncrement: true,    remarks: "主键")  {constraints(primaryKey: true)} 
            column(name: "user_id", type: "bigint",   defaultValue:"-1",   remarks: "用户ID，表iam_user.id")  {constraints(nullable:"false")}  
            column(name: "role_id", type: "bigint",   defaultValue:"-1",   remarks: "角色ID，表iam_role.id")  {constraints(nullable:"false")}  
            column(name: "menu_id", type: "bigint",   defaultValue:"-1",   remarks: "菜单ID，表iam_menu.id")  {constraints(nullable:"false")}  
            column(name: "permission_code", type: "varchar(" + 128* weight_c + ")",  remarks: "权限编码，表iam_permission.code")  {constraints(nullable:"false")}  
            column(name: "enabled_flag", type: "tinyint",   defaultValue:"1",   remarks: "是否启用，1-启用 0-未启用")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint",   defaultValue:"0",   remarks: "租户ID")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "创建人")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "最近更新人")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "创建时间")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "最近更新时间")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
        }
    }
    changeSet(author: "Admin@hand-china.com", id: "hiam_api_authority-2021-05-14-version-2") {
        setTableRemarks (tableName: "hiam_api_authority", remarks: "接口操作数据权限表") 
        if (helper.isSqlServer()) {
            dropDefaultValue (tableName: "hiam_api_authority", columnName: "role_id", columnDataType: "bigint") 
        }
        dropColumn (tableName: "hiam_api_authority", columnName: "role_id") 
        if (helper.isSqlServer()) {
            dropDefaultValue (tableName: "hiam_api_authority", columnName: "menu_id", columnDataType: "bigint") 
        }
        dropColumn (tableName: "hiam_api_authority", columnName: "menu_id") 
        renameColumn (tableName: "hiam_api_authority", oldColumnName: "user_id", newColumnName: "api_authority_dim_id", columnDataType: "bigint", remarks: "接口操作数据权限维度ID，表hiam_api_authority_dim.api_authority_dim_id") 
        //mysql在修改列名时候会清空非空约束和默认值
        if (helper.isMysql()) {
            addNotNullConstraint (tableName: "hiam_api_authority", columnName: "api_authority_dim_id", columnDataType: "bigint") 
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hiam_api_authority", columnName: "api_authority_dim_id", remarks: "接口操作数据权限维度ID，表hiam_api_authority_dim.api_authority_dim_id") 
        }
        dropDefaultValue (tableName: "hiam_api_authority", columnName: "api_authority_dim_id", columnDataType: "bigint") 
        addUniqueConstraint (tableName: "hiam_api_authority", columnNames: "api_authority_dim_id,permission_code,tenant_id", constraintName: "hiam_api_authority_u1") 
    }
    changeSet(author: "Admin@hand-china.com", id: "hiam_api_authority-2021-05-25-version-3") {
        renameColumn (tableName: "hiam_api_authority", oldColumnName: "api_authority_dim_id", newColumnName: "authority_id", columnDataType: "bigint", remarks: "数据权限维度ID，表hiam_authority.authority_id") 
        //mysql在修改列名时候会清空非空约束和默认值
        if (helper.isMysql()) {
            addNotNullConstraint (tableName: "hiam_api_authority", columnName: "authority_id", columnDataType: "bigint") 
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hiam_api_authority", columnName: "authority_id", remarks: "数据权限维度ID，表hiam_authority.authority_id") 
        }
    }
    changeSet(author: "Admin@hand-china.com", id: "hiam_api_authority-2021-06-04-version-4") {
        dropUniqueConstraint (tableName: "hiam_api_authority", constraintName: "hiam_api_authority_u1") 
        dropColumn (tableName: "hiam_api_authority", columnName: "authority_id") 
        addColumn (tableName: "hiam_api_authority") {
            column (name: "api_type", type: "varchar(" + 30* weight_c + ")", remarks: "API类型，新增：I 更新:U 查询:Q 删除:D", afterColumn: "object_version_number") {
                constraints (nullable: "false") 
            }
        }
        addUniqueConstraint (tableName: "hiam_api_authority", columnNames: "permission_code,tenant_id", constraintName: "hiam_api_authority_u1") 
    }
}
