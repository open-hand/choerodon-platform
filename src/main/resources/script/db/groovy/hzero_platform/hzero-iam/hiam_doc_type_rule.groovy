package script.db
databaseChangeLog(logicalFilePath: 'script/db/hiam_doc_type_rule.groovy') {
    def weight_c = 1
    if(helper.isSqlServer()){
    weight_c = 2
    } else if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "hzero@hand-china.com", id: "hiam_doc_type_rule-2021-03-31-version-1"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_doc_type_rule_s', startValue:"1")
        }
        createTable(tableName: "hiam_doc_type_rule", remarks: "单据权限规则表") {
            column(name: "rule_id", type: "bigint", autoIncrement: true ,   remarks: "规则id,表主键")  {constraints(primaryKey: true)}
            column(name: "rule_type", type: "varchar(" + 30* weight_c + ")",  remarks: "规则类型，HIAM.DOC_RULE_TYPE")  {constraints(nullable:"false")}  
            column(name: "user_id", type: "bigint",   defaultValue:"-1",   remarks: "用户Id，iam_user.id")
            column(name: "role_id", type: "bigint",   defaultValue:"-1",   remarks: "角色id，iam_role.id")
            column(name: "menu_id", type: "bigint",   defaultValue:"-1",   remarks: "菜单id，iam_menu.id")
            column(name: "enable_role_auth", type: "tinyint",   defaultValue:"1",   remarks: "生效角色权限维度配置，1为生效，0为不生效")  {constraints(nullable:"false")}
            column(name: "tenant_id", type: "bigint",   defaultValue:"0",   remarks: "租户id，hpfm_tenant.tenant_id")  {constraints(nullable:"false")}
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "创建人")  {constraints(nullable:"false")}
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "最近更新人")  {constraints(nullable:"false")}
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "创建时间")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "最近更新时间")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}
        }
        addUniqueConstraint(columnNames:"rule_type,tenant_id,user_id,role_id,menu_id",tableName:"hiam_doc_type_rule",constraintName: "hiam_doc_type_rule_u1")
    }
}
