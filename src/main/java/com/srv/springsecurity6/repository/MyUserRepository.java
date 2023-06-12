package com.srv.springsecurity6.repository;

import com.srv.springsecurity6.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Long>{

    @Query(value ="select * from user u where username = ?1",nativeQuery = true)
    MyUser loadUserByUsername(String username);
}
