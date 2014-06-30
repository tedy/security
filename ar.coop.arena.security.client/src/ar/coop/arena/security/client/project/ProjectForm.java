package ar.coop.arena.security.client.project;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.client.project.ProjectForm.MainBox.AuthorField;
import ar.coop.arena.security.client.project.ProjectForm.MainBox.CancelButton;
import ar.coop.arena.security.client.project.ProjectForm.MainBox.CustomerField;
import ar.coop.arena.security.client.project.ProjectForm.MainBox.EndDateField;
import ar.coop.arena.security.client.project.ProjectForm.MainBox.NameField;
import ar.coop.arena.security.client.project.ProjectForm.MainBox.OkButton;
import ar.coop.arena.security.client.project.ProjectForm.MainBox.StartDateField;
import ar.coop.arena.security.client.ui.forms.DesktopForm;
import ar.coop.arena.security.shared.project.IProjectService;
import ar.coop.arena.security.shared.project.ProjectFormData;
import ar.coop.arena.security.shared.project.UpdateProjectPermission;

@FormData(value = ProjectFormData.class, sdkCommand = SdkCommand.CREATE)
public class ProjectForm extends AbstractForm {

  private Long projectNr;

  public ProjectForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Project");
  }

  public AuthorField getAuthorField() {
    return getFieldByClass(AuthorField.class);
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  @FormData
  public Long getProjectNr() {
    return projectNr;
  }

  @FormData
  public void setProjectNr(Long projectNr) {
    this.projectNr = projectNr;
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  public CustomerField getCustomerField() {
    return getFieldByClass(CustomerField.class);
  }

  public EndDateField getEndDateField() {
    return getFieldByClass(EndDateField.class);
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

  public StartDateField getStartDateField() {
    return getFieldByClass(StartDateField.class);
  }

  /*public WorkDirField getWorkDirField() {
    return getFieldByClass(WorkDirField.class);
  }*/

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
    public class CustomerField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Customer");
      }
    }

    @Order(30.0)
    public class StartDateField extends AbstractDateField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("StartDate");
      }
    }

    @Order(40.0)
    public class EndDateField extends AbstractDateField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("EndDate");
      }
    }

    @Order(50.0)
    public class AuthorField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Author");
      }
    }

    /*@Order(60.0)
    public class WorkDirField extends AbstractFileChooserField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("WorkDir");
      }
    }*/

    @Order(70.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(80.0)
    public class CancelButton extends AbstractCancelButton {
    }
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

    @Override
    public void execStore() throws ProcessingException {
      IProjectService service = SERVICES.getService(IProjectService.class);
      ProjectFormData formData = new ProjectFormData();
      exportFormData(formData);
      formData = service.store(formData);
    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      IProjectService service = SERVICES.getService(IProjectService.class);
      ProjectFormData formData = new ProjectFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);
    }

    @Override
    public void execStore() throws ProcessingException {
      IProjectService service = SERVICES.getService(IProjectService.class);
      ProjectFormData formData = new ProjectFormData();
      exportFormData(formData);
      formData = service.create(formData);

      DesktopForm form = getDesktop().findForm(DesktopForm.class);
      if (form != null) {
        form.setProjectId(formData.getProjectNr().intValue());
      }
    }
  }
}
