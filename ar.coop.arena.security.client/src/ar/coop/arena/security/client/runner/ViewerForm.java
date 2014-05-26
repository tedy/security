package ar.coop.arena.security.client.runner;

import org.eclipse.scout.commons.annotations.ConfigOperation;
import org.eclipse.scout.commons.annotations.ConfigProperty;
import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.dnd.TextTransferObject;
import org.eclipse.scout.commons.dnd.TransferObject;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.IDNDSupport;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.client.ui.form.fields.textfield.AbstractTextField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.client.runner.ViewerForm.MainBox.ViewerDNDField;
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
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
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
  protected boolean getConfiguredMaximizeEnabled() {
    return true;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Viewer");
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected boolean getConfiguredBorderVisible() {
      return false;
    }

    @Order(10.0)
    public class ViewerField extends AbstractHtmlField {

      @ConfigProperty(ConfigProperty.DRAG_AND_DROP_TYPE)
      @Order(10)
      // @Override
      protected int getConfiguredDragType() {
        return IDNDSupport.TYPE_TEXT_TRANSFER | IDNDSupport.TYPE_JAVA_ELEMENT_TRANSFER;
      }

      @ConfigOperation
      @Order(20)
      protected TransferObject execDrag(String iText) throws ProcessingException {
        System.out.println(iText);
        return null;
      }

      @Override
      protected int getConfiguredGridH() {
        return 10;
      }

      @Override
      protected boolean getConfiguredHtmlEditor() {
        return true;
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

    @Order(20.0)
    public class ViewerDNDField extends AbstractTextField {

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
      protected boolean getConfiguredMultilineText() {
        return true;
      }

      @Override
      protected boolean getConfiguredWrapText() {
        return true;
      }

      @ConfigProperty(ConfigProperty.DRAG_AND_DROP_TYPE)
      @Override
      protected int getConfiguredDragType() {
        return TYPE_TEXT_TRANSFER | TYPE_JAVA_ELEMENT_TRANSFER;
      }

      @Override
      protected TransferObject execDragRequest() {
        int beginIndex = getSelectionStart();
        int endIndex = getSelectionEnd();
        return new TextTransferObject(getValue().substring(beginIndex, endIndex));
      }

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

  public ViewerDNDField getViewerDNDField() {
    return getFieldByClass(ViewerDNDField.class);
  }

  public ViewerField getViewerField() {
    return getFieldByClass(ViewerField.class);
  }
}
