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
}
