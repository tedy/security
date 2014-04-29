package ar.coop.arena.security.shared.services.lookup.target;

import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import ar.coop.arena.security.shared.services.lookup.target.ITargetLookupService;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;

public class TargetLookupCall extends LookupCall{

  private static final long serialVersionUID = 1L;

  @Override
  protected Class<? extends ILookupService> getConfiguredService() {
    return ITargetLookupService.class;
  }
}
