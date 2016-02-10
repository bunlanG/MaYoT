/*
 * MaYoT : Manage Your Tournament
 * Copyright (C) 2015-2016 - Ronan GUILBAULT
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

import javax.swing.*;
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
public class Navigator extends Box implements MouseListener, MouseMotionListener {

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
        super(BoxLayout.LINE_AXIS);

        init(null);
    }

    /** A Constructor with the {@link Navigable} object
     *
     * @param nav the object the {@link Navigator} can use
     */
    public Navigator(Navigable nav) {
        super(BoxLayout.LINE_AXIS);

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
        Color arrow = new Color(0xCC_CC_CC);
        Color linesSep = new Color(0xFF_FF_FF);
        Font font = new Font("Dialog", Font.BOLD, sizeFont);
        g.setFont(font);

        int midX = (x1 - (76 * sizeFont / 100)) / 2;
        int midY = (y1 + (53 * sizeFont / 100)) / 2;
        int leftBor = x1 / 15;
        int upBor = y1 / 15 + (50 * sizeFont / 100);
        int rightBor = x1 - x1 / 15 - (75 * sizeFont / 100);
        int downBor = y1 - y1 / 15;
        g.setColor(getArrowColor(Zone.DOWN));
        g.fillArc(0,0,x1,y1,-135,90);
        g.setColor(arrow);
        g.drawString("▼", midX     , downBor);
        g.setColor(getArrowColor(Zone.UP));
        g.fillArc(0,0,x1,y1,45,90);
        g.setColor(arrow);
        g.drawString("▲", midX     , upBor);
        g.setColor(getArrowColor(Zone.LEFT));
        g.fillArc(0,0,x1,y1,135,90);
        g.setColor(arrow);
        g.drawString("◀", leftBor  , midY);
        g.setColor(getArrowColor(Zone.RIGHT));
        g.fillArc(0,0,x1,y1,-45,90);
        g.setColor(arrow);
        g.drawString("▶", rightBor , midY);

        g.setColor(linesSep);
        g.drawLine(x2,y2,this.getWidth() - x2,this.getHeight() - y2);
        g.drawLine(x2,this.getHeight() - y2,this.getWidth() - x2,y2);
    }

    private Color getArrowColor(Zone zone) {
        Color pressedC = new Color(0x00_FF_55);
        Color hoveredC = new Color(0x44_99_FF);
        Color unhoveredC = new Color(0x55_55_55);
        Color disabledC = new Color(0xFF_55_55);

        boolean locked = true;
        if(_nav != null) {
            switch (zone) {
                case UP:
                    locked = _nav.lockedUp();
                    break;
                case DOWN:
                    locked = _nav.lockedDown();
                    break;
                case LEFT:
                    locked = _nav.lockedLeft();
                    break;
                case RIGHT:
                    locked = _nav.lockedRight();
                    break;
                case OUT:
                    locked = false;
                    break;
            }
        }

        return (locked ? disabledC : (getCurrZone() == zone ? (_isPressed ? pressedC : hoveredC) : unhoveredC));
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
