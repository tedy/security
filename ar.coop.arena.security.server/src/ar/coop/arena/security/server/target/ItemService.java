package ar.coop.arena.security.server.target;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import ar.coop.arena.security.shared.target.CreateItemPermission;
import ar.coop.arena.security.shared.target.IItemService;
import ar.coop.arena.security.shared.target.ItemFormData;
import ar.coop.arena.security.shared.target.ReadItemPermission;
import ar.coop.arena.security.shared.target.UpdateItemPermission;

public class ItemService extends AbstractService implements IItemService {

  @Override
  public ItemFormData prepareCreate(ItemFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateItemPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    return formData;
  }

  @Override
  public ItemFormData create(ItemFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateItemPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    SQL.insert("" +
        "INSERT INTO TARGETITEM (PROJECTID, TARGETID, RISKID, PORT, PROTOCOL, NAME) " +
        "VALUES (:projectId, :targetId, :risk, :port, :protocol, :name)"
        , formData);
    return formData;
  }

  @Override
  public ItemFormData load(ItemFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadItemPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    SQL.selectInto("" +
        "SELECT RISKID, PORT, PROTOCOL, NAME " +
        "FROM   TARGETITEM " +
        "WHERE  TARGETITEMID = :itemNr " +
        "INTO   :risk, :port, :protocol, :name"
        , formData);
    return formData;
  }

  @Override
  public ItemFormData store(ItemFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateItemPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    SQL.update(
        "UPDATE TARGETITEM SET "
            + " RISKID = :risk,"
            + " PORT = :port, "
            + " PROTOCOL = :protocol," +
            "   NAME = :name " +
            "WHERE  TARGETITEMID = :itemNr", formData);
    return formData;
  }

  @Override
  public ItemFormData delete(ItemFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateItemPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    SQL.delete("" +
        "DELETE FROM TARGETITEM " +
        "WHERE  TARGETITEMID = :itemNr " +
        " AND TARGETID = :targetId " +
        " AND PROJECTID = :projectId"
        , formData);
    return formData;
  }
}
