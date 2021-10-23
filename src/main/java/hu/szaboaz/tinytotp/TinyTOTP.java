package hu.szaboaz.tinytotp;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author u011279
 */
public class TinyTOTP extends javax.swing.JFrame {

    private Properties properties = new Properties();

    /**
     * Creates new form TOTPFrame
     */
    public TinyTOTP() {

        initComponents();
        this.setResizable(false);
        loadProperties();
        if (properties.getProperty("LOCATION.X") != null
                && properties.getProperty("LOCATION.Y") != null) {
            this.setLocation(Integer.parseInt(properties.getProperty("LOCATION.X")),
                    Integer.parseInt(properties.getProperty("LOCATION.Y")));
        } else {
            setLocationRelativeTo(null);
        }
        jTextField3.setText(properties.getProperty("PREFIX"));
        jTextField7.setText(properties.getProperty("SECRET"));
        autocloseCheckBox.setSelected(Boolean.valueOf(properties.getProperty("AUTOCLOSE")));
        autocopyCheckBox.setSelected(Boolean.valueOf(properties.getProperty("AUTOCOPY")));
        if (properties.getProperty("DIGITS") != null && !"".equals(properties.getProperty("DIGITS"))){
            int digitsProperty = Integer.parseInt(properties.getProperty("DIGITS"));
            ((javax.swing.SpinnerNumberModel) jSpinner1.getModel()).setValue(Integer.valueOf(digitsProperty));
        }
        
        if (properties.getProperty("CRYPTO") != null && !"".equals(properties.getProperty("CRYPTO"))){
            String crypto = properties.getProperty("CRYPTO");
            jComboBox1.setSelectedItem(crypto);
        }
        else {
            properties.setProperty("CRYPTO", (String)jComboBox1.getSelectedItem());
        }
        setupListeners();
    }

    private void setupListeners() {
        jTextField3.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateProperties();
            }

            public void removeUpdate(DocumentEvent e) {
                updateProperties();
            }

            public void insertUpdate(DocumentEvent e) {
                updateProperties();
            }

