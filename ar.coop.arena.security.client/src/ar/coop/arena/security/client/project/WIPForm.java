package ar.coop.arena.security.client.project;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;

import ar.coop.arena.security.client.project.WIPForm.MainBox.WIPField;
import ar.coop.arena.security.shared.project.WIPFormData;

@FormData(value = WIPFormData.class, sdkCommand = SdkCommand.CREATE)
public class WIPForm extends AbstractForm {

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
  protected String getConfiguredTitle() {
    return TEXTS.get("WIP");
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public WIPField getWIPField() {
    return getFieldByClass(WIPField.class);
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
    public class WIPField extends AbstractStringField {

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }
    }
  }

  public class ModifyHandler extends AbstractFormHandler {
  }
}
