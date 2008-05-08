/*
 * Copyright (c) 2006, Carman Consulting, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.dirent.tthelper.services;

import org.acegisecurity.providers.dao.SaltSource;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.apache.tapestry.services.ApplicationInitializer;
import org.apache.tapestry.services.ApplicationInitializerFilter;
import org.apache.tapestry.services.Context;
import org.hibernate.Session;

/**
 * @author James Carman
 * @author Robin Helgelin
 */
public class UserInitializerImpl implements ApplicationInitializerFilter {
    private PasswordEncoder passwordEncoder;
    private SaltSource saltSource;
    private Session session;
    
    public UserInitializerImpl(PasswordEncoder passwordEncoder, SaltSource saltSource, Session session) {
        this.passwordEncoder = passwordEncoder;
        this.saltSource = saltSource;
        this.session = session;
    }
    
    public void initializeApplication(Context context, ApplicationInitializer applicationInitializer) {

    	/*
        final UserDetailsBean ud = new UserDetailsBean();
        ud.setUsername("test");
        ud.setPassword(passwordEncoder.encodePassword("test", saltSource.getSalt(ud)));
        ud.setVerein( Verein.VFL_OLDENTRUP );
        ud.setEmail( "hschwan@gmx.de" );
        ud.addRole("ROLE_USER");
        ud.addRole("ROLE_ADMIN");
        session.save(ud);
		*/
    	
        applicationInitializer.initializeApplication(context);
    }
}
