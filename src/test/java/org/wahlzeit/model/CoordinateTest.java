/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */


package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class CoordinateTest {
	
/**
 * Created by Michael Weber on 24.10.2015.
 * 
 * A jUnit Test Class to test the Coordinates.java class.**/
	
	//real coordinates of real places for testing
	Coordinate nuernberg;
	Coordinate somePlaceInTheGulfOfGuinea;
	Coordinate melbourne;
	Coordinate ushuaia;
	
	// Allowed Delta for Double Rounding Errors
	
	public static final double DELTA = 0.01;
	public static final double DELTARAD = 2;

	
	//Expected Results
	
	double latNuernbergToSomePlace = Math.abs(0-49.452030);
	double longNuernbergToSomePlace = Math.abs(11.076750-0);
	double latUshuaiaToSomePlace = Math.abs(0-(-54.801912));
	double latNuernbergToUshuaia = Math.abs(49.452030-(-54.801912));
	double longNuernbergToUshuaia = Math.abs(11.076750-(-68.302951));
	double longMelbourneToUshuaia = (180-144.963280) + (180-68.302951);
	
	double distNuernbergToSomePlace = 50.677388;
	double distUshuaiaToMelbourne = 147.71386;
	
	double distNuernbergToUshuaia = 13732;
	double distNuernbergToZero = 5601;
	


	@Before
	public void setUp() throws Exception {
		
		nuernberg 		= new Coordinate (49.452030, 11.076750);	// lat+ , long+
		melbourne 		= new Coordinate (-37.814107, 144.963280);		// lat- , long+	
		ushuaia			= new Coordinate (-54.801912, -68.302951); 	// lat- , long-
		somePlaceInTheGulfOfGuinea = new Coordinate(0,0); 			// lat0 , long0
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test //Test for Longitudinal Distance From lat+, long + to 0,0
	public void testGetLongitudinalDistancePositiveToZero() {
		assertEquals("LongDistance from Nbg to 0,0.", longNuernbergToSomePlace, 
				      nuernberg.getLongitudinalDistance(somePlaceInTheGulfOfGuinea), DELTA);
	}
	
	@Test //Test for Longitudinal Distance from long+, to long- (via the 0 Meridian)
	public void testgetLongitudinalDistancePostiveToNegative() {
		assertEquals("LongDistance from Nbg to Ushuaia.", longNuernbergToUshuaia,
				     nuernberg.getLongitudinalDistance(ushuaia), DELTA);
	}
	
	@Test //Test for Longitudinal Distance from long- to long+ (via the Pacific Ocean)
	public void testgetLongitudinalDistanceViaPacific() {
		assertEquals("LongDistance from Melbourne to Ushuaia via the Pacific.", longMelbourneToUshuaia, 
				     melbourne.getLongitudinalDistance(ushuaia), DELTA);
	}

	@Test //Test for Latitudinal Distance from lat+ to 0
	public void testGetLatitudinalDistancePositiveToZero() {
		assertEquals("LatDistance from Nbg to 0,0.", latNuernbergToSomePlace, 
				     nuernberg.getLatitudinalDistance(somePlaceInTheGulfOfGuinea), DELTA);
	}
	
	@Test //Test for Latitudinal Distance from lat+ to lat-
	public void testgetLatitudinalDistancePostiveToNegative() {
		assertEquals("LatDistance from Nbg to Ushuaia.", latNuernbergToUshuaia,
				     nuernberg.getLatitudinalDistance(ushuaia), DELTA);
	}
	
	@Test //Test for Latitudinal Distance from lat- to 0;
	public void testGetLatitudinalDistnaceNetgativeToZero() {
		assertEquals("LatDistnace from Ushuaia to Some Place in the Gulf of Guinea.", latUshuaiaToSomePlace, 
				     ushuaia.getLatitudinalDistance(somePlaceInTheGulfOfGuinea), DELTA);
	}
	
//	@Test //Test for The Distance From Nürnberg to a Point with the Coordinates 0,0 in the Gulf of Guinea.
//	public void testGetDistanceNbgToZero() {
//		assertEquals("Overall Distance from Nuernberg to Some Place in the Gulf of Guinea.", distNuernbergToSomePlace,
//				      nuernberg.getDistance(somePlaceInTheGulfOfGuinea), DELTA);
//	}
//	
//	@Test //Tests the Distance From Ushuaia to Melbourne (via the Pacific).
//	public void testGetDistanceUshuaiaToMelbourne() {
//		assertEquals("Overall Distance from Ushuaia to Melbourne via the Pacific.", distUshuaiaToMelbourne,
//					 ushuaia.getDistance(melbourne), DELTA);
//	}
	
	@Test //Asserts Reversibility
	public void testReversibility() {
		assertEquals("The distance from one place to the other should be the same as back.", ushuaia.getDistance(melbourne),
				     melbourne.getDistance(ushuaia), DELTA);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testValidityCheckerLatitude() throws Exception {
			nuernberg.setLatitude(-200);
			fail();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testValidityCheckerLongitude() throws Exception {
			nuernberg.setLongitude(-200);
			fail();
	}
	
	@Test //Test for Distance in Meters
	public void testGetDistance () {
		assertEquals(distNuernbergToUshuaia, nuernberg.getDistance(ushuaia), DELTARAD);
		assertEquals(distNuernbergToZero, nuernberg.getDistance(somePlaceInTheGulfOfGuinea), DELTARAD);
	}



}
