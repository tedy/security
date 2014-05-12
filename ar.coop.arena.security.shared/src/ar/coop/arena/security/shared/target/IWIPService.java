package ar.coop.arena.security.shared.target;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService2;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface IWIPService extends IService2 {

  WIPFormData prepareCreate(WIPFormData formData) throws ProcessingException;

  WIPFormData create(WIPFormData formData) throws ProcessingException;

  WIPFormData load(WIPFormData formData) throws ProcessingException;

  WIPFormData store(WIPFormData formData) throws ProcessingException;
}
