package ar.coop.arena.security.server.project;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import ar.coop.arena.security.shared.project.ITargetsTreeService;
import ar.coop.arena.security.shared.project.ReadTargetsTreePermission;
import ar.coop.arena.security.shared.project.TargetsTreeFormData;

public class TargetsTreeService extends AbstractService implements ITargetsTreeService {

  @Override
  public TargetsTreeFormData prepareCreate(TargetsTreeFormData formData) throws ProcessingException {
    //TODO [Piojo] business logic here
    return formData;
  }

  @Override
  public TargetsTreeFormData create(TargetsTreeFormData formData) throws ProcessingException {
    //TODO [Piojo] business logic here.
    return formData;
  }

  @Override
  public TargetsTreeFormData load(TargetsTreeFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadTargetsTreePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [Piojo] business logic here
    return formData;
  }

  @Override
  public TargetsTreeFormData store(TargetsTreeFormData formData) throws ProcessingException {
    //TODO [Piojo] business logic here
    return formData;
  }
}
