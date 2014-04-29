package ar.coop.arena.security.shared.services;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

import ar.coop.arena.security.shared.services.lookup.target.TargetLookupCall;

public class DesktopFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public DesktopFormData() {
  }

  public ProjectIdProperty getProjectIdProperty() {
    return getPropertyByClass(ProjectIdProperty.class);
  }

  public Integer getProjectId() {
    return getProjectIdProperty().getValue();
  }

  public void setProjectId(Integer projectId) {
    getProjectIdProperty().setValue(projectId);
  }

  public TargetsTree getTargetsTree() {
    return getFieldByClass(TargetsTree.class);
  }

  public class ProjectIdProperty extends AbstractPropertyData<Integer> {
    private static final long serialVersionUID = 1L;

    public ProjectIdProperty() {
    }
  }

  public static class TargetsTree extends AbstractValueFieldData<Long[]> {
    private static final long serialVersionUID = 1L;

    public TargetsTree() {
    }

    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.LOOKUP_CALL, TargetLookupCall.class);
    }
  }
}
