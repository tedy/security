package ar.coop.arena.security.client.ui.desktop.outlines;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.AbstractExtensibleOutline;
import org.eclipse.scout.rt.shared.TEXTS;

import ar.coop.arena.security.client.ui.desktop.outlines.pages.TargetzzNodePage;

public class TargetsOutline extends AbstractExtensibleOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Targets");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    TargetzzNodePage targetzzNodePage = new TargetzzNodePage();
    pageList.add(targetzzNodePage);

  }
}
