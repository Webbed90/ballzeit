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

package org.wahlzeit.main;

import org.wahlzeit.model.*;
import org.wahlzeit.model.persistence.DatastoreAdapter;
import org.wahlzeit.model.persistence.ImageStorage;
import org.wahlzeit.services.LogBuilder;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.logging.Logger;
import org.wahlzeit.utils.Pattern;
/**
 * A single-threaded Main class with database connection. Can be used by tools that don't want to start a server.
 */

@Pattern(
		name="Chain of Responsibility",
		participants = {
				"Client",  // Class contextInitalized in org.wahlzeit.apps sends a request to SetUp Wahlzeit and Calls startUp in ServiceMain
				"Handler", // Abstract Main & ModelMain
				"ConcreteHandler" //ServiceMain 
		}
)
public abstract class ModelMain extends AbstractMain {

	private static final Logger log = Logger.getLogger(ModelMain.class.getName());

	/**
	 *
	 */
	@Pattern(
			name = "Strategy",
			participants = {
							"client" /*There are (or could be) different algorithmic solutions
									   for the Image storage available. setInstance sets the
									   strategy to use the DatastoreAdapter*/
			}
	)
	
	protected void startUp(String rootDir) throws Exception {
		super.startUp(rootDir);
		log.info("AbstractMain.startUp completed");

		log.config(LogBuilder.createSystemMessage().addAction("load image storage").toString());
		//GcsAdapter.Builder gcsAdapterBuilder = new GcsAdapter.Builder();
		ImageStorage.setInstance(new DatastoreAdapter());

		log.config(LogBuilder.createSystemMessage().addAction("load globals").toString());
		GlobalsManager.getInstance().loadGlobals();

		log.config(LogBuilder.createSystemMessage().addAction("load user").toString());
		UserManager.getInstance().init();

		log.config(LogBuilder.createSystemMessage().addAction("init FootballPhotoFactory").toString());
		FootballPhotoFactory.initialize();

		log.config(LogBuilder.createSystemMessage().addAction("load Photos").toString());
		PhotoManager.getInstance().init();
		
		log.config(LogBuilder.createSystemMessage().addAction("load Players").toString());
		FootballPlayerManager.getInstance();
	}


	/**
	 *
	 */
	protected void shutDown() throws Exception {
		saveAll();

		super.shutDown();
	}

	/**
	 *
	 */
	public void saveAll() throws IOException{
		PhotoCaseManager.getInstance().savePhotoCases();
		PhotoManager.getInstance().savePhotos();
		UserManager.getInstance().saveClients();
		GlobalsManager.getInstance().saveGlobals();
	}

	/**
	 *
	 */
	protected void createUser(String userId, String nickName, String emailAddress, String photoDir) throws Exception {
		UserManager userManager = UserManager.getInstance();
		new User(userId, nickName, emailAddress);

		PhotoManager photoManager = PhotoManager.getInstance();
		File photoDirFile = new File(photoDir);
		FileFilter photoFileFilter = new FileFilter() {
			public boolean accept(File file) {
				//TODO: check and change
				return file.getName().endsWith(".jpg");
			}
		};

		File[] photoFiles = photoDirFile.listFiles(photoFileFilter);
		for (int i = 0; i < photoFiles.length; i++) {
			//TODO: change to datastore/cloud storage
			//Photo newPhoto = photoManager.createPhoto(photoFiles[i]);
			//user.addPhoto(newPhoto);
		}
	}
}
