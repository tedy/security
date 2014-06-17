package ar.coop.arena.security.server.services.lookup.target;

import org.eclipse.scout.rt.server.services.lookup.AbstractSqlLookupService;

import ar.coop.arena.security.shared.services.lookup.target.ITargetLookupService;

public class TargetLookupService extends AbstractSqlLookupService implements ITargetLookupService {

  @Override
  protected String getConfiguredSqlSelect() {
    return "SELECT TRIM(CAST(TARGETID AS CHAR(7))), NAME "
        + " , '' AS ICONID, ''AS tooltip, 'F0F0F0', '404040', 'NULL' as font, 1, 'NULL', 1 "
        + " FROM  TARGET "
        + " WHERE PROJECTID = :projectId"
        + " UNION "
        + " SELECT TRIM(CAST(TARGETID AS CHAR(7)))||'_'||TRIM(CAST(TARGETITEMID AS CHAR(7))), CAST (PORT AS CHAR(5)) || PROTOCOL || '  ' || NAME "
        + " , PATH AS ICONID, ''AS tooltip, 'F0F0F0', '404040', 'NULL' as font, 1, TRIM(CAST(TARGETID AS CHAR(7))), 1 "
        + " FROM TARGETITEM "
        + " LEFT JOIN RISK ON (TARGETITEM.RISKID=RISK.RISKID) "
        + " LEFT JOIN ICONS ON (RISK.ICONID=ICONS.ICONID)"
        + " WHERE PROJECTID = :projectId";
  }
}
