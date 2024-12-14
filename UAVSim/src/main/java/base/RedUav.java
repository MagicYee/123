package base;

import java.awt.*;

public class RedUav extends BaseUav{

    public RedUav(int x, int y) {
        super(x, y);
    }

    public RedUav(int uuid) {
        super(uuid, "红方无人机");
    }

    @Override
    public void drawUav(Graphics g) {
        super.drawUav(g);
        damageRatio = damageRatio>1.0 ? 1.0 : damageRatio;
        int percent = (int) (360 * damageRatio);
        g.setColor(new Color(56, 94 ,15));
        g.fillArc(xPosition, yPosition, uavSize, uavSize, 0,  percent);
        g.setColor(new Color(178, 34, 34));
        g.fillArc(xPosition, yPosition, uavSize, uavSize,  percent, 360 - percent);
    }

    @Override
    public void drawComScope(Graphics g) {
        g.setColor(new Color(178, 34, 34));
        g.drawOval(xPosition - (comScope-uavSize)/2, yPosition - (comScope-uavSize)/2, comScope, comScope);
    }

    @Override
    public void moveDirection(int n) {
        switch (moveDirection) {
            case 1:
                move(0, -moveStep);
                break;
            case 2:
                move(moveStep, -moveStep);
                break;
            case 3:
                move(moveStep, 0);
                break;
            case 4:
                move(moveStep, moveStep);
                break;
            case 5:
                move(0, moveStep);
                break;
            case 6:
                move(-moveStep, moveStep);
                break;
            case 7:
                move(-moveStep, 0);
                break;
            case 8:
                move(-moveStep, -moveStep);
                break;
            default:
                move(-moveStep, -moveStep);
        }
    }
}
