/*
 * File    : ExportTaskDlg.java
 * Created : 24-nov-2015 16:37
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

import edu.xtec.util.Messages;
import edu.xtec.util.Options;
import edu.xtec.util.ResourceBridge;
import edu.xtec.util.StrUtils;
import edu.xtec.util.StreamIO;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Francesc Busquets (fbusquets@xtec.cat)
 * @version 24.11.15
 */
public class ExportTaskDlg extends javax.swing.JPanel {

  ResourceBridge rb;
  Options options;
  edu.xtec.util.SwingWorker sw;

  static String indexHtml
          = "<!DOCTYPE html>\n"
          + "<html>\n"
          + "  <head>\n"
          + "    <meta charset=\"UTF-8\">\n"
          + "    <title>%TITLE%</title>\n"
          + "    <meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n"
          + "    <meta name=\"mobile-web-app-capable\" content=\"yes\">\n"
          + "    <meta name=\"application-name\" content=\"%TITLE%\">\n"
          + "    <link rel=\"shortcut icon\" href=\"favicon.ico\">\n"
          + "    <link rel=\"icon\" sizes=\"16x16\" href=\"favicon.ico\">\n"
          + "    <link rel=\"icon\" sizes=\"72x72\" href=\"icons/icon-72.png\">\n"
          + "    <link rel=\"icon\" sizes=\"192x192\" href=\"icon-192.png\">\n"
          + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
          + "    <script type=\"text/javascript\" src=\"https://clic.xtec.cat/dist/jclic.js/jclic.min.js\"></script>\n"
          + "  </head>\n"
          + "  <body style=\"margin:0\">\n"
          + "    <div class =\"JClic\" data-project=\"%MAINFILE%\"></div>\n"
          + "  </body>\n"
          + "</html>";

  /**
   * Creates new ExportTaskDlg
   */
  public ExportTaskDlg(ResourceBridge rb) {
    this.rb = rb;
    options = rb.getOptions();
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    logArea = new javax.swing.JTextArea();
    javax.swing.JPanel bottomPanel = new javax.swing.JPanel();
    copyBtn = new javax.swing.JButton();
    cancelBtn = new javax.swing.JButton();

    setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    setLayout(new java.awt.BorderLayout(0, 10));

    jScrollPane1.setPreferredSize(new java.awt.Dimension(600, 500));

    logArea.setEditable(false);
    jScrollPane1.setViewportView(logArea);

    add(jScrollPane1, java.awt.BorderLayout.CENTER);

    bottomPanel.setLayout(new java.awt.BorderLayout());

    copyBtn.setText(options.getMsg("export_project_copyLog"));
    copyBtn.setEnabled(false);
    copyBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        copyBtnActionPerformed(evt);
      }
    });
    bottomPanel.add(copyBtn, java.awt.BorderLayout.WEST);

    cancelBtn.setText(options.getMsg("CANCEL"));
    cancelBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cancelBtnActionPerformed(evt);
      }
    });
    bottomPanel.add(cancelBtn, java.awt.BorderLayout.EAST);

    add(bottomPanel, java.awt.BorderLayout.SOUTH);
  }// </editor-fold>//GEN-END:initComponents

  private void copyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyBtnActionPerformed

    StringSelection stringSelection = new StringSelection(logArea.getText());
    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
    clpbrd.setContents(stringSelection, null);

  }//GEN-LAST:event_copyBtnActionPerformed

  private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed

    if (sw != null) {
      ProjectFileUtils.interrupt = true;
    } else {
      JDialog myDlg = (JDialog) javax.swing.SwingUtilities.getAncestorOfClass(JDialog.class, this);
      if (myDlg != null) {
        myDlg.dispose();
      }
    }
  }//GEN-LAST:event_cancelBtnActionPerformed

  public static void doTask(ResourceBridge rb, Component parent,
          final String inputPath, final String outputPath, final String mainFileName,
          final String projectTitle, final boolean copyAll) {

    final Messages msg = rb.getOptions().getMessages();
    final ExportTaskDlg exportDlg = new ExportTaskDlg(rb);

    final PrintStream ps = new PrintStream(new ByteArrayOutputStream()) {
      @Override
      public void println(String s) {
        exportDlg.logArea.append(s + "\n");
        exportDlg.logArea.setCaretPosition(exportDlg.logArea.getText().length());
      }

      @Override
      public void print(String s) {
        exportDlg.logArea.append(s);
        exportDlg.logArea.setCaretPosition(exportDlg.logArea.getText().length());
      }
    };

    JDialog dlg = new JDialog(JOptionPane.getFrameForComponent(parent), true);
    dlg.setTitle(msg.get("export_project_exporting"));
    dlg.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    dlg.getContentPane().setLayout(new BorderLayout(10, 10));
    dlg.getContentPane().add(exportDlg, BorderLayout.CENTER);
    dlg.pack();
    dlg.setLocationRelativeTo(parent);
    dlg.setLocation((parent.getWidth() - dlg.getWidth()) / 2, (parent.getHeight() - dlg.getHeight()) / 2);

    exportDlg.sw = new edu.xtec.util.SwingWorker() {

      @Override
      public Object construct() {
        try {

          if (!copyAll) {
            ps.println("Processing: " + inputPath);
            ProjectFileUtils pfu = new ProjectFileUtils(inputPath);
            pfu.normalizeFileNames(ps);
            pfu.avoidZipLinks(ps);
            pfu.saveTo(outputPath, ps);
          } else {
            ps.println("Processing al projects in: " + inputPath);
            ProjectFileUtils.processFolder(inputPath, outputPath, ps);
          }

          ps.println("Generating file " + outputPath + "/index.html");
          FileOutputStream fos = new FileOutputStream(new File(outputPath, "index.html"));
          PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos, "UTF-8"));
          String s = StrUtils.replace(indexHtml, "%TITLE%", projectTitle);
          s = StrUtils.replace(s, "%MAINFILE%", mainFileName);
          pw.print(s);
          pw.flush();
          pw.close();

          ps.println("Copying favicon.ico");
          StreamIO.writeStreamTo(getClass().getResourceAsStream("/edu/xtec/resources/icons/favicon.ico"),
                  new FileOutputStream(new File(outputPath, "favicon.ico")));

          ps.println("Copying icon-192.png");
          StreamIO.writeStreamTo(getClass().getResourceAsStream("/edu/xtec/resources/icons/icon-192.png"),
                  new FileOutputStream(new File(outputPath, "icon-192.png")));

          ps.println("Copying icon-72.png");
          StreamIO.writeStreamTo(getClass().getResourceAsStream("/edu/xtec/resources/icons/icon-72.png"),
                  new FileOutputStream(new File(outputPath, "icon-72.png")));

          ps.println("\nProject successfully exported to " + outputPath);

        } catch (InterruptedException iex) {
          ps.println("\nWARNING: The process was interrupted! Contents of the output folder might be unsuitable.");
        } catch (Exception ex) {
          ps.println("\nERROR processing ZIP file: " + ex.getMessage());
        }
        return null;
      }

      @Override
      public void finished() {
        exportDlg.cancelBtn.setText(msg.get("OK"));
        exportDlg.copyBtn.setEnabled(true);
        exportDlg.sw = null;
      }
    };

    exportDlg.sw.start();

    dlg.setVisible(true);

  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton cancelBtn;
  private javax.swing.JButton copyBtn;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea logArea;
  // End of variables declaration//GEN-END:variables

}
