package duan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProcessingDialog extends JDialog {
    private final JProgressBar progressBar;

    public ProcessingDialog(JFrame parent) {
        super(parent, "Processing", true);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel processingLabel = new JLabel("Processing... Please wait.", SwingConstants.CENTER);
        panel.add(processingLabel, BorderLayout.NORTH);

        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); // Sử dụng chế độ không xác định
        panel.add(progressBar, BorderLayout.CENTER);

        getContentPane().add(panel);
        setSize(200, 100);
        setLocationRelativeTo(parent);
    }

    public void startAnimation() {
        setVisible(true);
    }

    public void stopAnimation() {
        setVisible(false);
    }
}
