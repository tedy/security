package ar.coop.arena.security.ui.swing;

import java.awt.Frame;

import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.ui.swing.DefaultSwingEnvironment;
import org.eclipse.scout.rt.ui.swing.window.desktop.ISwingScoutRootFrame;

public class SwingEnvironment extends DefaultSwingEnvironment {

  @Override
  public ISwingScoutRootFrame createRootComposite(Frame rootframe, IDesktop desktop) {
    ISwingScoutRootFrame ui = new SecuritySwingScoutRootFrame(rootframe);
    ui.createField(desktop, this);
    decorate(desktop, ui);
    return ui;
  }

}
