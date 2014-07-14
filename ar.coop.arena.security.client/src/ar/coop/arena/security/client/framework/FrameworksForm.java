package ar.coop.arena.security.client.framework;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.client.framework.FrameworksForm.MainBox.AddButton;
import ar.coop.arena.security.client.framework.FrameworksForm.MainBox.CancelButton;
import ar.coop.arena.security.client.framework.FrameworksForm.MainBox.DownloadButton;
import ar.coop.arena.security.client.framework.FrameworksForm.MainBox.FrameworksField;
import ar.coop.arena.security.client.framework.FrameworksForm.MainBox.ModifyButton;
import ar.coop.arena.security.client.framework.FrameworksForm.MainBox.OkButton;
import ar.coop.arena.security.client.framework.FrameworksForm.MainBox.RemoveButton;
import ar.coop.arena.security.client.framework.FrameworksForm.MainBox.UpdateBtButton;
import ar.coop.arena.security.client.ui.forms.DesktopForm;
import ar.coop.arena.security.shared.framework.FrameworksFormData;
import ar.coop.arena.security.shared.framework.IFrameworksService;

@FormData(value = FrameworksFormData.class, sdkCommand = SdkCommand.CREATE)
public class FrameworksForm extends AbstractForm {

  private Long frameworkNr;
  private Integer m_projectId;
  private String m_selectedFile;

  public FrameworksForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Frameworks");
  }

  @FormData
  public Long getFrameworkNr() {
    return frameworkNr;
  }

  @FormData
  public void setFrameworkNr(Long frameworkNr) {
    this.frameworkNr = frameworkNr;
  }

  @FormData
  public Integer getProjectId() {
    return m_projectId;
  }

  @FormData
  public void setProjectId(Integer projectId) {
    m_projectId = projectId;
  }

  @FormData
  public String getSelectedFile() {
    return m_selectedFile;
  }

  @FormData
  public void setSelectedFile(String selectedFile) {
    m_selectedFile = selectedFile;
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public AddButton getAddButton() {
    return getFieldByClass(AddButton.class);
  }

  public DownloadButton getDownloadButton() {
    return getFieldByClass(DownloadButton.class);
  }

  public FrameworksField getFrameworksField() {
    return getFieldByClass(FrameworksField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  public ModifyButton getModifyButton() {
    return getFieldByClass(ModifyButton.class);
  }

  public RemoveButton getRemoveButton() {
    return getFieldByClass(RemoveButton.class);
  }

  public UpdateBtButton getUpdateBtButton() {
    return getFieldByClass(UpdateBtButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected boolean getConfiguredBorderVisible() {
      return false;
    }

    @Override
    protected boolean getConfiguredExpandable() {
      return true;
    }

    @Override
    protected boolean getConfiguredGridUseUiWidth() {
      return true;
    }

    @Override
    protected boolean getConfiguredLabelVisible() {
      return false;
    }

    @Override
    protected boolean getConfiguredScrollable() {
      return true;
    }

    @Order(10.0)
    public class FrameworksField extends AbstractTableField<FrameworksField.Table> {

      @Override
      protected int getConfiguredGridH() {
        return 10;
      }

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }

      @Override
      protected void execInitField() throws ProcessingException {
        IFrameworksService service = SERVICES.getService(IFrameworksService.class);
        getTable().addRowsByMatrix(service.loadFrameworksFromFileSystem(null));
      }

      @Override
      protected boolean execIsSaveNeeded() throws ProcessingException {
        return true;
      }

      @Order(10.0)
      public class Table extends AbstractExtensibleTable {

        public InfoColumn getInfoColumn() {
          return getColumnSet().getColumnByClass(InfoColumn.class);
        }

        public VersionColumn getVersionColumn() {
          return getColumnSet().getColumnByClass(VersionColumn.class);
        }

        public FileNameColumn getFileNameColumn() {
          return getColumnSet().getColumnByClass(FileNameColumn.class);
        }

        @Override
        protected boolean getConfiguredAutoResizeColumns() {
          return true;
        }

        public AuthorColumn getAuthorColumn() {
          return getColumnSet().getColumnByClass(AuthorColumn.class);
        }

        public NameColumn getNameColumn() {
          return getColumnSet().getColumnByClass(NameColumn.class);
        }

        @Order(10.0)
        public class NameColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("Name");
          }
        }

        @Order(20.0)
        public class AuthorColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("Author");
          }
        }

        @Order(30.0)
        public class InfoColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("Info");
          }

          @Override
          protected int getConfiguredWidth() {
            return 40;
          }
        }

        @Order(40.0)
        public class VersionColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("Version");
          }

          @Override
          protected int getConfiguredWidth() {
            return 40;
          }
        }

        @Order(50.0)
        public class FileNameColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("FileName");
          }

          @Override
          protected int getConfiguredWidth() {
            return 100;
          }
        }
      }
    }

    @Order(20.0)
    public class AddButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Add");
      }
    }

    @Order(30.0)
    public class ModifyButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Modify");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }
    }

    @Order(40.0)
    public class RemoveButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Remove");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }
    }

    @Order(50.0)
    public class DownloadButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Download");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }
    }

    @Order(60.0)
    public class UpdateBtButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("UpdateBt");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }
    }

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
      DesktopForm desktopForm = getDesktop().findForm(DesktopForm.class);
      if (desktopForm != null) {
        setProjectId(desktopForm.getProjectId());
      }
      FrameworksFormData formData = new FrameworksFormData();
      importFormData(formData);
      getForm().setAskIfNeedSave(false);
    }

    @Override
    public void execStore() throws ProcessingException {
      IFrameworksService service = SERVICES.getService(IFrameworksService.class);
      FrameworksFormData formData = new FrameworksFormData();
      setSelectedFile(getFrameworksField().getTable().getFileNameColumn().getSelectedValue());
      exportFormData(formData);
      formData = service.store(formData);
    }
  }
}
