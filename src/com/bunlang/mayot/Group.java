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
import com.bunlang.mayot.scores.MatchDay;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/** A Group contains all {@link MatchDay} and a {@link Table}.
 *
 *  @author bunlanG
 */
public class Group {
    private static Logger logger = Logger.getLogger("com.bunlang.mayot");
    // Fields
    protected Vector<MatchDay> _matchDays;
    protected int _currMDInd;
    protected Table _table;

    protected Box _box;

    /** Create a new {@link Group}.
     *
     */
    public Group() {
        _matchDays = new Vector<>();
        _currMDInd = -1;
        _table = new Table();
        _table.getPanel().setAlignmentY(Component.TOP_ALIGNMENT);

        _box = Box.createHorizontalBox();

        if(logger.isDebugEnabled()) {
            logger.debug("Group created");
        }
    }

    /** Adds an object in the internal containers.
     *
     * @param obj The object passed. It must be a {@link MatchDay} or a {@link com.bunlang.mayot.ranking.Team}.
     */
    public void add(Object obj) {
        if(obj.getClass().getName().equals("com.bunlang.mayot.scores.MatchDay")) {
            MatchDay matchDay = (MatchDay) obj;
            matchDay.getPanel().setAlignmentY(Component.TOP_ALIGNMENT);
            _matchDays.add(matchDay);
            _currMDInd = _matchDays.size() - 1;

            resetPanel();
            update();
        } else if(obj.getClass().getName().equals("com.bunlang.mayot.ranking.Team")) {
            _table.add(obj);

            updateSize();
        }
    }

    /** Clear the Panel content.
     *
     */
    public void resetPanel() {
        if(_currMDInd >= 0) {
            _box.removeAll();
        }
    }

    /** Update the Panel content.
     *
     */
    public void update() {
        Component gap = Box.createRigidArea(new Dimension(50, 5));
        if(_currMDInd >= 0) {
            MatchDay matchDay = _matchDays.get(_currMDInd);

            // Update the layout
            _box.removeAll();
            _box.add(matchDay.getPanel());
            _box.add(gap);
            _box.add(_table.getPanel());
        } else {
            // Update the layout
            _box.removeAll();
            _box.add(gap);
            _box.add(_table.getPanel());
        }

        updateSize();
    }

    private void updateSize() {
        Dimension mthDayDim;
        if(_currMDInd >= 0) {
            MatchDay matchDay = _matchDays.get(_currMDInd);
            mthDayDim = matchDay.getPanel().getSize();
        } else {
            mthDayDim = new Dimension(0,0);
        }

        int width = (int)(mthDayDim.getWidth()) + 50 + _table.getPanel().getWidth();
        int height = Math.max((int)(mthDayDim.getHeight()), _table.getPanel().getHeight()) + 26;
        Dimension pref = new Dimension(width, height);
        _box.setSize(pref);
        _box.setPreferredSize(pref);
        _box.setMinimumSize(pref);
    }

    /** Change current {@link MatchDay} to the next one.
     *
     */
    public void nextMatchDay() {
        if(_currMDInd < _matchDays.size() - 1) {
            _currMDInd++;

            resetPanel();
            update();

            if(logger.isDebugEnabled()) {
                logger.debug("next MatchDay.");
            }
        }
    }

    /** Change current {@link MatchDay} to the previous one.
     *
     */
    public void prevMatchDay() {
        if(_currMDInd > 0) {
            _currMDInd--;

            resetPanel();
            update();

            if(logger.isDebugEnabled()) {
                logger.debug("previous MatchDay.");
            }
        }
    }

    /** Get XML-format of the Group.
     *
     * @return a XML-format of the Group
     */
    public String toXml() {
        String s = "";

        s += "\t<group>\n";
        s += _table.toXml();

        s += "\t\t<scores>\n";

        for(MatchDay md : _matchDays) {
            s += md.toXml();
        }

        s += "\t\t</scores>\n";
        s += "\t</group>\n";

        return s;
    }

    /** Returns the panel of the Group, for GUIs.
     *
     * @return the panel of the Group
     */
    public Box getPanel() {
        return _box;
    }

    public String getCurrMDTitle() {
        if(_currMDInd >= 0) {
            return _matchDays.get(_currMDInd).getTitle();
        } else {
            return "";
        }
    }

    public boolean isFirstMD() {
        return _currMDInd == 0;
    }

    public boolean isLastMD() {
        return _currMDInd == _matchDays.size() - 1;
    }

    public Team findTeamById(int id) {
        return _table.findTeamById(id);
    }

    public Team findTeamByName(String name) {
        return _table.findTeamByName(name);
    }

}
