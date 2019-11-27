package com.HotComp.List.Compare.models.data;

import com.HotComp.List.Compare.models.RoomingList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RoomingDao extends CrudRepository<RoomingList,Integer> {
    List<RoomingList> findAllByUser_id(int id);
}
