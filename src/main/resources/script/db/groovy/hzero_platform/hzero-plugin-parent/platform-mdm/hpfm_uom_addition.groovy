package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_uom_addition.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2021-06-24-hpfm_uom_addition") {
        def weight = 1
        if (helper.isSqlServer()) {
            weight = 2
        } else if (helper.isOracle()) {
            weight = 3
        }
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'hpfm_uom_addition_s', startValue: "1")
        }
        createTable(tableName: "hpfm_uom_addition", remarks: "单位附加表") {
            column(name: "uom_id", type: "bigint", remarks: "hpfm_uom单位表的主键") { constraints(nullable: "false") }
            column(name: "conversion_rate", type: "decimal(20,6)", remarks: "基本单位换算比例") { constraints(nullable: "false") }
            column(name: "decimal_digits", type: "int", remarks: "小数位数") { constraints(nullable: "false") }
            column(name: "mantissa_mode_code", type: "varchar(" + 10 * weight + ")", remarks: "尾数处理模式,carry/进一法，round/四舍五入，truncation/去尾法") { constraints(nullable: "false") }
            column(name: "tenant_id", type: "bigint", remarks: "租户ID") { constraints(nullable: "false") }
            column(name: "created_by", type: "bigint", defaultValue: "-1", remarks: "创建人") { constraints(nullable: "false") }
            column(name: "last_updated_by", type: "bigint", defaultValue: "-1", remarks: "最近更新人") { constraints(nullable: "false") }
            column(name: "creation_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "创建时间") { constraints(nullable: "false") }
            column(name: "last_update_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "最近更新时间") { constraints(nullable: "false") }
            column(name: "object_version_number", type: "bigint", defaultValue: "1", remarks: "行版本号，用来处理锁") { constraints(nullable: "false") }
            column(name: "attribute1", type: "varchar(" + 150 * weight + ")", remarks: "")
            column(name: "attribute2", type: "varchar(" + 150 * weight + ")", remarks: "")
            column(name: "attribute3", type: "varchar(" + 150 * weight + ")", remarks: "")
            column(name: "attribute4", type: "varchar(" + 150 * weight + ")", remarks: "")
            column(name: "attribute5", type: "varchar(" + 150 * weight + ")", remarks: "")
            column(name: "attribute6", type: "varchar(" + 150 * weight + ")", remarks: "")
            column(name: "attribute7", type: "varchar(" + 150 * weight + ")", remarks: "")
            column(name: "attribute8", type: "varchar(" + 150 * weight + ")", remarks: "")
            column(name: "attribute9", type: "varchar(" + 150 * weight + ")", remarks: "")
            column(name: "attribute10", type: "varchar(" + 150 * weight + ")", remarks: "")
            column(name: "attribute11", type: "varchar(" + 150 * weight + ")", remarks: "")
            column(name: "attribute12", type: "varchar(" + 150 * weight + ")", remarks: "")
            column(name: "attribute13", type: "varchar(" + 150 * weight + ")", remarks: "")
            column(name: "attribute14", type: "varchar(" + 150 * weight + ")", remarks: "")
            column(name: "attribute15", type: "varchar(" + 150 * weight + ")", remarks: "")
            column(name: "uom_addition_id", type: "bigint", autoIncrement: true, remarks: "") { constraints(primaryKey: true) }
        }


        addUniqueConstraint(columnNames: "uom_id", tableName: "hpfm_uom_addition", constraintName: "hpfm_uom_addition_u1")
    }
    changeSet(author: "hzero@hand-china.com", id: "2021-10-14-hpfm_uom_addition") {
        dropNotNullConstraint (tableName: "hpfm_uom_addition", columnName: "conversion_rate", columnDataType: "decimal(20,6)")
    }
}