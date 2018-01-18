package com.kodekul.kondrat.bfsword.master;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterRepository extends CrudRepository <Master,Integer>  {

    List<Master> findByArchivedFalse();

    Master findByIdEqualsAndArchivedIsFalse(Integer id);
}
