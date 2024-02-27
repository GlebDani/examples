package com.example.baseline.servises;

import com.example.baseline.Model.Account;
import com.example.baseline.Model.Authority;
import com.example.baseline.repositories.AccountRepository;
import com.example.baseline.util.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AccountService implements UserDetailsService {

    @Value("/**")
    private String photo_prefix;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public Account save(Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
       if(account.getRole() == null)
           account.setRole(Roles.USER.getRole());
        if(account.getPhoto() == null) {
            String path = photo_prefix.replace("**", "images/person.jpg");
            account.setPhoto(path);
        }
       return accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findAccountByEmailIgnoreCase(email);
        if(!optionalAccount.isPresent())
            throw new UsernameNotFoundException("Account not found");
        Account account = optionalAccount.get();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(account.getRole()));

        for(Authority _auth: account.getAuthoritySet()){
            grantedAuthorities.add(new SimpleGrantedAuthority(_auth.getName()));
        }

        return new User(account.getEmail(), account.getPassword(), grantedAuthorities);
    }

    public Optional<Account> findOneByEmail(String email) {
        return accountRepository.findAccountByEmailIgnoreCase(email);

    }
    public Optional<Account> findOneById(long id) {
        return accountRepository.findById(id);

    }
    public Optional<Account> findByToken(String token) {
        return accountRepository.findByToken(token);

    }

}
