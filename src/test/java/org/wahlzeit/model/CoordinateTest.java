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
	Coordinate sphericNuernberg;				//Latitude positive, Longitude positive, spheric
	Coordinate sphericPlaceInTheGulfOfGuinea;   //Latitude zero    , Longitude zero,     spheric
	Coordinate sphericMelbourne;				//Latitude negative, Longitude positive, spheric
	Coordinate sphericUshuaia;					//Latitude negative, Longitude negative, spheric
	
	Coordinate cartesianNuernberg;				//Latitude positive, Longitude positive, cartesian
	Coordinate cartesianPlaceInTheGulfOfGuinea; //Latitude zero    , Longitude zero,     cartesian 
	Coordinate cartesianMelbourne;				//Latitude negative, Longitude positive, cartesian
	Coordinate cartesianUshuaia;				//Latitude negative, Longitude negative, cartesian
	
	Coordinate sphericSomePlaceOnMars;
	
	// Allowed Delta for Double Rounding Errors
	
	public static final double DELTA = 2;
	
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
		
		sphericNuernberg			     = Location.createCoordinate(49.452030, 11.076750, 6371, true);
		sphericMelbourne				 = Location.createCoordinate(-37.814107, 144.963280, 6371, true);
		sphericUshuaia					 = Location.createCoordinate(-54.801912, -68.302951, 6371, true);
		sphericPlaceInTheGulfOfGuinea 	 = Location.createCoordinate(0, 0, 6371, true);	
		
		cartesianNuernberg		         = Location.createCoordinate(4064.531363, 795.716658 ,4841.080540, false);
		cartesianMelbourne		         = Location.createCoordinate(-4121.036265, 2889.518447 ,-3906.070178, false);
		cartesianUshuaia			     = Location.createCoordinate(1357.636610, -3412.101688,-5206.152696, false);
		cartesianPlaceInTheGulfOfGuinea  = Location.createCoordinate(6371, 0 ,0, false);	
		
		sphericSomePlaceOnMars 			 = Location.createCoordinate(0, 0, 3369.6, true);
	}
	
	@Test
	public void testGetDistance() {
		//Tests with only SphericCoordinates
		assertEquals(sphericNuernberg.getDistance(sphericNuernberg),0,DELTA);
		assertEquals(sphericNuernberg.getDistance(sphericUshuaia), distNuernbergToUshuaia, DELTA);
		assertEquals(sphericNuernberg.getDistance(sphericPlaceInTheGulfOfGuinea), distNuernbergToZero, DELTA);
		
		//Tests with only CartesianCoordinates
		assertEquals(cartesianNuernberg.getDistance(cartesianNuernberg),0,DELTA);
		assertEquals(cartesianNuernberg.getDistance(cartesianUshuaia), distNuernbergToUshuaia, DELTA);
		assertEquals(cartesianNuernberg.getDistance(cartesianPlaceInTheGulfOfGuinea), distNuernbergToZero, DELTA);
		
		//Tests between Cartesian and Spheric Coordinates
		assertEquals(sphericNuernberg.getDistance(cartesianNuernberg),0,DELTA);
		assertEquals(sphericNuernberg.getDistance(cartesianUshuaia),distNuernbergToUshuaia,DELTA);
		assertEquals(sphericNuernberg.getDistance(cartesianPlaceInTheGulfOfGuinea), distNuernbergToZero,DELTA);
		assertEquals(cartesianNuernberg.getDistance(sphericUshuaia),distNuernbergToUshuaia,DELTA);
		assertEquals(cartesianNuernberg.getDistance(sphericPlaceInTheGulfOfGuinea), distNuernbergToZero, DELTA);		
	}
	
	@Test
	public void testIsEqual() {
		assertTrue(sphericNuernberg.isEqual(sphericNuernberg));
		assertTrue(sphericNuernberg.isEqual(cartesianNuernberg));
		assertTrue(cartesianUshuaia.isEqual(sphericUshuaia));
		assertTrue(cartesianMelbourne.isEqual(sphericMelbourne));
		assertFalse(sphericNuernberg.isEqual(sphericMelbourne));
		assertFalse(sphericNuernberg.isEqual(sphericUshuaia));
		assertFalse(sphericNuernberg.isEqual(cartesianPlaceInTheGulfOfGuinea));
		assertFalse(sphericUshuaia.isEqual(cartesianMelbourne));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void TestDifferentPlanetsException() {
		sphericNuernberg.getDistance(sphericSomePlaceOnMars);
		sphericSomePlaceOnMars.getDistance(sphericNuernberg);
	}
	
	@Test
	public void TestEquals() {
		assertTrue(cartesianNuernberg.equals(cartesianNuernberg));
	}
	
	@Test
	public void TestToCartesianCoordinate() {
//		assertEquals(((CartesianCoordinate) cartesianNuernberg).getX(), ((SphericCoordinate) sphericNuernberg).toCartesianCoordinate().getX(),DELTA);
	}
	
	@Test
	public void TestHashCode() {
		assertEquals(sphericNuernberg.hashCode(), cartesianNuernberg.hashCode(),0);
		assertEquals(sphericNuernberg.hashCode(), cartesianNuernberg.getSphericCoordinate().hashCode());
		assertEquals(sphericMelbourne.hashCode(), cartesianMelbourne.hashCode(),0);
		assertEquals(sphericMelbourne.hashCode(), cartesianMelbourne.getSphericCoordinate().hashCode());
		assertEquals(sphericUshuaia.hashCode(), cartesianUshuaia.hashCode(),0);
		assertEquals(sphericUshuaia.hashCode(), cartesianUshuaia.getSphericCoordinate().hashCode());
	}
	
	@Test
	public void testEquals() {
		assertTrue(sphericNuernberg.equals(cartesianNuernberg));
		assertTrue(sphericMelbourne.equals(cartesianMelbourne));
		assertTrue(cartesianUshuaia.equals(sphericUshuaia));
		assertFalse(cartesianNuernberg.equals(sphericMelbourne));
		assertFalse(sphericUshuaia.equals(sphericNuernberg));
	}
}
