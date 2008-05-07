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

import javax.persistence.Entity;

import org.acegisecurity.GrantedAuthority;

/**
 * @author James Carman
 */
@Entity
public class GrantedAuthorityBean extends AbstractEntity 
implements GrantedAuthority {
    static final long serialVersionUID = 1;
    
    private String authority;
    
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
    
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        final GrantedAuthorityBean that = (GrantedAuthorityBean) o;
        if (that != null ? !authority.equals(that.authority) : that.authority != null) {
            return false;
        }
        
        return true;
    }
    
    public int hashCode() {
        return (authority != null ? authority.hashCode() : 0);
    }
    
    public String toString() {
        return authority;
    }
}
