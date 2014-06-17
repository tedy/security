package ar.coop.arena.security.shared.framework;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService2;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface IFrameworksService extends IService2 {

  FrameworksFormData load(FrameworksFormData formData) throws ProcessingException;

  FrameworksFormData store(FrameworksFormData formData) throws ProcessingException;

  public Object[][] loadFrameworksFromFileSystem(Integer ProjectId) throws ProcessingException;
}
