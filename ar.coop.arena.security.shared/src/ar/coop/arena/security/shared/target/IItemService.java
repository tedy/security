package ar.coop.arena.security.shared.target;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService2;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface IItemService extends IService2 {

  ItemFormData prepareCreate(ItemFormData formData) throws ProcessingException;

  ItemFormData create(ItemFormData formData) throws ProcessingException;

  ItemFormData load(ItemFormData formData) throws ProcessingException;

  ItemFormData store(ItemFormData formData) throws ProcessingException;

  public ItemFormData delete(ItemFormData formData) throws ProcessingException;
}
