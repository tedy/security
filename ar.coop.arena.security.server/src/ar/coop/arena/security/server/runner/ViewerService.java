package ar.coop.arena.security.server.runner;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import ar.coop.arena.security.shared.runner.IViewerService;
import ar.coop.arena.security.shared.runner.ReadViewerPermission;
import ar.coop.arena.security.shared.runner.UpdateViewerPermission;
import ar.coop.arena.security.shared.runner.ViewerFormData;

public class ViewerService extends AbstractService implements IViewerService {

  @Override
  public ViewerFormData prepareCreate(ViewerFormData formData) throws ProcessingException {
    //TODO [tedy] business logic here
    return formData;
  }

  @Override
  public ViewerFormData create(ViewerFormData formData) throws ProcessingException {
    //TODO [tedy] business logic here.
    return formData;
  }

  @Override
  public ViewerFormData load(ViewerFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadViewerPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [tedy] business logic here
    return formData;
  }

  @Override
  public ViewerFormData store(ViewerFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateViewerPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [tedy] business logic here
    return formData;
  }
}
