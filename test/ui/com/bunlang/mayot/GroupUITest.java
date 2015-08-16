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

import com.bunlang.mayot.ranking.Team;
import com.bunlang.mayot.scores.Match;
import com.bunlang.mayot.scores.MatchDay;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.JFrame;
import javax.swing.JPanel;

import static org.junit.Assert.assertTrue;

public class GroupUITest {

    @BeforeClass
    public static void setupAll() {
        // Setup L&F in UI Test
        com.bunlang.mayot.Main.initLAF();
    }

    @Test
    public void test() {
        // scores
        MatchDay matchDay = new MatchDay();
        Match match = new Match("Team 1", "Team 5");
        Match match2 = new Match("Team 4", "Team 6");
        Match match3 = new Match("Team 2", "Team 3");

        matchDay.add(match);
        matchDay.add(match2);
        matchDay.add(match3);

        // ranking
        Team team1 = new Team("Team 1", 0,0,0,0,0, 0,0);
        Team team2 = new Team("Team 2", 0,0,0,0,0, 0,0);
        Team team3 = new Team("Team 3", 0,0,0,0,0, 0,0);
        Team team4 = new Team("Team 4", 0,0,0,0,0, 0,0);
        Team team5 = new Team("Team 5", 0,0,0,0,0, 0,0);
        Team team6 = new Team("Team 6", 0,0,0,0,0, 0,0);

        Group grp = new Group();

        grp.add(matchDay);

        grp.add(team1);
        grp.add(team2);
        grp.add(team3);
        grp.add(team4);
        grp.add(team5);
        grp.add(team6);

        JPanel ui = grp.getPanel();
        JFrame win = new JFrame();
        win.setContentPane(ui);
        win.setTitle("MaYoT - org.bunlang.mayot.Group UI Test");
        win.setSize(ui.getSize());
        win.setMinimumSize(ui.getSize());
        win.setLocationRelativeTo(null);
        win.setVisible(true);

        while(win.isShowing()) {
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        assertTrue(true);
    }
}
