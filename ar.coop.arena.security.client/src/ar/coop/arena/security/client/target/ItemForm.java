package ar.coop.arena.security.client.target;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.client.target.ItemForm.MainBox.CancelButton;
import ar.coop.arena.security.client.target.ItemForm.MainBox.NameField;
import ar.coop.arena.security.client.target.ItemForm.MainBox.OkButton;
import ar.coop.arena.security.client.target.ItemForm.MainBox.PortField;
import ar.coop.arena.security.client.target.ItemForm.MainBox.RiskField;
import ar.coop.arena.security.shared.services.lookup.target.RiskLookupCall;
import ar.coop.arena.security.shared.target.IItemService;
import ar.coop.arena.security.shared.target.ItemFormData;
import ar.coop.arena.security.shared.target.UpdateItemPermission;

@FormData(value = ItemFormData.class, sdkCommand = SdkCommand.CREATE)
public class ItemForm extends AbstractForm {

  private Long itemNr;
  private Integer m_projectId;
  private Integer m_targetId;

  public ItemForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Item");
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  @FormData
  public Long getItemNr() {
    return itemNr;
  }

  @FormData
  public void setItemNr(Long itemNr) {
    this.itemNr = itemNr;
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public NameField getNameField() {
    return getFieldByClass(NameField.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  public PortField getPortField() {
    return getFieldByClass(PortField.class);
  }

  public RiskField getRiskField() {
    return getFieldByClass(RiskField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Override
    protected int getConfiguredGridW() {
      return 1;
    }

    @Order(10.0)
    public class RiskField extends AbstractSmartField<Integer> {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Risk");
      }

      @Override
      protected Class<? extends LookupCall> getConfiguredLookupCall() {
        return RiskLookupCall.class;

      }
    }

    @Order(20.0)
    public class PortField extends AbstractIntegerField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Port");
      }
    }

    @Order(30.0)
    public class NameField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Name");
      }
    }

    @Order(40.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(50.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      IItemService service = SERVICES.getService(IItemService.class);
      ItemFormData formData = new ItemFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateItemPermission());
    }

    @Override
    public void execStore() throws ProcessingException {
      IItemService service = SERVICES.getService(IItemService.class);
      ItemFormData formData = new ItemFormData();
      exportFormData(formData);
      formData = service.store(formData);
    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      IItemService service = SERVICES.getService(IItemService.class);
      ItemFormData formData = new ItemFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);
    }

    @Override
    public void execStore() throws ProcessingException {
      IItemService service = SERVICES.getService(IItemService.class);
      ItemFormData formData = new ItemFormData();
      exportFormData(formData);
      formData = service.create(formData);
    }
  }

  @FormData
  public Integer getProjectId() {
    return m_projectId;
  }

  @FormData
  public void setProjectId(Integer projectId) {
    m_projectId = projectId;
  }

  @FormData
  public Integer getTargetId() {
    return m_targetId;
  }

  @FormData
  public void setTargetId(Integer targetId) {
    m_targetId = targetId;
  }
}
