package ar.coop.arena.security.server.runner;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import ar.coop.arena.security.shared.runner.CreateRunToolPermission;
import ar.coop.arena.security.shared.runner.IRunToolService;
import ar.coop.arena.security.shared.runner.ReadRunToolPermission;
import ar.coop.arena.security.shared.runner.RunToolFormData;
import ar.coop.arena.security.shared.runner.UpdateRunToolPermission;

public class RunToolService extends AbstractService implements IRunToolService {

  @Override
  public RunToolFormData prepareCreate(RunToolFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateRunToolPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [tedy] business logic here
    return formData;
  }

  @Override
  public RunToolFormData create(RunToolFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateRunToolPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [tedy] business logic here.
    return formData;
  }

  @Override
  public RunToolFormData load(RunToolFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadRunToolPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [tedy] business logic here
    return formData;
  }

  @Override
  public RunToolFormData store(RunToolFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateRunToolPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [tedy] business logic here
    return formData;
  }
}
