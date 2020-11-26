package script.db

databaseChangeLog(logicalFilePath: 'script/db/iam_role_label.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2020-08-31-iam_role_label") {
        def weight = 1
        if (helper.isSqlServer()) {
            weight = 2
        } else if (helper.isOracle()) {
            weight = 3
        }
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: "iam_role_label_s", startValue: "1")
        }
        createTable(tableName: "iam_role_label", remarks: "") {
            column(name: "id", type: "bigint", autoIncrement: true, remarks: "") { constraints(primaryKey: true) }
            column(name: "role_id", type: "bigint", remarks: "角色的id") { constraints(nullable: "false") }
            column(name: "label_id", type: "bigint", remarks: "label的id") { constraints(nullable: "false") }
            column(name: "object_version_number", type: "bigint", remarks: "")
            column(name: "created_by", type: "bigint", remarks: "")
            column(name: "creation_date", type: "datetime", remarks: "")
            column(name: "last_updated_by", type: "bigint", remarks: "")
            column(name: "last_update_date", type: "datetime", remarks: "")
        }
    }
    changeSet(author: "hzero@hand-china.com", id: "2020-08-31-2-iam_role_label") {
        addUniqueConstraint(columnNames: "role_id,label_id", tableName: "iam_role_label", constraintName: "iam_role_label_u1")
    }

    changeSet(id: '2020-11-16-drop-iam-role-label', author: 'scp') {
        dropTable(tableName: 'iam_role_label')
    }
}