package ar.coop.arena.security.server.target;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import ar.coop.arena.security.shared.target.IWIPService;
import ar.coop.arena.security.shared.target.ReadWIPPermission;
import ar.coop.arena.security.shared.target.UpdateWIPPermission;
import ar.coop.arena.security.shared.target.WIPFormData;

public class WIPService extends AbstractService implements IWIPService {

  @Override
  public WIPFormData prepareCreate(WIPFormData formData) throws ProcessingException {
    //TODO [tedy] business logic here
    return formData;
  }

  @Override
  public WIPFormData create(WIPFormData formData) throws ProcessingException {
    //TODO [tedy] business logic here.
    return formData;
  }

  @Override
  public WIPFormData load(WIPFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadWIPPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    /*SQL.selectInto("SELECT CONTENT FROM TARGET " +
        "WHERE  TARGETID = :nodeNr INTO   :content", formData);*/
    SQL.selectInto("SELECT CONTENT FROM TARGETITEM "
        + "WHERE  TARGETITEMID = :nodeNr INTO   :content", formData);
    return formData;
  }

  @Override
  public WIPFormData store(WIPFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateWIPPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    SQL.update("UPDATE TARGETITEM SET CONTENT = :content "
        + "WHERE  TARGETITEMID = :nodeNr", formData);
    return formData;
  }
}
