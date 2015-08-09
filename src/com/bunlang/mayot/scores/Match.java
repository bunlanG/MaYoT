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
    private enum Period {
        NOT_BEGUN("...", true),
        FIRST_HT("1st HT", false),
        PAUSE_HT("Pause", true),
        SECOND_HT("2nd HT", false),
        FINISHED("Finished", true);

        private String _str;
        private boolean _lockScore;

        Period(String str, boolean lockScr) {
            _str = str;
            _lockScore = lockScr;
        }

        public String getVal() {
            return _str;
        }

        public boolean lockedScore() {
            return _lockScore;
        }
    }

    // Attributes
    protected String _hostName;
    protected String _guestName;
    protected int _hostScr;
    protected int _guestScr;
    protected Period _period;
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

        _period = Period.values()[0];

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

    public void nextPeriod() {
        if(_period.ordinal() + 1 < Period.values().length) {
            _period = Period.values()[_period.ordinal() + 1];
        }

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

    /** Get the label of the period.
     *
     * @return label of the current period
     */
    public String getPeriodLabel() {
        return _period.getVal();
    }

    /** Ask if the current period lock the score edition.
     *
     * @return the score locked state
     */
    public boolean getPeriodLockScore() {
        return _period.lockedScore();
    }

    /** Ask if the match has begun
     *
     * @return the match state
     */
    public boolean isBegun() {
        return _period.ordinal() != 0;
    }

    /** Ask if the match has finished
     *
     * @return the match state
     */
    public boolean isFinished() {
        return _period.ordinal() == Period.values().length - 1;
    }

    /** Ask if the host win the match
     *
     * @return the host wins / wan
     */
    public boolean hostWins() {
        return _hostScr > _guestScr;
    }

    /** Ask if the guest win the match
     *
     * @return the guest wins / wan
     */
    public boolean guestWins() {
        return _hostScr < _guestScr;
    }
}
