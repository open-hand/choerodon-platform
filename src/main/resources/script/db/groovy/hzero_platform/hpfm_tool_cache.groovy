package script.db
databaseChangeLog(logicalFilePath: 'script/db/hpfm_tool_cache.groovy') {
    def weight_c = 1
    if(helper.isSqlServer()){
    weight_c = 2
    } else if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "hzero@hand-china.com", id: "hpfm_tool_cache-2020-10-20-version-3"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_tool_cache_s', startValue:"1")
        }
        createTable(tableName: "hpfm_tool_cache", remarks: "缓存工具配置") {
            column(name: "cache_code", type: "varchar(" + 60* weight_c + ")",  remarks: "缓存配置编码")  {constraints(nullable:"false")}  
            column(name: "cache_description", type: "varchar(" + 240* weight_c + ")",  remarks: "缓存配置描述")   
            column(name: "cache_id", type: "bigint", autoIncrement: true ,   remarks: "主键ID")  {constraints(primaryKey: true)}
            column(name: "cache_name", type: "varchar(" + 120* weight_c + ")",  remarks: "缓存配置名称")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}
            column(name: "request_method", type: "varchar(" + 30* weight_c + ")",   defaultValue:"GET",   remarks: "请求方式，值集： HPFM.REQUEST_METHOD")  {constraints(nullable:"false")}  
            column(name: "request_url", type: "varchar(" + 480* weight_c + ")",  remarks: "调用地址")  {constraints(nullable:"false")}  
        }
        addUniqueConstraint(columnNames:"cache_code",tableName:"hpfm_tool_cache",constraintName: "hpfm_tool_cache_u1")
    }
}
