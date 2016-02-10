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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;

/** The window of MaYoT
 *
 * @author bunlanG
 */
public class MaYoT extends JFrame implements ActionListener {
    // Fields
    Tournament _tournament;

    // Menu & Menu items
    private JMenuBar _menuBar;
    private JMenu _fileMenu;
    private JMenuItem _newFileMenu;
    private JMenuItem _openFileMenu;
    private JMenuItem _saveFileMenu;
    private JMenuItem _saveFileAsMenu;
    private JMenuItem _quitMenu;

    public MaYoT() {
        _tournament = null;

        _menuBar = new JMenuBar();

        _fileMenu = new JMenu("File");

        _newFileMenu = new JMenuItem("New...");
        _newFileMenu.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK));
        _newFileMenu.addActionListener(this);
        _fileMenu.add(_newFileMenu);

        _openFileMenu = new JMenuItem("Open...");
        _openFileMenu.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_MASK));
        _openFileMenu.addActionListener(this);
        _fileMenu.add(_openFileMenu);

        _saveFileMenu = new JMenuItem("Save");
        _saveFileMenu.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));
        _saveFileMenu.addActionListener(this);
        _fileMenu.add(_saveFileMenu);

        _saveFileAsMenu = new JMenuItem("Save As...");
        _saveFileAsMenu.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        _saveFileAsMenu.addActionListener(this);
        _fileMenu.add(_saveFileAsMenu);

        _quitMenu = new JMenuItem("Quit");
        _quitMenu.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.CTRL_MASK));
        _quitMenu.addActionListener(this);
        _fileMenu.add(_quitMenu);

        _menuBar.add(_fileMenu);

        this.setJMenuBar(_menuBar);
    }

    public void setTournament(Tournament tournament) {
        _tournament = tournament;
        Box box = _tournament.getPanel();

        box.setBackground(new Color(0.33f,0.33f,0.33f));
        box.setOpaque(true);

        this.setContentPane(box);
        this.setSize(new Dimension(box.getWidth(), box.getHeight() + 21));
        this.setPreferredSize(new Dimension(box.getWidth(), box.getHeight() + 21));
        this.setMinimumSize(new Dimension(box.getWidth(), box.getHeight()  + 21));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == _newFileMenu) {
            newFileAction();
        }
        if(actionEvent.getSource() == _openFileMenu) {
            openFileAction();
        }
        if(actionEvent.getSource() == _saveFileMenu) {
            saveFileAction();
        }
        if(actionEvent.getSource() == _saveFileAsMenu) {
            saveFileAsAction();
        }
        if(actionEvent.getSource() == _quitMenu) {
            quitAction();
        }
    }

    private void newFileAction() {
        JOptionPane.showMessageDialog(this, "New File");
    }

    private void openFileAction() {
        JFileChooser openDialog = new JFileChooser(".");
        File f = null;
        if(openDialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            f = openDialog.getSelectedFile();
        }

        JOptionPane.showMessageDialog(this, f);
    }

    private void saveFileAction() {
        JOptionPane.showMessageDialog(this, "Save File");
    }

    private void saveFileAsAction() {
        JFileChooser saveDialog = new JFileChooser(".");
        File f = null;
        if(saveDialog.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            f = saveDialog.getSelectedFile();
        }

        JOptionPane.showMessageDialog(this, f);
    }

    private void quitAction() {
        System.exit(0);
    }

}
