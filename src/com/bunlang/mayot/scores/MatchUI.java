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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
public class MatchUI extends JFrame implements ActionListener {
    protected Match _data;

    protected JButton _hostBut;
    protected JButton _guestBut;
    protected JLabel _scrUI;
    protected JButton _infoUI;
    protected JPanel _pan;
    protected JPanel _panLbl;

    public MatchUI(Match data) {
        _data = data;

        init();
    }

    protected void init() {
        Dimension dimBut = new Dimension(200, 30);
        Dimension dimLbl = new Dimension(75, 30);
        Dimension dimLbl1 = new Dimension(75, 18);
        Dimension dimLbl2 = new Dimension(75, 12);
        Dimension dimTtl = new Dimension(475, 30);
        Color grey10 = new Color(0.1f, 0.1f, 0.1f);
        Color grey15 = new Color(0.15f, 0.15f, 0.15f);
        Color grey30 = new Color(0.3f, 0.3f, 0.3f);
        Font dimFont = new Font("Dialog", Font.ITALIC, 9);
        Font normFont = new Font("Dialog.Bold", 0, 15);

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
        _scrUI.setHorizontalAlignment(SwingConstants.CENTER);

        _infoUI = new JButton();
        _infoUI.setMinimumSize(dimLbl2);
        _infoUI.setPreferredSize(dimLbl2);
        _infoUI.setMaximumSize(dimLbl2);
        _infoUI.setFont(dimFont);
        _infoUI.setForeground(Color.WHITE);
        _infoUI.setBackground(grey30);
        _infoUI.setBorder(null);
        _infoUI.addActionListener(this);

        _panLbl = new JPanel();
        _panLbl.setLayout(new BoxLayout(_panLbl, BoxLayout.PAGE_AXIS));
        _panLbl.add(_scrUI);
        _panLbl.add(_infoUI);
        _panLbl.setMinimumSize(dimLbl);
        _panLbl.setPreferredSize(dimLbl);
        _panLbl.setMaximumSize(dimLbl);
        _panLbl.setOpaque(false);

        _pan = new JPanel();
        _pan.setLayout(new BoxLayout(_pan, BoxLayout.LINE_AXIS));
        _pan.add(_hostBut);
        _pan.add(_panLbl);
        _pan.add(_guestBut);

        this.setContentPane(_pan);

        this.setTitle("MaYoT - org.bunlang.mayot.scores.Match UI Test");
        this.setMinimumSize(dimTtl);
        this.setPreferredSize(dimTtl);
        this.setMaximumSize(dimTtl);
        this.setSize(475, 56);
        this.getContentPane().setBackground(grey15);
        this.setLocationRelativeTo(null);

        update();
    }

    protected void update() {
        _hostBut.setText(_data.getHostName());
        _guestBut.setText(_data.getGuestName());
        _scrUI.setText(Integer.toString(_data.getHostScr()) + " - " + Integer.toString(_data.getGuestScr()));
        _infoUI.setText(_data.getPeriodLabel());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Cut the link if the current Period order it
        if(!_data.getPeriodLockScore()) {
            if (actionEvent.getSource() == _hostBut) {
                _data.addPointHost();
            }
            if (actionEvent.getSource() == _guestBut) {
                _data.addPointGuest();
            }
        }
        if(actionEvent.getSource() == _infoUI) {
            _data.nextPeriod();
        }
    }
}
