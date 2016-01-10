package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FootballPlayerTest {
	
	FootballPlayerManager fpm = FootballPlayerManager.getInstance();
	
	FootballPlayerType professionalDefender;
	FootballPlayerType amateurAttacker;
	FootballPlayerType professionalCenterBack;
	
	FootballPlayer philippLahm;
	FootballPlayer seppTrifftNix;
	FootballPlayer jeromeBoateng;
	
	@Before
	public void setUp() throws Exception {
		FootballPlayerManager.getInstance();
		professionalDefender = fpm.createFootballPlayerType("Defense", "left", true);
		amateurAttacker = fpm.createFootballPlayerType("Attack", "right", false);
		professionalCenterBack = fpm.createFootballPlayerType("Centerback", "right", true);
		philippLahm = fpm.createFootballPlayer("Defense", "Philipp Lahm", 21);
		seppTrifftNix = fpm.createFootballPlayer("Attack", "Sepp Trifftnix", 9);
		jeromeBoateng = fpm.createFootballPlayer("Centerback", "Jerome Boateng", 17);
	}	

	@Test
	public void createFootballPlayerTypeTest() {
		assertEquals(fpm.footballPlayerTypes.get(professionalDefender.getID()),professionalDefender);
		assertEquals(fpm.footballPlayerTypes.get(amateurAttacker.getID()),amateurAttacker);
	}
	
	@Test
	public void createFootballPlayerTest() {
		assertEquals(fpm.footballPlayers.get(philippLahm.getID()),philippLahm);
		assertEquals(fpm.footballPlayers.get(seppTrifftNix.getID()),seppTrifftNix);
		
		assertEquals(fpm.footballPlayers.get(philippLahm.getID()).getName(),"Philipp Lahm");
		assertEquals(fpm.footballPlayers.get(seppTrifftNix.getID()).getName(),"Sepp Trifftnix");

	}
	
	@Test
	public void getFootballPlayerTest() {
		assertEquals(fpm.getFootballPlayer("Philipp Lahm"),philippLahm);
		assertEquals(fpm.getFootballPlayer("Sepp Trifftnix"),seppTrifftNix);
	}
	
	@Test
	public void getFootballPlayerTypeTest() {
		assertEquals(fpm.getFootballPlayerType("Centerback"),professionalCenterBack);
		assertEquals(fpm.getFootballPlayerType("Defense"),professionalDefender);
	}

}
