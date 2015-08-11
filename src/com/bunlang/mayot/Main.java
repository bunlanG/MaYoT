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

import com.bunlang.mayot.scores.Match;

import com.bunlang.mayot.scores.MatchDay;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Color;

import org.apache.log4j.Logger;

/** Main class of MaYoT.
 *
 *  @author bunlanG
 */
public class Main {

    private static Logger logger = Logger.getLogger("com.bunlang.mayot");

    /** The main function of MaYoT.
     *
     *  @param args
     *  Program parameters.
     */
    public static void main(String[] args) {
        logger.info("MaYoT : Manage Your Tournament");
        initLAF();

        MatchDay matchDay = new MatchDay();
        Match match = new Match("Home", "Guest");
        Match match2 = new Match("Home 2", "Guest 2");
        Match match3 = new Match("Home 3", "Guest 3");

        matchDay.add(match);
        matchDay.add(match2);
        matchDay.add(match3);

        JPanel content = matchDay.getPanel();
        JFrame win = new JFrame();
            win.setContentPane(content);
            win.setTitle("MaYoT");
            win.setSize(content.getSize());
            win.setMinimumSize(content.getSize());
            win.setLocationRelativeTo(null);
            win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }

    /** Setup a custom L&F.
     *
     */
    public static void initLAF() {
        Color transparent = new Color(0,0,0,0);
        Color semiTrans = new Color(255,255,255,50);

        UIManager.put("Button.focus", new javax.swing.plaf.ColorUIResource(transparent));
        UIManager.put("Button.shadow", new javax.swing.plaf.ColorUIResource(transparent));
        UIManager.put("Button.darkShadow", new javax.swing.plaf.ColorUIResource(transparent));
        UIManager.put("Button.select", new javax.swing.plaf.ColorUIResource(semiTrans));
        UIManager.put("Button.gradient", new javax.swing.plaf.ColorUIResource(transparent));
    }
}
