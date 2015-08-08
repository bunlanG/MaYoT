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
import java.awt.Dimension;

/** .
 *
 *  @author bunlanG
 */
public class Match extends JFrame {

    // Attributes
    protected String _hostName;
    protected String _guestName;
    protected int _hostScr;
    protected int _guestScr;

    protected JButton _hostBut;
    protected JButton _guestBut;
    protected JLabel _scrUI;
    protected JPanel _pan;

    /** Default constructor of a Match.
     *
     */
    public Match() {
        super();

        init("", "");
	}

    /** Another constructor of a Match, with the names of the teams.
     *
     * @param hostName The name of the host team
     * @param guestName The name of the guest team
     */
    public Match(String hostName, String guestName) {
        super();

        init(hostName, guestName);
    }

    /** Initialize the Match object.
     *
     * @param hostName The name of the host team
     * @param guestName The name of the guest team
     */
    protected void init(String hostName, String guestName) {
        Dimension dimBut = new Dimension(200, 30);
        Dimension dimLbl = new Dimension(75, 30);
        Dimension dimTtl = new Dimension(475, 30);

        _hostName = hostName;
        _hostBut = new JButton(_hostName);
        _hostBut.setMinimumSize(dimBut);
        _hostBut.setPreferredSize(dimBut);
        _hostBut.setMaximumSize(dimBut);
        _hostScr = 0;

        _guestName = guestName;
        _guestBut = new JButton(_guestName);
        _guestBut.setMinimumSize(dimBut);
        _guestBut.setPreferredSize(dimBut);
        _guestBut.setMaximumSize(dimBut);
        _guestScr = 0;

        _scrUI = new JLabel(Integer.toString(_hostScr) + " - " + Integer.toString(_guestScr));
        _scrUI.setMinimumSize(dimLbl);
        _scrUI.setPreferredSize(dimLbl);
        _scrUI.setMaximumSize(dimLbl);
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
        this.setLocationRelativeTo(null);
    }

    // Getters / setters

    /** Get information about the Match object.
     *
     * @return a String with all information
     */
    public String getInfos() {
        return (_hostName + " " + _hostScr + " - " + _guestScr + " " + _guestName);
    }

    /** Get the name of the guest team.
     *
     * @return name of the guest team
     */
    public String getGuestName() {
        return _guestName;
    }

    /** Get the name of the host team.
     *
     * @return name of the host team
     */
    public String getHostName() {
        return _hostName;
    }

    /** Get the score of the guest team.
     *
     * @return score of the guest team
     */
    public int getGuestScr() {
        return _guestScr;
    }

    /** Get the name of the host team.
     *
     * @return name of the host team
     */
    public int getHostScr() {
        return _hostScr;
    }
}
