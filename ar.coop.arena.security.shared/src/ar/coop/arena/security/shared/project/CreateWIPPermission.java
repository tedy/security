package ar.coop.arena.security.shared.project;

import java.security.BasicPermission;

public class CreateWIPPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public CreateWIPPermission() {
    super("CreateWIP");
  }
}
