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
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

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

    protected JLabel _sep1;
    protected JLabel _sep2;
    protected JLabel _sep3;

    protected JPanel _pan;


    public TeamUI(Team data) {
        Dimension dimTtl = new Dimension(347, 16);
        Dimension dimName = new Dimension(60, 16);
        Dimension dimInt = new Dimension(25, 16);
        Dimension dimSep = new Dimension(2, 16);
        Color grey15 = new Color(0.15f, 0.15f, 0.15f);
        Font normFont = new Font("Sansation", Font.BOLD, 12);


        _data = data;

        _pos = new JLabel();
        _pos.setMinimumSize(dimInt);
        _pos.setPreferredSize(dimInt);
        _pos.setMaximumSize(dimInt);
        _pos.setFont(normFont);
        _pos.setForeground(Color.WHITE);
        _pos.setHorizontalAlignment(SwingConstants.CENTER);

        _name = new JLabel();
        _name.setMinimumSize(dimName);
        _name.setPreferredSize(dimName);
        _name.setMaximumSize(dimName);
        _name.setFont(normFont);
        _name.setForeground(Color.WHITE);
        _name.setHorizontalAlignment(SwingConstants.CENTER);

        _pTotal = new JLabel();
        _pTotal.setMinimumSize(dimInt);
        _pTotal.setPreferredSize(dimInt);
        _pTotal.setMaximumSize(dimInt);
        _pTotal.setFont(normFont);
        _pTotal.setForeground(Color.WHITE);
        _pTotal.setHorizontalAlignment(SwingConstants.CENTER);

        _mPlayed = new JLabel();
        _mPlayed.setMinimumSize(dimInt);
        _mPlayed.setPreferredSize(dimInt);
        _mPlayed.setMaximumSize(dimInt);
        _mPlayed.setFont(normFont);
        _mPlayed.setForeground(Color.WHITE);
        _mPlayed.setHorizontalAlignment(SwingConstants.CENTER);

        _mWins = new JLabel();
        _mWins.setMinimumSize(dimInt);
        _mWins.setPreferredSize(dimInt);
        _mWins.setMaximumSize(dimInt);
        _mWins.setFont(normFont);
        _mWins.setForeground(Color.WHITE);
        _mWins.setHorizontalAlignment(SwingConstants.CENTER);

        _mDraws = new JLabel();
        _mDraws.setMinimumSize(dimInt);
        _mDraws.setPreferredSize(dimInt);
        _mDraws.setMaximumSize(dimInt);
        _mDraws.setFont(normFont);
        _mDraws.setForeground(Color.WHITE);
        _mDraws.setHorizontalAlignment(SwingConstants.CENTER);

        _mLoses = new JLabel();
        _mLoses.setMinimumSize(dimInt);
        _mLoses.setPreferredSize(dimInt);
        _mLoses.setMaximumSize(dimInt);
        _mLoses.setFont(normFont);
        _mLoses.setForeground(Color.WHITE);
        _mLoses.setHorizontalAlignment(SwingConstants.CENTER);

        _pBonus = new JLabel();
        _pBonus.setMinimumSize(dimInt);
        _pBonus.setPreferredSize(dimInt);
        _pBonus.setMaximumSize(dimInt);
        _pBonus.setFont(normFont);
        _pBonus.setForeground(Color.WHITE);
        _pBonus.setHorizontalAlignment(SwingConstants.CENTER);

        _pFixer = new JLabel();
        _pFixer.setMinimumSize(dimInt);
        _pFixer.setPreferredSize(dimInt);
        _pFixer.setMaximumSize(dimInt);
        _pFixer.setFont(normFont);
        _pFixer.setForeground(Color.WHITE);
        _pFixer.setHorizontalAlignment(SwingConstants.CENTER);

        _sFor = new JLabel();
        _sFor.setMinimumSize(dimInt);
        _sFor.setPreferredSize(dimInt);
        _sFor.setMaximumSize(dimInt);
        _sFor.setFont(normFont);
        _sFor.setForeground(Color.WHITE);
        _sFor.setHorizontalAlignment(SwingConstants.CENTER);

        _sAgnst = new JLabel();
        _sAgnst.setMinimumSize(dimInt);
        _sAgnst.setPreferredSize(dimInt);
        _sAgnst.setMaximumSize(dimInt);
        _sAgnst.setFont(normFont);
        _sAgnst.setForeground(Color.WHITE);
        _sAgnst.setHorizontalAlignment(SwingConstants.CENTER);

        _sDiff = new JLabel();
        _sDiff.setMinimumSize(dimInt);
        _sDiff.setPreferredSize(dimInt);
        _sDiff.setMaximumSize(dimInt);
        _sDiff.setFont(normFont);
        _sDiff.setForeground(Color.WHITE);
        _sDiff.setHorizontalAlignment(SwingConstants.CENTER);

        _sep1 = new JLabel();
        _sep1.setMinimumSize(dimSep);
        _sep1.setPreferredSize(dimSep);
        _sep1.setMaximumSize(dimSep);
        _sep1.setBackground(Color.WHITE);
        _sep1.setOpaque(true);

        _sep2 = new JLabel();
        _sep2.setMinimumSize(dimSep);
        _sep2.setPreferredSize(dimSep);
        _sep2.setMaximumSize(dimSep);
        _sep2.setBackground(Color.WHITE);
        _sep2.setOpaque(true);

        _sep3 = new JLabel();
        _sep3.setMinimumSize(dimSep);
        _sep3.setPreferredSize(dimSep);
        _sep3.setMaximumSize(dimSep);
        _sep3.setBackground(Color.WHITE);
        _sep3.setOpaque(true);

        _pan = new JPanel();
        _pan.setLayout(new BoxLayout(_pan, BoxLayout.LINE_AXIS));
        _pan.add(_pos);

        _pan.add(_sep1);

        _pan.add(_name);
        _pan.add(_pTotal);

        _pan.add(_sep2);

        _pan.add(_mPlayed);
        _pan.add(_mWins);
        _pan.add(_mDraws);
        _pan.add(_mLoses);
        _pan.add(_pBonus);
        _pan.add(_pFixer);

        _pan.add(_sep3);

        _pan.add(_sFor);
        _pan.add(_sAgnst);
        _pan.add(_sDiff);

        _pan.setMinimumSize(dimTtl);
        _pan.setPreferredSize(dimTtl);
        _pan.setMaximumSize(dimTtl);
        _pan.setSize(dimTtl);
        _pan.setBackground(grey15);

        this.update();
    }

    protected void update() {
        if(_data != null) {
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
        } else {
            _pos.setText("Pos");
            _name.setText("Name");
            _pTotal.setText("Pts");

            _mPlayed.setText("MP");
            _mWins.setText("MW");
            _mDraws.setText("MD");
            _mLoses.setText("ML");
            _pBonus.setText("Bon");
            _pFixer.setText("Fix");

            _sFor.setText("SF");
            _sAgnst.setText("SA");
            _sDiff.setText("Diff");
        }
    }

    public JPanel getPanel() {
        return _pan;
    }
}
