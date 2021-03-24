package script.db

databaseChangeLog(logicalFilePath: 'script/db/hiam_sec_grp_dcl.groovy') {
    changeSet(author: "hzero", id: "2020-03-13-hiam_sec_grp_dcl") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_sec_grp_dcl_s', startValue:"1")
        }
        createTable(tableName: "hiam_sec_grp_dcl", remarks: "安全组数据权限") {
            column(name: "sec_grp_dcl_id", type: "bigint", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "sec_grp_id", type: "bigint",  remarks: "安全组ID")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint",   defaultValue:"0",   remarks: "租户")  {constraints(nullable:"false")}  
            column(name: "authority_type_code", type: "varchar(" + 30 * weight + ")",  remarks: "权限类型代码，HIAM.AUTHORITY_TYPE_CODE")  {constraints(nullable:"false")}  
            column(name: "include_all_flag", type: "tinyint",   defaultValue:"0",   remarks: "是否包含所有标识")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"sec_grp_id,authority_type_code",tableName:"hiam_sec_grp_dcl",constraintName: "hiam_sec_grp_dcl_u1")
    }

    changeSet(author: "bo.he02@hand-china.com", id: "hiam_sec_grp_dcl-2021-01-25-version-2") {
        addColumn (tableName: "hiam_sec_grp_dcl") {
            column (name: "menu_id", type: "bigint", remarks: "菜单Id，iam_menu.id", defaultValue: "-1") {
                constraints (nullable: "false")
            }
        }
        dropUniqueConstraint (tableName: "hiam_sec_grp_dcl", constraintName: "hiam_sec_grp_dcl_u1")
        addUniqueConstraint (tableName: "hiam_sec_grp_dcl", columnNames: "sec_grp_id,authority_type_code,menu_id", constraintName: "hiam_sec_grp_dcl_u1")
    }

}