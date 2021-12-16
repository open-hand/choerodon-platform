package script.db
databaseChangeLog(logicalFilePath: 'script/db/hiam_menu_tenant_tl.groovy') {
    def weight_c = 1
    if(helper.isOracle()){
    weight_c = 3
    }
    if(helper.isSqlServer()){
    weight_c = 2
    }
    changeSet(author: "hzero@hand-china.com", id: "hiam_menu_tenant_tl-2021-10-13-version-1"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_menu_tenant_tl_s', startValue:"1")
        }
        createTable(tableName: "hiam_menu_tenant_tl", remarks: "菜单租户多语言") {
            column(name: "id", type: "bigint", autoIncrement: true , remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)}
            column(name: "menu_id", type: "bigint",  remarks: "菜单ID,iam_menu.id")  {constraints(nullable:"false")}
            column(name: "lang", type: "varchar(" + 16* weight_c + ")",  remarks: "语言")  {constraints(nullable:"false")}
            column(name: "name", type: "varchar(" + 128* weight_c + ")",  remarks: "菜单名称")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint",  remarks: "租户ID,hpfm_tenant.tenant_id")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "创建人")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "最近更新人")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "创建时间")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "最近更新时间")  {constraints(nullable:"false")}  
        }
        addUniqueConstraint(columnNames:"menu_id,lang,name,tenant_id",tableName:"hiam_menu_tenant_tl",constraintName: "hiam_menu_tenant_tl_u1")
    }
}
