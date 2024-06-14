package com.example.viewServer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.viewServer.entity.User;


public interface UserRepository extends JpaRepository<User, String>{
	
	@Query(value="select * from user where username= :username AND password= :password",nativeQuery=true)
	public User findByUnPw(@Param("username") String username, @Param("password") String password );
}
