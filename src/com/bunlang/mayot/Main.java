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

import com.bunlang.mayot.ranking.Table;
import com.bunlang.mayot.ranking.Team;
import com.bunlang.mayot.ranking.TeamUI;
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
        if(logger.isInfoEnabled()) {
            logger.info("MaYoT : Manage Your Tournament");
            logger.info("(C) 2015, Ronan GUILBAULT, License GPL v3");
        }
        if(logger.isDebugEnabled()) {
            logger.debug("Launching MaYoT...");
        }
        initLAF();

        // scores
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

        // ranking
        Table table = new Table();
        Team team1 = new Team("Team 1", 0,0,0,0,0, 0,0);
        Team team2 = new Team("Team 2", 0,0,0,0,0, 0,0);
        Team team3 = new Team("Team 3", 0,0,0,0,0, 0,0);
        Team team4 = new Team("Team 4", 0,0,0,0,0, 0,0);

        table.add(team1);
        table.add(team2);
        table.add(team3);
        table.add(team4);

        JPanel tableUI = table.getPanel();
        JFrame win2 = new JFrame();
            win2.setContentPane(tableUI);
            win2.setTitle("MaYoT - ranking");
            win2.setSize(tableUI.getSize());
            win2.setMinimumSize(tableUI.getSize());
            win2.setLocationRelativeTo(null);
            win2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win2.setVisible(true);


        if(logger.isDebugEnabled()) {
            logger.debug("MaYoT launched.");
        }
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
