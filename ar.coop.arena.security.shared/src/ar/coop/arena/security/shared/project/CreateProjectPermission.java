package ar.coop.arena.security.shared.project;

import java.security.BasicPermission;

public class CreateProjectPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public CreateProjectPermission() {
    super("CreateProject");
  }
}
