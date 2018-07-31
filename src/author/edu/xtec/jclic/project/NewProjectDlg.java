/*
 * File    : NewProjectDlg.java
 * Created : 08-jul-2004 14:32
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

package edu.xtec.jclic.project;

import edu.xtec.jclic.fileSystem.FileSystem;
import edu.xtec.jclic.misc.Utils;
import edu.xtec.util.Messages;
import edu.xtec.util.Options;
import edu.xtec.util.ResourceBridge;
import edu.xtec.util.SimpleFileFilter;
import edu.xtec.util.StrUtils;
import java.awt.Component;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author Francesc Busquets (fbusquets@xtec.cat)
 * @version 13.08.09
 */
public class NewProjectDlg extends javax.swing.JPanel {

  ResourceBridge rb;
  Options options;
  public String name;
  public String fileName;
  public String folder;
  String baseFolder;

  /** Creates new form NewProjectDlg */
  public NewProjectDlg(ResourceBridge rb, String baseFolder) {
    this.rb = rb;
    options = rb.getOptions();
    this.baseFolder = baseFolder.replace('/', File.separatorChar);
    folder = baseFolder;
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    nameLb = new javax.swing.JLabel();
    nameText = new javax.swing.JTextField();
    nameText.getDocument().addDocumentListener(dl);
    spacer = new javax.swing.JLabel();
    fileNameLb = new javax.swing.JLabel();
    fileNameText = new javax.swing.JTextField();
    fileNameText.getDocument().addDocumentListener(dl);
    extLb = new javax.swing.JLabel();
    folderLb = new javax.swing.JLabel();
    folderText = new javax.swing.JTextField();
    folderBtn = new javax.swing.JButton();

    setLayout(new java.awt.GridBagLayout());

    nameLb.setLabelFor(nameText);
    nameLb.setText(options.getMsg("edit_new_project_name"));
    nameLb.setToolTipText(options.getMsg("edit_new_project_name_tooltip"));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
    add(nameLb, gridBagConstraints);

    nameText.setToolTipText(options.getMsg("edit_new_project_name_tooltip"));
    nameText.setPreferredSize(new java.awt.Dimension(100, 20));
    nameText.setMinimumSize(new java.awt.Dimension(100, 21));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 1);
    add(nameText, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    add(spacer, gridBagConstraints);

    fileNameLb.setText(options.getMsg("edit_new_project_filename"));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
    add(fileNameLb, gridBagConstraints);

    fileNameText.setToolTipText(options.getMsg("edit_new_project_filename_tooltip"));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 1);
    add(fileNameText, gridBagConstraints);

    extLb.setText(Utils.EXT_JCLIC_ZIP);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(4, 1, 4, 4);
    add(extLb, gridBagConstraints);

    folderLb.setLabelFor(folderText);
    folderLb.setText(options.getMsg("edit_new_project_folder"));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
    add(folderLb, gridBagConstraints);

    folderText.setToolTipText(options.getMsg("edit_new_project_folder_tooltip"));
    folderText.setPreferredSize(new java.awt.Dimension(280, 21));
    folderText.setMinimumSize(new java.awt.Dimension(280, 21));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
    add(folderText, gridBagConstraints);

