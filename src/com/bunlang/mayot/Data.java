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

import java.util.Vector;

/** Functions to convert a String into Vector, and in inverse.
 *
 *  @author bunlanG
 */
public class Data {
    static public Vector<Integer> str2IntVect(String s) {
        Vector<Integer> res = new Vector<>();
        int nbrBeg = 0;

        for(int i = 0 ; i < s.length() ; i++) {
            if(s.charAt(i) == '_') {
                res.add(Integer.decode(s.substring(nbrBeg, i)));
                nbrBeg = i+1;
            }
        }

        // Last int
        res.add(Integer.decode(s.substring(nbrBeg)));

        return res;
    }


    static public String intVect2Str(Vector<Integer> v) {
        String res = "";

        for(int i = 0 ; i < v.size() - 1 ; i++) {
            res = res.concat(Integer.toString(v.get(i))).concat("_");
        }

        // Last int
        res = res.concat(Integer.toString(v.lastElement()));

        return res;
    }
}
