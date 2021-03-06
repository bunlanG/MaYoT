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

package com.bunlang.mayot.scores;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author bunlanG
 */
public class MatchUI implements ActionListener {
    protected Match _data;

    protected JButton _hostBut;
    protected JButton _guestBut;
    protected JLabel _scrUI;
    protected JButton _infoUI;
    protected JButton _fixBut;
    protected Box _box;
    protected Box _boxLbl;

    public MatchUI(Match data) {
        _data = data;

        init();
    }

    protected void init() {
        Dimension dimBut = new Dimension(200, 30);
        Dimension dimFix = new Dimension(60, 30);
        Dimension dimLbl = new Dimension(75, 30);
        Dimension dimLbl1 = new Dimension(75, 18);
        Dimension dimLbl2 = new Dimension(75, 12);
        Dimension dimTtl = new Dimension(535, 30);
        Color grey10 = new Color(0.1f, 0.1f, 0.1f);
        Color grey15 = new Color(0.15f, 0.15f, 0.15f);
        Color grey25 = new Color(0.25f, 0.25f, 0.25f);
        Font dimFont = new Font("Sansation", Font.ITALIC | Font.BOLD, 9);
        Font normFont = new Font("Sansation", Font.BOLD, 15);

        _hostBut = new JButton();
        _hostBut.setMinimumSize(dimBut);
        _hostBut.setPreferredSize(dimBut);
        _hostBut.setMaximumSize(dimBut);
        _hostBut.setFont(normFont);
        _hostBut.setForeground(Color.WHITE);
        _hostBut.setBackground(grey10);
        _hostBut.setBorder(null);
        _hostBut.addActionListener(this);

        _guestBut = new JButton();
        _guestBut.setMinimumSize(dimBut);
        _guestBut.setPreferredSize(dimBut);
        _guestBut.setMaximumSize(dimBut);
        _guestBut.setFont(normFont);
        _guestBut.setForeground(Color.WHITE);
        _guestBut.setBackground(grey10);
        _guestBut.setBorder(null);
        _guestBut.addActionListener(this);

        _scrUI = new JLabel();
        _scrUI.setMinimumSize(dimLbl1);
        _scrUI.setPreferredSize(dimLbl1);
        _scrUI.setMaximumSize(dimLbl1);
        _scrUI.setFont(normFont);
        _scrUI.setForeground(Color.WHITE);
        _scrUI.setBackground(grey15);
        _scrUI.setOpaque(true);
        _scrUI.setHorizontalAlignment(SwingConstants.CENTER);

        _infoUI = new JButton();
        _infoUI.setMinimumSize(dimLbl2);
        _infoUI.setPreferredSize(dimLbl2);
        _infoUI.setMaximumSize(dimLbl2);
        _infoUI.setFont(dimFont);
        _infoUI.setForeground(Color.WHITE);
        _infoUI.setBackground(grey25);
        _infoUI.setBorder(null);
        _infoUI.addActionListener(this);

        _fixBut = new JButton();
        _fixBut.setMinimumSize(dimFix);
        _fixBut.setPreferredSize(dimFix);
        _fixBut.setMaximumSize(dimFix);
        _fixBut.setFont(normFont);
        _fixBut.setForeground(Color.WHITE);
        _fixBut.setBackground(grey25);
        _fixBut.setBorder(null);
        _fixBut.addActionListener(this);

        _boxLbl = Box.createVerticalBox();
        _boxLbl.add(_scrUI);
        _boxLbl.add(_infoUI);
        _boxLbl.setMinimumSize(dimLbl);
        _boxLbl.setPreferredSize(dimLbl);
        _boxLbl.setMaximumSize(dimLbl);
        _boxLbl.setOpaque(false);

        _box = Box.createHorizontalBox();
        _box.add(_hostBut);
        _box.add(_boxLbl);
        _box.add(_guestBut);
        _box.add(_fixBut);

        _box.setMinimumSize(dimTtl);
        _box.setPreferredSize(dimTtl);
        _box.setMaximumSize(dimTtl);
        _box.setBackground(grey15);

        update();
    }

    protected void update() {
        _hostBut.setText(_data.getHostName());
        _guestBut.setText(_data.getGuestName());
        _fixBut.setText("Fix" + (_data.isFixing() ? "!" : "?"));

        // If not begun, show date / hour instead of the score
        if(_data.isBegun()) {
            _scrUI.setText(Integer.toString(_data.getHostScr()) + " - " + Integer.toString(_data.getGuestScr()));
            _infoUI.setText(_data.getPeriodLabel());
        } else {
            _scrUI.setText(_data.getHourMatch());
            _infoUI.setText(_data.getDateMatch());
        }

        // Update Color
        Color blueC = new Color(120,150,255);
        Color greenC = new Color(100,255,100);
        Color redC = new Color(255,100,100);
        Color yellowC = new Color(255,255,90);
        Color whiteC = new Color(255,255,255);
        Color greyC = new Color(150,150,150);

        // Score
        if(_data.isBegun()) {
            if(_data.isFinished()) {
                _scrUI.setForeground(whiteC);
                _infoUI.setForeground(whiteC);
            } else if(_data.getPeriodLockScore()) {
                _scrUI.setForeground(yellowC);
                _infoUI.setForeground(yellowC);
            } else {
                _scrUI.setForeground(greenC);
                _infoUI.setForeground(greenC);
            }
        } else {
            _scrUI.setForeground(blueC);
            _infoUI.setForeground(blueC);
        }

        // Teams
        if(_data.isFinished()) {
            if(_data.hostWins()) {
                _hostBut.setForeground(whiteC);
                _guestBut.setForeground(greyC);
            } else if(_data.guestWins()) {
                _hostBut.setForeground(greyC);
                _guestBut.setForeground(whiteC);
            } else {
                // Draw
                _hostBut.setForeground(whiteC);
                _guestBut.setForeground(whiteC);
            }
        } else {
            _hostBut.setForeground(whiteC);
            _guestBut.setForeground(whiteC);
        }

        // FixBut
        if(_data.isFixing()) {
            _fixBut.setForeground(redC);
        } else {
            _fixBut.setForeground(greenC);
        }
    }

    public Box getPanel() {
        return _box;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == _hostBut) {
            _data.addPointHost();
        }
        if (actionEvent.getSource() == _guestBut) {
            _data.addPointGuest();
        }
        if(actionEvent.getSource() == _infoUI) {
            _data.nextPeriod();
        }
        if(actionEvent.getSource() == _fixBut) {
            _data.changeFactFix();
        }
    }
}
