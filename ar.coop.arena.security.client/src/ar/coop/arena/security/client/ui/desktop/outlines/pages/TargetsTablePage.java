package ar.coop.arena.security.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.shared.services.IDesktopService;

public class TargetsTablePage extends AbstractPageWithTable<TargetsTablePage.Table> {

  private Integer m_projectId;

/*  @Override
  protected IPage execCreateChildPage(ITableRow row) throws ProcessingException {
    TargetsDetailNodePage childPage = new TargetsDetailNodePage();
    childPage.setTargetId(getTable().getTargetIdColumn().getValue(row));
    return childPage;
  }*/

  @Override
  protected Object[][] execLoadTableData(SearchFilter filter) throws ProcessingException {
    return SERVICES.getService(IDesktopService.class).getTargetTableData(1l);
  }

  @Order(10.0)
  public class Table extends AbstractExtensibleTable {

    public TargetIdColumn getTargetIdColumn() {
      return getColumnSet().getColumnByClass(TargetIdColumn.class);
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
    public class TargetIdColumn extends AbstractIntegerColumn {
    }
  }

  @FormData
  public Integer getProjectId() {
    return m_projectId;
  }

  @FormData
  public void setProjectId(Integer projectId) {
    m_projectId = projectId;
  }
}
