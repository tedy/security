package ar.coop.arena.security.client.ui.desktop;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ClientSyncJob;
import org.eclipse.scout.rt.client.ui.action.keystroke.AbstractKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractFormToolButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.ScoutInfoForm;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.desktop.AbstractExtensibleDesktop;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.ui.UserAgentUtility;

import ar.coop.arena.security.client.ClientSession;
import ar.coop.arena.security.client.project.ProjectForm;
import ar.coop.arena.security.client.project.SelectProjectForm;
import ar.coop.arena.security.client.runner.ViewerForm;
import ar.coop.arena.security.client.target.WIPForm;
import ar.coop.arena.security.client.ui.forms.DesktopForm;

public class Desktop extends AbstractExtensibleDesktop implements IDesktop {
  private static IScoutLogger logger = ScoutLogManager.getLogger(Desktop.class);

  public Desktop() {
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Security");
  }

  @Override
  protected boolean getConfiguredTrayVisible() {
    return true;
  }

  @Override
  protected void execOpened() throws ProcessingException {
    //If it is a mobile or tablet device, the DesktopExtension in the mobile plugin takes care of starting the correct forms.
    if (!UserAgentUtility.isDesktopDevice()) {
      return;
    }
    SelectProjectForm selectProjectForm = new SelectProjectForm();
    selectProjectForm.startModify();

    DesktopForm desktopForm = new DesktopForm();
    desktopForm.startView();

//    ViewerForm viewerForm = new ViewerForm();
//    viewerForm.startModify();
    WIPForm wipForm = new WIPForm();
    wipForm.startModify();
  }

  @Order(10.0)
  public class ProjectMenu extends AbstractExtensibleMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Project");
    }

    @Order(10.0)
    public class NewProjectMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("NewMenu");
      }

      @Override
      protected void execAction() throws ProcessingException {
        /*DefaultWizardContainerForm form = new DefaultWizardContainerForm(new ProjectWizard());
        form.startWizard();*/

        ProjectForm form = new ProjectForm();
        form.startNew();
      }
    }

    @Order(20.0)
    public class LoadProjectMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("LoadMenu");
      }

      @Override
      protected void execAction() throws ProcessingException {
        SelectProjectForm form = new SelectProjectForm();
        form.startModify();
      }

    }

    @Order(30.0)
    public class SaveProjectMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("SaveMenu");
      }
    }

    @Order(40.0)
    public class ExitMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("ExitMenu");
      }

      @Override
      public void execAction() throws ProcessingException {
        ClientSyncJob.getCurrentSession(ClientSession.class).stopSession();
      }
    }
  }

  @Order(20.0)
  public class FrameworksMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("FrameworksMenu");
    }

    @Order(10.0)
    public class AddMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("AddMenu");
      }
    }

    @Order(20.0)
    public class ModifyMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("ModifyMenu");
      }
    }

    @Order(30.0)
    public class RemoveMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("RemoveMenu");
      }
    }

    @Order(40.0)
    public class DownloadMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("DownloadMenu");
      }
    }

    @Order(50.0)
    public class UpdateMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("UpdateMenu");
      }
    }
  }

  @Order(30.0)
  public class TargetsMenu extends AbstractExtensibleMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("TargetsMenu");
    }

    @Order(10.0)
    public class AddMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("AddMenu");
      }
    }

    @Order(20.0)
    public class ModifyMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("ModifyMenu");
      }
    }

    @Order(30.0)
    public class RemoveMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("RemoveMenu");
      }
    }
  }

  @Order(40.0)
  public class ToolsMenu extends AbstractExtensibleMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ToolsMenu");
    }

    @Order(10.0)
    public class AddMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("AddMenu");
      }
    }

    @Order(20.0)
    public class ModifyMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("ModifyMenu");
      }
    }

    @Order(30.0)
    public class RemoveMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("RemoveMenu");
      }
    }
  }

  @Order(50.0)
  public class HelpMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("HelpMenu");
    }

    @Order(10.0)
    public class AboutMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("AboutMenu");
      }

      @Override
      public void execAction() throws ProcessingException {
        ScoutInfoForm form = new ScoutInfoForm();
        form.startModify();
      }
    }
  }

  @Order(10.0)
  public class WIPTool extends AbstractFormToolButton {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("WIP");
    }

    @Override
    protected void execAction() throws ProcessingException {
      IForm form = findForm(WIPForm.class);
      if (form == null) {
        form = new WIPForm();
        ((WIPForm) form).startModify();
      }
      form.activate();

      form = findForm(ViewerForm.class);
//      form.doClose();
    }
  }

  @Order(20.0)
  public class ViewerTool extends AbstractFormToolButton {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Viewer");
    }

    @Override
    protected void execAction() throws ProcessingException {
      ViewerForm form = findForm(ViewerForm.class);
      if (form == null) {
        form = new ViewerForm();
        form.startModify();
      }
      form.activate();
    }
  }

  @Order(10.0)
  public class RefreshOutlineKeyStroke extends AbstractKeyStroke {

    @Override
    protected String getConfiguredKeyStroke() {
      return "f5";
    }

    @Override
    protected void execAction() throws ProcessingException {
      if (getOutline() != null) {
        IPage page = getOutline().getActivePage();
        if (page != null) {
          page.reloadPage();
        }
      }
    }
  }

}
