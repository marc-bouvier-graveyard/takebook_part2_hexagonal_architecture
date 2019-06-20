package fr.takebook.user.infrastructure.out.adapter;

import fr.takebook.library.domain.model.User;
import fr.takebook.library.domain.ports.out.UserOutputPort;
import fr.takebook.user.infrastructure.out.persistence.entity.UserEntity;
import fr.takebook.user.infrastructure.out.persistence.repository.UserSpringRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

//@Component
public class UserOutputAdapter implements UserOutputPort {

    //@Autowired
    private UserSpringRepository repository;

    public UserOutputAdapter(UserSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return repository.findById(id)
                .map(entity -> fromEntity(entity));
    }

    @Override
    public Stream<User> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(entity -> fromEntity(entity));
    }

    private User fromEntity(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        return model;
    }

}
