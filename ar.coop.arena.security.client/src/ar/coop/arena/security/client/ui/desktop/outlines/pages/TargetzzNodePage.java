package ar.coop.arena.security.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.pages.AbstractExtensiblePageWithNodes;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.shared.services.IDesktopService;

public class TargetzzNodePage extends AbstractExtensiblePageWithNodes {

  @Override
  protected void execPageDataLoaded() throws ProcessingException {
    //TODO [tedy] Auto-generated method stub.
  }

//  @Override
  protected Object[][] execLoadTableData(SearchFilter filter) throws ProcessingException {
    return SERVICES.getService(IDesktopService.class).getTargetTableData(1l);
  }
}
