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

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;

/** The view of a Team.
 *
 *  @author bunlanG
 */
public class TeamUI {
    // Fields
    protected Team _data;

    protected JLabel _name;

    protected JLabel _pos;
    protected JLabel _pTotal;
    protected JLabel _mPlayed;
    protected JLabel _sDiff;

    protected JLabel _mWins;
    protected JLabel _mDraws;
    protected JLabel _mLoses;
    protected JLabel _pBonus;
    protected JLabel _pFixer;
    protected JLabel _sFor;
    protected JLabel _sAgnst;

    protected JPanel _pan;


    public TeamUI(Team data) {
        Dimension dimTtl = new Dimension(400, 20);

        _data = data;

        _pos = new JLabel();
        _name = new JLabel();
        _pTotal = new JLabel();
        _mPlayed = new JLabel();
        _mWins = new JLabel();
        _mDraws = new JLabel();
        _mLoses = new JLabel();
        _pBonus = new JLabel();
        _pFixer = new JLabel();
        _sFor = new JLabel();
        _sAgnst = new JLabel();
        _sDiff = new JLabel();

        _pan = new JPanel();
        _pan.setLayout(new BoxLayout(_pan, BoxLayout.LINE_AXIS));
        _pan.add(_pos);
        _pan.add(_name);
        _pan.add(_pTotal);
        _pan.add(_mPlayed);
        _pan.add(_mWins);
        _pan.add(_mDraws);
        _pan.add(_mLoses);
        _pan.add(_pBonus);
        _pan.add(_pFixer);
        _pan.add(_sFor);
        _pan.add(_sAgnst);
        _pan.add(_sDiff);

        _pan.setMinimumSize(dimTtl);
        _pan.setPreferredSize(dimTtl);
        _pan.setMaximumSize(dimTtl);
        _pan.setSize(dimTtl);

        this.update();
    }

    protected void update() {
        _pos.setText(_data.getPos());
        _name.setText(_data.getName());
        _pTotal.setText(Integer.toString(_data.getPTotal()));

        _mPlayed.setText(Integer.toString(_data.getMPlayed()));
        _mWins.setText(Integer.toString(_data.getMWins()));
        _mDraws.setText(Integer.toString(_data.getMDraws()));
        _mLoses.setText(Integer.toString(_data.getMLoses()));
        _pBonus.setText(Integer.toString(_data.getPBonus()));
        _pFixer.setText(Integer.toString(_data.getPFixer()));

        _sFor.setText(Integer.toString(_data.getSFor()));
        _sAgnst.setText(Integer.toString(_data.getSAgnst()));
        _sDiff.setText(Integer.toString(_data.getSDiff()));


    }

    public JPanel getPanel() {
        return _pan;
    }
}
