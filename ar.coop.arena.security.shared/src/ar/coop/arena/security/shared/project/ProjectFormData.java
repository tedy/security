package ar.coop.arena.security.shared.project;

import java.util.Date;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

public class ProjectFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public ProjectFormData() {
  }

  public ProjectNrProperty getProjectNrProperty() {
    return getPropertyByClass(ProjectNrProperty.class);
  }

  /**
   * access method for property ProjectNr.
   */
  public Long getProjectNr() {
    return getProjectNrProperty().getValue();
  }

  /**
   * access method for property ProjectNr.
   */
  public void setProjectNr(Long projectNr) {
    getProjectNrProperty().setValue(projectNr);
  }

  public Author getAuthor() {
    return getFieldByClass(Author.class);
  }

  public Customer getCustomer() {
    return getFieldByClass(Customer.class);
  }

  public EndDate getEndDate() {
    return getFieldByClass(EndDate.class);
  }

  public Name getName() {
    return getFieldByClass(Name.class);
  }

  public StartDate getStartDate() {
    return getFieldByClass(StartDate.class);
  }

  public class ProjectNrProperty extends AbstractPropertyData<Long> {
    private static final long serialVersionUID = 1L;

    public ProjectNrProperty() {
    }
  }

  public static class Author extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Author() {
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

  public static class Customer extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Customer() {
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

  public static class EndDate extends AbstractValueFieldData<Date> {
    private static final long serialVersionUID = 1L;

    public EndDate() {
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

  public static class StartDate extends AbstractValueFieldData<Date> {
    private static final long serialVersionUID = 1L;

    public StartDate() {
    }
  }
}
