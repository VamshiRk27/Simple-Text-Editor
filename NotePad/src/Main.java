import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

class notepad extends JFrame implements ActionListener {
    JTextArea t;
    JFrame f;

    notepad() {
        f = new JFrame("NotePad"); // initialising a new Frame
        t = new JTextArea(); // initialising a new TextArea

        JMenuBar menuBar = new JMenuBar(); // Creating a new Menu Bar
        JMenu file = new JMenu("File"); // Creates a Menu option in Menu Bar
        JMenuItem f1 = new JMenuItem("New"); // Menu Items inside File Menu Option
        JMenuItem f2 = new JMenuItem("Open");
        JMenuItem f3 = new JMenuItem("Save");
        JMenuItem f4 = new JMenuItem("Print");

        f1.addActionListener(this); // Adding actionListener to individual menuItems
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        file.add(f1); // Adding menuItems to the File Menu
        file.add(f2);
        file.add(f3);
        file.add(f4);


        JMenu edit = new JMenu("Edit"); // Creates a Menu option in Menu Bar
        JMenuItem f5 = new JMenuItem("Cut"); // Menu Items inside File Menu Option
        JMenuItem f6 = new JMenuItem("Copy");
        JMenuItem f7 = new JMenuItem("Paste");

        f5.addActionListener(this); // Adding actionListener to individual menuItems
        f6.addActionListener(this);
        f7.addActionListener(this);

        edit.add(f5); // Adding menuItems to the File Menu
        edit.add(f6);
        edit.add(f7);

        JMenu customize = new JMenu("Customization"); // Creates a Setting menu option in Menu Bar
        JMenu fonts = new JMenu("Font");
        JMenu fontName = new JMenu("Font");
        JMenu fontStyle = new JMenu("Style");
        customize.add(fonts);
        fonts.add(fontName);
        fonts.add(fontStyle);


        //Font Name Options
        JMenuItem timesNewRoman = new JMenuItem("Times New Roman");
        JMenuItem arialRounded = new JMenuItem("Arial Rounded");
        JMenuItem cooperBlack = new JMenuItem("Cooper Black");
        JMenuItem rockwell = new JMenuItem("Rockwell");
        JMenuItem verdana = new JMenuItem("Verdana");

        //Adding Font Name Options to the font Name Menu
        fontName.add(timesNewRoman);
        fontName.add(arialRounded);
        fontName.add(cooperBlack);
        fontName.add(rockwell);
        fontName.add(verdana);

        JMenuItem close =new JMenuItem("Close");
        close.addActionListener(this);
        menuBar.add(file);// Adding Menu Options to the Menu Bar
        menuBar.add(edit);
        menuBar.add(customize);
        menuBar.add(close);
        

        f.setJMenuBar(menuBar); // will set the Menu Bar on the frame
        f.add(t);
        f.setSize(1080,720);
        f.setResizable(false); // Disables the resizable property of screen
        f.setLocationRelativeTo(null); // Sets position of Frame to Center
        f.show();


        //Action Listeners for different Font Style Options
        //Default Font size will be 12
        timesNewRoman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            }
        });
        arialRounded.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
            }
        });
        cooperBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.setFont(new Font("Cooper Black", Font.PLAIN, 16));
            }
        });
        rockwell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.setFont(new Font("Rockwell", Font.PLAIN, 16));
            }
        });
        verdana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.setFont(new Font("Verdana", Font.PLAIN, 16));
            }
        });

    }

    // Implementing Functionalities
    public void actionPerformed(ActionEvent e) {
        String s=e.getActionCommand(); //This string will store the name of JMenu Item

        // Processing the String
        switch (s){
            case "New":
                t.setText("");
                break;
            case "Open":
                JFileChooser j=new JFileChooser("Home:");
                int r=j.showOpenDialog(null); //initialising open Dialog
                if(r == JFileChooser.APPROVE_OPTION){
                    File fi=new File(j.getSelectedFile().getAbsolutePath());

                    String s1,s2;
                    try {
                        FileReader fr=new FileReader(fi);
                        BufferedReader br=new BufferedReader(fr);
                        s1=br.readLine();

                        while((s2=br.readLine())!=null){
                            s1=s1 + "\n"+s2;
                        }
                        t.setText(s1);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch(IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(f,"Operation Cancelled");
                }
                break;
            case "Save":
                JFileChooser m=new JFileChooser("Home:");
                int n=m.showSaveDialog(null); //initialising open Dialog
                if(n == JFileChooser.APPROVE_OPTION){
                    File fi=new File(m.getSelectedFile().getAbsolutePath());

                    try {
                        FileWriter fr=new FileWriter(fi);
                        BufferedWriter br=new BufferedWriter(fr);

                        br.write(t.getText());
                        br.flush();
                        br.close();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch(IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(f,"Operation Cancelled");
                }
                break;
            case "Print":
                try {
                    t.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Cut":
                t.cut(); // cut ,print etc functions are provided by Swing
                break;
            case "Copy":
                t.copy();
                break;
            case "Paste":
                t.paste();
                break;
            case "Close":
                f.setVisible(false);
                break;
        }
    }
}
public class Main{
    public static void main(String[] args) {
        notepad obj=new notepad();
    }
}