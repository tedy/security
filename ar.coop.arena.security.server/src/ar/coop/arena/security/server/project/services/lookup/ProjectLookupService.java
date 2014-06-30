package ar.coop.arena.security.server.project.services.lookup;

import org.eclipse.scout.rt.server.services.lookup.AbstractSqlLookupService;

import ar.coop.arena.security.shared.project.services.lookup.IProjectLookupService;

public class ProjectLookupService extends AbstractSqlLookupService implements IProjectLookupService {

  @Override
  protected String getConfiguredSqlSelect() {
    return "SELECT PROJECTID, NAME "
        //        + " ,PATH AS ICONID, ''AS tooltip, 'F0F0F0', '404040', 'NULL' as font, 1, 'NULL', 1 "
        + " FROM PROJECT "
        + "<key>  WHERE PROJECTID = :key </key> "
        + "<text> WHERE UPPER(NAME) LIKE UPPER(:text||'%') </text> "
        + "<all> </all> ";
  }
}
