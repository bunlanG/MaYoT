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

    }

    /** Test the addPoint*() functions
     *
     */
    @Test
    public void testScoreUpdate() {
        // 0-0 : @see testInit()

        // 1-0
        _match.addPointHost();
        assertEquals("Should be 1-0 : Host", 1, _match.getHostScr());
        assertEquals("Should be 1-0 : Guest", 0, _match.getGuestScr());

        // 1-1
        _match.addPointGuest();
        assertEquals("Should be 1-1 : Host", 1, _match.getHostScr());
        assertEquals("Should be 1-1 : Guest", 1, _match.getGuestScr());
    }
}
