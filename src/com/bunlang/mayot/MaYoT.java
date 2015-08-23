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

/** The window of MaYoT
 *
 * @author bunlanG
 */
public class MaYoT extends JFrame {
    // Fields
    Tournament _tournament;

    public MaYoT() {
        _tournament = null;
    }

    public void setTournament(Tournament tournament) {
        _tournament = tournament;

        this.setContentPane(_tournament.getPanel());
        this.setSize(_tournament.getPanel().getSize());
        this.setMinimumSize(_tournament.getPanel().getSize());
    }
}
