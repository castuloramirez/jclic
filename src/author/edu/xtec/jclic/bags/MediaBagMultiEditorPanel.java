/*
 * File    : MediaBagMultiEditorPanel.java
 * Created : 23-jan-2004 15:36
 * By      : fbusquets
 *
 * JClic - Authoring and playing system for educational activities
 *
 * Copyright (C) 2000 - 2005 Francesc Busquets & Departament
 * d'Educacio de la Generalitat de Catalunya
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details (see the LICENSE file).
 */

package edu.xtec.jclic.bags;

import edu.xtec.jclic.boxes.ThumbsExplorerPanel;
import edu.xtec.jclic.edit.Editor;
import edu.xtec.jclic.edit.EditorPanel;
import edu.xtec.jclic.fileSystem.FileSystem;
import edu.xtec.jclic.misc.Utils;
import edu.xtec.util.Messages;
import edu.xtec.util.Options;
import edu.xtec.util.SmallButton;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Francesc Busquets (fbusquets@xtec.cat)
 * @version 13.09.17
 */
public class MediaBagMultiEditorPanel extends EditorPanel {

  public static final int ICON_MAX_WIDTH = 100;
  public static final int BUTTON_WIDTH = 130;
  public static final int ICON_MAX_HEIGHT = 70;
  public static final int BUTTON_HEIGHT = 110;

  public static final String[] COMBO_ITEMS = {
    "filefilter_all_media",
    "filefilter_all_images",
    "filefilter_all_sounds",
    "filefilter_midi",
    "filefilter_all_video",
    "filefilter_all_anim",
    "filefilter_skins",
    "filefilter_fonts"
  };

  public static final int[] COMBO_FILTERS = {
    Utils.ALL_FF,
    Utils.ALL_IMAGES_FF,
    Utils.ALL_SOUNDS_FF,
    Utils.MIDI_FF,
    Utils.ALL_VIDEO_FF,
    Utils.ALL_ANIM_FF,
    Utils.SKINS_FF,
    Utils.FONTS_FF
  };

  private ThumbsExplorerPanel selectPanel;
  private MediaBagElementEditor current;
  int filters = -1;

  /** Creates new form MediaBagMultiEditorPanel */
  public MediaBagMultiEditorPanel(Options options) {
    super(options);
    MediaBagEditor.createActions(options);
    MediaBagElementEditor.createActions(options);
    initComponents();
    selectPanel = (ThumbsExplorerPanel) sPanel;
    scrollPanel.getVerticalScrollBar().setUnitIncrement(selectPanel.boxSize.height);
    selectPanel.addListSelectionListener(btSelListener);
    selectPanel.addActionListener(btActListener);
    filterCombo.setSelectedIndex(0);
    postInit(250, false, true);
    setEnabled(false);
  }

  @Override
  protected String getTitle() {
    return options.getMsg("edit_media_title");
  }

  protected List<String> getComboItems() {
    List<String> result = new ArrayList<String>();
    for (String item : COMBO_ITEMS) result.add(options.getMsg(item));
    return result;
  }

