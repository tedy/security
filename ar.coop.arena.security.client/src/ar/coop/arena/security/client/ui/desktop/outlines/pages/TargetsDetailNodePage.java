package ar.coop.arena.security.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.tree.ITreeNode;
import org.eclipse.scout.rt.client.ui.basic.tree.IVirtualTreeNode;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.pages.AbstractExtensiblePageWithNodes;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.shared.services.IDesktopService;

public class TargetsDetailNodePage extends AbstractExtensiblePageWithNodes {

  private Integer m_targetId;

  /*  @Override
    protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
      TargetItemTablePage targetItemTablePage = new TargetItemTablePage();
      targetItemTablePage.setTargetId(getTargetId());
      pageList.add(targetItemTablePage);
    }*/

  @Override
  protected void execPageDataLoaded() throws ProcessingException {
    SERVICES.getService(IDesktopService.class).getTargetTableData(1l);
  }

  @Override
  protected ITreeNode execResolveVirtualChildNode(IVirtualTreeNode node) throws ProcessingException {
    //TODO [tedy] Auto-generated method stub.
    return null;
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
