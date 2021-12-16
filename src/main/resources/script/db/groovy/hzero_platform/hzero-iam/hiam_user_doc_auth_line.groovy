package script.db
databaseChangeLog(logicalFilePath: 'script/db/hiam_user_doc_auth_line.groovy') {
    def weight_c = 1
    if(helper.isSqlServer()){
    weight_c = 2
    } else if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "hzero@hand-china.com", id: "hiam_user_doc_auth_line-2021-03-31-version-1"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_user_doc_auth_line_s', startValue:"1")
        }
        createTable(tableName: "hiam_user_doc_auth_line", remarks: "用户单据权限定义行表") {
            column(name: "user_auth_line_id", type: "bigint", autoIncrement: true ,   remarks: "用户单据行Id，主键")  {constraints(primaryKey: true)}
            column(name: "user_auth_id", type: "bigint",  remarks: "用户单据Id，hiam_user_doc_auth.user_auth_id")  {constraints(nullable:"false")}
            column(name: "user_id", type: "bigint",  remarks: "用户id，iam_user.id")  {constraints(nullable:"false")}
            column(name: "auth_type_code", type: "varchar(" + 30* weight_c + ")",  remarks: "权限类型代码，HIAM.AUTHORITY_TYPE_CODE")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint",   defaultValue:"0",   remarks: "租户id，hpfm_tenant.tenant_id")  {constraints(nullable:"false")}
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "创建人")  {constraints(nullable:"false")}
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "最近更新人")  {constraints(nullable:"false")}
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "创建时间")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "最近更新时间")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}
        }
    }
}
