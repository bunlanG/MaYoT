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

package com.bunlang.mayot.ranking;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.Observable;

/** Manage a Team.
 *
 *  @author bunlanG
 */
public class Team extends Observable implements Comparable<Team> {
    private static Logger logger = Logger.getLogger("com.bunlang.mayot");
    // Fields
    private static int _nextId = 1;

    protected int _id;
    protected String _name;

    protected int _pTotal;
    protected int _mPlayed;
    protected int _sDiff;
    protected String _pos;

    protected int _mWins;
    protected int _mDraws;
    protected int _mLoses;
    protected int _pBonus;
    protected int _pFixer;
    protected int _sFor;
    protected int _sAgnst;

    protected TeamUI _ui;


    public Team(String name) {
        init(name, nextId(), 0,0,0,0,0, 0,0);
    }

    public Team(String name, int mWins, int mDraws, int mLoses, int pBonus, int pFixer, int sFor, int sAgnst) {
        init(name, nextId(), mWins,mDraws,mLoses,pBonus,pFixer, sFor,sAgnst);
    }

    public Team(String name, int id, int mWins, int mDraws, int mLoses, int pBonus, int pFixer, int sFor, int sAgnst) {
        init(name, id, mWins,mDraws,mLoses,pBonus,pFixer, sFor,sAgnst);
    }

    /** Create a Team with all customisable fields.
     *
     * @param name The name of the team.
     * @param id The id of the team.
     * @param mWins The number of matches won.
     * @param mDraws The number of matches drawn.
     * @param mLoses The number of matches lose.
     * @param pBonus The number of bonus points.
     * @param pFixer A manual fixer for the points.
     * @param sFor The score for the team.
     * @param sAgnst The score against the team.
     */
    public void init(String name, int id, int mWins, int mDraws, int mLoses, int pBonus, int pFixer, int sFor, int sAgnst) {
        _id = id;
        _name = name;
        _mWins = 0;
        _mDraws = 0;
        _mLoses = 0;
        _pBonus = 0;
        _pFixer = 0;
        _sFor = 0;
        _sAgnst = 0;

        _ui = new TeamUI(this);

        this.updateAdd(mWins,mDraws,mLoses,pBonus,pFixer, sFor,sAgnst);

        if(logger.isDebugEnabled()) {
            logger.debug("ranking.Team created : " + this);
        }
    }

    /** Update all fields by adding all params to their linked fields
     *
     * @param mWins
     * @param mDraws
     * @param mLoses
     * @param pBonus
     * @param pFixer
     * @param sFor
     * @param sAgnst
     */
    public void updateAdd(int mWins, int mDraws, int mLoses, int pBonus, int pFixer, int sFor, int sAgnst) {
        // Raw Fields
        _mWins += mWins;
        _mDraws += mDraws;
        _mLoses += mLoses;
        _pBonus += pBonus;
        _pFixer += pFixer;
        _sFor += sFor;
        _sAgnst += sAgnst;

        // Calculated Fields
        _pTotal =  3 * _mWins + _mDraws + _pBonus + _pFixer;
        _mPlayed = _mWins + _mDraws + _mLoses;
        _sDiff = _sFor - _sAgnst;
        _pos = ".";

        _ui.update();

        // Observable state changed
        setChanged();
        notifyObservers();

        if(logger.isDebugEnabled()) {
            logger.debug("ranking.Team updated : " + this);
        }
    }

    private static int nextId() {
        return (_nextId++);
    }

    /** The {@link compareTo} override from {@link Comparable} Interface.
     *
     * @param t the other team to compare with.
     *
     * @return a negative integer, zero, or a positive integer as this team is better than, equal to, or worse than the other team.
     */
    @Override
    public int compareTo(Team t) {
        if(this.getPTotal() != t.getPTotal()) {
            return (t.getPTotal() - this.getPTotal());
        } else if(this.getSDiff() != t.getSDiff()){
            return (t.getSDiff() - this.getSDiff());
        } else {
            return (this.getName().compareTo(t.getName()));
        }
    }

    // Getters / Setters
    /** Gets the current position.
     *
     * @return the current position
     */
    public String getPos() {
        return _pos;
    }

    /** Set the new position.
     *
     * @param pos the new position
     */
    public void setPos(String pos) {
        _pos = pos;
        _ui.update();
    }

    /** Gets the name of the Team.
     *
     * @return the name of the Team
     */
    public String getName() {
        return _name;
    }

    /** Gets the id of the Team.
     *
     * @return the id of the Team
     */
    public int getId() {
        return _id;
    }

    /** Get the next id WITHOUT MODIFY IT.
     *
     * @warning Use it carefully !
     */
    public static int getNextId() {
        return _nextId;
    }

    /** Set the next id.
     *
     * @warning Use it carefully !
     */
    public static void setNextId(int nextId) {
        _nextId = nextId;
    }

    /** Gets the total of points.
     *
     * @return the total of points
     */
    public int getPTotal() {
        return _pTotal;
    }

    /** Gets the total of matches played.
     *
     * @return the total of matches played.
     */
    public int getMPlayed() {
        return _mPlayed;
    }

    /** Gets the number of won matches.
     *
     * @return the number of won matches
     */
    public int getMWins() {
        return _mWins;
    }

    /** Gets the number of drawn matches.
     *
     * @return the number of drawn matches
     */
    public int getMDraws() {
        return _mDraws;
    }

    /** Gets the number of lose matches.
     *
     * @return the number of lose matches
     */
    public int getMLoses() {
        return _mLoses;
    }

    /** Gets the total of bonus points.
     *
     * @return the total of bonus points
     */
    public int getPBonus() {
        return _pBonus;
    }

    /** Gets the point fixer.
     *
     * @return the point fixer.
     */
    public int getPFixer() {
        return _pFixer;
    }

    /** Gets the score FOR the team.
     *
     * @return the score FOR the team.
     */
    public int getSFor() {
        return _sFor;
    }

    /** Gets the score AGAINST the team.
     *
     * @return the score AGAINST the team.
     */
    public int getSAgnst() {
        return _sAgnst;
    }

    /** Gets the difference score.
     *
     * @return the difference score
     */
    public int getSDiff() {
        return _sDiff;
    }

    /** Returns the panel of the Team, for GUIs.
     *
     * @return the panel of the Team
     */
    public Box getPanel() {
        return _ui.getPanel();
    }

    public String toString() {
        return _name + "[" + _id + "] | " + _pTotal + ':' + _mWins + '-' + _mDraws + '-' + _mLoses + " | " + _sFor + '-' + _sAgnst;
    }

    /** Get XML-format of the Team.
     *
     * @return a XML-format of the Team
     */
    public String toXml() {
        String s = "";

        s += "\t\t\t<team ";

        s += "id=\"" + _id  + "\" ";
        s += "name=\"" + _name + "\" ";
        s += "pts=\"" + _mWins +
                "_" + _mDraws +
                "_" + _mLoses +
                "_" + _pBonus +
                "_" + _pFixer +
                "\" ";
        s += "scr=\"" + _sFor +
                "_" + _sAgnst +
                "\" ";

        s += "/>\n";

        return s;
    }
}
