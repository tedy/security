package ar.coop.arena.security.shared.target;

import java.security.BasicPermission;

public class CreateItemPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public CreateItemPermission() {
    super("CreateItem");
  }
}
