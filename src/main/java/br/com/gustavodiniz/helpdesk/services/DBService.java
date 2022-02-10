package br.com.gustavodiniz.helpdesk.services;

import br.com.gustavodiniz.helpdesk.domain.Called;
import br.com.gustavodiniz.helpdesk.domain.Client;
import br.com.gustavodiniz.helpdesk.domain.Technician;
import br.com.gustavodiniz.helpdesk.domain.enums.Priority;
import br.com.gustavodiniz.helpdesk.domain.enums.Profile;
import br.com.gustavodiniz.helpdesk.domain.enums.Status;
import br.com.gustavodiniz.helpdesk.repositories.CalledRepository;
import br.com.gustavodiniz.helpdesk.repositories.ClientRepository;
import br.com.gustavodiniz.helpdesk.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CalledRepository calledRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanceDB() {
        Technician technician1 = new Technician(null, "Gustavo Diniz", "616.452.220-04", "gustavo@gmail.com", encoder.encode("123"));
        Technician technician2 = new Technician(null, "João Gomes", "158.003.450-07", "joao@gmail.com", encoder.encode("123"));
        Technician technician3 = new Technician(null, "Pedro Marcos", "028.250.600-46", "pedro@gmail.com", encoder.encode("123"));
        Technician technician4 = new Technician(null, "Vinicius Oliveira", "476.932.950-46", "viniciues@gmail.com", encoder.encode("123"));
        Technician technician5 = new Technician(null, "Leonardo Pereira", "174.843.150-10", "leonardo@gmail.com", encoder.encode("123"));
        technician1.addProfile(Profile.ADMIN);

        Client client1 = new Client(null, "Alberto Chaves", "753.597.110-51", "alberto@gmail.com", encoder.encode("123"));
        Client client2 = new Client(null, "Marisa Gomes", "502.999.840-36", "marisa@gmail.com", encoder.encode("123"));
        Client client3 = new Client(null, "Erica Diniz", "530.684.940-79", "erica@gmail.com", encoder.encode("123"));
        Client client4 = new Client(null, "Camila Diniz", "933.040.650-59", "camila@gmail.com", encoder.encode("123"));
        Client client5 = new Client(null, "Antonio Gomes", "802.942.350-00", "antonio@gmail.com", encoder.encode("123"));
        Client client6 = new Client(null, "Alessandro Mendes", "512.216.140-29", "alessandro@gmail.com", encoder.encode("123"));
        Client client7 = new Client(null, "Gabriel Magno", "906.687.060-58", "gabriel@gmail.com", encoder.encode("123"));
        Client client8 = new Client(null, "Ulisses Costa", "876.304.410-29", "ulisses@gmail.com", encoder.encode("123"));
        Client client9 = new Client(null, "Everton Mendonça", "014.681.750-84", "everton@gmail.com", encoder.encode("123"));
        Client client10 = new Client(null, "Maria Oliveira", "164.320.550-16", "maria@gmail.com", encoder.encode("123"));

        Called called1 = new Called(null, Priority.MEDIUM, Status.IN_PROGRESS, "Chamado 01", "Primeiro Chamado", technician1, client1);
        Called called2 = new Called(null, Priority.LOW, Status.OPEN, "Chamado 02", "Segundo Chamado", technician2, client2);
        Called called3 = new Called(null, Priority.MEDIUM, Status.IN_PROGRESS, "Chamado 03", "Terceiro Chamado", technician4, client4);
        Called called4 = new Called(null, Priority.HIGH, Status.CLOSED, "Chamado 04", "Quarto Chamado", technician3, client7);
        Called called5 = new Called(null, Priority.LOW, Status.CLOSED, "Chamado 05", "Quinto Chamado", technician5, client10);

        technicianRepository.saveAll(Arrays.asList(technician1, technician2, technician3, technician4, technician5));
        clientRepository.saveAll(Arrays.asList(client1, client2, client3, client4, client5, client6, client7, client8, client9, client10));
        calledRepository.saveAll(Arrays.asList(called1, called2, called3, called4, called5));
    }
}
