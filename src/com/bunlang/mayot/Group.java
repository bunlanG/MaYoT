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
import com.bunlang.mayot.scores.MatchDay;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/** A Group contains all MatchDay and a Table.
 *
 *  @author bunlanG
 */
public class Group {
    // Fields
    protected Vector<MatchDay> _matchDays;
    protected int _currMDInd;
    protected Table _table;

    protected JPanel _pan;

    public Group() {
        _matchDays = new Vector<>();
        _currMDInd = -1;
        _table = new Table();

        _pan = new JPanel();
        GroupLayout bxLy = new GroupLayout(_pan);
        _pan.setLayout(bxLy);
        _pan.setBackground(new Color(0.33f,0.33f,0.33f));
    }

    public void add(Object obj) {
        if(obj.getClass().getName().equals("com.bunlang.mayot.scores.MatchDay")) {
            MatchDay matchDay = (MatchDay) obj;
            _matchDays.add(matchDay);
            _currMDInd = _matchDays.size() - 1;

            if(_currMDInd >= 0) {
                _pan.removeAll();
            }

            // Update the layout
            GroupLayout layout = ((GroupLayout) _pan.getLayout());
            Component gap = Box.createRigidArea(new Dimension(50, 5));
            GroupLayout.Group hGroup = layout.createSequentialGroup();
            hGroup.addGroup(layout.createParallelGroup().addComponent(matchDay.getPanel()));
            hGroup.addGroup(layout.createParallelGroup().addComponent(gap));
            hGroup.addGroup(layout.createParallelGroup().addComponent(_table.getPanel()));
            layout.setHorizontalGroup(hGroup);
            GroupLayout.Group vGroup = layout.createSequentialGroup();
            vGroup.addGroup(layout.createParallelGroup()
                    .addComponent(matchDay.getPanel())
                    .addComponent(gap)
                    .addComponent(_table.getPanel()));
            layout.setVerticalGroup(vGroup);


            int width = matchDay.getPanel().getWidth() + 50 + _table.getPanel().getWidth();
            int height = Math.max(matchDay.getPanel().getWidth(), _table.getPanel().getWidth()) + 26;
            Dimension pref = new Dimension(width, height);
            _pan.setSize(pref);
            _pan.setPreferredSize(pref);
            _pan.setMinimumSize(pref);
        } else if(obj.getClass().getName().equals("com.bunlang.mayot.ranking.Team")) {
            _table.add(obj);

            int width = _matchDays.get(_currMDInd).getPanel().getWidth() + 50 + _table.getPanel().getWidth();
            int height = Math.max(_matchDays.get(_currMDInd).getPanel().getHeight(), _table.getPanel().getHeight()) + 26;
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
