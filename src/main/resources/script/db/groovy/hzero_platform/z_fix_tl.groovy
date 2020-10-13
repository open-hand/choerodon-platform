package script.db

databaseChangeLog(logicalFilePath: 'script/db/z_fix_tl.groovy') {
    changeSet(author: "scp",id: "2020-09-23-fix-tl"){
        sql("""
            INSERT INTO hpfm_rule_script_tl ( rule_script_id, lang, script_description, tenant_id ) SELECT
            rule_script_id,
            lang,
            script_description,
            tenant_id
            FROM
            ( SELECT rule_script_id, 'zh_CN' AS lang, script_description, tenant_id FROM hpfm_rule_script UNION SELECT rule_script_id, 'en_US', script_description, tenant_id FROM hpfm_rule_script ) t
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_rule_script_tl WHERE rule_script_id = t.rule_script_id and lang = t.lang);

            INSERT INTO hiam_user_group_tl ( user_group_id, lang, group_name, tenant_id ) SELECT
            user_group_id,
            lang,
            group_name,
            tenant_id
            FROM
            ( SELECT user_group_id, 'zh_CN' AS lang, group_name, tenant_id FROM hiam_user_group UNION SELECT user_group_id, 'en_US', group_name, tenant_id FROM hiam_user_group ) t
            WHERE NOT EXISTS ( SELECT 1 FROM hiam_user_group_tl WHERE user_group_id = t.user_group_id and lang = t.lang);

            INSERT INTO hpfm_permission_rule_tl ( rule_id, lang, rule_name, tenant_id ) SELECT
            rule_id,
            lang,
            rule_name,
            tenant_id
            FROM
            ( SELECT rule_id, 'zh_CN' AS lang, rule_name, tenant_id FROM hpfm_permission_rule UNION SELECT rule_id, 'en_US', rule_name, tenant_id FROM hpfm_permission_rule ) t
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_permission_rule_tl WHERE rule_id = t.rule_id and lang = t.lang);

            INSERT INTO hpfm_profile_tl ( profile_id, lang, description, tenant_id ) SELECT
            profile_id,
            lang,
            description,
            tenant_id
            FROM
            ( SELECT profile_id, 'zh_CN' AS lang, description, tenant_id FROM hpfm_profile UNION SELECT profile_id, 'en_US', description, tenant_id FROM hpfm_profile ) t
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_profile_tl WHERE profile_id = t.profile_id and lang = t.lang);

            INSERT INTO hpfm_config_tl ( config_id, lang, config_value, tenant_id ) (
            SELECT
            hc.config_id,
            'zh_CN',
            hc.config_value,
            hc.tenant_id
            FROM
            hpfm_config hc
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_config_tl hct WHERE hct.config_id = hc.config_id AND hct.lang = 'zh_CN' )
            );

            INSERT INTO hpfm_config_tl ( config_id, lang, config_value, tenant_id ) (
            SELECT
            hc.config_id,
            'en_US',
            hc.config_value,
            hc.tenant_id
            FROM
            hpfm_config hc
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_config_tl hct WHERE hct.config_id = hc.config_id AND hct.lang = 'en_US' )
            );

            INSERT INTO hiam_doc_type_tl ( doc_type_id, lang, doc_type_name, tenant_id ) (
            SELECT
            hc.doc_type_id,
            'zh_CN',
            hc.doc_type_name,
            hc.tenant_id
            FROM
            hiam_doc_type hc
            WHERE NOT EXISTS ( SELECT 1 FROM hiam_doc_type_tl hct WHERE hct.doc_type_id = hc.doc_type_id AND hct.lang = 'zh_CN' )
            );

            INSERT INTO hiam_doc_type_tl ( doc_type_id, lang, doc_type_name, tenant_id ) (
            SELECT
            hc.doc_type_id,
            'en_US',
            hc.doc_type_name,
            hc.tenant_id
            FROM
            hiam_doc_type hc
            WHERE NOT EXISTS ( SELECT 1 FROM hiam_doc_type_tl hct WHERE hct.doc_type_id = hc.doc_type_id AND hct.lang = 'en_US' )
            );

            INSERT INTO hiam_doc_type_dimension_tl ( dimension_id, lang, dimension_name, tenant_id ) (
            SELECT
            hc.dimension_id,
            'zh_CN',
            hc.dimension_name,
            hc.tenant_id
            FROM
            hiam_doc_type_dimension hc
            WHERE NOT EXISTS ( SELECT 1 FROM hiam_doc_type_dimension_tl hct WHERE hct.dimension_id = hc.dimension_id AND hct.lang = 'zh_CN' )
            );

            INSERT INTO hiam_doc_type_dimension_tl ( dimension_id, lang, dimension_name, tenant_id ) (
            SELECT
            hc.dimension_id,
            'en_US',
            hc.dimension_name,
            hc.tenant_id
            FROM
            hiam_doc_type_dimension hc
            WHERE NOT EXISTS ( SELECT 1 FROM hiam_doc_type_dimension_tl hct WHERE hct.dimension_id = hc.dimension_id AND hct.lang = 'en_US' )
            );

            INSERT INTO hiam_open_app_tl ( open_app_id, lang, app_name, organization_id ) (
            SELECT
            hc.open_app_id,
            'zh_CN',
            hc.app_name,
            hc.organization_id
            FROM
            hiam_open_app hc
            WHERE NOT EXISTS ( SELECT 1 FROM hiam_open_app_tl hct WHERE hct.open_app_id = hc.open_app_id AND hct.lang = 'zh_CN' )
            );

            INSERT INTO hiam_open_app_tl ( open_app_id, lang, app_name, organization_id ) (
            SELECT
            hc.open_app_id,
            'en_US',
            hc.app_name,
            hc.organization_id
            FROM
            hiam_open_app hc
            WHERE NOT EXISTS ( SELECT 1 FROM hiam_open_app_tl hct WHERE hct.open_app_id = hc.open_app_id AND hct.lang = 'en_US' )
            );

            INSERT INTO hpfm_datasource_driver_tl ( driver_id, lang, driver_name, tenant_id ) (
            SELECT
            hc.driver_id,
            'zh_CN',
            hc.driver_name,
            hc.tenant_id
            FROM
            hpfm_datasource_driver hc
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_datasource_driver_tl hct WHERE hct.driver_id = hc.driver_id AND hct.lang = 'zh_CN' )
            );

            INSERT INTO hpfm_datasource_driver_tl ( driver_id, lang, driver_name, tenant_id ) (
            SELECT
            hc.driver_id,
            'en_US',
            hc.driver_name,
            hc.tenant_id
            FROM
            hpfm_datasource_driver hc
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_datasource_driver_tl hct WHERE hct.driver_id = hc.driver_id AND hct.lang = 'en_US' )
            );

            INSERT INTO hpfm_datasource_tl ( datasource_id, lang, description, tenant_id ) (
            SELECT
            hc.datasource_id,
            'zh_CN',
            hc.description,
            hc.tenant_id
            FROM
            hpfm_datasource hc
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_datasource_tl hct WHERE hct.datasource_id = hc.datasource_id AND hct.lang = 'zh_CN' )
            );

            INSERT INTO hpfm_datasource_tl ( datasource_id, lang, description, tenant_id ) (
            SELECT
            hc.datasource_id,
            'en_US',
            hc.description,
            hc.tenant_id
            FROM
            hpfm_datasource hc
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_datasource_tl hct WHERE hct.datasource_id = hc.datasource_id AND hct.lang = 'en_US' )
            );

            INSERT INTO hpfm_gantt_tl ( gantt_id, lang, gantt_name, tenant_id ) (
            SELECT
            hc.gantt_id,
            'zh_CN',
            hc.gantt_name,
            hc.tenant_id
            FROM
            hpfm_gantt hc
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_gantt_tl hct WHERE hct.gantt_id = hc.gantt_id AND hct.lang = 'zh_CN' )
            );

            INSERT INTO hpfm_gantt_tl ( gantt_id, lang, gantt_name, tenant_id ) (
            SELECT
            hc.gantt_id,
            'en_US',
            hc.gantt_name,
            hc.tenant_id
            FROM
            hpfm_gantt hc
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_gantt_tl hct WHERE hct.gantt_id = hc.gantt_id AND hct.lang = 'en_US' )
            );

            INSERT INTO hpfm_gantt_tl ( gantt_id, lang, gantt_name, tenant_id ) (
            SELECT
            hc.gantt_id,
            'en_US',
            hc.gantt_name,
            hc.tenant_id
            FROM
            hpfm_gantt hc
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_gantt_tl hct WHERE hct.gantt_id = hc.gantt_id AND hct.lang = 'en_US' )
            );

            INSERT INTO hpfm_event_tl ( event_id, lang, event_description, tenant_id ) (
            SELECT
            he.event_id,
            'zh_CN',
            he.event_description,
            he.tenant_id
            FROM
            hpfm_event he
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_event_tl het WHERE het.event_id = he.event_id AND het.lang = 'zh_CN' )
            );

            INSERT INTO hpfm_event_tl ( event_id, lang, event_description, tenant_id ) (
            SELECT
            he.event_id,
            'en_US',
            he.event_description,
            he.tenant_id
            FROM
            hpfm_event he
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_event_tl het WHERE het.event_id = he.event_id AND het.lang = 'en_US' )
            );

            INSERT INTO hpfm_template_tl ( template_id, lang, template_name, tenant_id ) (
            SELECT
            ht.template_id,
            'zh_CN',
            ht.template_name,
            ht.tenant_id
            FROM
            hpfm_template ht
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_template_tl htt WHERE htt.template_id = ht.template_id AND htt.lang = 'zh_CN' )
            );

            INSERT INTO hpfm_template_tl ( template_id, lang, template_name, tenant_id ) (
            SELECT
            ht.template_id,
            'en_US',
            ht.template_name,
            ht.tenant_id
            FROM
            hpfm_template ht
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_template_tl htt WHERE htt.template_id = ht.template_id AND htt.lang = 'en_US' )
            );

            INSERT INTO hpfm_code_rule_tl ( rule_id, lang, rule_name, tenant_id ) SELECT
            rule_id,
            lang,
            rule_name,
            tenant_id
            FROM
            ( SELECT rule_id, 'zh_CN' AS lang, rule_name, tenant_id FROM hpfm_code_rule UNION SELECT rule_id, 'en_US', rule_name, tenant_id FROM hpfm_code_rule ) t
            WHERE NOT EXISTS ( SELECT 1 FROM hpfm_code_rule_tl WHERE rule_id = t.rule_id and lang = t.lang);

            INSERT INTO hiam_domain_assign ( domain_id, tenant_id, company_id ) (
            SELECT
            hd.domain_id,
            hd.tenant_id,
            hd.company_id
            FROM
            hiam_domain hd
            WHERE
            NOT EXISTS ( SELECT 1 FROM hiam_domain_assign hda WHERE hda.domain_id = hd.domain_id AND hda.tenant_id = hd.tenant_id )
            )
        """)
    }
}
