package ar.coop.arena.security.shared.services.lookup.target;

import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import ar.coop.arena.security.shared.services.lookup.target.IRiskLookupService;

public class RiskLookupCall extends LookupCall{

  private static final long serialVersionUID = 1L;

  @Override
  protected Class<? extends ILookupService> getConfiguredService() {
    return IRiskLookupService.class;
  }
}