  @Override
  public void focusGained(FocusEvent focusEvent) {
    ((MediaBagElementEditorPanel) elementEditPanel).focusGained(focusEvent);
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    bGroup = new javax.swing.ButtonGroup();
    mainPanel = new javax.swing.JPanel();
    filterPanel = new javax.swing.JPanel();
    filterLb = new javax.swing.JLabel();
    filterCombo = new javax.swing.JComboBox<Object>(getComboItems().toArray());
    scrollPanel = new javax.swing.JScrollPane();
    sPanel = new ThumbsExplorerPanel();
    statusLb = new javax.swing.JLabel();
    elementEditPanel = new MediaBagElementEditorPanel(options);

    setLayout(new java.awt.BorderLayout());

    mainPanel.setLayout(new java.awt.GridBagLayout());

    filterPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

    filterLb.setText(options.getMsg("edit_media_show_type"));
    filterPanel.add(filterLb);

    filterCombo.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            filterComboActionPerformed(evt);
          }
        });
    filterPanel.add(filterCombo);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    mainPanel.add(filterPanel, gridBagConstraints);

    scrollPanel.setHorizontalScrollBarPolicy(
        javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPanel.addComponentListener(
        new java.awt.event.ComponentAdapter() {
          public void componentResized(java.awt.event.ComponentEvent evt) {
            scrollPanelComponentResized(evt);
          }
        });
    scrollPanel.setViewportView(sPanel);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    mainPanel.add(scrollPanel, gridBagConstraints);

    statusLb.setText("0 " + options.getMsg("edit_media_objects"));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    mainPanel.add(statusLb, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    mainPanel.add(elementEditPanel, gridBagConstraints);

    add(mainPanel, java.awt.BorderLayout.CENTER);
  } // </editor-fold>//GEN-END:initComponents

  private void filterComboActionPerformed(
      java.awt.event.ActionEvent evt) { // GEN-FIRST:event_filterComboActionPerformed

    setFilters(COMBO_FILTERS[filterCombo.getSelectedIndex()]);
  } // GEN-LAST:event_filterComboActionPerformed

  private void scrollPanelComponentResized(
      java.awt.event.ComponentEvent evt) { // GEN-FIRST:event_scrollPanelComponentResized

    selectPanel.resizeTo(scrollPanel.getViewport().getSize().width);
  } // GEN-LAST:event_scrollPanelComponentResized

  @Override
  public void editorDataChanged(Editor e) {
    if (e != null) {
      if (e.equals(getEditor())) fillData();
      else {
        ThumbsExplorerPanel.ThumbElement th = selectPanel.getElementFor(e);
        if (th != null) {
          MediaBagElementEditor mbed = (MediaBagElementEditor) e;
          Dimension d = selectPanel.maxThumbSize;
          th.setImage(
              mbed.getMediaBagElement()
                  .getThumbNail(d.width, d.height, getMediaBag().getProject().getFileSystem()));
          th.setText(mbed.getName());
          // Free discardable objects from memory
          mbed.getMediaBagElement().clearData();
          btSelListener.valueChanged(null);
          repaint();
        }
      }
    }
  }

  public boolean checkIfEditorValid(Editor e) {
    return e instanceof MediaBagEditor;
  }

  protected MediaBagEditor getMediaBagEditor() {
    return (MediaBagEditor) editor;
  }

  protected MediaBag getMediaBag() {
    if (editor == null) return null;
    else return getMediaBagEditor().getMediaBag();
  }

  public void setFilters(int filters) {
    this.filters = filters;
    for (int i = 0; i < COMBO_FILTERS.length; i++) {
      if (COMBO_FILTERS[i] == filters) {
        filterCombo.setSelectedIndex(i);
        break;
      }
    }
    fillData();
  }

  public int getFilters() {
    return filters;
  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    selectPanel.setEnabled(enabled);
    filterCombo.setEnabled(enabled);
    elementEditPanel.setEnabled(enabled);
  }

  protected void fillData() {
    current = null;
    selectPanel.removeAllThumbElements();
    ((MediaBagElementEditorPanel) elementEditPanel).attachEditor(null, true);
    MediaBagEditor mbe = getMediaBagEditor();
    int n = 0;
    if (mbe != null) {
      Dimension d = selectPanel.maxThumbSize;
      List<MediaBagElementEditor> v = mbe.getChildrenList(filters);
      n = v.size();
      FileSystem fs = getMediaBag().getProject().getFileSystem();
      for (MediaBagElementEditor mbed : v) {
        // ATENCIO!!!
        // Provoca bucles i stack overflow
        // mbed.addEditorListener(this);
        selectPanel.addThumbElement(
            mbed, mbed.getMediaBagElement().getThumbNail(d.width, d.height, fs), mbed.getName());

        // Free discardable objects from memory
        mbed.getMediaBagElement().clearData();
      }
    }
    btSelListener.valueChanged(null);
    statusLb.setText(
        Integer.toString(n)
            + " "
            + options.getMsg(n == 1 ? "edit_media_object" : "edit_media_objects"));
    scrollPanelComponentResized(null);
  }

  private ListSelectionListener btSelListener =
      new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent evt) {
          current = (MediaBagElementEditor) selectPanel.getCurrentObject();
          ((MediaBagElementEditorPanel) elementEditPanel).attachEditor(current, true);
          SwingUtilities.invokeLater(
              new Runnable() {
                public void run() {
                  selectPanel.checkCurrentVisibility();
                }
              });
        }
      };

  private ActionListener btActListener =
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          MediaBagElementEditor.testMediaBagElementAction.actionPerformed(
              new ActionEvent(elementEditPanel, ActionEvent.ACTION_PERFORMED, ""));
        }
      };

  protected void saveData() {}

  public MediaBagElementEditor getSelected() {
    return current;
  }

  public void setSelected(MediaBagElementEditor value) {
    selectPanel.setCurrentObject(value);
    btSelListener.valueChanged(null);
  }

  private static Map<Options, MediaBagMultiEditorPanel> dlgPanels =
      new HashMap<Options, MediaBagMultiEditorPanel>(1);

  public static String getMediaName(
      String initialValue, Options options, Component parent, MediaBagEditor mbe, int filters) {
    String result = null;
    Messages msg = options.getMessages();
    MediaBagMultiEditorPanel mbep = dlgPanels.get(options);
    if (mbep == null) {
      // mbep=(MediaBagMultiEditorPanel)mbe.createEditorPanel(options);
      mbep = new MediaBagMultiEditorPanel(options);
      mbep.setPreferredSize(new Dimension(500, 500));
      dlgPanels.put(options, mbep);
    }
    mbep.attachEditor(mbe, true);
    mbep.setFilters(filters);
    if (filters >= 0) mbep.filterCombo.setEnabled(false);
    mbep.setSelected(
        initialValue == null ? null : (MediaBagElementEditor) mbe.getChildByName(initialValue));

    boolean b = msg.showInputDlg(parent, mbep, "edit_media_dlg_title");
    if (b) {
      MediaBagElementEditor mbele = mbep.getSelected();
      if (mbele != null) result = mbele.getName();
    }

    mbep.filterCombo.setEnabled(true);
    mbep.removeEditor(true);
    return result;
  }

  @Override
  protected void addActionsTo(Container cnt) {
    // cnt.add(new SmallButton(MediaBagElementEditor.newMediaBagElementAction));
    cnt.add(new SmallButton(MediaBagEditor.newMediaBagElementAction));
    cnt.add(new SmallButton(MediaBagElementEditor.testMediaBagElementAction));
    cnt.add(new SmallButton(Editor.deleteAction));
    cnt.add(new SmallButton(MediaBagEditor.updateAllMediaAction));
    cnt.add(new SmallButton(MediaBagEditor.exportAllMediaAction));
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.ButtonGroup bGroup;
  private javax.swing.JPanel elementEditPanel;
  private javax.swing.JComboBox<Object> filterCombo;
  private javax.swing.JLabel filterLb;
  private javax.swing.JPanel filterPanel;
  private javax.swing.JPanel mainPanel;
  private javax.swing.JPanel sPanel;
  private javax.swing.JScrollPane scrollPanel;
  private javax.swing.JLabel statusLb;
  // End of variables declaration//GEN-END:variables

}
