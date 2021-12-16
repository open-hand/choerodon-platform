package script.db
databaseChangeLog(logicalFilePath: 'script/db/hiam_authority.groovy') {
    def weight_c = 1
    if(helper.isOracle()){
    weight_c = 2
    }
    if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "Admin@hand-china.com", id: "hiam_authority-2021-05-25-version-1"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_authority_s', startValue:"1")
        }
        createTable(tableName: "hiam_authority", remarks: "数据权限维度表") {
            column(name: "authority_id", type: "bigint",autoIncrement: true,    remarks: "主键")  {constraints(primaryKey: true)} 
            column(name: "user_id", type: "bigint",   defaultValue:"-1",   remarks: "用户ID，表iam_user.id")  {constraints(nullable:"false")}  
            column(name: "role_id", type: "bigint",   defaultValue:"-1",   remarks: "角色ID，表iam_role.id")  {constraints(nullable:"false")}  
            column(name: "menu_id", type: "bigint",   defaultValue:"-1",   remarks: "菜单ID，表iam_menu.id")  {constraints(nullable:"false")}  
            column(name: "enabled_flag", type: "tinyint",   defaultValue:"1",   remarks: "是否启用，1-启用 0-未启用")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint",   defaultValue:"1",   remarks: "租户ID")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "创建人")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "最近更新人")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "创建时间")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "最近更新时间")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
        }
    }
}
