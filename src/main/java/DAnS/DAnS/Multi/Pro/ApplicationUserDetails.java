package DAnS.DAnS.Multi.Pro;

//Ini adalah semacam DTO yang digunakan untuk Login

import DAnS.DAnS.Multi.Pro.Entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//DTO versi spring security
public class ApplicationUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); //Roles

    public ApplicationUserDetails(Account account){
        this.username = account.getUsername();
        this.password = account.getPassword();
        //GrantedAuthority adalah tipe data yang spring security gunakan untuk membaca Role
        //Notes: kenapa authorities dibuat collection, karena sebagian app ada yang user memiliki multiple roles.
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
