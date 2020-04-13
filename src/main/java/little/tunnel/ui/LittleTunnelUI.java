package little.tunnel.ui;

import little.tunnel.LittleTunnel;
import little.tunnel.site.Site;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Set;

public class LittleTunnelUI {
    private static final String GIT_REPO = "https://github.com/ngotrunghieu29/little-tunnel";
    private boolean serverStarted;
    private JPanel rootPanel;
    private JButton startStopServer;
    private JLabel serverStatusLabel;
    private JLabel serverStatus;
    private JList<String> sites;
    private DefaultListModel<String> siteModels;
    private JTextField siteToAddTextField;
    private JButton addSiteButton;
    private JLabel numberOfSites;
    private JFormattedTextField portNumberField;
    private JButton openGitHubRepoButton;

    public LittleTunnelUI() {
        loadSitesFromConfig();

        startStopServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverStarted) {
                    stopServer();
                } else {
                    startSever();
                }
            }
        });
        addSiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String siteToAdd = siteToAddTextField.getText().toLowerCase();
                Site.INSTANCE.addSite(siteToAdd);
                loadSitesFromConfig();
            }
        });
        openGitHubRepoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGitRepo();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LittleTunnelUI");
        frame.setContentPane(new LittleTunnelUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Site.INSTANCE.save();
                LittleTunnel.INSTANCE.stopServer();
                System.exit(0);
            }
        });
    }

    private void startSever() {
        this.serverStarted = true;
        int port = Integer.valueOf(portNumberField.getText());
        LittleTunnel.INSTANCE.startServer(port);
        this.startStopServer.setText("Stop Server");
        this.serverStatus.setText(ServerStatus.RUNNING.getValue());
        this.serverStatus.setForeground(Color.GREEN);
    }

    private void stopServer() {
        this.serverStarted = false;
        LittleTunnel.INSTANCE.stopServer();
        this.startStopServer.setText("Start Server");
        this.serverStatus.setText(ServerStatus.NOT_RUNNING.getValue());
        this.serverStatus.setForeground(Color.RED);
    }

    private void loadSitesFromConfig() {
        Set<String> configSites = Site.INSTANCE.getConfigSites();
        this.siteModels = new DefaultListModel<>();
        for (String configSite : configSites) {
            this.siteModels.addElement(configSite);
        }
        this.sites.setModel(siteModels);
        numberOfSites.setText(String.valueOf(sites.getModel().getSize()));
    }

    private void createUIComponents() {
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        portNumberField = new JFormattedTextField(format);
        portNumberField.setValue(Integer.valueOf(8085));
    }

    private void openGitRepo() {
        try {
            Desktop.getDesktop().browse(new URL(GIT_REPO).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
