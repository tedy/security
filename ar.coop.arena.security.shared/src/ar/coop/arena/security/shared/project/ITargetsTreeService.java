package ar.coop.arena.security.shared.project;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService2;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface ITargetsTreeService extends IService2 {

  TargetsTreeFormData prepareCreate(TargetsTreeFormData formData) throws ProcessingException;

  TargetsTreeFormData create(TargetsTreeFormData formData) throws ProcessingException;

  TargetsTreeFormData load(TargetsTreeFormData formData) throws ProcessingException;

  TargetsTreeFormData store(TargetsTreeFormData formData) throws ProcessingException;
}
