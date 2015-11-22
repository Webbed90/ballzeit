package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;
import java.util.logging.Logger;

public class FootballPhotoFactory extends PhotoFactory {
	
	
	private static final Logger log = Logger.getLogger(PhotoFactory.class.getName());
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static PhotoFactory instance = null;

	/**
	 * @methodtype constructor
	 */
	public FootballPhotoFactory() {
		// nothing to do here ;)
	}
	
    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }

	/**
	 * Public singleton access method.
	 * 
	 * methodtype get
	 */
	public static synchronized PhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage()
					.addAction("setting Football PhotoFactory").toString());
			setInstance(new FootballPhotoFactory());
		}
		return instance;
	}

	/**
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto() {
		return new FootballPhoto();
	}

	/**
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto(PhotoId id) {
		return new FootballPhoto(id);
	}

	/**
	 * @methodtype factory
	 */
	public FootballPhoto createFootballPhoto(PhotoId id, Location location) {
		return new FootballPhoto(id, location);
	}
	
	/**
	 * @methodtype factory
	 */
	
	public FootballPhoto createFootballPhoto(PhotoId id, String club, String name) {
		FootballPhoto newFootballPhoto = new FootballPhoto(id);
		newFootballPhoto.setClub(club);
		newFootballPhoto.setPlayerName(name);
		
		return newFootballPhoto;
		
	}


}
