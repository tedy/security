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
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.client.target.TargetForm.MainBox.CancelButton;
import ar.coop.arena.security.client.target.TargetForm.MainBox.NameField;
import ar.coop.arena.security.client.target.TargetForm.MainBox.OkButton;
import ar.coop.arena.security.client.target.TargetForm.MainBox.TargetTypeField;
import ar.coop.arena.security.shared.services.lookup.target.TargetTypeLookupCall;
import ar.coop.arena.security.shared.target.ITargetService;
import ar.coop.arena.security.shared.target.TargetFormData;
import ar.coop.arena.security.shared.target.UpdateTargetPermission;

@FormData(value = TargetFormData.class, sdkCommand = SdkCommand.CREATE)
public class TargetForm extends AbstractForm {

  private Long targetNr;
  private Integer m_projectId;

  public TargetForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Target");
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  @FormData
  public Long getTargetNr() {
    return targetNr;
  }

  @FormData
  public void setTargetNr(Long targetNr) {
    this.targetNr = targetNr;
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

  public TargetTypeField getTargetTypeField() {
    return getFieldByClass(TargetTypeField.class);
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
    public class NameField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Name");
      }
    }

    @Order(20.0)
    public class TargetTypeField extends AbstractSmartField<Integer> {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("TargetType");
      }

      @Override
      protected Class<? extends LookupCall> getConfiguredLookupCall() {
        return TargetTypeLookupCall.class;

      }
    }

    @Order(30.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(40.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      ITargetService service = SERVICES.getService(ITargetService.class);
      TargetFormData formData = new TargetFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateTargetPermission());
    }

    @Override
    public void execStore() throws ProcessingException {
      ITargetService service = SERVICES.getService(ITargetService.class);
      TargetFormData formData = new TargetFormData();
      exportFormData(formData);
      formData = service.store(formData);
    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      ITargetService service = SERVICES.getService(ITargetService.class);
      TargetFormData formData = new TargetFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);
    }

    @Override
    public void execStore() throws ProcessingException {
      ITargetService service = SERVICES.getService(ITargetService.class);
      TargetFormData formData = new TargetFormData();
      exportFormData(formData);
      formData = service.create(formData);
    }
  }

  @FormData
  public Integer getProjectId() {
    return m_projectId;
  }

  @FormData
  public void setProjectId(Integer projectNr) {
    m_projectId = projectNr;
  }
}
