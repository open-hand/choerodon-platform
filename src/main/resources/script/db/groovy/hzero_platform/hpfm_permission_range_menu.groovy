package script.db
databaseChangeLog(logicalFilePath: 'script/db/hpfm_permission_range_menu.groovy') {
    def weight_c = 1
    if(helper.isSqlServer()){
    weight_c = 2
    } else if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "hzero@hand-china.com", id: "hpfm_permission_range_menu-2021-01-04"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_permission_range_menu_s', startValue:"1")
        }
        createTable(tableName: "hpfm_permission_range_menu", remarks: "数据权限范围菜单关系表") {
            column(name: "range_menu_id", type: "bigint", autoIncrement: true ,   remarks: "数据权限范围菜单关系Id")  {constraints(primaryKey: true)}
            column(name: "range_id", type: "bigint",  remarks: "数据权限范围Id,hpfm_permission_range.range_id")  {constraints(nullable:"false")}
            column(name: "menu_id", type: "bigint",  remarks: "菜单Id,iam_menu.id")  {constraints(nullable:"false")}
            column (name: "rule_id", type: "bigint", remarks: "规则Id，hpfm_permission_rule.rule_id", defaultValue: "0")
            column(name: "tenant_id", type: "bigint",   defaultValue:"0",   remarks: "租户Id,hpfm_tenant.tenant_id")  {constraints(nullable:"false")}
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "创建人")  {constraints(nullable:"false")}
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "最近更新人")  {constraints(nullable:"false")}
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "创建时间")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "最近更新时间")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}
        }
        addUniqueConstraint(columnNames:"range_id,menu_id,rule_id",tableName:"hpfm_permission_range_menu",constraintName: "hpfm_permission_range_menu_u1")
    }
}
