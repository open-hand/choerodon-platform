package script.db
databaseChangeLog(logicalFilePath: 'script/db/hpfm_tool_cache_tl.groovy') {
    def weight_c = 1
    if(helper.isSqlServer()){
    weight_c = 2
    } else if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "hzero@hand-china.com", id: "hpfm_tool_cache_tl-2020-10-20-version-2"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_tool_cache_tl_s', startValue:"1")
        }
        createTable(tableName: "hpfm_tool_cache_tl", remarks: "缓存工具配置多语言") {
            column(name: "cache_description", type: "varchar(" + 240* weight_c + ")",  remarks: "缓存工具配置描述")   
            column(name: "cache_id", type: "bigint",  remarks: "")  {constraints(nullable:"false")}
            column(name: "cache_name", type: "varchar(" + 120* weight_c + ")",  remarks: "缓存工具配置名称")  {constraints(nullable:"false")}  
            column(name: "lang", type: "varchar(" + 30* weight_c + ")",  remarks: "语言")  {constraints(nullable:"false")}  
        }
        addUniqueConstraint(columnNames:"cache_id,lang",tableName:"hpfm_tool_cache_tl",constraintName: "hpfm_tool_cache_tl_u1")
    }
}
