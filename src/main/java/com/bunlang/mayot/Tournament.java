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

package com.bunlang.mayot;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.*;
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
    protected String _path;

    protected JLabel _tournamentTitleUI;
    protected JLabel _tableTitleUI;
    protected JLabel _matchdayTitleUI;
    protected JPanel _panLine1;
    protected JPanel _panLine2;
    protected Box _box;

    public Tournament() {
        Dimension dimNav = new Dimension(90,90);
        Dimension dimTrnTtl = new Dimension(740,100);
        Dimension dimMDTtl = new Dimension(535,50);
        Dimension dimTblTtl = new Dimension(455,50);
        Color grey = new Color(0.23f, 0.23f, 0.23f);
        Font bigFont = new Font("Sansation", Font.BOLD, 28);
        Font normFont = new Font("Sansation", Font.BOLD | Font.ITALIC, 21);

        _groups = new Vector<>();
        _currGroup = -1;

        _nav = new Navigator(this);
        _nav.setSize(dimNav);
        _nav.setMinimumSize(dimNav);
        _nav.setMaximumSize(dimNav);
        _nav.setPreferredSize(dimNav);
        _nav.setAlignmentY(Component.CENTER_ALIGNMENT);

        _tournamentTitleUI = new JLabel("Tournament :");
        _tournamentTitleUI.setSize(dimTrnTtl);
        _tournamentTitleUI.setMinimumSize(dimTrnTtl);
        _tournamentTitleUI.setMaximumSize(dimTrnTtl);
        _tournamentTitleUI.setPreferredSize(dimTrnTtl);
        _tournamentTitleUI.setForeground(Color.WHITE);
        _tournamentTitleUI.setHorizontalAlignment(SwingConstants.CENTER);
        _tournamentTitleUI.setFont(bigFont);
        _tournamentTitleUI.setAlignmentY(Component.CENTER_ALIGNMENT);

        _tableTitleUI = new JLabel("Table :");
        _tableTitleUI.setSize(dimTblTtl);
        _tableTitleUI.setMinimumSize(dimTblTtl);
        _tableTitleUI.setMaximumSize(dimTblTtl);
        _tableTitleUI.setPreferredSize(dimTblTtl);
        _tableTitleUI.setForeground(Color.WHITE);
        _tableTitleUI.setHorizontalAlignment(SwingConstants.CENTER);
        _tableTitleUI.setFont(normFont);
        _tableTitleUI.setAlignmentY(Component.CENTER_ALIGNMENT);

        _matchdayTitleUI = new JLabel("MatchDay :");
        _matchdayTitleUI.setSize(dimMDTtl);
        _matchdayTitleUI.setMinimumSize(dimMDTtl);
        _matchdayTitleUI.setMaximumSize(dimMDTtl);
        _matchdayTitleUI.setPreferredSize(dimMDTtl);
        _matchdayTitleUI.setForeground(Color.WHITE);
        _matchdayTitleUI.setHorizontalAlignment(SwingConstants.CENTER);
        _matchdayTitleUI.setFont(normFont);
        _matchdayTitleUI.setAlignmentY(Component.CENTER_ALIGNMENT);

        _panLine1 = new JPanel();
        _panLine1.setLayout(new BoxLayout(_panLine1, BoxLayout.LINE_AXIS));
        _panLine1.setBackground(grey);
        _panLine1.add(Box.createRigidArea(new Dimension(175,5)));
        _panLine1.add(_tournamentTitleUI);
        _panLine1.add(_nav);
        _panLine1.add(Box.createRigidArea(new Dimension(35,5)));

        _panLine2 = new JPanel();
        _panLine2.setLayout(new BoxLayout(_panLine2, BoxLayout.LINE_AXIS));
        _panLine2.setBackground(grey);
        _panLine2.add(_matchdayTitleUI);
        _panLine2.add(Box.createRigidArea(new Dimension(50,5)));
        _panLine2.add(_tableTitleUI);
        _panLine2.setAlignmentY(Component.CENTER_ALIGNMENT);

        _box = Box.createVerticalBox();
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
        // Update titles
        _matchdayTitleUI.setText(_groups.get(_currGroup).getCurrMDTitle());

        // Update UI
        _box.removeAll();

        _box.add(_panLine1);
        _box.add(_panLine2);
        _box.add(_groups.get(_currGroup).getPanel());
        _box.add(Box.createGlue());

        int grpSizeW = _groups.get(_currGroup).getPanel().getWidth();
        int sizeH = 152 + _groups.get(_currGroup).getPanel().getHeight();

        _box.setPreferredSize(new Dimension(grpSizeW, sizeH));
        _box.setMinimumSize(new Dimension(grpSizeW, sizeH));
        _box.setSize(new Dimension(grpSizeW, sizeH));

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

        update();
    }

    @Override
    public void gotoDown() {
        _groups.get(_currGroup).nextMatchDay();

        update();
    }

    @Override
    public boolean lockedLeft() {
        return _currGroup == 0;
    }

    @Override
    public boolean lockedRight() {
        return _currGroup == _groups.size() - 1;
    }

    @Override
    public boolean lockedUp() {
        return _groups.get(_currGroup).isFirstMD();
    }

    @Override
    public boolean lockedDown() {
        return _groups.get(_currGroup).isLastMD();
    }

    public Box getPanel() {
        return _box;
    }

    public String getFilePath() {
        return _path;
    }

    public void setFilePath(String path) {
        _path = path;
    }

    /** Get XML-format of the Tournament, with header line for XML.
     *
     * @return a XML-format of the Tournament
     */
    public String toXml() {
        String s = "";

        s += "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";

        s += "<tournament>\n";

        for(Group g : _groups) {
            s += g.toXml();
        }

        s += "</tournament>\n";

        return s;
    }
}
