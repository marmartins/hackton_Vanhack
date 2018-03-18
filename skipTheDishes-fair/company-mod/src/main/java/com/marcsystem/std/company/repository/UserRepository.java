package com.marcsystem.std.company.repository;

import com.marcsystem.std.company.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long>{

    @Query("SELECT user FROM AppUser user WHERE LOWER(user.userName) = LOWER(:userName)")
    AppUser findByUserName(@Param("userName") String userName);

}
