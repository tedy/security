package ar.coop.arena.security.client.project;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.tree.ITreeNode;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.treebox.AbstractTreeBox;
import org.eclipse.scout.rt.extension.client.ui.basic.tree.AbstractExtensibleTree;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.client.project.SelectProjectForm.MainBox.ProjectField;
import ar.coop.arena.security.client.ui.forms.DesktopForm;
import ar.coop.arena.security.shared.project.IProjectService;
import ar.coop.arena.security.shared.project.ProjectFormData;
import ar.coop.arena.security.shared.project.SelectProjectFormData;
import ar.coop.arena.security.shared.project.UpdateProjectPermission;
import ar.coop.arena.security.shared.project.services.lookup.ProjectLookupCall;

@FormData(value = SelectProjectFormData.class, sdkCommand = SdkCommand.CREATE)
public class SelectProjectForm extends AbstractForm {

  public SelectProjectForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Project");
  }

  /*public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }*/

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public ProjectField getProjectField() {
    return getFieldByClass(ProjectField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected boolean getConfiguredBorderVisible() {
      return false;
    }

    @Override
    protected boolean getConfiguredLabelVisible() {
      return false;
    }

    @Order(10.0)
    public class ProjectField extends AbstractTreeBox<Long> {

      @Override
      protected boolean getConfiguredAutoExpandAll() {
        return true;
      }

      @Override
      protected int getConfiguredGridH() {
        return 7;
      }

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }

      @Override
      protected Class<? extends LookupCall> getConfiguredLookupCall() {
        return ProjectLookupCall.class;
      }

      @Order(10.0)
      public class Tree extends AbstractExtensibleTree {

        @Override
        protected boolean getConfiguredDragEnabled() {
          return true;
        }

        @Override
        protected boolean getConfiguredMultiCheck() {
          return false;
        }

        @Override
        protected boolean getConfiguredRootHandlesVisible() {
          return false;
        }

        @Override
        protected void execNodeClick(ITreeNode node) throws ProcessingException {
          Long id = ((Long) getSelectedNode().getPrimaryKey());

          DesktopForm form = getDesktop().findForm(DesktopForm.class);
          if (form == null) {
            form = new DesktopForm();
            form.startView();
          }
          form.setProjectId(id.intValue());
          form.refresh();

          getForm().doClose();
        }
      }
    }

    /*@Order(20.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(30.0)
    public class CancelButton extends AbstractCancelButton {
    }*/
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      IProjectService service = SERVICES.getService(IProjectService.class);
      ProjectFormData formData = new ProjectFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateProjectPermission());
    }
  }
}
