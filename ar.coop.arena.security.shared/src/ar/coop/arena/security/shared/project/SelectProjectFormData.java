package ar.coop.arena.security.shared.project;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

import ar.coop.arena.security.shared.project.services.lookup.ProjectLookupCall;

public class SelectProjectFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public SelectProjectFormData() {
  }

  public Project getProject() {
    return getFieldByClass(Project.class);
  }

  public static class Project extends AbstractValueFieldData<Long[]> {
    private static final long serialVersionUID = 1L;

    public Project() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.LOOKUP_CALL, ProjectLookupCall.class);
    }
  }
}
