import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;



public class window {
    private JFrame frame;
    private Character chara;

    public window(){ 
        viewWindow();
        chara = new Character( frame.getWidth(), frame.getHeight());
        chara.putRandomChara();
        frame.getContentPane().add(chara.viewPanel());
        frame.repaint();
    }

    private void viewWindow(){//createWindow()
        frame = new JFrame();

        frame.setSize(1920,1080);
        frame.setUndecorated(true);
        frame.setBackground( new Color(0,0,0,0));
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    System.exit(0);
                }
            }
        });
    }

    public void moveStart(){
       
        
        ActionListener listener = new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent arg0) {
                chara.move();
            }
        };
        Timer timer = new Timer(150,listener);
        timer.start();

    }
}