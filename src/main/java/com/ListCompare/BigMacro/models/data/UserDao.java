package com.ListCompare.BigMacro.models.data;

import com.ListCompare.BigMacro.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User,Integer> {

}
