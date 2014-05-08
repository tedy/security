package ar.coop.arena.security.shared.runner;

import java.security.BasicPermission;

public class UpdateViewerPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public UpdateViewerPermission() {
    super("UpdateViewer");
  }
}
