package ar.coop.arena.security.server.services.lookup.target;

import org.eclipse.scout.rt.server.services.lookup.AbstractSqlLookupService;

import ar.coop.arena.security.shared.services.lookup.target.ITargetTypeLookupService;

public class TargetTypeLookupService extends AbstractSqlLookupService implements ITargetTypeLookupService {

  @Override
  protected String getConfiguredSqlSelect() {
    return "SELECT TARGETTYPEID, NAME "
        + " FROM  TARGETTYPE "
        + "<key>  WHERE TARGETTYPEID = :key </key> "
        + "<text> WHERE UPPER(NAME) LIKE UPPER(:text||'%') </text> "
        + "<all> </all> ";
  }
}
