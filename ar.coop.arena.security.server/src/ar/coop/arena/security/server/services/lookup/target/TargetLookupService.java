package ar.coop.arena.security.server.services.lookup.target;

import org.eclipse.scout.rt.server.services.lookup.AbstractSqlLookupService;

import ar.coop.arena.security.shared.services.lookup.target.ITargetLookupService;

public class TargetLookupService extends AbstractSqlLookupService implements ITargetLookupService {

  @Override
  protected String getConfiguredSqlSelect() {
    return "SELECT 'tgt_'||CAST (TARGETID AS CHAR), NAME "
        + " , '' AS icon, ''AS tooltip, 'F0F0F0', '404040', 'NULL' as font, 1, 'NULL', 1 "
        + " FROM  TARGET "
        //        + " WHERE PROJECTID=1"
        + " UNION "
        + " SELECT 'it_'||CAST(TARGETITEMID AS CHAR), CAST (PORT AS CHAR(5)) || '  ' || NAME "
        + " , '' AS icon, ''AS tooltip, 'F0F0F0', '404040', 'NULL' as font, 1, 'tgt_'||CAST (TARGETID AS CHAR), 1 "
        + " FROM TARGETITEM ";
  }
}
