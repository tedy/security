package ar.coop.arena.security.client.runner;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.client.runner.RunToolForm.MainBox.CancelButton;
import ar.coop.arena.security.client.runner.RunToolForm.MainBox.CommandField;
import ar.coop.arena.security.client.runner.RunToolForm.MainBox.OkButton;
import ar.coop.arena.security.shared.runner.IRunToolService;
import ar.coop.arena.security.shared.runner.RunToolFormData;
import ar.coop.arena.security.shared.runner.UpdateRunToolPermission;

@FormData(value = RunToolFormData.class, sdkCommand = SdkCommand.CREATE)
public class RunToolForm extends AbstractForm {

  private Long toolNr;
  private String m_result;

  public RunToolForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("RunTool");
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  @FormData
  public Long getToolNr() {
    return toolNr;
  }

  @FormData
  public void setToolNr(Long toolNr) {
    this.toolNr = toolNr;
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  public CommandField getCommandField() {
    return getFieldByClass(CommandField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class CommandField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Command");
      }
    }

    @Order(20.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(30.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      IRunToolService service = SERVICES.getService(IRunToolService.class);
      RunToolFormData formData = new RunToolFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateRunToolPermission());
    }

    @Override
    public void execStore() throws ProcessingException {
      IRunToolService service = SERVICES.getService(IRunToolService.class);
      RunToolFormData formData = new RunToolFormData();
      exportFormData(formData);
      formData = service.store(formData);
    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      IRunToolService service = SERVICES.getService(IRunToolService.class);
      RunToolFormData formData = new RunToolFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);
    }

    @Override
    public void execStore() throws ProcessingException {
      IRunToolService service = SERVICES.getService(IRunToolService.class);
      RunToolFormData formData = new RunToolFormData();
      exportFormData(formData);
      formData = service.create(formData);

      IDesktop desktop = getDesktop();
      ViewerForm viewerForm = desktop.findForm(ViewerForm.class);
      if (viewerForm == null) {
        viewerForm = new ViewerForm();
        viewerForm.startModify();
      }
      viewerForm.getViewerField().setValue(formData.getResult());
      viewerForm.activate();
    }
  }

  @FormData
  public String getResult() {
    return m_result;
  }

  @FormData
  public void setResult(String result) {
    m_result = result;
  }
}
