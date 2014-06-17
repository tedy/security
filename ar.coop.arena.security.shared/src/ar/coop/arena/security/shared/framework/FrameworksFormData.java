package ar.coop.arena.security.shared.framework;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.tablefield.AbstractTableFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

public class FrameworksFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public FrameworksFormData() {
  }

  public FrameworksNrProperty getFrameworksNrProperty() {
    return getPropertyByClass(FrameworksNrProperty.class);
  }

  /**
   * access method for property FrameworksNr.
   */
  public Long getFrameworksNr() {
    return getFrameworksNrProperty().getValue();
  }

  /**
   * access method for property FrameworksNr.
   */
  public void setFrameworksNr(Long frameworksNr) {
    getFrameworksNrProperty().setValue(frameworksNr);
  }

  public Frameworks getFrameworks() {
    return getFieldByClass(Frameworks.class);
  }

  public class FrameworksNrProperty extends AbstractPropertyData<Long> {
    private static final long serialVersionUID = 1L;

    public FrameworksNrProperty() {
    }
  }

  public static class Frameworks extends AbstractTableFieldData {
    private static final long serialVersionUID = 1L;

    public Frameworks() {
    }

    public static final int NAME_COLUMN_ID = 0;
    public static final int AUTHOR_COLUMN_ID = 1;
    public static final int INFO_COLUMN_ID = 2;
    public static final int VERSION_COLUMN_ID = 3;
    public static final int FILE_NAME_COLUMN_ID = 4;

    public void setName(int row, String name) {
      setValueInternal(row, NAME_COLUMN_ID, name);
    }

    public String getName(int row) {
      return (String) getValueInternal(row, NAME_COLUMN_ID);
    }

    public void setAuthor(int row, String author) {
      setValueInternal(row, AUTHOR_COLUMN_ID, author);
    }

    public String getAuthor(int row) {
      return (String) getValueInternal(row, AUTHOR_COLUMN_ID);
    }

    public void setInfo(int row, String info) {
      setValueInternal(row, INFO_COLUMN_ID, info);
    }

    public String getInfo(int row) {
      return (String) getValueInternal(row, INFO_COLUMN_ID);
    }

    public void setVersion(int row, String version) {
      setValueInternal(row, VERSION_COLUMN_ID, version);
    }

    public String getVersion(int row) {
      return (String) getValueInternal(row, VERSION_COLUMN_ID);
    }

    public void setFileName(int row, String fileName) {
      setValueInternal(row, FILE_NAME_COLUMN_ID, fileName);
    }

    public String getFileName(int row) {
      return (String) getValueInternal(row, FILE_NAME_COLUMN_ID);
    }

    @Override
    public int getColumnCount() {
      return 5;
    }

    @Override
    public Object getValueAt(int row, int column) {
      switch (column) {
        case NAME_COLUMN_ID:
          return getName(row);
        case AUTHOR_COLUMN_ID:
          return getAuthor(row);
        case INFO_COLUMN_ID:
          return getInfo(row);
        case VERSION_COLUMN_ID:
          return getVersion(row);
        case FILE_NAME_COLUMN_ID:
          return getFileName(row);
        default:
          return null;
      }
    }

    @Override
    public void setValueAt(int row, int column, Object value) {
      switch (column) {
        case NAME_COLUMN_ID:
          setName(row, (String) value);
          break;
        case AUTHOR_COLUMN_ID:
          setAuthor(row, (String) value);
          break;
        case INFO_COLUMN_ID:
          setInfo(row, (String) value);
          break;
        case VERSION_COLUMN_ID:
          setVersion(row, (String) value);
          break;
        case FILE_NAME_COLUMN_ID:
          setFileName(row, (String) value);
          break;
      }
    }
  }
}
