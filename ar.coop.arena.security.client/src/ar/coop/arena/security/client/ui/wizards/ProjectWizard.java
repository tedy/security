package ar.coop.arena.security.client.ui.wizards;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.wizard.AbstractWizard;
import org.eclipse.scout.rt.client.ui.wizard.AbstractWizardStep;
import org.eclipse.scout.rt.shared.TEXTS;

import ar.coop.arena.security.client.project.ProjectForm;

public class ProjectWizard extends AbstractWizard {

  public ProjectWizard() {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Project");
  }

  @Order(10.0)
  public class ProjectStep extends AbstractWizardStep<ProjectForm> {

    @Override
    protected String getConfiguredTitle() {
      return TEXTS.get("Project");
    }

    @Override
    protected void execActivate(int stepKind) throws ProcessingException {
      ProjectForm form = getForm();
      if (getForm() == null) {
        form = new ProjectForm();

        // Start the form by executing the form handler
        form.startWizardStep(this, ProjectForm.ModifyHandler.class);

        // Register the form on the step
        setForm(form);
      }

      // Set the form on the wizard 
      // This will automatically display it as inner form of the wizard container form
      getWizard().setWizardForm(form);
    }

    @Override
    @Order(20.0)
    protected void execDeactivate(int stepKind) throws ProcessingException {
      // Save the form if the user clicks next
      if (stepKind == STEP_NEXT) {
        ProjectForm form = getForm();
        if (form != null) {
          form.doSave();
        }
      }
    }
  }

  public ProjectStep getProjectStep() {
    return getStep(ProjectWizard.ProjectStep.class);
  }

}
