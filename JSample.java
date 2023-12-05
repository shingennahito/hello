package duan;

import javax.swing.*;
import duan.DataHandler;
import duan.ProcessingDialog;

import java.awt.*;
import java.awt.event.*;

public class JSample extends JFrame implements ActionListener {
    private JTextField text1;
    private JTextArea statusTextArea;
    private final DataHandler dataHandler;
    JLabel label001;

    public static void main(String args[]) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSample frame = new JSample();
        frame.setTitle("MyTitle");
        frame.setBounds(0, 0, 1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    JSample() {
        JPanel p = new JPanel();
        p.setLayout(null);

        label001 = new JLabel("");
        label001.setBounds(200, 50, 2000, 30);
        label001.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
        p.add(label001);



        // mau chu KBCC
        JLabel label6 = new JLabel("K");
        label6.setForeground(Color.BLUE);
        label6.setBounds(10, 5, 300, 30);
        label6.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 30));
        p.add(label6);

        JLabel label7 = new JLabel("B");
        label7.setForeground(Color.RED);
        label7.setBounds(30, 5, 300, 30);
        label7.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 30));
        p.add(label7);

        JLabel label8 = new JLabel("C");
        label8.setForeground(Color.ORANGE);
        label8.setBounds(50, 5, 300, 30);
        label8.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 30));
        p.add(label8);

        JLabel label9 = new JLabel("C");
        label9.setForeground(Color.green);
        label9.setBounds(70, 5, 300, 30);
        label9.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 30));
        p.add(label9);
        // ... (phần khai báo và thêm giao diện người dùng)

        JButton btnl = new JButton("ROBOT");
        btnl.setBounds(1300, 720, 100, 30);
        btnl.setMnemonic(KeyEvent.VK_ENTER);
        btnl.addActionListener(this);
        btnl.setActionCommand("actl");

        // ... (thêm xử lý mouseListener)
        JButton btnl2 = new JButton("POST");
        btnl2.setBounds(400, 500, 100, 30);
        btnl2.addActionListener(this);
        btnl2.setActionCommand("actl2");
        p.add(btnl);
        p.add(btnl2);

        text1 = new JTextField();
        
        text1.setBounds(500, 500, 500, 30);
        p.add(text1);

        statusTextArea = new JTextArea();
        statusTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusTextArea);
        scrollPane.setBounds(500, 650, 600, 100);
        p.add(scrollPane);
        getContentPane().add(p, BorderLayout.CENTER);

        dataHandler = new DataHandler();
    }

    public void actionPerformed(ActionEvent e) {
        String act = e.getActionCommand();
        if (act.equals("actl")) {
            ProcessingDialog p = new ProcessingDialog(this);
            Timer timer = new Timer(3000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    p.stopAnimation();
                    // Đóng cửa sổ sau khi kết thúc thời gian chờ
                    p.dispose();
                }
            });
            timer.setRepeats(false);
            timer.start();

            p.startAnimation();

            // Xử lý dữ liệu trong tiến trình mới
            Thread processDataThread = new Thread(() -> {
                // Xử lý dữ liệu

                // Sau khi hoàn thành xử lý, đóng màn hình chờ xử lý (nếu chưa tự đóng)
                if (timer.isRunning()) {
                    SwingUtilities.invokeLater(() -> {
                        p.stopAnimation();
                        p.dispose();
                    });
                }
                String userInput = text1.getText();

                // Xử lý tin nhắn của ROBOT ở đây - Ví dụ: hiển thị thông điệp dựa trên
                // userInput
                String responseMessage = generateResponse(userInput);
                // KET NOI DATABASE
                dataHandler.addToDatabase(text1.getText());
                String adminaiAwn = dataHandler.fetchaiAwn(2023001);

                // Hiển thị thông điệp của ROBOT trong cửa sổ pop-up
                JOptionPane.showMessageDialog(this, "できました ");
                appendToConversation(responseMessage + adminaiAwn);
                dataHandler.deleteFromDatabase(2023002);
                
            });

            processDataThread.start();

        }else if(act.equals("actl2")) {
            label001.setText(text1.getText());
        }
        
    }

    private void appendToConversation(String message) {
        statusTextArea.append(message + "\n");
        text1.setText("");
    }

    private String generateResponse(String userInput) {
        // Xử lý logic để tạo thông điệp phản hồi từ ROBOT dựa trên userInput
        // Ví dụ đơn giản:
        return "こたえは : ";
    }
    
}