    folderBtn.setText(options.getMsg("edit_new_project_folder_browse"));
    folderBtn.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            folderBtnActionPerformed(evt);
          }
        });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
    add(folderBtn, gridBagConstraints);
  } // </editor-fold>//GEN-END:initComponents

  private JFileChooser chooser;

  private void folderBtnActionPerformed(
      java.awt.event.ActionEvent evt) { // GEN-FIRST:event_folderBtnActionPerformed

    if (chooser == null) {
      chooser = new JFileChooser(baseFolder);
      chooser.setDialogType(JFileChooser.DIRECTORIES_ONLY);
    }
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      baseFolder = chooser.getCurrentDirectory().getAbsolutePath();
      folderText.setText(baseFolder);
    }
  } // GEN-LAST:event_folderBtnActionPerformed

  public void fillData() {
    nameText.setText(name == null ? "" : name);
    fileNameText.setText(fileName == null ? "" : fileName);
    folderText.setText(folder == null ? "" : folder);
  }

  public void getData() {
    name = StrUtils.nullableString(nameText.getText());
    fileName = StrUtils.nullableString(fileNameText.getText());
    folder = StrUtils.nullableString(folderText.getText());
  }

  DocumentListener dl =
      new DocumentListener() {
        public void changedUpdate(DocumentEvent e) {
          changeName(e);
        }

        public void insertUpdate(DocumentEvent e) {
          changeName(e);
        }

        public void removeUpdate(DocumentEvent e) {
          changeName(e);
        }

        protected void changeName(DocumentEvent e) {
          String s;
          boolean fromName = false;
          if (nameText.getDocument() == e.getDocument()) {
            s = nameText.getText();
            fromName = true;
          } else s = fileNameText.getText();
          s = FileSystem.getValidFileName(s.trim());
          if (fromName) fileNameText.setText(s);
          if (s.length() > 0) {
            StringBuilder sb = new StringBuilder(baseFolder);
            if (!baseFolder.endsWith(File.separator)) sb.append(File.separatorChar);
            sb.append(s);
            folderText.setText(sb.substring(0));
          }
        }
      };

  public static JClicProject prompt(ResourceBridge rb, Component parent, FileSystem baseFS) {
    JClicProject result = null;
    NewProjectDlg prjDlg = new NewProjectDlg(rb, baseFS.root);
    Messages msg = rb.getOptions().getMessages();
    prjDlg.fillData();
    while (result == null) {
      if (!msg.showInputDlg(parent, prjDlg, "edit_new_project_title")) break;
      prjDlg.getData();
      if (prjDlg.name == null || prjDlg.fileName == null || prjDlg.folder == null) {
        msg.showErrorWarning(parent, "edit_new_project_err_empty", null);
      } else {
        try {
          boolean folderOk = true;
          File folderFile = new File(prjDlg.folder);
          if (folderFile.exists() && folderFile.isDirectory()) {
            FilenameFilter ff =
                ((SimpleFileFilter) Utils.getFileFilter(Utils.ALL_JCLIC_CLIC_FF, msg))
                    .getFilenameFilter();
            if (folderFile.list(ff).length > 0) {
              folderOk =
                  msg.showQuestionDlg(parent, "edit_new_project_warning_noEmptyFolder", null, "yn")
                      == Messages.YES;
            }
          } else {
            if (!folderFile.mkdirs()) {
              msg.showErrorWarning(parent, "edit_new_project_err_folderCreation", null);
              folderOk = false;
            }
          }
          if (folderOk) {
            File projectFile = new File(folderFile, prjDlg.fileName + Utils.EXT_JCLIC_ZIP);
            if (msg.confirmOverwriteFile(parent, projectFile, "yn") == Messages.YES) {
              result =
                  new JClicProject(
                      rb,
                      new FileSystem(folderFile.getAbsolutePath(), rb),
                      projectFile.getAbsolutePath());
              result.settings.title = prjDlg.name;
              result.setName(prjDlg.fileName);
              result.saveProject(projectFile.getAbsolutePath());
            }
          }
        } catch (Exception ex) {
          msg.showErrorWarning(parent, "ERROR", ex);
        }
      }
    }
    return result;
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel extLb;
  private javax.swing.JLabel fileNameLb;
  private javax.swing.JTextField fileNameText;
  private javax.swing.JButton folderBtn;
  private javax.swing.JLabel folderLb;
  private javax.swing.JTextField folderText;
  private javax.swing.JLabel nameLb;
  private javax.swing.JTextField nameText;
  private javax.swing.JLabel spacer;
  // End of variables declaration//GEN-END:variables

}
