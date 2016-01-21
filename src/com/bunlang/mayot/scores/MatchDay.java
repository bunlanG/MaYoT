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

import org.apache.log4j.Logger;

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
    private static Logger logger = Logger.getLogger("com.bunlang.mayot");
    // Fields
    protected Vector<Match> _matches;
    protected String _title;

    Box _box;

    public MatchDay(String title) {
        _matches = new Vector<>();
        _title = title;

        _box = Box.createVerticalBox();

        if(logger.isDebugEnabled()) {
            logger.debug("scores.MatchDay created");
        }
    }

    public void add(Object obj) {
        if(obj.getClass().getName().equals("com.bunlang.mayot.scores.Match")) {
            Match match = (Match) obj;
            _matches.add(match);

            // Add a spacer
            if(_matches.size() > 1) {
                _box.add(Box.createRigidArea(new Dimension(0,2)));
            }

            _box.add(match.getPanel());

            int width = match.getPanel().getPreferredSize().width;
            int height = _matches.size() * (match.getPanel().getPreferredSize().height + 2) - 2;
            Dimension pref = new Dimension(width, height);
            _box.setMaximumSize(pref);
            _box.setPreferredSize(pref);
            _box.setMinimumSize(pref);
            _box.setSize(pref);
        }
    }

    /** Returns the panel of the MatchDay, for GUIs.
     *
     * @return the panel of the MatchDay
     */
    public Box getPanel() {
        return _box;
    }

    /** Returns the MatchDay's title
     *
     * @return the MatchDay's title
     */
    public String getTitle() {
        return _title;
    }

    /** Get XML-format of the MatchDay.
     *
     * @return a XML-format of the MatchDay
     */
    public String toXml() {
        String s = "";

        s += "\t\t\t<matchday ttl=\"" + _title + "\">\n";

        for(Match m : _matches) {
            s += m.toXml();
        }

        s += "\t\t\t</matchday>\n";

        return s;
    }
}
