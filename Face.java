import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Color;

public class Face implements ActionListener {
    static JFrame frame;

    public static void createGUI() {
        frame = new JFrame("");
        frame.setSize(650,450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                createGUI();
            }
        });


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
