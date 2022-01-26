package script.db

databaseChangeLog(logicalFilePath: 'script/db/z_fix_tl.groovy') {
    changeSet(author: "scp",id: "2020-09-23-fix-tl"){
        sql("""
            UPDATE hpfm_bank_tl hbt
            SET hbt.tenant_id = ( SELECT hb.tenant_id FROM hpfm_bank hb WHERE hb.bank_id = hbt.bank_id)
            WHERE hbt.bank_id IN (SELECT bank_id FROM hpfm_bank);

            UPDATE hpfm_calendar_hldy_detail hbt
            SET hbt.tenant_id = ( SELECT hb.tenant_id FROM hpfm_calendar hb WHERE hb.calendar_id = hbt.calendar_id )
            WHERE hbt.calendar_id IN (SELECT calendar_id FROM hpfm_calendar);
        """)
    }
    changeSet(author: "scp",id: "2021-12-15-fix-1-8"){
        sql("""
            UPDATE hpfm_dashboard_role_card hdrc
            SET hdrc.w = (
            SELECT
            hdc.w
            FROM
            hpfm_dashboard_card hdc
            WHERE
            hdrc.card_id = hdc.id
            ),
            hdrc.h = (
            SELECT
            hdc.h
            FROM
            hpfm_dashboard_card hdc
            WHERE
            hdrc.card_id = hdc.id
            );

            UPDATE hiam_sec_grp_acl_dashboard hsgad
            SET hsgad.w = (
            SELECT
            hdc.w
            FROM
            hpfm_dashboard_card hdc
            WHERE
            hsgad.card_id = hdc.id
            ),
            hsgad.h = (
            SELECT
            hdc.h
            FROM
            hpfm_dashboard_card hdc
            WHERE
            hsgad.card_id = hdc.id
            );
            
            update hpfm_config set config_value = 'iceflow' where config_code = 'MENU_LAYOUT_THEME' and config_value = 'theme1';

            update hpfm_config set config_value = 'peacock' where config_code = 'MENU_LAYOUT_THEME' and (config_value = 'theme2' or config_value = 'color' or config_value = 'default');

            update hpfm_config set config_value = 'aurora' where config_code = 'MENU_LAYOUT_THEME' and config_value = 'theme3';

            update hiam_user_config set menu_layout_theme = 'iceflow' where menu_layout_theme = 'theme1';

            update hiam_user_config set menu_layout_theme = 'peacock' where menu_layout_theme = 'theme2' or menu_layout_theme = 'color' or menu_layout_theme = 'default';

            update hiam_user_config set menu_layout_theme = 'aurora' where menu_layout_theme = 'theme3';
        """)
    }

    changeSet(author: "scp",id: "2022-01-26-fix-1-8"){
        sql("""
           DELETE FROM hpfm_lov_value WHERE lov_code = 'HPFM.CUST.FIELD_COND_REALTION' AND `VALUE` = '~AFTER';
        """)
    }
}
