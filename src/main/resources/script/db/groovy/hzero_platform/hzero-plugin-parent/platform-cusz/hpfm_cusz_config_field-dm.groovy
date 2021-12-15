package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_cusz_config_field.groovy') {

    def weight = 1
    if (helper.isSqlServer()) {
        weight = 2
    } else if (helper.isOracle()) {
        weight = 3
    }

    changeSet(author: "peng.yu01@hand-china.com", id: "2020-01-14_hpfm_cusz_config_field") {
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'hpfm_cusz_config_field_s', startValue: "1")
        }
        createTable(tableName: "hpfm_cusz_config_field") {
            column(name: "config_field_id", type: "bigint", autoIncrement: true, remarks: "表ID，主键，供其他表做外键") {
                constraints(primaryKey: true)
            }
            column(name: "tenant_id", type: "bigint", remarks: "租户ID") {
                constraints(nullable: false)
            }
            column(name: "unit_id", type: "bigint", remarks: "个性化单元ID") {
                constraints(nullable: false)
            }
            column(name: "model_id", type: "bigint", remarks: "模型ID") {
                constraints(nullable: false)
            }
            column(name: "field_id", type: "bigint", remarks: "字段ID") {
                constraints(nullable: false)
            }
            column(name: "field_name", type: "varchar(" + 255 * weight + ")", remarks: "字段名称")
            column(name: "field_editable", type: "smallint", defaultValue: "1", remarks: "是否可编辑") {
                constraints(nullable: false)
            }
            column(name: "field_required", type: "smallint", defaultValue: "0", remarks: "是否必输") {
                constraints(nullable: false)
            }
            column(name: "grid_seq", type: "int", remarks: "字段排序号")
            column(name: "field_alias", type: "varchar(" + 255 * weight + ")", remarks: "字段别名")
            column(name: "field_visible", type: "smallint", defaultValue: "1", remarks: "是否显示字段")
            column(name: "form_col", type: "smallint", remarks: "表单列序号")
            column(name: "form_row", type: "smallint", remarks: "表单行序号")
            column(name: "grid_fixed", type: "varchar(" + 30 * weight + ")", remarks: "表格冻结配置")
            column(name: "grid_width", type: "smallint", remarks: "表格列宽度")
            column(name: "render_options", type: "varchar(" + 30 * weight + ")", defaultValue: "WIDGET", remarks: "渲染类型") {
                constraints(nullable: false)
            }

            column(name: "object_version_number", type: "bigint", defaultValue: "1", remarks: "行版本号，用来处理锁") {
                constraints(nullable: false)
            }
            column(name: "created_by", type: "bigint", defaultValue: "-1")
            column(name: "creation_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "last_updated_by", type: "bigint", defaultValue: "-1")
            column(name: "last_update_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP")
        }
        addUniqueConstraint(columnNames: "unit_id,field_id,tenant_id", tableName: "hpfm_cusz_config_field", constraintName: "hpfm_cusz_config_field_U1")

        createTable(tableName: "hpfm_cusz_config_field_tl") {
            column(name: "config_field_id", type: "bigint", remarks: "个性化配置ID") {
                constraints(nullable: "false")
            }
            column(name: "lang", type: "varchar(8)", remarks: "租户ID") {
                constraints(nullable: "false")
            }
            column(name: "field_name", type: "varchar(255)", remarks: "多语言字段")
        }
    }
    changeSet(author: "xiangyu.qi01@hand-china.com", id: "2020-02-08_hpfm_cusz_config_field") {
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "label_col", type: "smallint", remarks: "label列数")
        }
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "wrapper_col", type: "smallint", remarks: "wrapper列数")
        }
    }

    changeSet(author: "xiangyu.qi01@hand-china.com", id: "2020-02-08_hpfm_cusz_config_field_index") {
        dropUniqueConstraint(tableName: 'hpfm_cusz_config_field', constraintName: 'hpfm_cusz_config_field_U1')
        createIndex(tableName: "hpfm_cusz_config_field", indexName: "hpfm_cusz_config_field_index1") {
            column(name: "unit_id")
            column(name: "field_id")
            column(name: "tenant_id")
        }
    }

    changeSet(author: "xiangyu.qi01@hand-china.com", id: "2020-02-11_hpfm_cusz_config_field_add_user_id") {
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "user_id", type: "bigint", remarks: "用户id", defaultValue: "-1")
        }
    }

    changeSet(author: "xiangyu.qi01@hand-china.com", id: "2020-03-04_hpfm_cusz_config_field_add_u1_code") {
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "field_code", type: "varchar(" + 255 * weight + ")", remarks: "字段编码")
        }
        addUniqueConstraint(columnNames: "unit_id, user_id ,field_id,field_code,tenant_id", tableName: "hpfm_cusz_config_field", constraintName: "hpfm_cusz_config_field_U1")
    }

    changeSet(author: "xiangyu.qi01@hand-china.com", id: "2020-03-20_hpfm_cusz_config_field_delete_not_null") {
        dropNotNullConstraint(tableName: "hpfm_cusz_config_field", columnName: "render_options", columnDataType: "varchar(" + 30 * weight + ")")
    }

    changeSet(author: "xiangyu.qi01@hand-china.com", id: "2020-03-25_hpfm_cusz_config_field_add_render_rule") {
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "render_rule", type: "varchar(4000)", remarks: "虚拟字段渲染规则")
        }
    }

    changeSet(author: "xiangyu.qi01@hand-china.com", id: "2020-05-27_hpfm_cusz_config_field_change_u1") {
        dropUniqueConstraint(tableName: 'hpfm_cusz_config_field', constraintName: 'hpfm_cusz_config_field_U1')
        addUniqueConstraint(columnNames: "unit_id, user_id ,model_id,field_code,tenant_id", tableName: "hpfm_cusz_config_field", constraintName: "hpfm_cusz_config_field_U1")
    }

    changeSet(author: "peng.yu01@hand-china.com", id: "2020-06-30_hpfm_cusz_config_field_add_where_option") {
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "where_option", type: "varchar(" + 30 * weight + ")", remarks: "where条件运算符")
        }
    }

    changeSet(author: "xiangyu.qi01@hand-china.com", id: "2020-07-27_hpfm_cusz_config_field_add_col_span") {
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "col_span", type: "int", remarks: "跨列配置")
        }
    }

    changeSet(author: "xiangyu.qi01@hand-china.com", id: "2020-07-28_hpfm_cusz_config_field_add_sorter") {
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "sorter", type: "smallint", remarks: "可否排序")
        }
    }

    changeSet(author: "peng.yu01@hand-china.com", id: "2020-10-27_hpfm_cusz_config_field_addColumns") {
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "row_span", type: "int", remarks: "跨行配置")
        }
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "bind_field", type: "varchar(" + 255 * weight + ")", remarks: "当前字段绑定的字段编码，多个字段使用逗号隔开")
        }
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "default_active", type: "tinyint", remarks: "默认激活控制,标签页/折叠面板单元用")
        }
    }

    changeSet(author: "xiangyu.qi01@hand-china.com", id: "2021-01-11-hpfm_cusz_hpfm_cusz_config_field-add_encrypt_flag") {
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "encrypt_flag", type: "smallint", remarks: "字段加密标识（-1-自动识别，0-禁用，1-启用）", defaultValue: "-1")
        }
    }

    changeSet(author: "peng.yu01@hand-china.com", id: "2021-03-22-hpfm_cusz_config_field-add_column_pro_default_flag") {
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "pro_default_flag", type: "tinyint", remarks: "高级默认值启用标识")
        }
    }

    changeSet(author: "yupeng@goning-link.com", id: "2021-04-12_hpfm_cusz_config_field_modify-defaultValue") {
        addDefaultValue(tableName: 'hpfm_cusz_config_field', columnName: 'default_active', defaultValue: '-1')
    }

    changeSet(author: "yupeng@going-link.com", id: "2021-04-19-hpfm_cusz_config_field-addColumns") {
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "display_field", type: "varchar(" + 120 * weight + ")", remarks: "值集显示字段编码")
        }
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "value_field", type: "varchar(" + 120 * weight + ")", remarks: "值集值字段编码")
        }
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "event_code", type: "varchar(" + 255 * weight + ")", remarks: "按钮单元字段事件编码")
        }
    }

    changeSet(author: "yupeng@going-link.com", id: "2021-04-20-hpfm_cusz_config_field-addColumns") {
        addColumn(tableName: 'hpfm_cusz_config_field') {
            column(name: "help_message", type: "varchar(" + 1200 * weight + ")", remarks: "帮助信息")
        }
        addColumn(tableName: 'hpfm_cusz_config_field_tl') {
            column(name: "help_message", type: "varchar(" + 1200 * weight + ")", remarks: "帮助信息")
        }
    }

    changeSet(id: '2021-06-10-hpfm_cusz_config_field_prefix', author: 'qixiangyu@going-link.com') {
        sql(splitStatements:"true",stripComments:"true"){
            "update hpfm_cusz_config_field set pro_default_flag = 0;"}
    }

    changeSet(id: '2021-06-10-hpfm_cusz_config_field', author: 'qixiangyu@going-link.com') {
        addDefaultValue(tableName: "hpfm_cusz_config_field", columnName: "pro_default_flag", defaultValue: "0")
        addNotNullConstraint(tableName: "hpfm_cusz_config_field", columnName: "pro_default_flag", columnDataType: "tinyint")
    }
    changeSet(author: "hzero@hand-china.com", id: "hpfm_cusz_config_field_tl-2021-07-08-version-2") {
        addUniqueConstraint (tableName: "hpfm_cusz_config_field_tl", columnNames: "config_field_id,lang", constraintName: "hpfm_cusz_config_field_tl_u1")
    }
}
