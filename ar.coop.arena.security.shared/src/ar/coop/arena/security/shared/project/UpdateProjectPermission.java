package ar.coop.arena.security.shared.project;

import java.security.BasicPermission;

public class UpdateProjectPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public UpdateProjectPermission() {
    super("UpdateProject");
  }
}
