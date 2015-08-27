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

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Dimension;

/** The window of MaYoT
 *
 * @author bunlanG
 */
public class MaYoT extends JFrame {
    // Fields
    Tournament _tournament;

    // Menu & Menu items
    private JMenuBar _menuBar;
    private JMenu _fileMenu;
    private JMenuItem _newFileMenu;

    public MaYoT() {
        _tournament = null;

        _menuBar = new JMenuBar();
        _fileMenu = new JMenu("File");
            _newFileMenu = new JMenuItem("New...");

        _fileMenu.add(_newFileMenu);

        _menuBar.add(_fileMenu);

        this.setJMenuBar(_menuBar);
    }

    public void setTournament(Tournament tournament) {
        _tournament = tournament;
        JPanel pan = _tournament.getPanel();

        this.setContentPane(pan);
        this.setSize(pan.getWidth(), pan.getHeight() + 21);
        this.setMinimumSize(new Dimension(pan.getWidth(), pan.getHeight()  + 21));
    }
}