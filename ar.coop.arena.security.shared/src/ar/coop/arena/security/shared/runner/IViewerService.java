package ar.coop.arena.security.shared.runner;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService2;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface IViewerService extends IService2 {

  ViewerFormData prepareCreate(ViewerFormData formData) throws ProcessingException;

  ViewerFormData create(ViewerFormData formData) throws ProcessingException;

  ViewerFormData load(ViewerFormData formData) throws ProcessingException;

  ViewerFormData store(ViewerFormData formData) throws ProcessingException;
}
