package ar.coop.arena.security.shared.framework;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService2;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface IUploadFrameworkService extends IService2 {

  UploadFrameworkFormData prepareCreate(UploadFrameworkFormData formData) throws ProcessingException;

  UploadFrameworkFormData create(UploadFrameworkFormData formData) throws ProcessingException;
}
