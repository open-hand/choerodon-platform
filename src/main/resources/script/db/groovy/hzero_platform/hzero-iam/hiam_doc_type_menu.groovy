package script.db
databaseChangeLog(logicalFilePath: 'script/db/hiam_doc_type_menu.groovy') {
    def weight_c = 1
    if(helper.isSqlServer()){
    weight_c = 2
    } else if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "hzero@hand-china.com", id: "hiam_doc_type_menu-2021-01-04"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_doc_type_menu_s', startValue:"1")
        }
        createTable(tableName: "hiam_doc_type_menu", remarks: "单据权限菜单关联表") {
            column(name: "doc_menu_id", type: "bigint", autoIncrement: true ,   remarks: "单据权限菜单关联Id")  {constraints(primaryKey: true)}
            column(name: "doc_type_id", type: "bigint",  remarks: "单据权限Id，hiam_doc_type.doc_type_id")  {constraints(nullable:"false")}
            column(name: "menu_id", type: "bigint",  remarks: "菜单Id，iam_menu.id")  {constraints(nullable:"false")}
            column(name: "tenant_id", type: "bigint",   defaultValue:"0",   remarks: "租户Id，hpfm_tenant.tenant_id")  {constraints(nullable:"false")}
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "创建人")  {constraints(nullable:"false")}
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "最近更新人")  {constraints(nullable:"false")}
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "创建时间")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "最近更新时间")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}
        }
        addUniqueConstraint(columnNames:"doc_type_id,menu_id",tableName:"hiam_doc_type_menu",constraintName: "hiam_doc_type_menu_u1")
    }
}
