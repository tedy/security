package ar.coop.arena.security.shared.project;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService2;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface IProjectService extends IService2 {

  ProjectFormData prepareCreate(ProjectFormData formData) throws ProcessingException;

  ProjectFormData create(ProjectFormData formData) throws ProcessingException;

  ProjectFormData load(ProjectFormData formData) throws ProcessingException;

  ProjectFormData store(ProjectFormData formData) throws ProcessingException;
}
