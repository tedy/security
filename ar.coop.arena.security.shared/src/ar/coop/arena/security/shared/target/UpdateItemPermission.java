package ar.coop.arena.security.shared.target;

import java.security.BasicPermission;

public class UpdateItemPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public UpdateItemPermission() {
    super("UpdateItem");
  }
}
