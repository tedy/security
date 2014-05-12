package ar.coop.arena.security.client.target;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.client.target.WIPForm.MainBox.CancelButton;
import ar.coop.arena.security.client.target.WIPForm.MainBox.ContentField;
import ar.coop.arena.security.shared.target.IWIPService;
import ar.coop.arena.security.shared.target.UpdateWIPPermission;
import ar.coop.arena.security.shared.target.WIPFormData;

@FormData(value = WIPFormData.class, sdkCommand = SdkCommand.CREATE)
public class WIPForm extends AbstractForm {

  private Long nodeNr;
  private int m_nodeType;

  public WIPForm() throws ProcessingException {
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
  protected boolean getConfiguredMaximizeEnabled() {
    return true;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("WIP");
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  @Override
  protected void execFormActivated() throws ProcessingException {
    ((ModifyHandler) getHandler()).execLoad();
  }

  @FormData
  public Long getNodeNr() {
    return nodeNr;
  }

  @FormData
  public void setNodeNr(Long nodeNr) {
    this.nodeNr = nodeNr;
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public ContentField getContentField() {
    return getFieldByClass(ContentField.class);
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
    public class ContentField extends AbstractHtmlField {

      @Override
      protected int getConfiguredGridH() {
        return 10;
      }

      @Override
      protected boolean getConfiguredHtmlEditor() {
        return true;
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
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      IWIPService service = SERVICES.getService(IWIPService.class);
      WIPFormData formData = new WIPFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateWIPPermission());
    }

    @Override
    public void execStore() throws ProcessingException {
      IWIPService service = SERVICES.getService(IWIPService.class);
      WIPFormData formData = new WIPFormData();
      exportFormData(formData);
      formData = service.store(formData);
    }
  }

  @FormData
  public int getNodeType() {
    return m_nodeType;
  }

  @FormData
  public void setNodeType(int nodeType) {
    m_nodeType = nodeType;
  }
}
