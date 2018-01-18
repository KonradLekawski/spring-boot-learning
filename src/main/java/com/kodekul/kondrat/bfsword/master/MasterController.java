package com.kodekul.kondrat.bfsword.master;

import com.kodekul.kondrat.bfsword.global.exception.IdNotExistsException;
import com.kodekul.kondrat.bfsword.global.exception.IncompleteDataException;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/masters")
public class MasterController {

    private MasterService service;

    public MasterController(MasterService service) {
        this.service = service;
    }

    @Deprecated
    public Iterable<Master> showAll() {
        return service.showAll();
    }

    @GetMapping
    public Iterable<Master> showAvaliableMasters() {
        return service.showAvailable();
    }

    @Deprecated
    public Master showOne(@PathVariable Integer id) {
        return service.showOne(id);
    }

    @GetMapping("/{id}")
    public Master showOneAvaileble(@PathVariable Integer id) throws NoSuchElementException {
        return service.showOneAvailable(id);
    }

    @Deprecated
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @DeleteMapping("/{id}")
    public void archive(@PathVariable Integer id) throws IdNotExistsException {
        service.archive(id);
    }

    @PostMapping("")
    public Master create(@RequestBody Master master) throws IncompleteDataException {
        return service.create(master);
    }

    @PutMapping("")
    public Master update(@RequestBody Master master) throws IncompleteDataException, IdNotExistsException {
        return service.update(master);
    }

}
