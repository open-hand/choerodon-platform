package script.db
databaseChangeLog(logicalFilePath: 'script/db/hpfm_quick_entry_tl.groovy') {
    def weight_c = 1
    if(helper.isSqlServer()){
    weight_c = 2
    } else if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "liguo.wang@hand-china.com", id: "hpfm_quick_entry_tl-2021-01-08-version-1"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_quick_entry_tl_s', startValue:"1")
        }
        createTable(tableName: "hpfm_quick_entry_tl", remarks: "") {
            column(name: "entry_id", type: "bigint",  remarks: "hpfm_quick_entry.entry_id")  {constraints(nullable:"false")}
            column(name: "entry_name", type: "varchar(" + 60* weight_c + ")",  remarks: "hpfm_quick_entry.entry_name")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint",   defaultValue:"0",   remarks: "hpfm_tenant.id")  {constraints(nullable:"false")}
            column(name: "lang", type: "varchar(" + 16* weight_c + ")",  remarks: "语言")  {constraints(nullable:"false")}  
        }
    }
}
