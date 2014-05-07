package ar.coop.arena.security.shared.runner;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService2;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface IRunToolService extends IService2 {

  RunToolFormData prepareCreate(RunToolFormData formData) throws ProcessingException;

  RunToolFormData create(RunToolFormData formData) throws ProcessingException;

  RunToolFormData load(RunToolFormData formData) throws ProcessingException;

  RunToolFormData store(RunToolFormData formData) throws ProcessingException;
}
