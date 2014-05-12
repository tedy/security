package ar.coop.arena.security.shared.runner;

import java.security.BasicPermission;

public class CreateRunToolPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public CreateRunToolPermission() {
    super("CreateRunTool");
  }
}
