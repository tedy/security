package ar.coop.arena.security.shared.target;

import java.security.BasicPermission;

public class DeleteItemPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public DeleteItemPermission() {
    super("DeleteItem");
  }
}
