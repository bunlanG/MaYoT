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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.Vector;

/** Manage several teams in a Table.
 *
 *  @see Team
 *  @author bunlanG
 */
public class Table {
    // Fields
    protected Vector<Team> _teams;
    protected TeamUI _head;

    JPanel _pan;

    public Table() {
        _teams = new Vector<>();
        _head = new TeamUI(null);

        _pan = new JPanel();
        _pan.setLayout(new BoxLayout(_pan, BoxLayout.PAGE_AXIS));

        _pan.add(_head.getPanel());
    }

    public void add(Object obj) {
        if(obj.getClass().getName().equals("com.bunlang.mayot.ranking.Team")) {
            Team team = (Team) obj;
            _teams.add(team);

            // Add a spacer
            _pan.add(Box.createRigidArea(new Dimension(0, 1)));

            _pan.add(team.getPanel());

            int width = team.getPanel().getWidth();
            int height = (_teams.size() + 1) * (team.getPanel().getHeight() + 1) - 1;
            Dimension pref = new Dimension(width, height);
            _pan.setSize(pref);
            _pan.setPreferredSize(pref);
            _pan.setMinimumSize(pref);
        }
    }

    public JPanel getPanel() {
        return _pan;
    }
}
