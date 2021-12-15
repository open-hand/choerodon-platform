package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_cusz_field_con_line.groovy') {
    def weight = 1
    if (helper.isSqlServer()) {
        weight = 2
    } else if (helper.isOracle()) {
        weight = 3
    }
    changeSet(author: "peng.yu01@hand-china.com", id: "2020-02-05-hpfm_cusz_field_con_line") {
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'hpfm_cusz_field_con_line_S', startValue: "10001")
        }
        createTable(tableName: "hpfm_cusz_field_con_line") {
            column(name: "con_line_id", type: "bigint", autoIncrement: "true", startWith: "10001", remarks: "表ID，主键，供其他表做外键") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "hpfm_cusz_field_con_line_PK")
            }
            column(name: "con_header_id", type: "bigint", remarks: "条件头表主键") {
                constraints(nullable: "false")
            }
            column(name: "tenant_id", type: "bigint", defaultValue: "0", remarks: "租户id") {
                constraints(nullable: "false")
            }
            column(name: "con_code", type: "int", remarks: "条件编码") {
                constraints(nullable: "false")
            }
            column(name: "source_unit_id", type: "bigint", remarks: "源字段所属单元id") {
                constraints(nullable: "false")
            }
            column(name: "source_model_id", type: "bigint", remarks: "源字段所属模型id") {
                constraints(nullable: "false")
            }
            column(name: "source_field_id", type: "bigint", remarks: "源字段id") {
                constraints(nullable: "false")
            }
            column(name: "con_expression", type: "varchar(" + 12 * weight + ")", remarks: "条件运算符") {
                constraints(nullable: "false")
            }
            column(name: "target_type", type: "varchar(" + 30 * weight + ")", remarks: "目标字段类型，本单元、固定值")
            column(name: "target_unit_id", type: "bigint", remarks: "目标字段所属单元id")
            column(name: "target_model_id", type: "bigint", remarks: "目标字段所属模型id")
            column(name: "target_field_id", type: "bigint", remarks: "目标字段id")
            column(name: "target_value", type: "varchar(" + 225 * weight + ")", remarks: "目标字段值")
            column(name: "object_version_number", type: "bigint", defaultValue: "1", remarks: "行版本号，用来处理锁") {
                constraints(nullable: "false")
            }
            column(name: "created_by", type: "bigint", defaultValue: "-1")
            column(name: "creation_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "last_updated_by", type: "bigint", defaultValue: "-1")
            column(name: "last_update_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP")
        }
        addUniqueConstraint(columnNames: "con_header_id,con_code,tenant_id", tableName: "hpfm_cusz_field_con_line", constraintName: "hpfm_cusz_field_con_line_U1")
    }

    changeSet(author: "peng.yu01@hand-china.com", id: "2020-02-14-hpfm_cusz_field_con_line") {
        addColumn(tableName: 'hpfm_cusz_field_con_line') {
            column(name: "target_value_meaning", type: "varchar(" + 225 * weight + ")", remarks: "值集翻译")
        }
    }

    changeSet(author: "yupeng@going-link.com", id: "2021-06-03-hpfm_cusz_field_con_line") {
        addColumn(tableName: 'hpfm_cusz_field_con_line') {
            column(name: "source_type", type: "varchar(" + 225 * weight + ")", remarks: "条件行类型，CUZ_UNIT，\n" +
                    "CTX_TENANT_ID，\n" +
                    "CTX_ROLE_ID，\n" +
                    "CTX_USER_ID")
        }
    }

    changeSet(author: "yupeng@going-link.com", id: "2021-06-03-hpfm_cusz_field_con_line") {
        addColumn(tableName: 'hpfm_cusz_field_con_line') {
            column(name: "source_type", type: "varchar(" + 225 * weight + ")", remarks: "条件行类型，CUZ_UNIT，\n" +
                    "CTX_TENANT_ID，\n" +
                    "CTX_ROLE_ID，\n" +
                    "CTX_USER_ID")
        }
    }

    changeSet(author: "yupeng@going-link.com", id: "2021-06-03-hpfm_cusz_field_con_line-addDefaultValue") {
        addNotNullConstraint(tableName: "hpfm_cusz_field_con_line", columnName: "source_type", defaultNullValue: "CUZ_UNIT", columnDataType: "varchar(" + 225 * weight + ")")
    }

    changeSet(author: "yupeng@going-link.com", id: "2021-06-05-hpfm_cusz_field_con_line-addDefaultValue") {
        addDefaultValue(tableName: "hpfm_cusz_field_con_line", columnName: "source_model_id", defaultValueNumeric: "-1", columnDataType: "bigint")
        addDefaultValue(tableName: "hpfm_cusz_field_con_line", columnName: "source_unit_id", defaultValueNumeric: "-1", columnDataType: "bigint")
        addDefaultValue(tableName: "hpfm_cusz_field_con_line", columnName: "source_field_id", defaultValueNumeric: "-1", columnDataType: "bigint")
    }

    changeSet(author: "yupeng@going-link.com", id: "2021-06-09-hpfm_cusz_field_con_line-addDefaultValue") {
        if (helper.isSqlServer()){
            return
        }
        if (helper.isOracle() || helper.isPostgresql()) {
            sql {
                "comment on column hpfm_cusz_field_con_line.source_type is '条件行类型，CUZ_UNIT，CTX_TENANT_ID，CTX_ROLE_ID，CTX_USER_ID'"
            }
        } else {
            sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
                "ALTER TABLE hpfm_cusz_field_con_line MODIFY COLUMN source_type varchar(" + 225 * weight + ") DEFAULT 'CUZ_UNIT' NOT NULL COMMENT '条件行类型，CUZ_UNIT，CTX_TENANT_ID，CTX_ROLE_ID，CTX_USER_ID'"
            }
        }

    }
}