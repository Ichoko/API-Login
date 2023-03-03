package DAnS.DAnS.Multi.Pro.service;

import DAnS.DAnS.Multi.Pro.ApplicationUserDetails;
import DAnS.DAnS.Multi.Pro.Entity.Account;
import DAnS.DAnS.Multi.Pro.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account entity = accountRepository.findById(username).get();
        UserDetails userDetailDTO = new ApplicationUserDetails(entity);
        return userDetailDTO;
    }

}
