package ar.coop.arena.security.server.framework.services.lookup;

import org.eclipse.scout.rt.server.services.lookup.AbstractSqlLookupService;

import ar.coop.arena.security.shared.framework.services.lookup.IFrameworkLookupService;

public class FrameworkLookupService extends AbstractSqlLookupService implements IFrameworkLookupService {

  @Override
  protected String getConfiguredSqlSelect() {
    return "SELECT FRAMEWORKITEMID, DESCRIPTION "
        + " , '' AS ICONID, ''AS tooltip, 'F0F0F0', '404040', 'NULL' as font, 1, PARENTFRAMEWORKITEMID, 1 "
        + " FROM FRAMEWORKITEM FWI "
        + " INNER JOIN FRAMEWORK FW ON (FWI.FRAMEWORKID=FW.FRAMEWORKID)"
        + " INNER JOIN PROJECT P ON (FW.FRAMEWORKID=P.FRAMEWORKID AND FW.VERSION=P.VERSION) "
        + " WHERE PROJECTID = :key";
  }
}
