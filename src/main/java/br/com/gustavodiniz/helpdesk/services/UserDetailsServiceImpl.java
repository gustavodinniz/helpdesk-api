package br.com.gustavodiniz.helpdesk.services;

import br.com.gustavodiniz.helpdesk.domain.Person;
import br.com.gustavodiniz.helpdesk.repositories.PersonRepository;
import br.com.gustavodiniz.helpdesk.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByEmail(email);

        if (person.isPresent()) {
            return new UserSS(person.get().getId(), person.get().getEmail(), person.get().getPassword(), person.get().getProfiles());
        }
        throw new UsernameNotFoundException(email);
    }
}
