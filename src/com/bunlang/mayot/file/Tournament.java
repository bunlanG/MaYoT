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

package com.bunlang.mayot.file;

import com.bunlang.mayot.Group;
import com.bunlang.mayot.ranking.Team;
import com.bunlang.mayot.scores.Match;
import com.bunlang.mayot.scores.MatchDay;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Vector;

import static com.bunlang.mayot.Data.*;

/** Load and save Tournament files.
 *
 *  @author bunlanG
 */
public class Tournament {
    private static Logger logger = Logger.getLogger("com.bunlang.mayot");
    // Fields
    com.bunlang.mayot.Tournament _trmnt;

    public Tournament(com.bunlang.mayot.Tournament trmnt) {
        _trmnt = trmnt;
    }

    public void save(String path) {
        try {
            Writer w = new FileWriter(path);

            w.write(_trmnt.toXml());

            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load(String path) {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();

            final Document doc = builder.parse(new File(path));

            final Element root = doc.getDocumentElement();

            parse(root, null, null);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void parse(Element elem, Object obj, Object obj2) {
        switch(elem.getNodeName()) {
            //////////////////////////////////////////////////////////////////
            case "tournament":
                logger.debug("parsing: tournament");

                NodeList listG = elem.getChildNodes();

                for(int i = 0 ; i < listG.getLength() ; i++) {
                    if(listG.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        parse((Element) listG.item(i), null, null);
                    }
                }

                break;
            //////////////////////////////////////////////////////////////////
            case "group":
                logger.debug("parsing: group");

                NodeList listGC = elem.getChildNodes();

                Group grp = new Group();

                for(int i = 0 ; i < listGC.getLength() ; i++) {
                    if(listGC.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        parse((Element) listGC.item(i), grp, null);
                    }
                }

                if(_trmnt != null) {
                    _trmnt.add(grp);
                }

                break;
            //////////////////////////////////////////////////////////////////
            case "table":
                logger.debug("parsing: table");
                Group grpT = (Group) obj;

                NodeList listTb = elem.getChildNodes();

                for(int i = 0 ; i < listTb.getLength() ; i++) {
                    if(listTb.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        parse((Element) listTb.item(i), grpT, null);
                    }
                }

                break;
            //////////////////////////////////////////////////////////////////
            case "team":
                logger.debug("parsing: team");
                Group grpTm = (Group) obj;

                int id = Integer.parseInt(elem.getAttribute("id"));
                String name = elem.getAttribute("name");
                Vector<Integer> pts = str2IntVect(elem.getAttribute("pts"));
                Vector<Integer> scrT = str2IntVect(elem.getAttribute("scr"));

                Team team = new Team(name, id, pts.get(0),pts.get(1),pts.get(2),pts.get(3),pts.get(4), scrT.get(0),scrT.get(1));

                grpTm.add(team);

                break;
            //////////////////////////////////////////////////////////////////
            case "scores":
                logger.debug("parsing: scores");
                Group grpS = (Group) obj;

                NodeList listS = elem.getChildNodes();

                for(int i = 0 ; i < listS.getLength() ; i++) {
                    if(listS.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        parse((Element) listS.item(i), grpS, null);
                    }
                }

                break;
            //////////////////////////////////////////////////////////////////
            case "matchday":
                logger.debug("parsing: matchday");
                Group grpMD = (Group) obj;

                String title = elem.getAttribute("ttl");
                NodeList listM = elem.getChildNodes();

                MatchDay mthDay = new MatchDay(title);

                for(int i = 0 ; i < listM.getLength() ; i++) {
                    if(listM.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        parse((Element) listM.item(i), mthDay, grpMD);
                    }
                }

                grpMD.add(mthDay);

                break;
            //////////////////////////////////////////////////////////////////
            case "match":
                logger.debug("parsing: match");
                MatchDay mthday = (MatchDay) obj;
                Group grpM = (Group) obj2;

                int hstId = Integer.parseInt(elem.getAttribute("hId"));
                int gstId = Integer.parseInt(elem.getAttribute("gId"));
                Vector<Integer> scrM = str2IntVect(elem.getAttribute("scr"));
                Match.Period period = Match.Period.values()[Integer.parseInt(elem.getAttribute("prd"))];

                Team hst = grpM.findTeamById(hstId);
                Team gst = grpM.findTeamById(gstId);

                Match mth = new Match(hst, gst, scrM.get(0),scrM.get(1), period);

                mthday.add(mth);

                break;
            //////////////////////////////////////////////////////////////////
            default:
                break;
        }
    }
}
