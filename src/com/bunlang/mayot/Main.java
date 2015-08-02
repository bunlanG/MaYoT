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

import javax.swing.JFrame;

/** Main class of MaYoT.
 *  
 *  @author bunlanG
 */
public class Main {

    /** The main function of MaYoT.
     *  
     *  @param args
     *  Program parameters.
     */
    public static void main(String[] args) {
        System.out.println("MaYoT : Manage Your Tournament");
        
        JFrame win = new JFrame();
            win.setTitle("MaYoT");
            win.setSize(600, 480);
            win.setLocationRelativeTo(null);
            win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);       
    }
}