            public void updateProperties() {
                properties.setProperty("PREFIX", jTextField3.getText());
                long now = Instant.now().getEpochSecond();
                generate(now);
            }
        });

        jTextField7.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateProperties();
            }

            public void removeUpdate(DocumentEvent e) {
                updateProperties();
            }

            public void insertUpdate(DocumentEvent e) {
                updateProperties();
            }

            public void updateProperties() {
                properties.setProperty("SECRET", jTextField7.getText());
                long now = Instant.now().getEpochSecond();
                generate(now);
            }
        });

        jSpinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int numberOfDigits = ((javax.swing.SpinnerNumberModel) jSpinner1.getModel()).getNumber().intValue();
                String numberOfDigitsString = Integer.toString(numberOfDigits);
                properties.setProperty("DIGITS", numberOfDigitsString);
                long now = Instant.now().getEpochSecond();
                generate(now);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveProperties();
            }
        });

    }

    public static boolean checkHex(String s) {
        try {
            new BigInteger("10" + s, 16);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        autocopyCheckBox = new javax.swing.JCheckBox();
        autocloseCheckBox = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TinyTOTP");

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField1.setColumns(2);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jTextField2)
                    .addComponent(jTextField1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("TOTP", jPanel1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Prefix:");

        jTextField3.setColumns(6);
        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        autocopyCheckBox.setText("autocopy");
        autocopyCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autocopyCheckBoxActionPerformed(evt);
            }
        });

        autocloseCheckBox.setText("autoclose");
        autocloseCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autocloseCheckBoxActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Secret:");

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(6, 1, 8, 1));

        jLabel2.setText("Digits:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HmacSHA1", "HmacSHA256", "HmacSHA512" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(autocloseCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(autocopyCheckBox))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autocloseCheckBox)
                    .addComponent(autocopyCheckBox)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Settings", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void autocloseCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autocloseCheckBoxActionPerformed
        properties.setProperty("AUTOCLOSE", Boolean.toString(autocloseCheckBox.isSelected()));
    }//GEN-LAST:event_autocloseCheckBoxActionPerformed

    private void autocopyCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autocopyCheckBoxActionPerformed
        properties.setProperty("AUTOCOPY", Boolean.toString(autocopyCheckBox.isSelected()));
    }//GEN-LAST:event_autocopyCheckBoxActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        properties.setProperty("CRYPTO", (String)jComboBox1.getSelectedItem());
        long now = Instant.now().getEpochSecond();
        generate(now);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            /*
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            */
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TinyTOTP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TinyTOTP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TinyTOTP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TinyTOTP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TinyTOTP frame = new TinyTOTP();
                frame.start();
                frame.setVisible(true);
            }
        });
    }

    private void start() {

        new Thread(new Runnable() {

            private DateFormat df = new SimpleDateFormat("ss");
            Instant startOfProgram = Instant.now();

            @Override
            public void run() {

                TimerTask task = new TimerTask() {
                    public void run() {
                        long now = Instant.now().getEpochSecond();
                        long sec = now % 60;

                        final String seconds = String.format("%02d", sec % 30);
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                jTextField1.setText(seconds);
                            }
                        });

                        if (sec % 30 == 0) {
                            generate(now);

                            if (Boolean.valueOf(properties.getProperty("AUTOCLOSE"))
                                    //don't autoclose if less than 5 seconds have elapsed since start
                                    && Duration.between(startOfProgram, Instant.now()).getSeconds() > 5) {
                                TinyTOTP.this.dispatchEvent(new WindowEvent(TinyTOTP.this, WindowEvent.WINDOW_CLOSING));
                            }
                        }
                    }
                };

                long now = Instant.now().getEpochSecond();
                generate(now);

                Timer timer = new Timer();
                timer.scheduleAtFixedRate(task, new Date((now) * 1000), 1000L);

            }

        }).start();

    }

    public void generate(long now) {
        long T0 = 0;
        long X = 30;

        String steps = "0";
        long T = (now - T0) / X;
        steps = Long.toHexString(T).toUpperCase();
        while (steps.length() < 16) {
            steps = "0" + steps;
        }

        if (!(properties.getProperty("SECRET") == null)
                && !("".equals(properties.getProperty("SECRET")))
                && !("null".equals(properties.getProperty("SECRET")))) {
            String secretHex = Hex.encodeHexString(new Base32().decode(jTextField7.getText().getBytes()));
            int numberOfDigits = ((javax.swing.SpinnerNumberModel) jSpinner1.getModel()).getNumber().intValue();
            String numberOfDigitsString = Integer.toString(numberOfDigits);
            String crypto = properties.getProperty("CRYPTO") == null ? "" : properties.getProperty("CRYPTO");
            //final String pw = (properties.getProperty("PREFIX")==null?"":properties.getProperty("PREFIX")) + IETF.generateTOTP(secretHex, steps, "6", "HmacSHA1");
            final String pw = (properties.getProperty("PREFIX") == null ? "" : properties.getProperty("PREFIX")) + IETF.generateTOTP(secretHex, steps, numberOfDigitsString, crypto);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    jTextField2.setText(pw);

                    if (Boolean.valueOf(properties.getProperty("AUTOCOPY"))) {
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clipboard.setContents(new StringSelection(pw), null);
                    }
                }
            });
        } else {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    jTextField2.setText("Secret?");
                }
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox autocloseCheckBox;
    private javax.swing.JCheckBox autocopyCheckBox;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables

    private void loadProperties() {
        try {
            String jarDir = new File(TinyTOTP.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getCanonicalPath();
            String txtPathString = jarDir + File.separator + "TinyTOTP.txt";
            InputStream propertiesInputStream = new FileInputStream(txtPathString);
            properties.load(propertiesInputStream);
            propertiesInputStream.close();
        } catch (FileNotFoundException ex1) {
            //No problem
        } catch (IOException | URISyntaxException ex) {
            JOptionPane.showConfirmDialog(this, "Error reading TinyTOTP.txt file!\n" + getStackTrace(ex), "Error!", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void saveProperties() {
        try {
            String jarDir = new File(TinyTOTP.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getCanonicalPath();
            String txtPathString = jarDir + File.separator + "TinyTOTP.txt";
            try (PrintWriter pw = new PrintWriter(txtPathString)) {
                pw.println("PREFIX=" + (properties.getProperty("PREFIX") == null ? "" : properties.getProperty("PREFIX")));
                pw.println("SECRET=" + (properties.getProperty("SECRET") == null ? "" : properties.getProperty("SECRET")));
                pw.println("DIGITS=" + (properties.getProperty("DIGITS") == null ? "" : properties.getProperty("DIGITS")));
                pw.println("CRYPTO=" + (properties.getProperty("CRYPTO") == null ? "" : properties.getProperty("CRYPTO")));
                Point location = this.getLocation();
                pw.println("LOCATION.X=" + Math.abs(location.x));
                pw.println("LOCATION.Y=" + Math.abs(location.y));

                pw.println("AUTOCLOSE=" + properties.getProperty("AUTOCLOSE"));
                pw.println("AUTOCOPY=" + properties.getProperty("AUTOCOPY"));
            }
        } catch (URISyntaxException | IOException ex) {
            JOptionPane.showConfirmDialog(this, "Error saving TinyTOTP.txt file!\n" + getStackTrace(ex), "Error!", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

    }
}
