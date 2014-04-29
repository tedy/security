package ar.coop.arena.security.client.ui.forms;

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

import ar.coop.arena.security.client.target.ItemForm;
import ar.coop.arena.security.client.target.TargetForm;
import ar.coop.arena.security.client.ui.forms.DesktopForm.MainBox.TargetsTreeField;
import ar.coop.arena.security.shared.services.DesktopFormData;
import ar.coop.arena.security.shared.services.IDesktopService;
import ar.coop.arena.security.shared.services.lookup.target.TargetLookupCall;

@FormData(value = DesktopFormData.class, sdkCommand = SdkCommand.CREATE)
public class DesktopForm extends AbstractForm {

  private Integer m_projectId;

  public DesktopForm() throws ProcessingException {
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
    return VIEW_ID_W;
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public TargetsTreeField getTargetsTreeField() {
    return getFieldByClass(TargetsTreeField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected boolean getConfiguredBorderVisible() {
      return false;
    }

    @Order(10.0)
    public class TargetsTreeField extends AbstractTreeBox<Long> {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Targets");
      }

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
        public class AddTargetMenu extends AbstractExtensibleMenu {

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("AddTarget");
          }

          @Override
          protected boolean getConfiguredEmptySpaceAction() {
            return true;
          }

          @Override
          protected boolean getConfiguredSingleSelectionAction() {
            return false;
          }

          @Override
          protected void execAction() throws ProcessingException {
            TargetForm form = new TargetForm();
            form.setProjectId(1l);
            form.startNew();
          }
        }

        @Order(20.0)
        public class AddItemMenu extends AbstractExtensibleMenu {

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("AddItem");
          }

          @Override
          protected void execAction() throws ProcessingException {
            ItemForm form = new ItemForm();
            form.setProjectId(1);
            String id = ((String) getSelectedNode().getPrimaryKey());
            form.setTargetId(new Integer(id.substring(4).trim()));
            form.startNew();
          }

          @Override
          protected void execPrepareAction() throws ProcessingException {
            String id = ((String) getSelectedNode().getPrimaryKey());
            boolean activate = id.startsWith("tgt_");
            setEnabled(activate);
          }
        }

        @Order(30.0)
        public class ModifyTargetMenu extends AbstractExtensibleMenu {

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("ModifyMenu");
          }

          @Override
          protected void execAction() throws ProcessingException {
            String id = ((String) getSelectedNode().getPrimaryKey());
            if (id.startsWith("tgt_")) {
              TargetForm form = new TargetForm();
              form.setTargetNr(new Long(id.substring(4).trim()));
              form.startModify();
            }
            else if (id.startsWith("it_")) {
              ItemForm form = new ItemForm();
              form.setItemNr(new Long(id.substring(3).trim()));
              form.startModify();
            }
          }
        }

      }
    }
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IDesktopService service = SERVICES.getService(IDesktopService.class);
      DesktopFormData formData = new DesktopFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);

    }
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Targets");
  }

  public void startView() throws ProcessingException {
    startInternal(new ViewHandler());
  }

  @FormData
  public Integer getProjectId() {
    return m_projectId;
  }

  @FormData
  public void setProjectId(Integer projectId) {
    m_projectId = projectId;
  }
}
