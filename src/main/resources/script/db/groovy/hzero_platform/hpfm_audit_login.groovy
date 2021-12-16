package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_audit_login.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_audit_login") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_audit_login_s', startValue:"1")
        }
        createTable(tableName: "hpfm_audit_login", remarks: "") {
            column(name: "audit_id", type: "bigint", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "audit_type", type: "varchar(" + 30 * weight + ")",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "user_id", type: "bigint",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "login_name", type: "varchar(" + 128 * weight + ")",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "phone", type: "varchar(" + 32 * weight + ")",  remarks: "")   
            column(name: "email", type: "varchar(" + 128 * weight + ")",  remarks: "")   
            column(name: "tenant_id", type: "bigint",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "tenant_name", type: "varchar(" + 120 * weight + ")",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "login_date", type: "datetime",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "login_ip", type: "varchar(" + 120 * weight + ")",  remarks: "")   
            column(name: "login_client", type: "varchar(" + 32 * weight + ")",  remarks: "")   
            column(name: "login_platform", type: "varchar(" + 250 * weight + ")",   defaultValue:"0",   remarks: "")   
            column(name: "login_os", type: "varchar(" + 250 * weight + ")",   defaultValue:"1",   remarks: "")   
            column(name: "login_browser", type: "varchar(" + 250 * weight + ")",  remarks: "")   
            column(name: "login_status", type: "tinyint",   defaultValue:"1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "login_message", type: "varchar(" + 250 * weight + ")",  remarks: "")   
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

    }

    changeSet(author: "shuangfei.zhu@hand-china.com", id: "2019-10-10-hpfm_audit_login") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        addColumn(tableName: 'hpfm_audit_login') {
            column(name: "access_token", type: "varchar(" + 60 * weight + ")", remarks: "用户token")
        }
    }

    changeSet(author: "shuangfei.zhu@hand-china.com", id: "2019-11-14-hpfm_audit_login") {
        dropColumn(tableName: 'hpfm_audit_login', columnName: 'tenant_name')
    }

    changeSet(id: '2020-11-10-hpfm_audit_login-add_column', author: 'xiaowei.zhang@hand-china.com') {
        addColumn(tableName: "hpfm_audit_login") {
            column(name: "endpoint_type", type: "varchar(60)", remarks:"认证终端", afterColumn: "tenant_id") { constraints(nullable: "true") }
        }
        addColumn(tableName: "hpfm_audit_login") {
            column(name: "grant_type", type: "varchar(60)", remarks:"授权类型", afterColumn: "tenant_id") { constraints(nullable: "true") }
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hpfm_audit_login", columnName: "audit_type", remarks: "审计类型")
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hpfm_audit_login", columnName: "email", remarks: "邮箱")
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hpfm_audit_login", columnName: "login_client", remarks: "登录客户端")
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hpfm_audit_login", columnName: "login_date", remarks: "登录时间")
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hpfm_audit_login", columnName: "login_ip", remarks: "登录IP")
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hpfm_audit_login", columnName: "login_message", remarks: "登录信息")
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hpfm_audit_login", columnName: "login_name", remarks: "登录名")
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hpfm_audit_login", columnName: "login_os", remarks: "登录操作系统")
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hpfm_audit_login", columnName: "login_platform", remarks: "登录平台")
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hpfm_audit_login", columnName: "login_status", remarks: "登录状态")
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hpfm_audit_login", columnName: "phone", remarks: "电话")
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hpfm_audit_login", columnName: "tenant_id", remarks: "租户ID")
        }
        //mysql不支持setColumnRemarks命令
        if (!helper.isMysql()) {
            setColumnRemarks (tableName: "hpfm_audit_login", columnName: "user_id", remarks: "用户ID")
        }
    }
    changeSet(author: "hzero@hand-china.com", id: "hpfm_audit_login-2021-07-08-version-3") {
        createIndex (tableName: "hpfm_audit_login", indexName: "hpfm_audit_login_n1") {
            column (name: "user_id")
        }
        createIndex (tableName: "hpfm_audit_login", indexName: "hpfm_audit_login_n2") {
            column (name: "audit_type")
            column (name: "phone")
            column (name: "email")
        }
    }
}
