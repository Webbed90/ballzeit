package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;
import java.util.logging.Logger;

public class FootballPhotoFactory extends PhotoFactory {
	
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static PhotoFactory instance = null;

	/**
	 * @methodtype constructor
	 */
	private FootballPhotoFactory() {
		// nothing to do here ;)
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
	 * Method to set the singleton instance of PhotoFactory.
	 * 
	 * @ methodtype set
	 */
	protected static synchronized void setInstance(PhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException(
					"attempt to initalize PhotoFactory twice");
		}

		instance = photoFactory;
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
	public FootballPhoto createFootballPhoto(PhotoId id, double latitude, double longitude) {
		return new FootballPhoto(id, latitude, longitude);
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
