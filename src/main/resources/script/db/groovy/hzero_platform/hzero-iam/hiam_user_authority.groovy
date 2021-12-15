package script.db
databaseChangeLog(logicalFilePath: 'script/db/hiam_user_authority.groovy') {
    def weight_c = 1
    if(helper.isOracle()){
        weight_c = 2
    }
    if(helper.isOracle()){
        weight_c = 3
    }
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hiam_user_authority") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_user_authority_s', startValue:"1")
        }
        createTable(tableName: "hiam_user_authority", remarks: "用户权限管理") {
            column(name: "authority_id", type: "bigint", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)}
            column(name: "user_id", type: "bigint",  remarks: "用户ID，HIAM.HIAM_USER")  {constraints(nullable:"false")}
            column(name: "tenant_id", type: "bigint",  remarks: "租户ID，HPFM.HPFM_TENANT")  {constraints(nullable:"false")}
            column(name: "authority_type_code", type: "varchar(" + 30 * weight + ")",  remarks: "权限类型代码，HIAM.USER_AUTHORITY_TYPE_CODE")  {constraints(nullable:"false")}
            column(name: "include_all_flag", type: "tinyint",   defaultValue:"0",   remarks: "是否包含所有标识")  {constraints(nullable:"false")}
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}
        }
        createIndex(tableName: "hiam_user_authority", indexName: "hiam_user_authority_n1") {
            column(name: "user_id")
            column(name: "authority_type_code")
            column(name: "tenant_id")
        }
    }
    changeSet(author: "xiaoyu.zhao@hand-china.com", id: "2019-05-10-hiam_user_authority") {
        dropIndex(tableName: "hiam_user_authority", indexName: "hiam_user_authority_n1")
        addUniqueConstraint(columnNames:"user_id, authority_type_code, tenant_id",tableName:"hiam_user_authority",constraintName: "hiam_user_authority_u1")
    }
    changeSet(author: 'jiangzhou.bo@hand-china.com', id: '2020-03-16-hiam_user_authority') {
        addColumn(tableName: 'hiam_user_authority') {
            column(name: 'data_source',  type: 'varchar(30)', defaultValue: "DEFAULT", remarks: '数据来源') {constraints(nullable: "false")}
        }
    }
    changeSet(author: "hzero@hand-china.com", id: "2020-05-28-hiam_user_authority"){
        update(tableName:'hiam_user_authority'){
            column(name:'authority_type_code', value:'INV_ORGANIZATION')
            where "authority_type_code='INVORG'"
        }
        update(tableName:'hiam_user_authority'){
            column(name:'authority_type_code', value:'PURCHASE_ORGANIZATION')
            where "authority_type_code='PURORG'"
        }
        update(tableName:'hiam_user_authority'){
            column(name:'authority_type_code', value:'PURCHASE_AGENT')
            where "authority_type_code='PURAGENT'"
        }
    }

    changeSet(author: "xiaoyu.zhao@hand-china.com", id: "hiam_user_authority-2021-01-08") {
        addColumn (tableName: "hiam_user_authority") {
            column (name: "menu_id", type: "bigint", remarks: "菜单Id，iam_menu.id", afterColumn: "include_all_flag", defaultValue: "-1") {
                constraints (nullable: "false")
            }
        }
        dropUniqueConstraint (tableName: "hiam_user_authority", constraintName: "hiam_user_authority_u1")
        addUniqueConstraint (tableName: "hiam_user_authority", columnNames: "user_id,authority_type_code,tenant_id,menu_id", constraintName: "hiam_user_authority_u1")
    }
    changeSet(author: "xiaoyu.zhao@hand-china.com", id: "hiam_user_authority-2021-03-08") {
        addColumn (tableName: "hiam_user_authority") {
            column (name: "role_id", type: "bigint", remarks: "角色Id，iam_role.id", defaultValue: "-1") {
                constraints (nullable: "false")
            }
        }
        dropUniqueConstraint (tableName: "hiam_user_authority", constraintName: "hiam_user_authority_u1")
        addUniqueConstraint (tableName: "hiam_user_authority", columnNames: "user_id,authority_type_code,tenant_id,menu_id,role_id", constraintName: "hiam_user_authority_u1")
    }
    changeSet(author: "Admin@hand-china.com", id: "hiam_user_authority-2021-06-22-version-3") {
        dropNotNullConstraint (tableName: "hiam_user_authority", columnName: "data_source", columnDataType: "varchar(" + 30* weight_c + ")")
        //mysql在删除非空约束时候会清空默认值
        if (helper.isMysql()) {
            addDefaultValue (tableName: "hiam_user_authority", columnName: "data_source", columnDataType: "varchar(" + 30* weight_c + ")", defaultValue: "DEFAULT")
        }
    }
}
