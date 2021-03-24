package script.db
databaseChangeLog(logicalFilePath: 'script/db/hpfm_quick_entry.groovy') {
    def weight_c = 1
    if(helper.isSqlServer()){
    weight_c = 2
    } else if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "liguo.wang@hand-china.com", id: "hpfm_quick_entry-2021-01-07-version-4"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_quick_entry_s', startValue:"1")
        }
        createTable(tableName: "hpfm_quick_entry", remarks: "") {
            column(name: "entry_id", type: "bigint", autoIncrement: true , remarks: "主键ID")  {constraints(primaryKey: true)}
            column(name: "entry_code", type: "varchar(" + 60* weight_c + ")",  remarks: "编码")  {constraints(nullable:"false")}
            column(name: "entry_name", type: "varchar(" + 60* weight_c + ")",  remarks: "名称")  {constraints(nullable:"false")}
            column(name: "entry_type", type: "varchar(" + 60* weight_c + ")",  remarks: "快捷图标访问类型,HPFM.ENTRY_TYPE")
            column(name: "entry_value", type: "varchar(" + 480* weight_c + ")",  remarks: "值")  {constraints(nullable:"false")}
            column(name: "entry_method", type: "varchar(" + 30* weight_c + ")",  remarks: "请求方式，接口类型使用, HIAM.REQUEST_METHOD")
            column(name: "entry_parameter", type: "varchar(" + 480* weight_c + ")",  remarks: "请求参数，接口类型请求使用")
            column(name: "entry_sort", type: "int",   defaultValue:"0",   remarks: "排序")  {constraints(nullable:"false")}
            column(name: "enable_flag", type: "tinyint",   defaultValue:"1",   remarks: "是否启用,HPFM.ENABLED_FLAG")  {constraints(nullable:"false")}
            column(name: "tenant_id", type: "bigint",   defaultValue:"0",   remarks: "租户ID，hpfm_tenant.id")  {constraints(nullable:"false")}
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "创建人")  {constraints(nullable:"false")}
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "最近更新人")  {constraints(nullable:"false")}
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "创建时间")  {constraints(nullable:"false")}
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "最近更新时间")  {constraints(nullable:"false")}
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}
            column(name: "icon", type: "varchar(" + 128* weight_c + ")",  remarks: "图标")
            column(name: "entry_category", type: "varchar(" + 60* weight_c + ")",  remarks: "大类")  {constraints(nullable:"false")}
        }
        addUniqueConstraint(columnNames:"entry_code,tenant_id",tableName:"hpfm_quick_entry",constraintName: "hpfm_quick_entry_u1")
    }
}
