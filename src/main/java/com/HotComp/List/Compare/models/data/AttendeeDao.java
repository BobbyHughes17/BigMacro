package com.HotComp.List.Compare.models.data;

import com.HotComp.List.Compare.models.Attendee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AttendeeDao extends CrudRepository<Attendee,Integer> {

    List<Attendee> findAllByRoomingList_Id(int roomingListId);
}
