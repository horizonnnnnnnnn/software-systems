package work1.runner;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainRunnerGUI {
    private JFrame frame;
    private JComboBox<String> demoSelector;
    private JButton runButton;
    private JButton showDiagramsButton;
    private JTextArea inputTextArea;
    private String inputFilePath = "input.txt";
    private String outputFilePath = "output.txt";

    public MainRunnerGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("经典软件体系结构教学软件");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new FlowLayout());

        demoSelector = new JComboBox<>(new String[]{"主程序-子程序软件体系结构", "面向对象软件体系结构", "事件系统软件体系结构", "管道-过滤软件体系结构"});
        frame.add(demoSelector);

        inputTextArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(inputTextArea);
        frame.add(scrollPane);

        runButton = new JButton("运行");
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedDemo = demoSelector.getSelectedIndex();
                saveInputToFile();
                runDemo(selectedDemo + 1);
            }
        });
        frame.add(runButton);

        showDiagramsButton = new JButton("查看原理");
        showDiagramsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedDemo = demoSelector.getSelectedIndex();
                showDiagrams(selectedDemo + 1);
            }
        });
        frame.add(showDiagramsButton);

        frame.setVisible(true);
    }

    private void saveInputToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFilePath))) {
            writer.write(inputTextArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runDemo(final int choice) {
        Thread demoThread = new Thread(() -> {
            try {
                PrintStream out = new PrintStream(new FileOutputStream(outputFilePath));
                System.setOut(out);

                switch (choice) {
                    case 1:
                        work1.first.demo1.exeMain(new String[]{});
                        break;
                    case 2:
                        work1.objectOriented.Main.exeMain(new String[]{});
                        break;
                    case 3:
                        work1.observer.Main.exeMain(new String[]{});
                        break;
                    case 4:
                        work1.pipelineFiltration.Main.exeMain(new String[]{});
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }

                System.setOut(System.out);

                SwingUtilities.invokeLater(this::displayOutput);

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        demoThread.start();
    }

    private void displayOutput() {
        String content = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JFrame outputFrame = new JFrame("Output");
        outputFrame.setSize(400, 300);
        outputFrame.setLayout(new BorderLayout());
        outputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea outputArea = new JTextArea(content);
        outputArea.setEditable(false);
        outputFrame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        outputFrame.setVisible(true);
    }

    private void showDiagrams(int demoNumber) {
        String yPath = "D:\\新建文件夹 (46)\\picture\\y" + demoNumber + ".png";
        String dPath = "D:\\新建文件夹 (46)\\picture\\d" + demoNumber + ".png";

        JFrame diagramsFrame = new JFrame("Diagrams for Demo " + demoNumber);
        diagramsFrame.setSize(2400, 1800);
        diagramsFrame.setLayout(new GridLayout(2, 2, 10, 10));
        diagramsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel principleDiagramLabel = new JLabel(new ImageIcon(yPath));
        JLabel codeDiagramLabel = new JLabel(new ImageIcon(dPath));
        diagramsFrame.add(new JLabel("原理图"));
        diagramsFrame.add(principleDiagramLabel);
        diagramsFrame.add(new JLabel("函数图"));
        diagramsFrame.add(codeDiagramLabel);

        diagramsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainRunnerGUI());
    }
}