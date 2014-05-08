package ar.coop.arena.security.client.runner;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.client.runner.ViewerForm.MainBox.CancelButton;
import ar.coop.arena.security.client.runner.ViewerForm.MainBox.OkButton;
import ar.coop.arena.security.client.runner.ViewerForm.MainBox.ViewerField;
import ar.coop.arena.security.shared.runner.IViewerService;
import ar.coop.arena.security.shared.runner.UpdateViewerPermission;
import ar.coop.arena.security.shared.runner.ViewerFormData;

@FormData(value = ViewerFormData.class, sdkCommand = SdkCommand.CREATE)
public class ViewerForm extends AbstractForm {

  public ViewerForm() throws ProcessingException {
    super();
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_CENTER;
  }

  @Override
  protected boolean getConfiguredModal() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Viewer");
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(20.0)
    public class ViewerField extends AbstractHtmlField {

      @Override
      protected int getConfiguredGridH() {
        return 10;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Viewer");
      }

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }

      @Override
      protected boolean getConfiguredScrollBarEnabled() {
        return true;
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
      IViewerService service = SERVICES.getService(IViewerService.class);
      ViewerFormData formData = new ViewerFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateViewerPermission());
    }

    @Override
    public void execStore() throws ProcessingException {
      IViewerService service = SERVICES.getService(IViewerService.class);
      ViewerFormData formData = new ViewerFormData();
      exportFormData(formData);
      formData = service.store(formData);
    }
  }

  public ViewerField getViewerField() {
    return getFieldByClass(ViewerField.class);
  }
}
