package ar.coop.arena.security.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.shared.services.IDesktopService;

public class ProjectTablePage extends AbstractPageWithTable<ProjectTablePage.Table> {

  @Override
  protected boolean getConfiguredExpanded() {
    return true;
  }

  @Override
  protected boolean getConfiguredTableVisible() {
    return false;
  }

  @Override
  protected IPage execCreateChildPage(ITableRow row) throws ProcessingException {
    ProjectDetailsNodePage childPage = new ProjectDetailsNodePage();
    childPage.setProjectId(getTable().getProjectIdColumn().getValue(row));
    return childPage;
  }

  @Override
  protected Object[][] execLoadTableData(SearchFilter filter) throws ProcessingException {
    return SERVICES.getService(IDesktopService.class).getProjectTableData();
  }

  @Order(10.0)
  public class Table extends AbstractExtensibleTable {

    public NameColumn getNameColumn() {
      return getColumnSet().getColumnByClass(NameColumn.class);
    }

    public ProjectIdColumn getProjectIdColumn() {
      return getColumnSet().getColumnByClass(ProjectIdColumn.class);
    }

    @Order(10.0)
    public class NameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Name");
      }
    }

    @Order(20.0)
    public class ProjectIdColumn extends AbstractIntegerColumn {
    }
  }
}
