package ar.coop.arena.security.shared.framework;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;
import org.eclipse.scout.rt.shared.services.common.file.RemoteFile;

public class UploadFrameworkFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public UploadFrameworkFormData() {
  }

  public ProjectIdProperty getProjectIdProperty() {
    return getPropertyByClass(ProjectIdProperty.class);
  }

  /**
   * access method for property ProjectId.
   */
  public Integer getProjectId() {
    return getProjectIdProperty().getValue();
  }

  /**
   * access method for property ProjectId.
   */
  public void setProjectId(Integer projectId) {
    getProjectIdProperty().setValue(projectId);
  }

  public RemoteFileProperty getRemoteFileProperty() {
    return getPropertyByClass(RemoteFileProperty.class);
  }

  /**
   * access method for property RemoteFile.
   */
  public RemoteFile getRemoteFile() {
    return getRemoteFileProperty().getValue();
  }

  /**
   * access method for property RemoteFile.
   */
  public void setRemoteFile(RemoteFile remoteFile) {
    getRemoteFileProperty().setValue(remoteFile);
  }

  public FileName getFileName() {
    return getFieldByClass(FileName.class);
  }

  public class ProjectIdProperty extends AbstractPropertyData<Integer> {
    private static final long serialVersionUID = 1L;

    public ProjectIdProperty() {
    }
  }

  public class RemoteFileProperty extends AbstractPropertyData<RemoteFile> {
    private static final long serialVersionUID = 1L;

    public RemoteFileProperty() {
    }
  }

  public static class FileName extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public FileName() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MANDATORY, true);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }
}
