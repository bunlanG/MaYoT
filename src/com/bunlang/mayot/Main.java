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
import java.awt.*;

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
            logger.info("Launching MaYoT...");
        }
        initLAF();
        if(logger.isDebugEnabled()) {
            logger.debug("L&F initialized.");
            logger.debug("Creating the Group...");
        }

        // ranking
        Team team1 = new Team("Team 1", 0,0,0,0,0, 0,0);
        Team team2 = new Team("Team 2", 0,0,0,0,0, 0,0);
        Team team3 = new Team("Team 3", 0,0,0,0,0, 0,0);
        Team team4 = new Team("Team 4", 0,0,0,0,0, 0,0);
        Team team5 = new Team("Team 5", 0,0,0,0,0, 0,0);
        Team team6 = new Team("Team 6", 0,0,0,0,0, 0,0);

        // scores
        // MD 01
        MatchDay matchDay1 = new MatchDay();
        Match match11 = new Match(team1, team5);
        Match match12 = new Match(team4, team6);
        Match match13 = new Match(team2, team3);

        matchDay1.add(match11);
        matchDay1.add(match12);
        matchDay1.add(match13);

        // MD02
        MatchDay matchDay2 = new MatchDay();
        Match match21 = new Match(team5, team4);
        Match match22 = new Match(team6, team2);
        Match match23 = new Match(team3, team1);

        matchDay2.add(match21);
        matchDay2.add(match22);
        matchDay2.add(match23);

        final Group grp = new Group();

        grp.add(matchDay1);
        grp.add(matchDay2);

        grp.add(team1);
        grp.add(team2);
        grp.add(team3);
        grp.add(team4);
        grp.add(team5);
        grp.add(team6);

        if(logger.isDebugEnabled()) {
            logger.debug("Group created.");
            logger.debug("Making visible this Group...");
        }

        JPanel ui = grp.getPanel();
        final JFrame win2 = new JFrame();
            win2.setContentPane(ui);
            win2.setTitle("MaYoT - Group");
            win2.setSize(ui.getSize());
        win2.setMinimumSize(ui.getSize());
        win2.setLocationRelativeTo(null);
            win2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win2.setVisible(true);

        if(logger.isInfoEnabled()) {
            logger.info("MaYoT launched.");
        }

        // Test the nextMD with another Thread
        Runnable test = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                grp.prevMatchDay();

                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                grp.nextMatchDay();
            }
        };
        new Thread(test, "TEST change MatchDay").start();

        // Navigator
        Navigator nav = new Navigator();
        JFrame winN = new JFrame();
        winN.setContentPane(nav);
        winN.setTitle("MaYoT - Navigator");
        winN.setSize(100,100);
        winN.setLocationRelativeTo(null);
        winN.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winN.setVisible(true);


    }

    /** Setup a custom L&F.
     *
     */
    public static void initLAF() {
        if(logger.isDebugEnabled()) {
            logger.debug("Initializing L&F...");
        }
        Color transparent = new Color(0,0,0,0);
        Color semiTrans = new Color(255,255,255,50);

        UIManager.put("Button.focus", new javax.swing.plaf.ColorUIResource(transparent));
        UIManager.put("Button.shadow", new javax.swing.plaf.ColorUIResource(transparent));
        UIManager.put("Button.darkShadow", new javax.swing.plaf.ColorUIResource(transparent));
        UIManager.put("Button.select", new javax.swing.plaf.ColorUIResource(semiTrans));
        UIManager.put("Button.gradient", new javax.swing.plaf.ColorUIResource(transparent));
    }
}
