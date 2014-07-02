package ar.coop.arena.security.ui.swing.headerpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
//import javax.swing.JSplitPane;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import org.eclipse.scout.commons.OptimisticLock;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.ui.swing.basic.SwingScoutComposite;
import org.eclipse.scout.rt.ui.swing.ext.JPanelEx;
import org.eclipse.scout.rt.ui.swing.window.desktop.toolbar.AbstractJTabBar;
import org.eclipse.scout.rt.ui.swing.window.desktop.toolbar.SwingScoutHeaderPanel;

public class SecuritySwingScoutHeaderPanel extends SwingScoutComposite<IDesktop> {

  private static final IScoutLogger LOG = ScoutLogManager.getLogger(SwingScoutHeaderPanel.class);
  private static final long serialVersionUID = 1L;
  private static final int DISTANCE_NAVIGATION_TABS = 2;
  private JLabel m_windowIcons;

  private int m_topLevelMenuCount;
  protected AbstractJTabBar m_toolTabsPanelRight;
  protected AbstractJTabBar m_toolTabsPanelLeft;

  protected final SpringLayout m_layout;

//  protected JSplitPane splitPane;

  public SecuritySwingScoutHeaderPanel() {
    m_layout = new SpringLayout();
  }

  @Override
  protected void initializeSwing() {
    m_topLevelMenuCount = getScoutObject().getMenus().length;
    final JPanelEx container = new JPanelEx(m_layout);

    m_toolTabsPanelRight = createViewTabsBar();
    container.add(m_toolTabsPanelRight);
    m_layout.putConstraint(SpringLayout.SOUTH, m_toolTabsPanelRight, 0, SpringLayout.SOUTH, container);
    m_layout.putConstraint(SpringLayout.EAST, m_toolTabsPanelRight, 0, SpringLayout.EAST, container);
    m_layout.putConstraint(SpringLayout.WEST, m_toolTabsPanelRight, 0, SpringLayout.WEST, container);

    m_toolTabsPanelLeft = createToolTabsBar();
    ((SecurityJToolTabsBar) m_toolTabsPanelLeft).setSwingScoutHeaderPanel(this);
    container.add(m_toolTabsPanelLeft);
    m_layout.putConstraint(SpringLayout.NORTH, m_toolTabsPanelLeft, 0, SpringLayout.WEST, m_toolTabsPanelRight);
    m_layout.putConstraint(SpringLayout.SOUTH, m_toolTabsPanelLeft, 0, SpringLayout.SOUTH, container);
    m_layout.putConstraint(SpringLayout.EAST, m_toolTabsPanelLeft, 0, SpringLayout.EAST, container);

    /*splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, m_toolTabsPanelRight, m_toolTabsPanelLeft);
    //    splitPane.setDividerSize(300);
    //    splitPane.setDividerLocation(0.8);
    //    splitPane.setMinimumSize(new Dimension(500, 0));
    splitPane.setEnabled(false);
    container.add(splitPane);*/

    Color color = UIManager.getColor("HeaderPanel.background");
    if (color != null) {
      container.setOpaque(true);
      container.setBackground(color);
    }

    int height = UIManager.getInt("HeaderPanel.height");
    if (height > 0) {
      container.setPreferredSize(new Dimension(-1, height));
    }
    else {
      // register listener to calculate panel height
      container.addComponentListener(new ComponentAdapter() {

        private int m_height;
        private final OptimisticLock m_syncLock = new OptimisticLock();

        @Override
        public void componentResized(ComponentEvent e) {
          try {
            if (m_syncLock.acquire()) {
              int newHeight = calculatePanelHeight();
              if (m_height != newHeight) {
                m_height = newHeight;
                container.setPreferredSize(new Dimension(-1, m_height));
              }
            }
          }
          finally {
            m_syncLock.release();
          }
        }

        private int calculatePanelHeight() {
          double heightViewTabPanel = m_toolTabsPanelRight.getPreferredSize().getHeight();
          double heightToolTabsPanel = m_toolTabsPanelLeft.getPreferredSize().getHeight();

          double heightBottomPanel = Math.max(heightViewTabPanel, heightToolTabsPanel);
          return (int) (DISTANCE_NAVIGATION_TABS + heightBottomPanel);
        }

      });
    }

    setSwingField(container);
    rebuildViewTabs();
    rebuildToolTabs();
  }

  @Override
  protected boolean isHandleScoutPropertyChange(String name, Object newValue) {
    return false;
  }

  public boolean isEmpty() {
    return m_topLevelMenuCount == 0;
  }

  public AbstractJTabBar getSwingToolTabsPanel() {
    return m_toolTabsPanelLeft;
  }

  /**
   * Update layout of {@link RayoToolTabsBar} tool button bar to have the very same size as the tool bar itself
   * 
   * @param width
   *          the new width to be set. Thereby, the with is only set if is different to the old width.
   * @param force
   *          to force the panel width to be updated also if the given value is the same
   */
  public void adjustToolButtonPanelWidth(int width, boolean force) {
    // it is crucial to compare the new value against the current value hold by the layout manager and not the size of the toolTabsPanel itself.
    // This is because the toolTabsPanel might have the correct size, but the layout manager was never told about.
    Spring constraintLeft = m_layout.getConstraint(SpringLayout.WEST, m_toolTabsPanelLeft);
    Spring constraintRigth = m_layout.getConstraint(SpringLayout.EAST, m_toolTabsPanelLeft);
    int currentWidth = (constraintRigth.getValue() - constraintLeft.getValue());

    if (force || width != currentWidth) {
      // adjust width of tool button bar to be equals to the tool bar width
      m_layout.putConstraint(SpringLayout.WEST, m_toolTabsPanelLeft, width * -1, SpringLayout.EAST, getSwingField());

      // set maximum width to outline tabs in order to not obscure tool buttons
      m_layout.putConstraint(SpringLayout.EAST, m_toolTabsPanelRight, width * -1, SpringLayout.EAST, getSwingField());

      // revalidate layout to immediately reflect changed layout
      getSwingField().revalidate();
    }
  }

  private void rebuildViewTabs() {
//    ((SecurityJViewTabsBar) m_toolTabsPanelRight).rebuild(getScoutObject());
    ((SecurityJToolTabsBar) m_toolTabsPanelRight).rebuild(getScoutObject());
  }

  private void rebuildToolTabs() {
    ((SecurityJToolTabsBar) m_toolTabsPanelLeft).rebuild(getScoutObject());
  }

  protected AbstractJTabBar createToolTabsBar() {
    SecurityJToolTabsBar tb = new SecurityJToolTabsBar(getSwingEnvironment());
    tb.setLeftSide(false);
    return tb;
  }

  protected AbstractJTabBar createViewTabsBar() {
//    return new SecurityJViewTabsBar(getSwingEnvironment());
    return new SecurityJToolTabsBar(getSwingEnvironment());
  }

}
