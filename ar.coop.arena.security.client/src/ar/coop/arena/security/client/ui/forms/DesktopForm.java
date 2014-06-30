package ar.coop.arena.security.client.ui.forms;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.tree.ITreeNode;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.treebox.AbstractTreeBox;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.basic.tree.AbstractExtensibleTree;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.client.runner.RunToolForm;
import ar.coop.arena.security.client.target.ItemForm;
import ar.coop.arena.security.client.target.TargetForm;
import ar.coop.arena.security.client.target.WIPForm;
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

      @Override
      protected void execPrepareLookup(LookupCall call, ITreeNode parent) throws ProcessingException {
        TargetLookupCall lookupCall = (TargetLookupCall) call;
        lookupCall.setProjectId(getProjectId());
      }

      @Order(10.0)
      public class Tree extends AbstractExtensibleTree {

        @Override
        protected boolean getConfiguredDragEnabled() {
          return true;
        }

        @Override
        protected boolean getConfiguredScrollToSelection() {
          return true;
        }

        /*@Override
        protected void execNodesSelected(TreeEvent e) throws ProcessingException {
          String id = ((String) getSelectedNode().getPrimaryKey());
          showWIP(id);
        }*/

        @Override
        protected void execNodeClick(ITreeNode node) throws ProcessingException {
          String id = ((String) node.getPrimaryKey());
          showWIP(id);
        }

        private void showWIP(String id) throws ProcessingException {
          int nodeType = 1;
          Long nodeNr = 0l;
          if (id.indexOf("_") == -1) {
            nodeType = 1;
            nodeNr = new Long(id);
          }
          else if (id.indexOf("_") >= 0) {
            nodeType = 2;
            nodeNr = new Long(id.substring(id.indexOf("_") + 1));
          }
          //          form.getContentField().setValue(formData.getResult());

          WIPForm form = getDesktop().findForm(WIPForm.class);
          if (form == null) {
            form = new WIPForm();
          }
          form.setNodeType(nodeType);
          form.setNodeNr(nodeNr);
          if (!form.isShowing()) {
            form.startModify();
          }
          form.activate();
          //          getForm().activate();
        }

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
            form.setProjectId(getProjectId());
            form.startNew();

            form.waitFor();
            if (form.isFormStored()) {
              refresh();
              activate();
            }
          }

          @Override
          protected void execPrepareAction() throws ProcessingException {
            DesktopForm desktopForm = (DesktopForm) getForm();
            boolean activate = (desktopForm.getProjectId() != null);
            setEnabled(activate);
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
            form.setProjectId(getProjectId());
            String id = ((String) getSelectedNode().getPrimaryKey());
            form.setTargetId(new Integer(id));
            form.startNew();

            form.waitFor();
            if (form.isFormStored()) {
              refresh();
            }
          }

          @Override
          protected void execPrepareAction() throws ProcessingException {
            String id = ((String) getSelectedNode().getPrimaryKey());
            boolean activate = (id.indexOf("_") == -1);
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
            editNode();
          }
        }

        @Order(40.0)
        public class ToolsMenu extends AbstractExtensibleMenu {

          @Override
          protected boolean getConfiguredEmptySpaceAction() {
            return true;
          }

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("ToolsMenu");
          }

          @Order(10.0)
          public class RunCommandMenu extends AbstractExtensibleMenu {

            @Override
            protected String getConfiguredText() {
              return TEXTS.get("RunCommand");
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
              RunToolForm form = new RunToolForm();
              form.startNew();
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

  public void editNode() throws ProcessingException {
    String id = getSelectedNode();
    if (!"".equals(id)) {
      IForm iform = new TargetForm();
      if (id.indexOf("_") == -1) {//Targets
        TargetForm form = (TargetForm) iform;
        form.setTargetNr(new Long(id));
        form.startModify();
        //iform = form;
      }
      else if (id.indexOf("_") >= 0) {//Items
        ItemForm form = new ItemForm();
        form.setItemNr(new Long(id.substring(id.indexOf("_") + 1)));
        form.startModify();
        iform = form;
      }

      iform.waitFor();
      if (iform.isFormStored()) {
        refresh();
        activate();
      }
    }
  }

  public String getSelectedNode() {
    TargetsTreeField tree = (TargetsTreeField) getFieldByClass(TargetsTreeField.class);
    String id = "";
    if (tree.getTree().getSelectedNode() != null) {
      id = (String) tree.getTree().getSelectedNode().getPrimaryKey();
    }
    return id;
  }

  public void refresh() throws ProcessingException {
    getTargetsTreeField().loadRootNode();
  }
}
