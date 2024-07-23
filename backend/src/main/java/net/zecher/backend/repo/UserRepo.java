package net.zecher.backend.repo;

import net.zecher.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUserName(String userName);
    void deleteByUserName(String userName);
}
