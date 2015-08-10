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

import static org.junit.Assert.*;   
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MatchTest {
    protected Match _match;

    @Before
    public void setUp() {
        _match = new Match("Home", "Guest");
    }

    @After
    public void tearDown() {
        _match = null;
    }

    /** Test the init() function
     *
     */
    @Test
    public void testInit() {
        assertEquals("Host name initialisation broken", "Home", _match.getHostName());
        assertEquals("Guest name initialisation broken", "Guest", _match.getGuestName());

        assertEquals("Host score initialisation broken", 0, _match.getHostScr());
        assertEquals("Guest score initialisation broken", 0, _match.getGuestScr());

        assertEquals("Fixer is disabled in init()", false, _match.isFixing());

        assertEquals("Match should have not begun yet", false, _match.isBegun());
    }

    /** Test the addPoint*(), nextPeriod() and changeFactFix() functions
     *
     */
    @Test
    public void testScoreUpdate() {
        // Start the match
        _match.nextPeriod();
        assertEquals("Match should be running", true, _match.isBegun());

        // 0-0 : @see testInit()

        // 1-0
        _match.addPointHost();
        assertEquals("Should be 1-0 : Host", 1, _match.getHostScr());
        assertEquals("Should be 1-0 : Guest", 0, _match.getGuestScr());

        // 1-1
        _match.addPointGuest();
        assertEquals("Should be 1-1 : Host", 1, _match.getHostScr());
        assertEquals("Should be 1-1 : Guest", 1, _match.getGuestScr());

        // Enable fixer
        _match.changeFactFix();
        assertEquals("Fixer should be enabled", true, _match.isFixing());

        // 0-1
        _match.addPointHost();
        assertEquals("Should be 0-1 : Host", 0, _match.getHostScr());
        assertEquals("Should be 0-1 : Guest", 1, _match.getGuestScr());

        // Disable fixer
        _match.changeFactFix();
        assertEquals("Fixer should be disabled", false, _match.isFixing());

        // Half-time : score is locked
        _match.nextPeriod();
        assertEquals("Score should be locked", true, _match.getPeriodLockScore());

        // Test that the score cannot change
        _match.addPointHost();
        _match.addPointGuest();
        assertEquals("Should always be 0-1 : Host", 0, _match.getHostScr());
        assertEquals("Should always be 0-1 : Guest", 1, _match.getGuestScr());

        // Finish the match
        _match.nextPeriod(); // 2nd HT
        _match.nextPeriod(); // Finished
        assertEquals("Match should be finished", true, _match.isFinished());
        assertEquals("Guest wins", true, _match.guestWins());
        assertEquals("Host loses", false, _match.hostWins());
    }
}
