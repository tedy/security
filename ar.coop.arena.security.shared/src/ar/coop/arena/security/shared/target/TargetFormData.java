package ar.coop.arena.security.shared.target;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

import ar.coop.arena.security.shared.services.lookup.target.TargetTypeLookupCall;

public class TargetFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public TargetFormData() {
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

  public TargetNrProperty getTargetNrProperty() {
    return getPropertyByClass(TargetNrProperty.class);
  }

  /**
   * access method for property TargetNr.
   */
  public Long getTargetNr() {
    return getTargetNrProperty().getValue();
  }

  /**
   * access method for property TargetNr.
   */
  public void setTargetNr(Long targetNr) {
    getTargetNrProperty().setValue(targetNr);
  }

  public Name getName() {
    return getFieldByClass(Name.class);
  }

  public TargetType getTargetType() {
    return getFieldByClass(TargetType.class);
  }

  public class ProjectIdProperty extends AbstractPropertyData<Integer> {
    private static final long serialVersionUID = 1L;

    public ProjectIdProperty() {
    }
  }

  public class TargetNrProperty extends AbstractPropertyData<Long> {
    private static final long serialVersionUID = 1L;

    public TargetNrProperty() {
    }
  }

  public static class Name extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Name() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }

  public static class TargetType extends AbstractValueFieldData<Integer> {
    private static final long serialVersionUID = 1L;

    public TargetType() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.LOOKUP_CALL, TargetTypeLookupCall.class);
      ruleMap.put(ValidationRule.ZERO_NULL_EQUALITY, true);
    }
  }
}
