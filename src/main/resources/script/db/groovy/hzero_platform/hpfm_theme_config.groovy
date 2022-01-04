package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_theme_config.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2021-10-11-hpfm_theme_config"){
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_theme_config_s', startValue:"1")
        }
        createTable(tableName: "hpfm_theme_config", remarks: "主题配置") {
            column(name: "theme_config_id", type: "bigint",autoIncrement: true,    remarks: "主键ID")  {constraints(primaryKey: true)} 
            column(name: "object_type", type: "varchar(" + 10 * weight + ")",  remarks: "对象类型，T:租户 U:用户")  {constraints(nullable:"false")}
            column(name: "object_id", type: "bigint",  remarks: "租户ID,hpfm_tenant.tenant_id或用户ID,iam_user.id")  {constraints(nullable:"false")}  
            column(name: "config_type", type: "varchar(" + 30 * weight + ")",  remarks: "配置类型，THEME:主题,COLOR:颜色")  {constraints(nullable:"false")}
            column(name: "config_name", type: "varchar(" + 30 * weight + ")",  remarks: "配置名称")  {constraints(nullable:"false")}
            column(name: "config_value", type: "varchar(" + 480 * weight + ")",  remarks: "配置值")  {constraints(nullable:"false")}
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint",   defaultValue:"0",   remarks: "创建人")  {constraints(nullable:"false")}
            column(name: "last_updated_by", type: "bigint",   defaultValue:"0",   remarks: "最近更新人")  {constraints(nullable:"false")}
            column(name: "creation_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "创建时间")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "最近更新时间")  {constraints(nullable:"false")}  
        }
        addUniqueConstraint(columnNames:"object_type,object_id,config_type,config_name",tableName:"hpfm_theme_config",constraintName: "hpfm_theme_config_u1")
    }
}
