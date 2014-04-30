package ar.coop.arena.security.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.commons.annotations.FormData;

public class TargetItemTablePage extends AbstractPageWithTable<TargetItemTablePage.Table> {

  private Integer m_targetId;

  @Order(10.0)
  public class Table extends AbstractExtensibleTable {

    public ProtocolColumn getProtocolColumn() {
      return getColumnSet().getColumnByClass(ProtocolColumn.class);
    }

    public NameColumn getNameColumn() {
      return getColumnSet().getColumnByClass(NameColumn.class);
    }

    public PortColumn getPortColumn() {
      return getColumnSet().getColumnByClass(PortColumn.class);
    }

    @Order(10.0)
    public class PortColumn extends AbstractIntegerColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Port");
      }
    }

    @Order(20.0)
    public class ProtocolColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Protocol");
      }
    }

    @Order(30.0)
    public class NameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Name");
      }
    }
  }

  @FormData
  public Integer getTargetId() {
    return m_targetId;
  }

  @FormData
  public void setTargetId(Integer targetId) {
    m_targetId = targetId;
  }
}
