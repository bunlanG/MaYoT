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

import org.apache.log4j.Logger;

import javax.swing.JPanel;

/** Manage a Match.
 *
 *  @author bunlanG
 */
public class Match {
    private static Logger logger = Logger.getLogger("com.bunlang.mayot");

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
    protected Team _host;
    protected Team _guest;
    protected Period _period;
    protected String _dateMatch;
    protected String _hourMatch;
    protected int _factFix;         // If you need to del a point.

    protected MatchUI _ui;


    /** Default constructor of a Match.
     *
     */
    public Match() {
        super();

        init(null, null);
	}

    /** Another constructor of a Match, with the teams from the Table.
     *
     * @param host The host team
     * @param guest The guest team
     */
    public Match(com.bunlang.mayot.ranking.Team host, com.bunlang.mayot.ranking.Team guest) {
        super();

        init(host, guest);
    }

    /** Initialize the Match object.
     *
     * @param host The host team
     * @param guest The guest team
     */
    protected void init(com.bunlang.mayot.ranking.Team host,  com.bunlang.mayot.ranking.Team guest) {
        _host = new Team(host);

        _guest = new Team(guest);

        _period = Period.values()[0];

        _factFix = 1;

        _dateMatch = "xx/xx/20xx";
        _hourMatch = "xx:xx";

        _ui = new MatchUI(this);

        if(logger.isDebugEnabled()) {
            logger.debug("scores.Match created : " + this);
        }
    }

    public void addPointHost() {
        // Do nothing if the current period lock the score
        if(!this.getPeriodLockScore()) {
            if (_factFix < 0 && _host.getScore() <= 0) {
                // Don't support negative score !
            } else {
                _host.addToScore(_factFix);
            }

            _ui.update();

            if(logger.isDebugEnabled()) {
                logger.debug("scores.Match point for the Host : " + this);
            }
        }
    }

    public void addPointGuest() {
        // Do nothing if the current period lock the score
        if(!this.getPeriodLockScore()) {
            if (_factFix < 0 && _guest.getScore() <= 0) {
                // Don't support negative score !
            } else {
                _guest.addToScore(_factFix);
            }
            _ui.update();

            if(logger.isDebugEnabled()) {
                logger.debug("scores.Match point for the Guest : " + this);
            }
        }
    }

    public void nextPeriod() {
        if(this.isFixing()) {
            if (_period.ordinal() > 0) {
                _period = Period.values()[_period.ordinal() - 1];
            }
        } else {
            if (_period.ordinal() + 1 < Period.values().length) {
                _period = Period.values()[_period.ordinal() + 1];
            }
        }

        _ui.update();

        if(logger.isDebugEnabled()) {
            logger.debug("scores.Match period has changed : " + this);
        }
    }

    public void changeFactFix() {
        _factFix *= -1; // 1 <-> -1

        _ui.update();
    }

    // Getters / setters
    public JPanel getPanel() {
        return _ui.getPanel();
    }

    /** Get information about the Match object.
     *
     * @return a String with all information
     */
    public String toString() {
        return (_host.getTeamName() + " / " + _guest.getTeamName()  + " : " + _host.getScore() + "-" + _guest.getScore() +
                (this.isFinished() ? " [F]" : (
                        !this.isBegun() ? " [N]" : (
                                this.getPeriodLockScore() ? " [x]" : "")))
        );
    }

    /** Get the name of the guest team.
     *
     * @return name of the guest team
     */
    public String getGuestName() {
        return _guest.getTeamName() ;
    }

    /** Get the name of the host team.
     *
     * @return name of the host team
     */
    public String getHostName() {
        return _host.getTeamName() ;
    }

    /** Get the score of the guest team.
     *
     * @return score of the guest team
     */
    public int getGuestScr() {
        return _guest.getScore();
    }

    /** Get the name of the host team.
     *
     * @return name of the host team
     */
    public int getHostScr() {
        return _host.getScore();
    }

    /** Get the date of the match.
     *
     * @return date of the match
     */
    public String getDateMatch() {
        return _dateMatch;
    }

    /** Get the hour of the match.
     *
     * @return date of the match
     */
    public String getHourMatch() {
        return _hourMatch;
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
        return _host.getScore() > _guest.getScore();
    }

    /** Ask if the guest win the match
     *
     * @return the guest wins / wan
     */
    public boolean guestWins() {
        return _host.getScore() < _guest.getScore();
    }

    /** Ask if we can fix the score
     *
     * @return match fixing state
     */
    public boolean isFixing() {
        return (_factFix == -1);
    }
}
