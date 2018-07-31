/*
 * File    : JigSawEditor.java
 * Created : 03-dec-2002 10:03
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

package edu.xtec.jclic.shapers;

import edu.xtec.jclic.Constants;
import edu.xtec.jclic.boxes.BoxBase;
import edu.xtec.util.Messages;
import edu.xtec.util.Options;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * @author Francesc Busquets (fbusquets@xtec.cat)
 * @version 13.09.17
 */
public class JigSawEditorPanel extends javax.swing.JPanel {

  Options options;
  JigSaw shaper;
  Image img;
  Rectangle previewArea;
  BoxBase previewBb;
  boolean modified;

  static final int MARGIN = 40;

  /** Creates new form JigSawEditor */
  public JigSawEditorPanel(
      Options options, JigSaw shaper, Dimension previewDim, Image img, BoxBase previewBb) {
    this.options = options;
    this.shaper = shaper;
    this.img = img;
    previewArea =
        new Rectangle(
            img == null ? previewDim : new Dimension(img.getWidth(this), img.getHeight(this)));
    this.previewBb = previewBb;
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  private void initComponents() { // GEN-BEGIN:initComponents
    javax.swing.JLabel toothWidthLb;
    javax.swing.JLabel toothHeightLb;
    java.awt.GridBagConstraints gridBagConstraints;

    controlPanel = new javax.swing.JPanel();
    toothHeightLb = new javax.swing.JLabel();
    toothHeightSlider = new javax.swing.JSlider();
    toothWidthLb = new javax.swing.JLabel();
    toothWidthSlider = new javax.swing.JSlider();
    randomChk = new javax.swing.JCheckBox();
    previewPanel = new PreviewPanel();

    setLayout(new java.awt.BorderLayout());

    controlPanel.setLayout(new java.awt.GridBagLayout());

    toothHeightLb.setText(options.getMsg("edit_act_toothHeightFactor"));
    toothHeightLb.setLabelFor(toothHeightSlider);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    controlPanel.add(toothHeightLb, gridBagConstraints);

    toothHeightSlider.setMinorTickSpacing(5);
    toothHeightSlider.setPaintLabels(true);
    toothHeightSlider.setPaintTicks(true);
    toothHeightSlider.setMajorTickSpacing(25);
    toothHeightSlider.setMaximum(50);
    toothHeightSlider.setValue(
        (int) (shaper.toothHeightFactor * 50 / shaper.getBaseFactor() * 100));
    toothHeightSlider.addChangeListener(
        new javax.swing.event.ChangeListener() {
          public void stateChanged(javax.swing.event.ChangeEvent evt) {
            toothHeightSliderStateChanged(evt);
          }
        });

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    controlPanel.add(toothHeightSlider, gridBagConstraints);

    toothWidthLb.setText(options.getMsg("edit_act_toothWidthFactor"));
    toothWidthLb.setLabelFor(toothWidthSlider);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    controlPanel.add(toothWidthLb, gridBagConstraints);

    toothWidthSlider.setMinorTickSpacing(5);
    toothWidthSlider.setPaintLabels(true);
    toothWidthSlider.setPaintTicks(true);
    toothWidthSlider.setMajorTickSpacing(25);
    toothWidthSlider.setValue((int) (shaper.baseWidthFactor * 100));
    toothWidthSlider.addChangeListener(
        new javax.swing.event.ChangeListener() {
          public void stateChanged(javax.swing.event.ChangeEvent evt) {
            toothWidthSliderStateChanged(evt);
          }
        });

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    controlPanel.add(toothWidthSlider, gridBagConstraints);

    randomChk.setSelected(shaper.randomLines);
    randomChk.setText(options.getMsg("edit_act_toothRandom"));
    randomChk.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            randomChkActionPerformed(evt);
          }
        });

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    controlPanel.add(randomChk, gridBagConstraints);

    add(controlPanel, java.awt.BorderLayout.NORTH);

    previewPanel.setBorder(new javax.swing.border.TitledBorder(options.getMsg("edit_act_preview")));
    previewPanel.setPreferredSize(
        new Dimension(previewArea.width + MARGIN, previewArea.height + MARGIN));
    add(previewPanel, java.awt.BorderLayout.CENTER);
  } // GEN-END:initComponents

  private void toothHeightSliderStateChanged(
      javax.swing.event.ChangeEvent evt) { // GEN-FIRST:event_toothHeightSliderStateChanged

    shaper.toothHeightFactor =
        ((double) toothHeightSlider.getValue() * shaper.getBaseFactor() / 50) / 100;
    ((PreviewPanel) previewPanel).updateView();
  } // GEN-LAST:event_toothHeightSliderStateChanged

  private void toothWidthSliderStateChanged(
      javax.swing.event.ChangeEvent evt) { // GEN-FIRST:event_toothWidthSliderStateChanged

    shaper.baseWidthFactor = ((double) toothWidthSlider.getValue()) / 100;
    ((PreviewPanel) previewPanel).updateView();
  } // GEN-LAST:event_toothWidthSliderStateChanged

  private void randomChkActionPerformed(
      java.awt.event.ActionEvent evt) { // GEN-FIRST:event_randomChkActionPerformed

    shaper.randomLines = randomChk.isSelected();
    ((PreviewPanel) previewPanel).updateView();
  } // GEN-LAST:event_randomChkActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JCheckBox randomChk;
  private javax.swing.JSlider toothHeightSlider;
  private javax.swing.JPanel controlPanel;
  private javax.swing.JPanel previewPanel;
  private javax.swing.JSlider toothWidthSlider;
  // End of variables declaration//GEN-END:variables

  class PreviewPanel extends JPanel {

    List<Shape> shapes;

    PreviewPanel() {
      this.shapes = new ArrayList<Shape>();
    }

    public void updateView() {
      shapes.clear();
      shaper.reset();
      for (int i = 0; i < shaper.getNumCells(); i++) {
        shapes.add(shaper.getShape(i, previewArea));
      }
      repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      RenderingHints rh = g2.getRenderingHints();
      g2.setRenderingHints(Constants.DEFAULT_RENDERING_HINTS);

      Color defaultBgColor = g2.getBackground();
      Color defaultColor = g2.getColor();

      g2.setColor(previewBb.backColor);
      g2.fill(previewArea);
      g2.setBackground(previewBb.backColor);
      g2.setColor(previewBb.borderColor);
      Stroke defaultStroke = g2.getStroke();
      g2.setStroke(previewBb.getBorder());

      if (img != null) g2.drawImage(img, previewArea.x, previewArea.y, this);

      for (int i = 0; i < shapes.size(); i++) g2.draw((Shape) shapes.get(i));

      g2.setStroke(defaultStroke);
      g2.setColor(defaultColor);
      g2.setBackground(defaultBgColor);

      g2.setRenderingHints(rh);
    }

    @Override
    public void doLayout() {
      previewArea.x = (getBounds().width - previewArea.width) / 2;
      previewArea.y = (getBounds().height - previewArea.height) / 2;
      updateView();
    }
  }

  public static Shaper getShaper(
      Shaper initialShaper,
      Component parent,
      Options options,
      Dimension dim,
      Image img,
      BoxBase bb) {
    Messages msg = options.getMessages();
    if (initialShaper == null || !(initialShaper instanceof JigSaw)) return null;

    JigSaw sh;
    try {
      sh = (JigSaw) initialShaper.clone();
    } catch (CloneNotSupportedException ex) {
      msg.showErrorWarning(parent, "edit_act_shaper_err", ex);
      return null;
    }

    JigSawEditorPanel jse = new JigSawEditorPanel(options, sh, dim, img, bb);
    boolean b = msg.showInputDlg(parent, jse, "edit_act_shaper_properties");
    return b ? sh : null;
  }
}
