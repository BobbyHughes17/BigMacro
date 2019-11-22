package com.HotComp.List.Compare.models.data;

import com.HotComp.List.Compare.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User,Long> {

    //find User by User Name
    List<User> findAllByUserName(String userName);

    //find User by Email address
    List<User> findAllByEmail(String email);


}