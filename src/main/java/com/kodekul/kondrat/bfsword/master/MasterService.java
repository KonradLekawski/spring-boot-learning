package com.kodekul.kondrat.bfsword.master;


import com.kodekul.kondrat.bfsword.global.exception.IdNotExistsException;
import com.kodekul.kondrat.bfsword.global.exception.IncompleteDataException;
import com.kodekul.kondrat.bfsword.global.ServiceInterface;
import com.kodekul.kondrat.bfsword.knife.Knife;
import com.kodekul.kondrat.bfsword.knife.KnifeRepository;
import com.kodekul.kondrat.bfsword.logger.LogerInfo;
import com.kodekul.kondrat.bfsword.logger.LoggerInterface;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MasterService implements ServiceInterface<Master> {
    private MasterRepository repository;
    private KnifeRepository knifeRepository;
    private LoggerInterface logger;

    public MasterService(MasterRepository repository, KnifeRepository knifeRepository, LoggerInterface logger) {
        this.repository = repository;
        this.knifeRepository = knifeRepository;
        this.logger = logger;
    }

    @Deprecated
    public Master showOne(Integer id) {
        return repository.findOne(id);
    }

    public Master showOneAvailable(Integer id) throws NoSuchElementException {
        Master master = repository.findOne(id);
        if (master == null) throw new NoSuchElementException();
        List<Knife> knifes = knifeRepository.findByMasterEqualsAndArchivedIsFalse(master);
        master.setKnifes(knifes);
        return  master;
    }

    @Deprecated
    public Iterable<Master> showAll() {
        return repository.findAll();
    }

    public Iterable<Master> showAvailable() {
        List<Master> masters = repository.findByArchivedFalse();
        for(Master master : masters ) {
            List<Knife> knifes = knifeRepository.findByMasterEqualsAndArchivedIsFalse(master);
            master.setKnifes(knifes);
        }
        return masters;
    }

    public  Master create(Master master) throws IncompleteDataException {
        if (master.getId() != null) {
            master.setId(null);
        }
        try {
            master.setKnifes(new ArrayList<>());
            repository.save(master);
            logger.logInfo(LogerInfo.OBJECT_CREATED);
            return master;
        } catch (ConstraintViolationException e) {
            throw new IncompleteDataException();
        }
    }

    @Deprecated
    public  void delete(Integer id) {
        repository.delete(repository.findOne(id));
        logger.logInfo(LogerInfo.OBJECT_DELETED);
    }

    public void archive(Integer id) throws IdNotExistsException {

       if (! repository.exists(id)) {
           throw new IdNotExistsException("Id not exsists");
       }

        Master master = repository.findOne(id);
        List<Knife> knifes = master.getKnifes();
        for (Knife knife : knifes) {
            knife.setArchived(true);
        }
        master.setArchived(true);
        repository.save(master);
        logger.logInfo(LogerInfo.OBJECT_ARCHIVED);
    }

    public  Master update(Master master) throws IncompleteDataException, IdNotExistsException {
        if (master.getId() != null && repository.findByIdEqualsAndArchivedIsFalse(master.getId()) != null) {
            try {
                repository.save(master);
            } catch (RuntimeException e) {
                throw new IncompleteDataException();
            }
            logger.logInfo(LogerInfo.OBJECT_UPDATED);
            return master;
        }
        throw new IdNotExistsException("Id not exists");
    }

}
