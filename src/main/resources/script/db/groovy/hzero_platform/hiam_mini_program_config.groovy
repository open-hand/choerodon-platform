package script.db
databaseChangeLog(logicalFilePath: 'script/db/hiam_mini_program_config.groovy') {
    def weight_c = 1
    if(helper.isSqlServer()){
    weight_c = 2
    } else if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "bo.he02@hand-china.com", id: "hiam_mini_program_config-2020-09-28-version-1"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_mini_program_config_s', startValue:"1")
        }
        createTable(tableName: "hiam_mini_program_config", remarks: "小程序配置信息") {
            column(name: "config_id", type: "bigint", autoIncrement: true ,   remarks: "主键")  {constraints(primaryKey: true)}
            column(name: "mini_program_type", type: "varchar(" + 30* weight_c + ")",  remarks: "小程序类型,值集：HIAM.MINI_PROGRAM.TYPE")  {constraints(nullable:"false")}  
            column(name: "mini_program_code", type: "varchar(" + 60* weight_c + ")",  remarks: "小程序编码")  {constraints(nullable:"false")}  
            column(name: "mini_program_name", type: "varchar(" + 120* weight_c + ")",  remarks: "小程序名称")   
            column(name: "app_key", type: "varchar(" + 60* weight_c + ")",  remarks: "小程序key")  {constraints(nullable:"false")}  
            column(name: "app_secret", type: "varchar(" + 120* weight_c + ")",  remarks: "小程序secret")  {constraints(nullable:"false")}  
            column(name: "sign_token", type: "varchar(" + 60* weight_c + ")",  remarks: "签名Token")   
            column(name: "encoding_aes_key", type: "varchar(" + 120* weight_c + ")",  remarks: "数据加密密钥")   
            column(name: "extra_data", type: "longtext",  remarks: "扩展数据")   
            column(name: "enabled_flag", type: "tinyint",   defaultValue:"1",   remarks: "启用标识(1: 启用，0: 不启用)，默认启用")  {constraints(nullable:"false")}
            column(name: "tenant_id", type: "bigint",   defaultValue:"0",   remarks: "租户ID")  {constraints(nullable:"false")}
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "版本号")  {constraints(nullable:"false")}
        }
        addUniqueConstraint(columnNames:"mini_program_type,mini_program_code,tenant_id",tableName:"hiam_mini_program_config",constraintName: "hiam_mini_program_config_u2")
        addUniqueConstraint(columnNames:"mini_program_type,app_key,tenant_id",tableName:"hiam_mini_program_config",constraintName: "hiam_mini_program_config_u3")
    }
}
