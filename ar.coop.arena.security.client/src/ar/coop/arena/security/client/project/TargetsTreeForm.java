package ar.coop.arena.security.client.project;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.treebox.AbstractTreeBox;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.basic.tree.AbstractExtensibleTree;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.client.project.TargetsTreeForm.MainBox.TargetsTreeField;
import ar.coop.arena.security.shared.project.ITargetsTreeService;
import ar.coop.arena.security.shared.project.TargetsTreeFormData;
import ar.coop.arena.security.shared.services.lookup.target.TargetLookupCall;

@FormData(value = TargetsTreeFormData.class, sdkCommand = SdkCommand.CREATE)
public class TargetsTreeForm extends AbstractForm {

  private Long targetsNr;

  public TargetsTreeForm() throws ProcessingException {
    super();
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_W;
  }

  @Override
  protected boolean getConfiguredMinimizeEnabled() {
    return true;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Targets");
  }

  @FormData
  public Long getTargetsNr() {
    return targetsNr;
  }

  @FormData
  public void setTargetsNr(Long targetsNr) {
    this.targetsNr = targetsNr;
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public void startView() throws ProcessingException {
    startInternal(new ViewHandler());
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public TargetsTreeField getTargetsTreeField() {
    return getFieldByClass(TargetsTreeField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class TargetsTreeField extends AbstractTreeBox<Long> {

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }

      @Override
      protected Class<? extends LookupCall> getConfiguredLookupCall() {
        return TargetLookupCall.class;
      }

      @Order(10.0)
      public class Tree extends AbstractExtensibleTree {

        @Order(10.0)
        public class EditMenu extends AbstractExtensibleMenu {

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("Edit");
          }

          @Override
          protected void execAction() throws ProcessingException {
            ProjectForm form = new ProjectForm();
            form.setProjectNr(new Long("1"));
            form.startModify();
          }
        }
      }
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      ITargetsTreeService service = SERVICES.getService(ITargetsTreeService.class);
      TargetsTreeFormData formData = new TargetsTreeFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
    }

    @Override
    public void execStore() throws ProcessingException {
      ITargetsTreeService service = SERVICES.getService(ITargetsTreeService.class);
      TargetsTreeFormData formData = new TargetsTreeFormData();
      exportFormData(formData);
      formData = service.store(formData);
    }
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      ITargetsTreeService service = SERVICES.getService(ITargetsTreeService.class);
      TargetsTreeFormData formData = new TargetsTreeFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
    }
  }
}
