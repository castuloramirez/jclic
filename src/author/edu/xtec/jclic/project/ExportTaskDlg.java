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

import edu.xtec.jclic.AuthorSettings;
import edu.xtec.util.BrowserLauncher;
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.ListIterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 * @author Francesc Busquets (fbusquets@xtec.cat)
 * @version 24.11.15
 */
public class ExportTaskDlg extends javax.swing.JPanel {

  ResourceBridge rb;
  Options options;
  edu.xtec.util.SwingWorker sw;
  File exportPath;

  static String indexHtml =
      "<!DOCTYPE html>\n"
          + "<html>\n"
          + "  <head>\n"
          + "    <meta charset=\"UTF-8\">\n"
          + "    <title>%TITLE%</title>\n"
          + "    <meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n"
          + "    <meta name=\"mobile-web-app-capable\" content=\"yes\">\n"
          + "    <meta name=\"application-name\" content=\"%TITLE%\">\n"
          + "    <link rel=\"shortcut icon\" href=\"%FAVICONICO%\">\n"
          + "    <link rel=\"icon\" sizes=\"16x16\" href=\"%FAVICONICO%\">\n"
          + "    <link rel=\"icon\" sizes=\"72x72\" href=\"%ICON72PNG%\">\n"
          + "    <link rel=\"icon\" sizes=\"192x192\" href=\"%ICON192PNG%\">\n"
          + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
          + "    <script type=\"text/javascript\" src=\"%JSCODEBASE%\"></script>\n"
          + "  </head>\n"
          + "  <body style=\"margin:0\">\n"
          + "    <div class =\"JClic\" data-project=\"%MAINFILE%\"></div>\n"
          + "  </body>\n"
          + "</html>";

  static String imsmanifest =
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
          + "<!-- Generated by JClic - https://projectestac.github.io/jclic -->\n"
          + "<manifest identifier=\"%SCORMID%\" version=\"1.0\" xmlns=\"http://www.imsproject.org/xsd/imscp_rootv1p1p2\" "
          + "xmlns:adlcp=\"http://www.adlnet.org/xsd/adlcp_rootv1p2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
          + "xsi:schemaLocation= \"http://www.imsproject.org/xsd/imscp_rootv1p1p2 imscp_rootv1p1p2.xsd http://www.imsglobal.org/xsd/imsmd_rootv1p2p1 imsmd_rootv1p2p1.xsd http://www.adlnet.org/xsd/adlcp_rootv1p2 adlcp_rootv1p2.xsd\">\n"
          + " <metadata> \n"
          + "  <schema>ADL SCORM</schema>\n"
          + "  <schemaversion>1.2</schemaversion>\n"
          + " </metadata> \n"
          + " <organizations default=\"JClic\">  \n"
          + "  <organization identifier=\"JClic\">  \n"
          + "   <title>%SCORMTITLE%</title>\n"
          + "   <item identifier=\"ITEM_%SCORMID%\" identifierref=\"RES_%SCORMID%\">\n"
          + "    <title>%SCORMTITLE%</title>\n"
          + "   </item>\n"
          + "  </organization>\n"
          + " </organizations>\n"
          + " <resources>\n"
          + "  <resource identifier=\"RES_%SCORMID%\" type=\"webcontent\" href=\"/index.html\" adlcp:scormtype=\"sco\"> \n"
          + "%FILETAGS%  </resource>\n"
          + " </resources>\n"
          + "</manifest>";

  /**
   * Creates new ExportTaskDlg
   *
   * @param rb
   */
  public ExportTaskDlg(ResourceBridge rb) {
    this.rb = rb;
    options = rb.getOptions();
    initComponents();
  }

  PrintStream ps =
      new PrintStream(new ByteArrayOutputStream()) {
        @Override
        public synchronized void println(String s) {
          logArea.append(s + "\n");
          logArea.setCaretPosition(logArea.getText().length());
        }

        @Override
        public synchronized void print(String s) {
          logArea.append(s);
          logArea.setCaretPosition(logArea.getText().length());
        }
      };

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    logArea = new javax.swing.JTextArea();
    javax.swing.JPanel bottomPanel = new javax.swing.JPanel();
    copyBtn = new javax.swing.JButton();
    browserBtn = new javax.swing.JButton();
    cancelBtn = new javax.swing.JButton();

    setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    setLayout(new java.awt.BorderLayout(0, 10));

    jScrollPane1.setPreferredSize(new java.awt.Dimension(600, 500));

    logArea.setEditable(false);
    logArea.setLineWrap(true);
    logArea.setWrapStyleWord(true);
    jScrollPane1.setViewportView(logArea);

    add(jScrollPane1, java.awt.BorderLayout.CENTER);

    bottomPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

    copyBtn.setText(options.getMsg("export_project_copyLog"));
    copyBtn.setEnabled(false);
    copyBtn.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            copyBtnActionPerformed(evt);
          }
        });
    bottomPanel.add(copyBtn);

    browserBtn.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource("/edu/xtec/resources/icons/html_doc.gif"))); // NOI18N
    browserBtn.setText(options.getMsg("export_project_launchBrowser"));
    browserBtn.setEnabled(false);
    browserBtn.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            browserBtnActionPerformed(evt);
          }
        });
    bottomPanel.add(browserBtn);

    cancelBtn.setText(options.getMsg("CANCEL"));
    cancelBtn.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            cancelBtnActionPerformed(evt);
          }
        });
    bottomPanel.add(cancelBtn);

    add(bottomPanel, java.awt.BorderLayout.SOUTH);
  } // </editor-fold>//GEN-END:initComponents

  private void copyBtnActionPerformed(
      java.awt.event.ActionEvent evt) { // GEN-FIRST:event_copyBtnActionPerformed

    StringSelection stringSelection = new StringSelection(logArea.getText());
    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
    clpbrd.setContents(stringSelection, null);
  } // GEN-LAST:event_copyBtnActionPerformed

  private void cancelBtnActionPerformed(
      java.awt.event.ActionEvent evt) { // GEN-FIRST:event_cancelBtnActionPerformed

    if (sw != null) {
      ProjectFileUtils.interrupt = true;
    } else {
      JDialog myDlg = (JDialog) javax.swing.SwingUtilities.getAncestorOfClass(JDialog.class, this);
      if (myDlg != null) {
        myDlg.dispose();
      }
    }
  } // GEN-LAST:event_cancelBtnActionPerformed

  private void browserBtnActionPerformed(
      java.awt.event.ActionEvent evt) { // GEN-FIRST:event_browserBtnActionPerformed

    String url = exportPath + "/index.html";
    BrowserLauncher.openURL(url);
    ps.println("File " + url + " opened in web browser.");
  } // GEN-LAST:event_browserBtnActionPerformed

  public static void doTask(
      ResourceBridge rb,
      AuthorSettings settings,
      Component parent,
      final String inputPath,
      final String outputPath,
      final String mainFileName,
      final JClicProject project,
      final boolean copyAll,
      final String scormFile) {

    final Messages msg = rb.getOptions().getMessages();
    final ExportTaskDlg exportDlg = new ExportTaskDlg(rb);
    final AuthorSettings set = settings;

    JDialog dlg = new JDialog(JOptionPane.getFrameForComponent(parent), true);
    dlg.setTitle(msg.get("export_project_exporting"));
    dlg.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    dlg.getContentPane().setLayout(new BorderLayout(10, 10));
    dlg.getContentPane().add(exportDlg, BorderLayout.CENTER);
    dlg.pack();
    dlg.setLocationRelativeTo(parent);
    dlg.setLocation(
        (parent.getWidth() - dlg.getWidth()) / 2, (parent.getHeight() - dlg.getHeight()) / 2);

    exportDlg.sw =
        new edu.xtec.util.SwingWorker() {

          @Override
          public Object construct() {
            try {

              long[] counters = {0, 0, 0};

              ArrayList<String> fileList = new ArrayList<String>();
              fileList.add("imsmanifest.xml");

              if (!copyAll) {
                exportDlg.ps.println("Processing: " + inputPath);
                ProjectFileUtils.processSingleFile(
                    inputPath, outputPath, fileList, exportDlg.ps, counters);
              } else {
                exportDlg.ps.println("Processing all projects in: " + inputPath);
                ProjectFileUtils.processRootFolder(
                    inputPath, outputPath, fileList, exportDlg.ps, counters);
              }

              // Get normalized filenames
              project.mediaBag.setNormalizedFileNames();

              exportDlg.ps.println("Generating file " + outputPath + "/index.html");
              FileOutputStream fos = new FileOutputStream(new File(outputPath, "index.html"));
              PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos, "UTF-8"));
              String s = indexHtml.replaceAll("%TITLE%", StrUtils.safeHtml(project.settings.title));
              s = StrUtils.replace(s, "%MAINFILE%", mainFileName);
              s = StrUtils.replace(s, "%JSCODEBASE%", set.jsCodeBase);
              s =
                  StrUtils.replace(
                      s,
                      "%FAVICONICO%",
                      project.settings.icon16 == null ? "favicon.ico" : project.settings.icon16);
              s =
                  StrUtils.replace(
                      s,
                      "%ICON72PNG%",
                      project.settings.icon72 == null ? "icon-72.png" : project.settings.icon72);
              s =
                  StrUtils.replace(
                      s,
                      "%ICON192PNG%",
                      project.settings.icon192 == null ? "icon-192.png" : project.settings.icon192);

              pw.print(s);
              pw.flush();
              counters[ProjectFileUtils.TOTAL_FILESIZE] += fos.getChannel().size();
              pw.close();
              fileList.add("index.html");

              JSONObject json = project.settings.toJSON(msg);
              json.put("mainFile", mainFileName);

              exportDlg.exportPath = new File(outputPath);

              String fn = "favicon.ico";
              if (project.settings.icon16 == null) {
                exportDlg.ps.println("Copying " + fn);
                fos = new FileOutputStream(new File(outputPath, fn));
                counters[ProjectFileUtils.TOTAL_FILESIZE] +=
                    StreamIO.writeStreamTo(
                        getClass().getResourceAsStream("/edu/xtec/resources/icons/" + fn), fos);
                fos.close();
                fileList.add(fn);
                counters[ProjectFileUtils.NUM_MEDIA]++;
              }

              if (project.settings.icon192 == null) {
                fn = "icon-192.png";
                exportDlg.ps.println("Copying " + fn);
                fos = new FileOutputStream(new File(outputPath, fn));
                counters[ProjectFileUtils.TOTAL_FILESIZE] +=
                    StreamIO.writeStreamTo(
                        getClass().getResourceAsStream("/edu/xtec/resources/icons/" + fn), fos);
                fos.close();
                fileList.add(fn);
                counters[ProjectFileUtils.NUM_MEDIA]++;
              }

              if (project.settings.icon72 == null) {
                fn = "icon-72.png";
                exportDlg.ps.println("Copying " + fn);
                fos = new FileOutputStream(new File(outputPath, fn));
                counters[ProjectFileUtils.TOTAL_FILESIZE] +=
                    StreamIO.writeStreamTo(
                        getClass().getResourceAsStream("/edu/xtec/resources/icons/" + fn), fos);
                fos.close();
                fileList.add(fn);
                counters[ProjectFileUtils.NUM_MEDIA]++;
              }

              String cover = project.settings.coverFileName;
              if (cover == null) {
                exportDlg.ps.println("Copying project cover model");
                cover = "project-cover.jpg";
                fos = new FileOutputStream(new File(outputPath, cover));
                counters[ProjectFileUtils.TOTAL_FILESIZE] +=
                    StreamIO.writeStreamTo(
                        getClass().getResourceAsStream("/edu/xtec/resources/icons/cover-base.jpg"),
                        fos);
                fos.close();
                ;
                fileList.add(cover);
                counters[ProjectFileUtils.NUM_MEDIA]++;
              } else {
                cover = project.mediaBag.getElement(cover).getFileName();
              }
              json.put("cover", cover);

              String thumb = project.settings.thumbnailFileName;
              if (thumb == null) {
                thumb = "project-thumb.jpg";
                exportDlg.ps.println("Copying project thumbnail model");
                fos = new FileOutputStream(new File(outputPath, thumb));
                counters[ProjectFileUtils.TOTAL_FILESIZE] +=
                    StreamIO.writeStreamTo(
                        getClass().getResourceAsStream("/edu/xtec/resources/icons/thumb-base.jpg"),
                        fos);
                fos.close();
                fileList.add(thumb);
                counters[ProjectFileUtils.NUM_MEDIA]++;
              } else {
                thumb = project.mediaBag.getElement(thumb).getFileName();
              }
              json.put("thumbnail", thumb);

              exportDlg.ps.println("Generating project.json");
              fileList.add("project.json");

              // Order fileList and remove duplicate values
              Collections.sort(fileList);
              fileList = new ArrayList<String>(new LinkedHashSet<String>(fileList));

              // Write imsmanifest
              exportDlg.ps.println("Generating imsmanifest.xml");
              fos = new FileOutputStream(new File(outputPath, "imsmanifest.xml"));
              pw = new PrintWriter(new OutputStreamWriter(fos, "UTF-8"));
              s = imsmanifest.replaceAll("%SCORMTITLE%", StrUtils.safeHtml(project.settings.title));
              s =
                  StrUtils.replace(
                      s,
                      "%SCORMID%",
                      "JClic-"
                          + Integer.toHexString((int) (Math.random() * 0xEFFFFF + 0x100000))
                              .toUpperCase());
              StringBuilder sb = new StringBuilder();
              ListIterator<String> it = fileList.listIterator();
              while (it.hasNext()) {
                sb.append("   <file href=\"").append(it.next()).append("\"/>\n");
              }
              s = StrUtils.replace(s, "%FILETAGS%", sb.toString());
              pw.print(s);
              pw.flush();
              counters[ProjectFileUtils.TOTAL_FILESIZE] += fos.getChannel().size();
              pw.close();

              // Save statistics and file list
              json.put("activities", counters[ProjectFileUtils.NUM_ACTS]);
              json.put("mediaFiles", counters[ProjectFileUtils.NUM_MEDIA]);
              json.put("totalSize", counters[ProjectFileUtils.TOTAL_FILESIZE]);
              json.append("files", fileList);
              counters[ProjectFileUtils.TOTAL_FILESIZE] += json.toString(2).length();
              json.put("totalSize", counters[ProjectFileUtils.TOTAL_FILESIZE]);

              // Write project.json
              fos = new FileOutputStream(new File(outputPath, "project.json"));
              pw = new PrintWriter(new OutputStreamWriter(fos, "UTF-8"));
              pw.print(json.toString(2));
              pw.flush();
              pw.close();

              if (scormFile != null) {
                final byte[] BUFFER = new byte[1024];
                exportDlg.ps.println(
                    "Generating file \"" + scormFile + "\" with all content inside");

                ZipOutputStream out = new ZipOutputStream(new FileOutputStream(scormFile));
                it = fileList.listIterator();
                while (it.hasNext()) {
                  String file = it.next();
                  FileInputStream in = new FileInputStream(new File(outputPath, file));
                  ZipEntry entry = new ZipEntry(file);
                  out.putNextEntry(entry);
                  int i;
                  while ((i = in.read(BUFFER)) != -1) {
                    out.write(BUFFER, 0, i);
                  }
                  out.closeEntry();
                }
                out.close();
              }

              exportDlg.ps.println("\n" + msg.get("export_project_finished") + " " + outputPath);
              exportDlg.ps.println(
                  "\n"
                      + msg.get("export_project_numActivities")
                      + " "
                      + counters[ProjectFileUtils.NUM_ACTS]);
              exportDlg.ps.println(
                  msg.get("export_project_mediaFiles")
                      + " "
                      + counters[ProjectFileUtils.NUM_MEDIA]);
              exportDlg.ps.println(
                  msg.get("export_project_totalSize")
                      + " "
                      + counters[ProjectFileUtils.TOTAL_FILESIZE]);

              exportDlg.ps.println("\n" + msg.get("export_project_notice"));

              if (scormFile != null) {
                exportDlg.ps.printf(
                    "\n" + msg.get("export_project_scorm_notice") + "\n\n", scormFile);
              }

              exportDlg.browserBtn.setEnabled(true);

            } catch (InterruptedException iex) {
              exportDlg.ps.println(
                  "\nWARNING: The process was interrupted! Contents of the output folder might be unsuitable.");
            } catch (Exception ex) {
              exportDlg.ps.println("\nERROR processing ZIP file: " + ex.getMessage());
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
  private javax.swing.JButton browserBtn;
  private javax.swing.JButton cancelBtn;
  private javax.swing.JButton copyBtn;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea logArea;
  // End of variables declaration//GEN-END:variables

}
