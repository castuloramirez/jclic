/*
 * File    : TextGridContentControlPanel.java
 * Created : 01-apr-2003 10:38
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

package edu.xtec.jclic.boxes;

import edu.xtec.jclic.Activity;
import edu.xtec.jclic.activities.textGrid.CrossWord;
import edu.xtec.jclic.activities.textGrid.WordSearch;
import edu.xtec.jclic.beans.*;
import edu.xtec.util.Options;
import java.util.EventObject;

/**
 * @author Francesc Busquets (fbusquets@xtec.cat)
 * @version 13.09.09
 */
public class TextGridContentControlPanel extends edu.xtec.util.CtrlPanel {

  ActiveBagContentEditor parent;
  TextGridContent tgc;
  Options options;
  boolean isWordSearch;
  int index;

  /** Creates new form ActiveBagContentControlPanel */
  public TextGridContentControlPanel(
      ActiveBagContentEditor parent, int index, boolean isWordSearch) {
    setInitializing(true);
    this.isWordSearch = isWordSearch;
    this.parent = parent;
    options = parent.getOptions();
    this.index = index;
    initComponents();
    if (!isWordSearch) {
      wordListLb.setVisible(false);
      wordListEditor.setEnabled(false);
      wordListEditor.setVisible(false);
      useGridBChk.setEnabled(false);
      useGridBChk.setVisible(false);
    } else {
      wildTransparentChk.setEnabled(false);
      wildTransparentChk.setVisible(false);
    }
    setInitializing(false);
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  private void initComponents() { // GEN-BEGIN:initComponents
    java.awt.GridBagConstraints gridBagConstraints;
    javax.swing.JLabel heightLb;
    javax.swing.JPanel leftPanel;
    javax.swing.JLabel nColsLb;
    javax.swing.JLabel nRowsLb;
    javax.swing.JLabel spacer;
    javax.swing.JLabel spacer2;
    javax.swing.JLabel styleLb;
    javax.swing.JLabel widthLb;

    leftPanel = new javax.swing.JPanel();
    spacer = new javax.swing.JLabel();
    useGridBChk = new javax.swing.JCheckBox();
    nRowsLb = new javax.swing.JLabel();
    nRowsEdit = new edu.xtec.jclic.beans.SmallIntEditor();
    nColsLb = new javax.swing.JLabel();
    nColsEdit = new edu.xtec.jclic.beans.SmallIntEditor();
    wildTransparentChk = new javax.swing.JCheckBox();
    spacer2 = new javax.swing.JLabel();
    widthLb = new javax.swing.JLabel();
    widthEdit = new edu.xtec.jclic.beans.SmallIntEditor();
    heightLb = new javax.swing.JLabel();
    heightEdit = new edu.xtec.jclic.beans.SmallIntEditor();
    styleLb = new javax.swing.JLabel();
    boxBaseButton = new edu.xtec.jclic.beans.BoxBaseButton();
    boxBaseButton.setOptions(options);
    borderChk = new javax.swing.JCheckBox();
    wordSearchPanel = new javax.swing.JPanel();
    wordListLb = new javax.swing.JLabel();
    wordListEditor = new TextListEditor(options);

    setLayout(new java.awt.GridBagLayout());

    leftPanel.setLayout(new java.awt.GridBagLayout());

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
    gridBagConstraints.weighty = 1.0;
    leftPanel.add(spacer, gridBagConstraints);

    useGridBChk.setToolTipText(options.getMsg("edit_act_wordSearch_panelB_tooltip"));
    useGridBChk.setText(options.getMsg("edit_act_wordSearch_panelB"));
    useGridBChk.addActionListener(this);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    leftPanel.add(useGridBChk, gridBagConstraints);

    nRowsLb.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource("/edu/xtec/resources/icons/adjust_rows.gif")));
    nRowsLb.setText(":");
    nRowsLb.setIconTextGap(0);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    leftPanel.add(nRowsLb, gridBagConstraints);

