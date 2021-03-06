package de.dirent.tthelper.pages.pokalmannschaft;


import java.util.List;

import org.acegisecurity.annotation.Secured;

import de.dirent.tthelper.entities.PokalMannschaft;
import de.dirent.tthelper.pages.SecuredPage;


@Secured( "ROLE_ADMIN" )
public class AdminPokalMannschaft extends SecuredPage {

	public List<PokalMannschaft> getGemeldetePokalMannschaften() {

		return getPersistenceManager().getAllPokalMannschaften();
	}	
}
