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

import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertTrue;

public class TableUITest {

    @BeforeClass
    public static void setupAll() {
        // Setup L&F in UI Test
        com.bunlang.mayot.Main.initLAF();
    }

    @Test
    public void test() {
        Table table = new Table();
        Team team1 = new Team("Team 1", 0,0,0,0,0, 0,0);
        Team team2 = new Team("Team 2", 0,0,0,0,0, 0,0);
        Team team3 = new Team("Team 3", 0,0,0,0,0, 0,0);
        Team team4 = new Team("Team 4", 0,0,0,0,0, 0,0);

        table.add(team1);
        table.add(team2);
        table.add(team3);
        table.add(team4);

        Box ui = table.getPanel();
        JFrame win = new JFrame();
        win.setContentPane(ui);
        win.setTitle("MaYoT - org.bunlang.mayot.ranking.Table UI Test");
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
