package com.kodekul.kondrat.bfsword.knife;


import com.kodekul.kondrat.bfsword.global.exception.IdNotExistsException;
import com.kodekul.kondrat.bfsword.global.exception.IncompleteDataException;
import com.kodekul.kondrat.bfsword.global.ServiceInterface;
import com.kodekul.kondrat.bfsword.logger.LogerInfo;
import com.kodekul.kondrat.bfsword.logger.LoggerService;
import com.kodekul.kondrat.bfsword.master.MasterRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class KnifeService implements ServiceInterface<Knife> {

    private KnifeRepository repository;
    private MasterRepository masterRepository;
    private LoggerService logger;

    public KnifeService(KnifeRepository repository, MasterRepository masterRepository, LoggerService logger) {
        this.repository = repository;
        this.masterRepository = masterRepository;
        this.logger = logger;
    }

    @Deprecated
    public Iterable showAll() {
        return repository.findAll();
    }

    public Iterable<Knife> showAvailable() {
        return repository.findByArchivedFalse();
    }

    @Deprecated
    public Knife showOne(Integer id) {
        return repository.findOne(id);
    }

    public Knife showOneAvailable(Integer id) throws NoSuchElementException {
       Knife knife = repository.findByIdEqualsAndArchivedIsFalse(id);
       if(knife == null) {
           throw new NoSuchElementException();
       }
       return knife;
    }

    public Knife create(Knife knife)  throws IncompleteDataException {
        if (knife.getId() != null) {
            knife.setId(null);
        }

        try {
            knife = repository.save(knife);
            logger.logInfo(LogerInfo.OBJECT_CREATED);
            return knife;
        } catch (javax.validation.ConstraintViolationException e) {
            throw new IncompleteDataException();
        }
    }


    @Deprecated
    public void delete(Integer id) {
        repository.delete(repository.findOne(id));
        logger.logInfo(LogerInfo.OBJECT_DELETED);
    }

    public void archive(Integer id) throws IdNotExistsException {
        if(! repository.exists(id)) {
            throw new IdNotExistsException();
        }
        Knife knife = repository.findOne(id);
        knife.setArchived(true);
        repository.save(knife);
        logger.logInfo(LogerInfo.OBJECT_ARCHIVED);
    }

    public Knife update(Knife knife) throws IdNotExistsException, IncompleteDataException {

        if (knife.getId() != null && repository.findByIdEqualsAndArchivedIsFalse(knife.getId()) != null) {
            try {
                repository.save(knife);
                logger.logInfo(LogerInfo.OBJECT_UPDATED);
            } catch (RuntimeException e) {
                throw new IncompleteDataException();
            }
            return knife;
        }
        throw new IdNotExistsException("Id not exists");

    }
}
