package ar.coop.arena.security.server.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.service.AbstractService;

import ar.coop.arena.security.shared.services.DesktopFormData;
import ar.coop.arena.security.shared.services.IDesktopService;

public class DesktopService extends AbstractService implements IDesktopService {

  @Override
  public DesktopFormData load(DesktopFormData formData) throws ProcessingException {
    //TODO [Piojo] Auto-generated method stub.
    return formData;

  }

  @Override
  public Object[][] getProjectTableData() throws ProcessingException {
    return SQL.select("" +
        "SELECT PROJECTID, " +
        "       NAME, CUSTOMER," +
        "       STARTDATE, ENDDATE," +
        "       AUTHOR, WORKDIR," +
        "       FRAMEWORKID" +
        " FROM  PROJECT");
  }
}
