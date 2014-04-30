package ar.coop.arena.security.shared.project;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

import ar.coop.arena.security.shared.services.lookup.target.TargetsLookupCall;

public class TargetsTreeFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public TargetsTreeFormData() {
  }

  public TargetsNrProperty getTargetsNrProperty() {
    return getPropertyByClass(TargetsNrProperty.class);
  }

  /**
   * access method for property TargetsNr.
   */
  public Long getTargetsNr() {
    return getTargetsNrProperty().getValue();
  }

  /**
   * access method for property TargetsNr.
   */
  public void setTargetsNr(Long targetsNr) {
    getTargetsNrProperty().setValue(targetsNr);
  }

  public TargetsTree getTargetsTree() {
    return getFieldByClass(TargetsTree.class);
  }

  public class TargetsNrProperty extends AbstractPropertyData<Long> {
    private static final long serialVersionUID = 1L;

    public TargetsNrProperty() {
    }
  }

  public static class TargetsTree extends AbstractValueFieldData<Long[]> {
    private static final long serialVersionUID = 1L;

    public TargetsTree() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.LOOKUP_CALL, TargetsLookupCall.class);
    }
  }
}
