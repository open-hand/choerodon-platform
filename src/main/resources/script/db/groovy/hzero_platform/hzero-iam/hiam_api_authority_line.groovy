package script.db
databaseChangeLog(logicalFilePath: 'script/db/hiam_api_authority_line.groovy') {
    def weight_c = 1
    if(helper.isOracle()){
    weight_c = 2
    }
    if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "Admin@hand-china.com", id: "hiam_api_authority_line-2021-05-07-version-1"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_api_authority_line_s', startValue:"1")
        }
        createTable(tableName: "hiam_api_authority_line", remarks: "接口数据权限行表") {
            column(name: "api_authority_line_id", type: "bigint",autoIncrement: true,    remarks: "主键")  {constraints(primaryKey: true)} 
            column(name: "api_authority_id", type: "bigint",  remarks: "数据权限ID，表hiam_api_authority.api_authority_id")  {constraints(nullable:"false")}  
            column(name: "field_name", type: "varchar(" + 120* weight_c + ")",  remarks: "字段ID，表hiam_field.field_name")  {constraints(nullable:"false")}  
            column(name: "authority_type_code", type: "varchar(" + 30* weight_c + ")",  remarks: "权限类型代码")  {constraints(nullable:"false")}  
            column(name: "enabled_flag", type: "tinyint",   defaultValue:"1",   remarks: "是否启用，1-启用 0-未启用")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint",   defaultValue:"0",   remarks: "租户ID")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "创建人")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "最近更新人")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "创建时间")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "最近更新时间")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
        }
    }
    changeSet(author: "Admin@hand-china.com", id: "hiam_api_authority_line-2021-05-14-version-2") {
        setTableRemarks (tableName: "hiam_api_authority_line", remarks: "接口操作数据权限行表") 
        addUniqueConstraint (tableName: "hiam_api_authority_line", columnNames: "api_authority_id,field_name,tenant_id", constraintName: "hiam_api_authority_line_u1") 
    }
    changeSet(author: "Admin@hand-china.com", id: "hiam_api_authority_line-2021-06-04-version-3") {
        renameColumn (tableName: "hiam_api_authority_line", oldColumnName: "authority_type_code", newColumnName: "dimension_code", columnDataType: "varchar(" + 30* weight_c + ")", remarks: "权限类型代码,hiam_doc_type_dimension.dimension_code") 
        //mysql在修改列名时候会清空非空约束和默认值
        if (helper.isMysql()) {
            addNotNullConstraint (tableName: "hiam_api_authority_line", columnName: "dimension_code", columnDataType: "varchar(" + 30* weight_c + ")") 
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hiam_api_authority_line", columnName: "dimension_code", remarks: "权限类型代码,hiam_doc_type_dimension.dimension_code") 
        }
    }
}
