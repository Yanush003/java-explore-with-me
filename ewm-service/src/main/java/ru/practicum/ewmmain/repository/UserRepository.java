package ru.practicum.ewmmain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewmmain.model.User;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByIdIn(Collection<Long> id, Pageable pageable);

    @Query("select count(u)>0 from User u where u.name = :name")
    Boolean isNameExisting(String name);
}
