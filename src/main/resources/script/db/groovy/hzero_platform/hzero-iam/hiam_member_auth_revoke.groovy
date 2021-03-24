package script.db
databaseChangeLog(logicalFilePath: 'script/db/hiam_member_auth_revoke.groovy') {
    def weight_c = 1
    if(helper.isSqlServer()){
    weight_c = 2
    } else if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "jiangzhou.bo@hand-china.com", id: "hiam_member_auth_revoke-2020-11-26-version-3"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_member_auth_revoke_s', startValue:"1")
        }
        createTable(tableName: "hiam_member_auth_revoke", remarks: "成员角色权限排除表") {
            column(name: "member_auth_revoke_id", type: "bigint", autoIncrement: true ,   remarks: "主键")  {constraints(primaryKey: true)}
            column(name: "member_id", type: "bigint",  remarks: "成员ID")  {constraints(nullable:"false")}
            column(name: "member_type", type: "varchar(" + 30* weight_c + ")",  remarks: "成员类型")  {constraints(nullable:"false")}  
            column(name: "role_id", type: "bigint",  remarks: "角色ID")  {constraints(nullable:"false")}
            column(name: "permission_id", type: "bigint",  remarks: "权限ID")  {constraints(nullable:"false")}
            column(name: "permission_type", type: "varchar(" + 30* weight_c + ")",   defaultValue:"PS",   remarks: "权限类型：PS:权限集，HITF-SVR:服务，HITF-ITF:接口")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint",  remarks: "租户ID")  {constraints(nullable:"false")}
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}
        }
        addUniqueConstraint(columnNames:"member_id,member_type,role_id,permission_id,permission_type",tableName:"hiam_member_auth_revoke",constraintName: "hiam_member_auth_revoke_u1")
    }
}
