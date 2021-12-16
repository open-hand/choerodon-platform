package script.db
databaseChangeLog(logicalFilePath: 'script/db/hiam_black_white.groovy') {
    def weight_c = 1
    if(helper.isSqlServer()){
    weight_c = 2
    }
    if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "hzero@hand-china.com", id: "hiam_black_white-2021-07-20-version-1"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_black_white_s', startValue:"1")
        }
        createTable(tableName: "hiam_black_white", remarks: "黑白名单") {
            column(name: "black_white_id", type: "bigint",autoIncrement: true,    remarks: "主键ID")  {constraints(primaryKey: true)} 
            column(name: "tenant_id", type: "bigint",  remarks: "租户ID")  {constraints(nullable:"false")}  
            column(name: "type", type: "varchar(" + 30* weight_c + ")",  remarks: "黑白名单类型(WHITE/白名单;BLACK/黑名单)")  {constraints(nullable:"false")}  
            column(name: "ip", type: "varchar(" + 1000* weight_c + ")",  remarks: "ip列表")  {constraints(nullable:"false")}  
            column(name: "description", type: "varchar(" + 512* weight_c + ")",  remarks: "描述")   
            column(name: "enable_flag", type: "tinyint",   defaultValue:"1",   remarks: "是否启用(0/禁用;1启用)")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "创建人")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "最近更新人")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "创建时间")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "最近更新时间")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
        }
    }
}