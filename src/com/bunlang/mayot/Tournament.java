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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.Vector;

/** Represent a tournament.
 *
 *  @author bunlanG
 */
public class Tournament implements Navigable {
    // Fields

    protected Vector<Group> _groups;
    protected int _currGroup;
    protected Navigator _nav;

    protected JLabel _tournamentTitleUI;
    protected JLabel _tableTitleUI;
    protected JLabel _matchdayTitleUI;
    protected JPanel _panLine1;
    protected JPanel _panLine2;
    protected JPanel _pan;

    public Tournament() {
        _groups = new Vector<>();
        _currGroup = -1;
        _nav = new Navigator(this);

        _tournamentTitleUI = new JLabel("Tournament :");

        _tableTitleUI = new JLabel("Raking :");

        _matchdayTitleUI = new JLabel("MatchDay :");

        _panLine1 = new JPanel();
        _panLine1.setLayout(new BoxLayout(_panLine1, BoxLayout.LINE_AXIS));
        _panLine1.add(_tournamentTitleUI);
        _panLine1.add(_nav);

        _panLine2 = new JPanel();
        _panLine2.setLayout(new BoxLayout(_panLine2, BoxLayout.LINE_AXIS));
        _panLine2.add(_matchdayTitleUI);
        _panLine2.add(Box.createRigidArea(new Dimension(50,5)));
        _panLine2.add(_tableTitleUI);

        _pan = new JPanel();
        _pan.setLayout(new BoxLayout(_pan, BoxLayout.PAGE_AXIS));
    }

    public void add(Object obj) {
        if(obj.getClass().getName().equals("com.bunlang.mayot.Group")) {
            Group grp = (Group) obj;
            _groups.add(grp);
            _currGroup = _groups.size() - 1;

            update();
        }
    }

    public void update() {
        _pan.removeAll();

        _pan.add(_panLine1);
        _pan.add(_panLine2);
        _pan.add(_groups.get(_currGroup).getPanel());

    }

    @Override
    public void gotoLeft() {
        if(_currGroup > 0) {
            _currGroup--;

            update();
        }
    }

    @Override
    public void gotoRight() {
        if(_currGroup < _groups.size() - 1) {
            _currGroup++;

            update();
        }
    }

    @Override
    public void gotoUp() {
        _groups.get(_currGroup).prevMatchDay();
    }

    @Override
    public void gotoDown() {
        _groups.get(_currGroup).nextMatchDay();
    }

    public JPanel getPanel() {
        return _pan;
    }
}
