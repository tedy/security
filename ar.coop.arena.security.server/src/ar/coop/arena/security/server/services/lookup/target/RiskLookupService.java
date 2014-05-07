package ar.coop.arena.security.server.services.lookup.target;

import org.eclipse.scout.rt.server.services.lookup.AbstractSqlLookupService;

import ar.coop.arena.security.shared.services.lookup.target.IRiskLookupService;

public class RiskLookupService extends AbstractSqlLookupService implements IRiskLookupService {

  @Override
  protected String getConfiguredSqlSelect() {
    return "SELECT RISKID, DESCRIPTION "
        + " ,PATH AS ICONID, ''AS tooltip, 'F0F0F0', '404040', 'NULL' as font, 1, 'NULL', 1 "
        + " FROM RISK LEFT JOIN ICONS ON (RISK.ICONID=ICONS.ICONID) "
        + "<key>  WHERE RISKID = :key </key> "
        + "<text> WHERE UPPER(DESCRIPTION) LIKE UPPER(:text||'%') </text> "
        + "<all> </all> ";
  }
}
