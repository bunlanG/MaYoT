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

/** Manage a Match.
 *
 *  @author bunlanG
 */
public class Match {

    // Attributes
    protected String _hostName;
    protected String _guestName;
    protected int _hostScr;
    protected int _guestScr;
    protected MatchUI _ui;

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
        _hostName = hostName;
        _hostScr = 0;

        _guestName = guestName;
        _guestScr = 0;

        _ui = new MatchUI(this);
        _ui.init();
    }

    public void addPointHost() {
        _hostScr++;

        _ui.update();
    }

    public void addPointGuest() {
        _guestScr++;

        _ui.update();
    }

    // Getters / setters
    public MatchUI getUI() {
        return _ui;
    }

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
