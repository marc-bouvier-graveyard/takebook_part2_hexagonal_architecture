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
        "fr.takebook.user.infrastructure.out.persistence.entity",
        "fr.takebook.library.infrastructure.out.persistence.entity"
})
@EnableJpaRepositories({
        "fr.takebook.data.infrastructure.out.persistence.repository",
        "fr.takebook.user.infrastructure.out.persistence.repository",
        "fr.takebook.library.infrastructure.out.persistence.repository"
})
public class SpringConfiguration {

    @Bean
    public UserInputPort userInputPort(UserOutputAdapter userOutputAdapter) {
        return new UserInputPort(userOutputAdapter);
    }

    @Bean
    public DataBookInputPort dataBookInputPort(DataBookOutputAdapter dataBookOutputAdapter) {
        return new DataBookInputPort(dataBookOutputAdapter);
    }

    @Bean
    public LibraryBookInputPort libraryBookInputPort(LibraryBookOutputAdapter libraryBookOutputAdapter) {
        return new LibraryBookInputPort(libraryBookOutputAdapter);
    }

}
