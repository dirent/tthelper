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

package de.dirent.tthelper.entities;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import de.dirent.tthelper.model.Verein;

/**
 * @author James Carman
 */
@Entity
public class UserDetailsBean extends AbstractEntity implements UserDetails {
    static final long serialVersionUID = 1;
    
    private String password;
    private String username;
    private Verein verein;
    private String email;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    private Collection<GrantedAuthorityBean> grantedAuthorities = 
        new HashSet<GrantedAuthorityBean>();
    
    @Transient
    public GrantedAuthority[] getAuthorities() {
        final Collection<GrantedAuthorityBean> grantedAuthorities =
            getGrantedAuthorities();
        return (GrantedAuthority[]) grantedAuthorities.toArray(
                new GrantedAuthority[grantedAuthorities.size()]);
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String toString() {
        return username;
    }
    
    public void addRole(String role) {
        final GrantedAuthorityBean authority = new GrantedAuthorityBean();
        authority.setAuthority(role);
        getGrantedAuthorities().add(authority);
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany
    @Cascade(CascadeType.ALL)
    public Collection<GrantedAuthorityBean> getGrantedAuthorities() {
        return grantedAuthorities;
    }
    
    public void setGrantedAuthorities(
            Collection<GrantedAuthorityBean> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
	public Verein getVerein() {
		return verein;
	}

	public void setVerein(Verein verein) {
		this.verein = verein;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
