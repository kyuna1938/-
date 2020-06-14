import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.Color;

public class Character{
    private JPanel panel;
    private JLabel label;
    private Random r = new Random();

    private static final String image_name = "Girl.png";
    private static final int horizontal = 4;
    private static final int vertical = 4;
    private static final int moveX = (int)(6.4*4);
    private static final int moveY = 2*4;

    private int[] imageX = new int[4];
    private int[] imageY = new int[4];
    private int viewImage;

    private int stop;
    private int step;
    private int way;
    private int charaX;
    private int charaY;
    private int maxX;
    private int maxY;

    public Character(int windowWidth, int windowHeight){
        
        ImageIcon icon = new ImageIcon(image_name);
        int imageWidth = icon.getIconWidth();
        int imageHight = icon.getIconHeight();

        label = new JLabel(icon);
        label.setSize(imageWidth,imageHight);

        int characterWidth = imageWidth / horizontal;
        int characterHight = imageHight / vertical;
        maxX = windowWidth - characterWidth;
        maxY = windowHeight - characterHight;

        for(int i = 0; i < horizontal; ++i){
            imageX[i] = characterWidth * -i;
        }
        for(int i = 0; i < vertical; ++i){
            imageY[i] = characterHight * -i;
        }

        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(characterWidth,characterHight);
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.add(label);
    }

    public Character(){
        this(1280, 720);
    }

    public JPanel viewPanel(){
        return panel;
    }

    public void move(){
        if(stop > 0){
            --stop;
        } else {
            action();
        }
    }

    private void action(){
        if(step > 0){
            walk();
            --step;
        } else {
            stop();
            setHAYAMI();
        }
    }

    private void setHAYAMI(){
        stop = r.nextInt(15);
        step = r.nextInt(11) * 2;
        way = r.nextInt(horizontal);
    }

    private void walk(){
        ++viewImage;
        if( viewImage >= horizontal){
            viewImage = 0;
        }
        label.setLocation(imageX[viewImage],imageY[way]);

        switch(way){
              case 0:{
                charaY += moveY;
                if(charaY > maxY){
                    charaY = maxY;
                    step = 0;
                }
                panel.setLocation(charaX, charaY);
                break;
            } case 1:{
                charaX -= moveX;
                if(charaX < 0){
                    charaX = 0;
                    step = 0;
                }
                panel.setLocation(charaX, charaY);
                break;
            } case 2:{
                charaX += moveX;
                if(charaX > maxX){
                    charaX = maxX;
                    step = 0;
                }
                panel.setLocation(charaX, charaY);
                break;
            } case 3:{
                charaY -= moveY;
                if(charaY < 0){
                    charaY = 0;
                    step = 0;
                }
                panel.setLocation(charaX, charaY);
                break;
            }

        }
        //panel.setLocation(panel.getX(), panel.getY() + moveY);
    }
 
    private void stop(){
        step = 0;
        label.setLocation(imageX[0], imageY[way]);
        panel.getParent().repaint();
        viewImage= 0;
    }

    public void putRandomChara(){
        charaX = r.nextInt(maxX);
        charaY = r.nextInt(maxY);
        panel.setLocation(charaX, charaY);
    }

    public void putCenterChara(){
        charaX = panel.getParent().getWidth() / 2 - panel.getWidth() / 2;
        charaY = panel.getParent().getHeight() / 2 - panel.getHeight() / 2;
        panel.setLocation(charaX, charaY);
    }
}