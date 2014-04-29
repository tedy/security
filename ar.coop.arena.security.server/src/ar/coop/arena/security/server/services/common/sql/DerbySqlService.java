package ar.coop.arena.security.server.services.common.sql;

import org.eclipse.scout.rt.services.common.jdbc.AbstractDerbySqlService;
import org.eclipse.scout.service.IService2;

public class DerbySqlService extends AbstractDerbySqlService implements IService2 {

  @Override
  protected String getConfiguredJdbcMappingName() {
//    return "jdbc:derby:/home/work/projects/arena/security/wks-scout-security-V2/db/security";
//    return "jdbc:derby:security/dbSecurity;create=true";
    return "jdbc:derby:security/dbSecurity";
  }
}
