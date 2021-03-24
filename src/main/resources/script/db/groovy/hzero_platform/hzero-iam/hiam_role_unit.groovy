package script.db
databaseChangeLog(logicalFilePath: 'script/db/hiam_role_unit.groovy') {
    def weight_c = 1
    if(helper.isSqlServer()){
    weight_c = 2
    } else if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "hzero@hand-china.com", id: "hiam_role_unit-2020-12-02-version-1"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_role_unit_s', startValue:"1")
        }
        createTable(tableName: "hiam_role_unit", remarks: "角色分配组织架构表") {
            column(name: "role_unit_id", type: "bigint", autoIncrement: true ,   remarks: "主键Id")  {constraints(primaryKey: true)}
            column(name: "role_id", type: "bigint",  remarks: "角色id,iam_role.id")  {constraints(nullable:"false")}
            column(name: "unit_id", type: "bigint",  remarks: "公司/部门Id,hpfm_unit.unit_id")  {constraints(nullable:"false")}
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "创建人")  {constraints(nullable:"false")}
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "最近更新人")  {constraints(nullable:"false")}
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "创建时间")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "最近更新时间")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}
            column(name: "tenant_id", type: "bigint",   defaultValue:"0",   remarks: "租户Id,hpfm_tenant.tenant_id")  {constraints(nullable:"false")}
        }
        addUniqueConstraint(columnNames:"role_id,unit_id",tableName:"hiam_role_unit",constraintName: "hiam_role_unit_u1")
    }
}
