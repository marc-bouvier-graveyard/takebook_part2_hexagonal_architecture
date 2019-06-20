package fr.takebook.app;

import fr.takebook.data.infrastructure.out.adapter.DataBookOutputAdapter;
import fr.takebook.data.ports.DataBookInputPort;
import fr.takebook.library.domain.ports.in.LibraryBookInputPort;
import fr.takebook.library.domain.ports.in.UserInputPort;
import fr.takebook.library.infrastructure.out.adapter.LibraryBookOutputAdapter;
import fr.takebook.user.infrastructure.out.adapter.UserOutputAdapter;
import fr.takebook.user.infrastructure.out.persistence.repository.UserSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"fr.takebook"})
@EntityScan({
        "fr.takebook.data.infrastructure.out.persistence.entity",
        "fr.takebook.library.infrastructure.out.persistence.entity",
        "fr.takebook.user.infrastructure.out.persistence.entity"
})
@EnableJpaRepositories({
        "fr.takebook.data.infrastructure.out.persistence.repository",
        "fr.takebook.library.infrastructure.out.persistence.repository",
        "fr.takebook.user.infrastructure.out.persistence.repository"
})
public class SpringConfiguration {

    @Autowired
    private UserSpringRepository userSpringRepository;

    @Autowired
    private DataBookOutputAdapter dataBookOutputAdapter;

    @Autowired
    private LibraryBookOutputAdapter libraryBookOutputAdapter;

    @Bean
    @DependsOn({"dataBookOutputAdapter"})
    public DataBookInputPort dataBookInputPort() {
        return new DataBookInputPort(dataBookOutputAdapter);
    }

    @Bean
    @DependsOn({"libraryBookOutputAdapter"})
    public LibraryBookInputPort libraryBookInputPort() {
        return new LibraryBookInputPort(libraryBookOutputAdapter);
    }

    @Bean
    @DependsOn({"userSpringRepository"})
    public UserOutputAdapter userOutputAdapter() {
        return new UserOutputAdapter(userSpringRepository);
    }

    @Bean
    @DependsOn("userOutputAdapter")
    public UserInputPort userInputPort() {
        return new UserInputPort(userOutputAdapter());
    }



}
