package ar.coop.arena.security.server.services.lookup;

import org.eclipse.scout.rt.server.services.lookup.AbstractSqlLookupService;

import ar.coop.arena.security.shared.services.lookup.IProjectLookupService;

public class ProjectLookupService extends AbstractSqlLookupService implements IProjectLookupService {

  @Override
  protected String getConfiguredSqlSelect() {
    return "SELECT PROJECTID, " +
        "       NAME, " +
        //        "       NAME, CUSTOMER," +
//        "       STARTDATE, ENDDATE," +
//        "       AUTHOR, WORKDIR," +
        "       FRAMEWORKID" +
        " FROM  PROJECT";
  }
}
