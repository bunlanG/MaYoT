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
    protected JPanel _pan;

    public MatchUI(Match data) {
        _data = data;

        init();
    }

    protected void init() {
        Dimension dimBut = new Dimension(200, 30);
        Dimension dimLbl = new Dimension(75, 30);
        Dimension dimTtl = new Dimension(475, 30);
        Color grey10 = new Color(0.1f, 0.1f, 0.1f);
        Color grey15 = new Color(0.15f, 0.15f, 0.15f);

        _hostBut = new JButton();
        _hostBut.setMinimumSize(dimBut);
        _hostBut.setPreferredSize(dimBut);
        _hostBut.setMaximumSize(dimBut);
        _hostBut.setForeground(Color.WHITE);
        _hostBut.setBackground(grey10);
        _hostBut.setBorder(null);
        _hostBut.addActionListener(this);

        _guestBut = new JButton();
        _guestBut.setMinimumSize(dimBut);
        _guestBut.setPreferredSize(dimBut);
        _guestBut.setMaximumSize(dimBut);
        _guestBut.setForeground(Color.WHITE);
        _guestBut.setBackground(grey10);
        _guestBut.setBorder(null);
        _guestBut.addActionListener(this);

        _scrUI = new JLabel();
        _scrUI.setMinimumSize(dimLbl);
        _scrUI.setPreferredSize(dimLbl);
        _scrUI.setMaximumSize(dimLbl);
        _scrUI.setForeground(Color.WHITE);
        _scrUI.setHorizontalAlignment(SwingConstants.CENTER);

        _pan = new JPanel();
        _pan.setLayout(new BoxLayout(_pan, BoxLayout.LINE_AXIS));
        _pan.add(_hostBut);
        _pan.add(_scrUI);
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
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == _hostBut) {
            _data.addPointHost();
        }
        if(actionEvent.getSource() == _guestBut) {
            _data.addPointGuest();
        }
    }
}
