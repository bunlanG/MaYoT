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

package com.bunlang.mayot.ranking;

import org.apache.log4j.Logger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/** Manage several {@link Team} in a {@link Table}.
 *
 *  @see Team
 *  @author bunlanG
 */
public class Table implements Observer {
    private static Logger logger = Logger.getLogger("com.bunlang.mayot");
    // Fields
    protected Vector<Team> _teams;
    protected TeamUI _head;

    Box _box;

    public Table() {
        _teams = new Vector<>();
        _head = new TeamUI(null);

        _box = Box.createVerticalBox();

        _box.add(_head.getPanel());

        if(logger.isDebugEnabled()) {
            logger.debug("ranking.Table created");
        }
    }

    /** Adds an object in the internal containers.
     *
     * @param obj The object passed. It must be a {@link Team}.
     */
    public void add(Object obj) {
        if(obj.getClass().getName().equals("com.bunlang.mayot.ranking.Team")) {
            Team team = (Team) obj;
            _teams.add(team);
            team.addObserver(this);

            // Add a spacer
            _box.add(Box.createRigidArea(new Dimension(0, 1)));

            _box.add(team.getPanel());

            int width = team.getPanel().getWidth();
            int height = (_teams.size() + 1) * (team.getPanel().getHeight() + 1) - 1;
            Dimension pref = new Dimension(width, height);
            _box.setSize(pref);
            _box.setPreferredSize(pref);
            _box.setMinimumSize(pref);
            _box.setMaximumSize(pref);

            this.update(null, null);

            if(logger.isDebugEnabled()) {
                logger.debug("ranking.Table add a Team : " + team);
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        // Clear _pan
        _box.removeAll();

        // Sort _teams
        Collections.sort(_teams);

        // Re-make _pan
        // Header
        _box.add(_head.getPanel());
        for(int i = 0 ; i < _teams.size() ; i++) {
            _box.add(Box.createRigidArea(new Dimension(0, 1)));

            _box.add(_teams.get(i).getPanel());

            _teams.get(i).setPos((i+1) + "e");
        }

        if(logger.isDebugEnabled()) {
            logger.debug("ranking.Table updated.");
        }
    }
    /** Returns the panel of the Table, for GUIs.
     *
     * @return the panel of the Table
     */
    public Box getPanel() {
        return _box;
    }

    public Team findTeamById(int id) {
        for (Team _team : _teams) {
            if (id == _team.getId()) {
                return _team;
            }
        }

        // Default value if not founded
        return null;
    }

    public Team findTeamByName(String name) {
        for (Team _team : _teams) {
            if (name.equals(_team.getName())) {
                return _team;
            }
        }

        // Default value if not founded
        return null;
    }

    /** Get XML-format of the Table.
     *
     * @return a XML-format of the Table
     */
    public String toXml() {
        String s = "";

        s += "\t\t<table>\n";

        for(Team t : _teams) {
            s += t.toXml();
        }

        s += "\t\t</table>\n";


        return s;
    }
}