    nRowsEdit.setToolTipText(options.getMsg("edit_act_num_rows_tooltip"));
    nRowsEdit.setMax(30);
    nRowsEdit.setMin(1);
    nRowsEdit.addPropertyChangeListener(SmallIntEditor.PROP_VALUE, this);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    leftPanel.add(nRowsEdit, gridBagConstraints);

    nColsLb.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource("/edu/xtec/resources/icons/adjust_cols.gif")));
    nColsLb.setText(":");
    nColsLb.setIconTextGap(0);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    leftPanel.add(nColsLb, gridBagConstraints);

    nColsEdit.setToolTipText(options.getMsg("edit_act_num_cols_tooltip"));
    nColsEdit.setMax(30);
    nColsEdit.setMin(1);
    nColsEdit.addPropertyChangeListener(SmallIntEditor.PROP_VALUE, this);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    leftPanel.add(nColsEdit, gridBagConstraints);

    wildTransparentChk.setToolTipText(options.getMsg("edit_act_wildTransparent_tooltip"));
    wildTransparentChk.setText(options.getMsg("edit_act_wildTransparent"));
    wildTransparentChk.addActionListener(this);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    leftPanel.add(wildTransparentChk, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    leftPanel.add(spacer2, gridBagConstraints);

    widthLb.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource("/edu/xtec/resources/icons/adjust_width.gif")));
    widthLb.setLabelFor(widthEdit);
    widthLb.setText(":");
    widthLb.setIconTextGap(0);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    leftPanel.add(widthLb, gridBagConstraints);

    widthEdit.setToolTipText(options.getMsg("edit_act_cell_width_tooltip"));
    widthEdit.setMax(999);
    widthEdit.setMin(edu.xtec.jclic.Constants.MIN_CELL_SIZE);
    widthEdit.addPropertyChangeListener(SmallIntEditor.PROP_VALUE, this);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    leftPanel.add(widthEdit, gridBagConstraints);

    heightLb.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource("/edu/xtec/resources/icons/adjust_height.gif")));
    heightLb.setLabelFor(heightEdit);
    heightLb.setText(":");
    heightLb.setIconTextGap(0);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    leftPanel.add(heightLb, gridBagConstraints);

    heightEdit.setToolTipText(options.getMsg("edit_act_cell_height_tooltip"));
    heightEdit.setMax(999);
    heightEdit.setMin(edu.xtec.jclic.Constants.MIN_CELL_SIZE);
    heightEdit.addPropertyChangeListener(SmallIntEditor.PROP_VALUE, this);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    leftPanel.add(heightEdit, gridBagConstraints);

    styleLb.setText(options.getMsg("edit_act_style"));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    leftPanel.add(styleLb, gridBagConstraints);

    boxBaseButton.setToolTipText(options.getMsg("edit_style_tooltip"));
    boxBaseButton.addPropertyChangeListener(BoxBaseButton.PROP_BOX_BASE, this);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    leftPanel.add(boxBaseButton, gridBagConstraints);

    borderChk.setText(options.getMsg("edit_act_border"));
    borderChk.addActionListener(this);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    leftPanel.add(borderChk, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    add(leftPanel, gridBagConstraints);

    wordSearchPanel.setLayout(new java.awt.GridBagLayout());

    wordListLb.setText(options.getMsg("edit_act_hiddenWords"));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    wordSearchPanel.add(wordListLb, gridBagConstraints);

    wordListEditor.setToolTipText(options.getMsg("edit_act_hiddenWords_tooltip"));
    wordListEditor.setPreferredSize(new java.awt.Dimension(160, 80));
    wordListEditor.setUpperCase(true);
    wordListEditor.addPropertyChangeListener(TextListEditor.PROP_LIST, this);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
    wordSearchPanel.add(wordListEditor, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    add(wordSearchPanel, gridBagConstraints);
  } // GEN-END:initComponents

  @Override
  public boolean eventPerformed(EventObject ev) {
    boolean result = true;
    if (tgc != null && ev != null && ev.getSource() != null) {
      Object src = ev.getSource();
      if (src == boxBaseButton) {
        boxBaseButtonActionPerformed();
      } else if (src == borderChk) {
        borderChkActionPerformed();
      } else if (src == useGridBChk && isWordSearch) {
        parent.enableGridB(useGridBChk.isSelected());
        return result;
      }

      if (src == widthEdit || src == heightEdit) {
        parent.abcpp.doResize(index, widthEdit.getValue(), heightEdit.getValue(), true);
      } else {
        if (src == nColsEdit || src == nRowsEdit) {
          tgc.ncw = nColsEdit.getValue();
          tgc.nch = nRowsEdit.getValue();
          tgc.completeText();
        }
        parent.abcpp.setActiveBagContent(index, null, null, tgc);
        AbstractBox abx = parent.abcpp.getAbstractBox(index);
        if (abx != null) {
          boxBaseButton.setPreview(abx);
        }
      }
    }
    return result;
  }

  protected void resized() {
    if (tgc != null) {
      widthEdit.setValue((int) tgc.w);
      heightEdit.setValue((int) tgc.h);
    }
  }

  public void fillData(Activity act) {
    tgc = (act != null ? act.tgc : null);
    boxBaseButton.setBoxBase(tgc != null ? tgc.bb : null);
    parent.abcpp.setActiveBagContent(index, null, null, tgc);
    boxBaseButton.setPreview(parent.abcpp.getAbstractBox(index));
    boxBaseButton.setEnabled(tgc != null);
    nColsEdit.setValue(tgc != null ? tgc.ncw : 1);
    nColsEdit.setEnabled(tgc != null);
    nRowsEdit.setValue(tgc != null ? tgc.nch : 1);
    nRowsEdit.setEnabled(tgc != null);
    widthEdit.setValue(tgc != null ? (int) tgc.w : 20);
    widthEdit.setEnabled(tgc != null);
    heightEdit.setValue(tgc != null ? (int) tgc.h : 20);
    heightEdit.setEnabled(tgc != null);
    borderChk.setSelected(tgc != null ? tgc.border : true);
    borderChk.setEnabled(tgc != null);
    if (isWordSearch && (act instanceof WordSearch)) {
      useGridBChk.setSelected(act.abc != null && act.abc[0] != null);
      wordListEditor.setTextList(((WordSearch) act).getClues());
    } else {
      wordListEditor.setTextList(null);
      wordListEditor.setEnabled(false);
    }
    if (!isWordSearch && (act instanceof CrossWord)) {
      wildTransparentChk.setSelected(((CrossWord) act).wildTransparent);
    } else {
      wildTransparentChk.setSelected(false);
    }
  }

  public void saveData(Activity act) {
    if (act != null) {
      act.tgc = tgc;
      if (isWordSearch && (act instanceof WordSearch)) {
        ((WordSearch) act).setClues(wordListEditor.getTextList(), null);
      } else if (!isWordSearch && (act instanceof CrossWord)) {
        ((CrossWord) act).wildTransparent = wildTransparentChk.isSelected();
      }
    }
  }

  private void boxBaseButtonActionPerformed() {
    BoxBase bb = boxBaseButton.getBoxBase();
    if (bb != null && tgc != null) tgc.bb = bb;
  }

  private void borderChkActionPerformed() {
    if (tgc != null) tgc.border = borderChk.isSelected();
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JCheckBox borderChk;
  private edu.xtec.jclic.beans.BoxBaseButton boxBaseButton;
  private edu.xtec.jclic.beans.SmallIntEditor heightEdit;
  private edu.xtec.jclic.beans.SmallIntEditor nColsEdit;
  private edu.xtec.jclic.beans.SmallIntEditor nRowsEdit;
  private javax.swing.JCheckBox useGridBChk;
  private edu.xtec.jclic.beans.SmallIntEditor widthEdit;
  private javax.swing.JCheckBox wildTransparentChk;
  private edu.xtec.jclic.beans.TextListEditor wordListEditor;
  private javax.swing.JLabel wordListLb;
  private javax.swing.JPanel wordSearchPanel;
  // End of variables declaration//GEN-END:variables

}
