package ar.coop.arena.security.shared.runner;

import java.security.BasicPermission;

public class ReadViewerPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public ReadViewerPermission() {
    super("ReadViewer");
  }
}
