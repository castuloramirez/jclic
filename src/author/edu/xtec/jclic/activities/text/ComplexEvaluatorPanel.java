/*
 * File    : ComplexEvaluatorPanel.java
 * Created : 25-jun-2003 13:07
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

package edu.xtec.jclic.activities.text;

import edu.xtec.util.Options;

/**
 * @author Francesc Busquets (fbusquets@xtec.cat)
 * @version 1.0
 */
public class ComplexEvaluatorPanel extends javax.swing.JPanel {

  Options options;

  /** Creates new form ComplexEvaluatorPanel */
  public ComplexEvaluatorPanel(Options options) {
    this.options = options;
    initComponents();
  }

  public void setEvaluator(ComplexEvaluator ev) {
    detailChk.setSelected(ev.detail);
    detailSteps.setValue(ev.checkSteps);
    detailField.setValue(ev.checkScope);
    checkEnabled();
  }

  public void collectData(ComplexEvaluator ev) {
    ev.detail = detailChk.isSelected();
    ev.checkSteps = detailSteps.getValue();
    ev.checkScope = detailField.getValue();
  }

  protected void checkEnabled() {
    detailSteps.setEnabled(detailChk.isSelected());
    detailField.setEnabled(detailChk.isSelected());
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  private void initComponents() { // GEN-BEGIN:initComponents
    javax.swing.JLabel detailLb;
    javax.swing.JLabel detailStepsLb;
    javax.swing.JLabel detailFieldLb;
    java.awt.GridBagConstraints gridBagConstraints;

    detailChk = new javax.swing.JCheckBox();
    detailLb = new javax.swing.JLabel();
    detailStepsLb = new javax.swing.JLabel();
    detailSteps = new edu.xtec.jclic.beans.SmallIntEditor();
    detailFieldLb = new javax.swing.JLabel();
    detailField = new edu.xtec.jclic.beans.SmallIntEditor();

    setLayout(new java.awt.GridBagLayout());

    setBorder(
        new javax.swing.border.TitledBorder(
            options.getMsg("edit_text_act_advancedCorrectionOptions")));
    detailChk.setText(options.getMsg("edit_text_act_checkDetail"));
    detailChk.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            detailChkActionPerformed(evt);
          }
        });

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    add(detailChk, gridBagConstraints);

    detailLb.setText(options.getMsg("edit_text_act_checkDetail_params"));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    add(detailLb, gridBagConstraints);

    detailStepsLb.setText(options.getMsg("edit_text_act_checkDetail_steps"));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    add(detailStepsLb, gridBagConstraints);

    detailSteps.setMax(99);
    detailSteps.setMin(1);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 1.0;
    add(detailSteps, gridBagConstraints);

    detailFieldLb.setText(options.getMsg("edit_text_act_checkDetail_field"));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    add(detailFieldLb, gridBagConstraints);

    detailField.setMax(99);
    detailField.setMin(1);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    add(detailField, gridBagConstraints);
  } // GEN-END:initComponents

  private void detailChkActionPerformed(
      java.awt.event.ActionEvent evt) { // GEN-FIRST:event_detailChkActionPerformed

    checkEnabled();
  } // GEN-LAST:event_detailChkActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private edu.xtec.jclic.beans.SmallIntEditor detailField;
  private javax.swing.JCheckBox detailChk;
  private edu.xtec.jclic.beans.SmallIntEditor detailSteps;
  // End of variables declaration//GEN-END:variables

}
