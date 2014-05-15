package ar.coop.arena.security.shared.project.services.lookup;

import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import ar.coop.arena.security.shared.project.services.lookup.IProjectLookupService;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;

public class ProjectLookupCall extends LookupCall{

  private static final long serialVersionUID = 1L;

  @Override
  protected Class<? extends ILookupService> getConfiguredService() {
    return IProjectLookupService.class;
  }
}
