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

package com.bunlang.mayot.ranking;

import com.bunlang.mayot.scores.Match;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertTrue;

public class TeamUITest {

    @BeforeClass
    public static void setupAll() {
        // Setup L&F in UI Test
        com.bunlang.mayot.Main.initLAF();
    }

    @Test
    public void test() {
        // ranking
        Team team = new Team("Hello", 0,0,0,0,0, 0,0);
        Box tUI = team.getPanel();
        JFrame win = new JFrame();
            win.setContentPane(tUI);
            win.setTitle("MaYoT - org.bunlang.mayot.ranking.Team UI Test");
            win.setSize(tUI.getSize());
            win.setMinimumSize(tUI.getSize());
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
