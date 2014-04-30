package ar.coop.arena.security.client.ui.forms;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.longfield.AbstractLongField;
import org.eclipse.scout.rt.client.ui.form.fields.splitbox.AbstractSplitBox;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.client.ui.form.fields.treebox.AbstractTreeBox;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.basic.tree.AbstractExtensibleTree;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.client.project.ProjectForm;
import ar.coop.arena.security.client.ui.forms.DesktopForm.MainBox.ScreenSplitField;
import ar.coop.arena.security.client.ui.forms.DesktopForm.MainBox.ScreenSplitField.LeftTabBox;
import ar.coop.arena.security.client.ui.forms.DesktopForm.MainBox.ScreenSplitField.LeftTabBox.FrameworkGrpBox;
import ar.coop.arena.security.client.ui.forms.DesktopForm.MainBox.ScreenSplitField.LeftTabBox.SectionsGrpBox;
import ar.coop.arena.security.client.ui.forms.DesktopForm.MainBox.ScreenSplitField.LeftTabBox.TargetsGrpBox;
import ar.coop.arena.security.client.ui.forms.DesktopForm.MainBox.ScreenSplitField.LeftTabBox.TargetsGrpBox.TargetsTreeField;
import ar.coop.arena.security.client.ui.forms.DesktopForm.MainBox.ScreenSplitField.RightTabBox;
import ar.coop.arena.security.client.ui.forms.DesktopForm.MainBox.ScreenSplitField.RightTabBox.WIPGrpBox;
import ar.coop.arena.security.client.ui.forms.DesktopForm.MainBox.ScreenSplitField.RightTabBox.WIPGrpBox.WIPLongField;
import ar.coop.arena.security.shared.services.DesktopFormData;
import ar.coop.arena.security.shared.services.IDesktopService;

@FormData(value = DesktopFormData.class, sdkCommand = SdkCommand.CREATE)
public class DesktopForm extends AbstractForm {

  public DesktopForm() throws ProcessingException {
    super();
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public RightTabBox getRightTabBox() {
    return getFieldByClass(RightTabBox.class);
  }

  public ScreenSplitField getScreenSplitField() {
    return getFieldByClass(ScreenSplitField.class);
  }

  public SectionsGrpBox getSectionsGrpBox() {
    return getFieldByClass(SectionsGrpBox.class);
  }

  public TargetsGrpBox getTargetsGrpBox() {
    return getFieldByClass(TargetsGrpBox.class);
  }

  public FrameworkGrpBox getFrameworkGrpBox() {
    return getFieldByClass(FrameworkGrpBox.class);
  }

  public LeftTabBox getLeftTabBox() {
    return getFieldByClass(LeftTabBox.class);
  }

  public TargetsTreeField getTargetsTreeField() {
    return getFieldByClass(TargetsTreeField.class);
  }

  public WIPGrpBox getWIPGrpBox() {
    return getFieldByClass(WIPGrpBox.class);
  }

  public WIPLongField getWIPLongField() {
    return getFieldByClass(WIPLongField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(20.0)
    public class ScreenSplitField extends AbstractSplitBox {

      @Override
      protected double getConfiguredSplitterPosition() {
        return 3.0;
      }

      @Order(10.0)
      public class LeftTabBox extends AbstractTabBox {

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Order(10.0)
        public class TargetsGrpBox extends AbstractGroupBox {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Targets");
          }

          @Order(10.0)
          public class TargetsTreeField extends AbstractTreeBox<Long> {

            @Override
            protected boolean getConfiguredLabelVisible() {
              return false;
            }

            @Order(10.0)
            public class Tree extends AbstractExtensibleTree {

              @Order(10.0)
              public class ModifyProjectMenu extends AbstractExtensibleMenu {

                @Override
                protected String getConfiguredText() {
                  return TEXTS.get("Modify");
                }

                @Override
                protected void execAction() throws ProcessingException {
                  ProjectForm form = new ProjectForm();
                  form.startModify();
                }
              }
            }
          }
        }

        @Order(20.0)
        public class FrameworkGrpBox extends AbstractGroupBox {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Framework");
          }
        }

        @Order(30.0)
        public class SectionsGrpBox extends AbstractGroupBox {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Sections");
          }
        }
      }

      @Order(20.0)
      public class RightTabBox extends AbstractTabBox {

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Order(10.0)
        public class WIPGrpBox extends AbstractGroupBox {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("WIP");
          }

          @Order(10.0)
          public class WIPLongField extends AbstractLongField {

            @Override
            protected boolean getConfiguredLabelVisible() {
              return false;
            }
          }
        }

        @Order(20.0)
        public class ViewerGrpBox extends AbstractGroupBox {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Viewer");
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
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_CENTER;
  }

  public void startView() throws ProcessingException {
    startInternal(new ViewHandler());
  }
}
