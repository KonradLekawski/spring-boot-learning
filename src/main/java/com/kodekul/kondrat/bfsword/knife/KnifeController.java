package com.kodekul.kondrat.bfsword.knife;

import com.kodekul.kondrat.bfsword.global.exception.IdNotExistsException;
import com.kodekul.kondrat.bfsword.global.exception.IncompleteDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/knifes")
public class KnifeController {

    private KnifeService service;

    @Autowired
    public KnifeController(KnifeService service) {
        this.service = service;
    }

    @Deprecated
    public Iterable showKnives() {
        return service.showAll();
    }

    @GetMapping("")
    public Iterable<Knife> showAvailable() {
        return service.showAvailable();
    }

    @Deprecated
    public Knife showOne(@PathVariable Integer id) {
        return service.showOne(id);
    }

    @GetMapping("/{id}")
    public Knife showOneAvailble(@PathVariable Integer id) {
        return service.showOneAvailable(id);
    }

    @PostMapping("")
    public Knife createKnife(@RequestBody Knife knife) throws IncompleteDataException {
        return service.create(knife);
    }

    @PutMapping("")
    public Knife update(@RequestBody Knife knife) throws IdNotExistsException, IncompleteDataException {
        return service.update(knife);
    }

    @Deprecated
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @DeleteMapping("/{id}")
    public void archive(@PathVariable Integer id) throws IdNotExistsException {
        service.archive(id);
    }

    public void setService(KnifeService service) {
        this.service = service;
    }

    public KnifeService getService() {
        return service;
    }


}
