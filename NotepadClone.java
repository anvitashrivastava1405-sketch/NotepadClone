import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class NotepadClone extends JFrame implements ActionListener {

    JTextArea textArea;
    JMenuItem newFile, openFile, saveFile, exit;
    JMenuItem cut, copy, paste;

    NotepadClone() {
        setTitle("Notepad Clone");

        textArea = new JTextArea();
        add(new JScrollPane(textArea));

        JMenuBar mb = new JMenuBar();

        JMenu file = new JMenu("File");
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        exit = new JMenuItem("Exit");

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(exit);

        JMenu edit = new JMenu("Edit");
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);

        mb.add(file);
        mb.add(edit);
        setJMenuBar(mb);

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        exit.addActionListener(this);

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);

        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == newFile) {
            textArea.setText("");
        }

        else if (e.getSource() == openFile) {
            JFileChooser fc = new JFileChooser();
            int option = fc.showOpenDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    textArea.read(br, null);
                    br.close();
                } catch (Exception ex) {
                    System.out.println("Error opening file");
                }
            }
        }

        else if (e.getSource() == saveFile) {
            JFileChooser fc = new JFileChooser();
            int option = fc.showSaveDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    textArea.write(bw);
                    bw.close();
                } catch (Exception ex) {
                    System.out.println("Error saving file");
                }
            }
        }

        else if (e.getSource() == exit) {
            System.exit(0);
        }

        else if (e.getSource() == cut) {
            textArea.cut();
        } else if (e.getSource() == copy) {
            textArea.copy();
        } else if (e.getSource() == paste) {
            textArea.paste();
        }
    }

    public static void main(String[] args) {
        new NotepadClone();
    }
}