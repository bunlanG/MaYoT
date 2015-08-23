/*
 * MaYoT : Manage Your Tournament
 * Copyright (C) 2015 - Ronan GUILBAULT
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.bunlang.mayot;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/** Helps to manage a Tournament.
 *
 * @author bunlanG
 */
public class Navigator extends JPanel implements MouseListener, MouseMotionListener {

    private enum Zone {
        UP("Up"),
        DOWN("Down"),
        LEFT("Left"),
        RIGHT("Right"),
        OUT("__OUT__");

        private String _val;

        Zone(String val) {
            _val = val;
        }

        public String toString() {
            return _val;
        }
    }

    // Fields
    protected Zone _currZone;
    protected boolean _isPressed;
    protected Navigable _nav;

    /** Default constructor.
     *
     *  @deprecated
     */
    public Navigator() {
        super();

        init(null);
    }

    /** A Constructor with the {@link Navigable} object
     *
     * @param nav the object the {@link Navigator} can use
     */
    public Navigator(Navigable nav) {
        super();

        init(nav);
    }

    protected void init(Navigable nav) {
        addMouseListener(this);
        addMouseMotionListener(this);

        _nav = nav;
        _currZone = Zone.OUT;
        _isPressed = false;
    }

    public void paintComponent(Graphics g) {
        int x1 = this.getWidth();
        int y1 = this.getHeight();
        int x2 = (int) (0.1465f * (float) this.getWidth());
        int y2 = (int) (0.1465f * (float) this.getHeight());
        int sizeFont = Math.min(x1, y1) / 3;
        Font font = new Font("Dialog", Font.BOLD, sizeFont);
        g.setFont(font);

        g.setColor(Color.RED);
        g.fillOval(0,0, x1, y1);

        g.setColor(Color.BLACK);
        g.drawLine(x2,y2,this.getWidth() - x2,this.getHeight() - y2);
        g.drawLine(x2,this.getHeight() - y2,this.getWidth() - x2,y2);

        int midX = (x1 - (76 * sizeFont / 100)) / 2;
        int midY = (y1 + (53 * sizeFont / 100)) / 2;
        int leftBor = x1 / 15;
        int upBor = y1 / 15 + (50 * sizeFont / 100);
        int rightBor = x1 - x1 / 15 - (75 * sizeFont / 100);
        int downBor = y1 - y1 / 15;
        g.setColor((getCurrZone() == Zone.DOWN ? (_isPressed ? Color.ORANGE : Color.GREEN) : Color.BLUE));
        g.drawString("▼", midX     , downBor);
        g.setColor((getCurrZone() == Zone.UP ? (_isPressed ? Color.ORANGE : Color.GREEN) : Color.BLUE));
        g.drawString("▲", midX     , upBor);
        g.setColor((getCurrZone() == Zone.LEFT ? (_isPressed ? Color.ORANGE : Color.GREEN) : Color.BLUE));
        g.drawString("◀", leftBor  , midY);
        g.setColor((getCurrZone() == Zone.RIGHT ? (_isPressed ? Color.ORANGE : Color.GREEN) : Color.BLUE));
        g.drawString("▶", rightBor , midY);
    }

    /** Detect in which zone the mouse is.
     *
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     * @return the zone where the mouse is
     */
    private Zone updateZone(int x, int y) {
        if(x < 0 || y < 0) {
            _currZone = Zone.OUT;
        } else {
            // Calc 3 fact : the panel dimension and the position of mouse with up-left and down-left corners
            float factHWPan = (float) this.getHeight() / (float) this.getWidth();
            float factHWUL = (float) y / (float) x;
            float factHWDL = (float) (this.getHeight() - y) / (float) x;

            // Calc if the mouse is over or under the 2 lines
            boolean overDwnDiag = factHWPan < factHWUL;
            boolean overUpDiag = factHWPan < factHWDL;

            // Calc the current zone, depends of the previous booleans
            if (overDwnDiag) {
                if (overUpDiag) {
                    _currZone = Zone.LEFT;
                } else {
                    _currZone = Zone.DOWN;
                }
            } else {
                if (overUpDiag) {
                    _currZone = Zone.UP;
                } else {
                    _currZone = Zone.RIGHT;
                }
            }
        }

        return _currZone;
    }

    private Zone getCurrZone() {
        return _currZone;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(_nav != null) {
            switch (getCurrZone()) {
                case UP:
                    _nav.gotoUp();
                    break;
                case DOWN:
                    _nav.gotoDown();
                    break;
                case LEFT:
                    _nav.gotoLeft();
                    break;
                case RIGHT:
                    _nav.gotoRight();
                    break;
                case OUT:
                    break;
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        _isPressed = true;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        _isPressed = false;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        updateZone(mouseEvent.getX(), mouseEvent.getY());
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        updateZone(-1,-1);
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        updateZone(mouseEvent.getX(), mouseEvent.getY());
        repaint();
    }
}
