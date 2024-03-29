package script.db

databaseChangeLog(logicalFilePath: 'script/db/iam_menu_tl.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-iam_menu_tl") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        createTable(tableName: "iam_menu_tl", remarks: "菜单多语言表") {
            column(name: "lang", type: "varchar(" + 16 * weight + ")",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "id", type: "bigint",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "name", type: "varchar(" + 64 * weight + ")",  remarks: "菜单名")   

        }

        addUniqueConstraint(columnNames:"lang,id",tableName:"iam_menu_tl",constraintName: "iam_menu_tl_pk")
    }
    changeSet(author: "hzero@hand-china.com", id: "2020-06-11-iam_menu_tl") {
        addColumn(tableName: 'iam_menu_tl') {
            column(name: "h_tenant_id", type: "bigint", defaultValue: "0", remarks: "租户ID,hpfm_tenant.tenant_id") {
                constraints(nullable: "false")
            }
        }
    }
    changeSet(author: 'hzero@hand-china.com', id: '2020-12-15-iam_menu_tl') {
        // 重建索引
        dropUniqueConstraint(tableName:"iam_menu_tl",constraintName: "iam_menu_tl_pk")
        addUniqueConstraint(columnNames:"id,lang",tableName:"iam_menu_tl",constraintName: "iam_menu_tl_u1")
    }

    changeSet(author: "hzero@hand-china.com", id: "2020-12-23-iam_menu_tl"){
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        modifyDataType(tableName: "iam_menu_tl", columnName: 'name', newDataType: "varchar(" + 128 * weight + ")")
    }
}
