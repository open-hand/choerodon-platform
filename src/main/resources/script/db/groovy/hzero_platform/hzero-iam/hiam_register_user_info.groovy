package script.db

databaseChangeLog(logicalFilePath: 'script/db/hiam_register_user_info.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2020-05-18-hiam_register_user_info") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_register_user_info_s', startValue:"1")
        }
        createTable(tableName: "hiam_register_user_info", remarks: "注册用户信息表") {
            column(name: "id", type: "bigint", autoIncrement: true ,   remarks: "")  {
                constraints(primaryKey: true)
            }
            column(name: "user_id", type: "bigint",  remarks: "用户主键ID")
            column(name: "user_name", type: "varchar(" + 128 * weight + ")",  remarks: "用户名")
            column(name: "organization_name", type: "varchar(" + 128 * weight + ")",  remarks: "组织名")
            column(name: " organization_home_page", type: "varchar(" + 255 * weight + ")",  remarks: "组织官网地址")
            column(name: "organization_position", type: "varchar(" + 255 * weight + ")",  remarks: "组织职位")

            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}
        }
    }
}