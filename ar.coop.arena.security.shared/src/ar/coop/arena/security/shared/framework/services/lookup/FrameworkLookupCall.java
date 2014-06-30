package ar.coop.arena.security.shared.framework.services.lookup;

import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

public class FrameworkLookupCall extends LookupCall{

  private static final long serialVersionUID = 1L;
  @Override
  protected Class<? extends ILookupService> getConfiguredService() {
    return IFrameworkLookupService.class;
  }
}
