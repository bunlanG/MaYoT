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

package com.bunlang.mayot.scores;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.Vector;

/** Manage several matches in a MatchDay.
 *
 *  @see Match
 *  @author bunlanG
 */
public class MatchDay {
    // Fields
    protected Vector<Match> _matches;

    JPanel _pan;

    public MatchDay() {
        _matches = new Vector<>();

        _pan = new JPanel();
        _pan.setLayout(new BoxLayout(_pan, BoxLayout.PAGE_AXIS));
    }

    public void add(Object obj) {
        if(obj.getClass().getName().equals("com.bunlang.mayot.scores.Match")) {
            Match match = (Match) obj;
            _matches.add(match);

            // Add a spacer
            if(_matches.size() > 1) {
                _pan.add(Box.createRigidArea(new Dimension(0,2)));
            }

            _pan.add(match.getPanel());

            int width = match.getPanel().getWidth();
            int height = _matches.size() * (match.getPanel().getHeight() + 2) + 24;
            Dimension pref = new Dimension(width, height);
            _pan.setSize(pref);
            _pan.setPreferredSize(pref);
            _pan.setMinimumSize(pref);

            System.out.println("Panel M  height : " + match.getPanel().getHeight() + "px");
            System.out.println("Panel MD height : " + _pan.getHeight() + "px");
        }
    }

    public JPanel getPanel() {
        return _pan;
    }
}
