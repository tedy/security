package ar.coop.arena.security.shared.target;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService2;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface ITargetService extends IService2 {

  TargetFormData prepareCreate(TargetFormData formData) throws ProcessingException;

  TargetFormData create(TargetFormData formData) throws ProcessingException;

  TargetFormData load(TargetFormData formData) throws ProcessingException;

  TargetFormData store(TargetFormData formData) throws ProcessingException;
}
