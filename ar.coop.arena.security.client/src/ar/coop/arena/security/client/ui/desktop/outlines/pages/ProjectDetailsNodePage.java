package ar.coop.arena.security.client.ui.desktop.outlines.pages;

import java.util.Collection;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;

public class ProjectDetailsNodePage extends AbstractPageWithNodes {

  private Integer m_projectId;

  /*  @Override
    protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
      TargetsTablePage targetsTablePage = new TargetsTablePage();
      targetsTablePage.setProjectId(getProjectId());
      pageList.add(targetsTablePage);

    }*/

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    TargetsDetailNodePage targetsDetailNodePage = new TargetsDetailNodePage();
    pageList.add(targetsDetailNodePage);

  }

  @Override
  protected void execInitTreeNode() {
    //TODO [tedy] Auto-generated method stub.
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
