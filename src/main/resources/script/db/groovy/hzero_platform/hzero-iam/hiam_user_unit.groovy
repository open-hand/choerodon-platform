package script.db
databaseChangeLog(logicalFilePath: 'script/db/hiam_user_unit.groovy') {
    def weight_c = 1
    if(helper.isOracle()){
    weight_c = 2
    }
    if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "hand@hand-china.com", id: "hiam_user_unit-2021-06-07-version-1"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_user_unit_s', startValue:"1")
        }
        createTable(tableName: "hiam_user_unit", remarks: "子账户分配组织") {
            column(name: "user_unit_id", type: "bigint",autoIncrement: true,    remarks: "")  {constraints(primaryKey: true)} 
            column(name: "user_id", type: "bigint",  remarks: "用户Id,iam_user.id")  {constraints(nullable:"false")}  
            column(name: "unit_id", type: "bigint",  remarks: "公司/部门Id,hpfm_unit.unit_id")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint",   defaultValue:"0",   remarks: "租户Id,hpfm_tenant.tenant_id")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "创建人")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "最近更新人")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "创建时间")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "最近更新时间")  {constraints(nullable:"false")}  
        }
        addUniqueConstraint(columnNames:"user_id,unit_id",tableName:"hiam_user_unit",constraintName: "hiam_user_unit_u1")
    }
}
