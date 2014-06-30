package ar.coop.arena.security.shared.services.lookup.target;

import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import ar.coop.arena.security.shared.services.lookup.target.ITargetLookupService;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.commons.annotations.FormData;

public class TargetLookupCall extends LookupCall{

  private static final long serialVersionUID = 1L;
  private Integer m_projectId;

  @Override
  protected Class<? extends ILookupService> getConfiguredService() {
    return ITargetLookupService.class;
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
