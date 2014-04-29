package ar.coop.arena.security.server.target;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import ar.coop.arena.security.shared.target.CreateTargetPermission;
import ar.coop.arena.security.shared.target.ITargetService;
import ar.coop.arena.security.shared.target.ReadTargetPermission;
import ar.coop.arena.security.shared.target.TargetFormData;
import ar.coop.arena.security.shared.target.UpdateTargetPermission;

public class TargetService extends AbstractService implements ITargetService {

  @Override
  public TargetFormData prepareCreate(TargetFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateTargetPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [tedy] business logic here
    return formData;
  }

  @Override
  public TargetFormData create(TargetFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateTargetPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    SQL.insert("" +
        "INSERT INTO TARGET (PROJECTID, NAME, TARGETTYPEID) " +
        "VALUES (:projectId, :name, :targetType)"
        , formData);
    return formData;
  }

  @Override
  public TargetFormData load(TargetFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadTargetPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    SQL.selectInto("" +
        "SELECT NAME, TARGETTYPEID " +
        "FROM   TARGET " +
        "WHERE  TARGETID = :targetNr " +
        "INTO   :name, :targetType"
        , formData);
    return formData;
  }

  @Override
  public TargetFormData store(TargetFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateTargetPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    SQL.update(
        "UPDATE TARGET SET" +
            "   NAME = :name," +
            "   TARGETTYPEID = :targetType " +
            " WHERE  TARGETID = :targetNr", formData);
    return formData;
  }
}
