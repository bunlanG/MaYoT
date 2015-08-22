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
        RIGHT("Right");

        private String _val;

        Zone(String val) {
            _val = val;
        }

        public String toString() {
            return _val;
        }
    }

    public Navigator() {
        super();

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        int x1 = this.getWidth() / 4;
        int y1 = this.getHeight() / 4;
        g.setColor(Color.BLACK);
        g.drawLine(0,0,this.getWidth(),this.getHeight());
        g.drawLine(0,this.getHeight(),this.getWidth(),0);
        g.setColor(Color.RED);
        g.fillOval(x1, y1, x1 * 2, y1 * 2);
    }

    /** Detect in which zone the mouse is.
     * 
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     * @return the zone where the mouse is
     */
    private Zone getZone(int x, int y) {
        // Calc 3 fact : the panel dimension and the position of mouse with up-left and down-left corners
        float factHWPan = (float) this.getHeight() / (float) this.getWidth();
        float factHWUL = (float) y / (float) x;
        float factHWDL = (float) (this.getHeight() - y) / (float) x;

        // Calc if the mouse is over or under the 2 lines
        boolean overDwnDiag = factHWPan < factHWUL;
        boolean overUpDiag = factHWPan < factHWDL;

        // Calc the current zone, depends of the previous booleans
        if(overDwnDiag) {
            if(overUpDiag) {
                return Zone.LEFT;
            } else {
                return Zone.DOWN;
            }
        } else {
            if (overUpDiag) {
                return Zone.UP;
            } else {
                return Zone.RIGHT;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("Nav : mouseClicked");
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        System.out.println("Nav : mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        System.out.println("Nav : mouseReleased");
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        System.out.println("Nav : mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        System.out.println("Nav : mouseExited");
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        System.out.println("Nav : mouseDragged");
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        System.out.println("Nav : mouseMoved / Zone : " + getZone(mouseEvent.getX(),mouseEvent.getY()));
    }
}
