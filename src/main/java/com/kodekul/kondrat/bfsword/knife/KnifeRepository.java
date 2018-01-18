package com.kodekul.kondrat.bfsword.knife;

import com.kodekul.kondrat.bfsword.master.Master;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnifeRepository extends CrudRepository<Knife, Integer> {

    List<Knife> findByArchivedFalse();

    Knife findByIdEqualsAndArchivedIsFalse(Integer id);

    List<Knife> findByMasterEqualsAndArchivedIsFalse(Master master);
}



