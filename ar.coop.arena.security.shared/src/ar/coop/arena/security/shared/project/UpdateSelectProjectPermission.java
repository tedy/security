package ar.coop.arena.security.shared.project;

import java.security.BasicPermission;

public class UpdateSelectProjectPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public UpdateSelectProjectPermission() {
    super("UpdateSelectProject");
  }
}
