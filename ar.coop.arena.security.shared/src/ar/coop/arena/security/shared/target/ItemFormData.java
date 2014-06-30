package ar.coop.arena.security.shared.target;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

import ar.coop.arena.security.shared.services.lookup.target.RiskLookupCall;

public class ItemFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public ItemFormData() {
  }

  public ItemNrProperty getItemNrProperty() {
    return getPropertyByClass(ItemNrProperty.class);
  }

  /**
   * access method for property ItemNr.
   */
  public Long getItemNr() {
    return getItemNrProperty().getValue();
  }

  /**
   * access method for property ItemNr.
   */
  public void setItemNr(Long itemNr) {
    getItemNrProperty().setValue(itemNr);
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

  public TargetIdProperty getTargetIdProperty() {
    return getPropertyByClass(TargetIdProperty.class);
  }

  /**
   * access method for property TargetId.
   */
  public Integer getTargetId() {
    return getTargetIdProperty().getValue();
  }

  /**
   * access method for property TargetId.
   */
  public void setTargetId(Integer targetId) {
    getTargetIdProperty().setValue(targetId);
  }

  public Name getName() {
    return getFieldByClass(Name.class);
  }

  public Port getPort() {
    return getFieldByClass(Port.class);
  }

  public Protocol getProtocol() {
    return getFieldByClass(Protocol.class);
  }

  public Risk getRisk() {
    return getFieldByClass(Risk.class);
  }

  public class ItemNrProperty extends AbstractPropertyData<Long> {
    private static final long serialVersionUID = 1L;

    public ItemNrProperty() {
    }
  }

  public class ProjectIdProperty extends AbstractPropertyData<Integer> {
    private static final long serialVersionUID = 1L;

    public ProjectIdProperty() {
    }
  }

  public class TargetIdProperty extends AbstractPropertyData<Integer> {
    private static final long serialVersionUID = 1L;

    public TargetIdProperty() {
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

  public static class Port extends AbstractValueFieldData<Integer> {
    private static final long serialVersionUID = 1L;

    public Port() {
    }
  }

  public static class Protocol extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Protocol() {
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

  public static class Risk extends AbstractValueFieldData<Integer> {
    private static final long serialVersionUID = 1L;

    public Risk() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.LOOKUP_CALL, RiskLookupCall.class);
      ruleMap.put(ValidationRule.ZERO_NULL_EQUALITY, true);
    }
  }
}
