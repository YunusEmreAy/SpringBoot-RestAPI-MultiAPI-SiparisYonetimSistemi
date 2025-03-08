package com.siparis.controller;

import com.siparis.dto.DtoUye;
import com.siparis.dto.DtoUyeIU;
import com.siparis.services.UyeService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "rest/api/uye")
public class RestUyeController
{
    @Autowired
    UyeService myUyeService;

    @GetMapping(path = "/findAll")
    public ArrayList<DtoUye> uye_findAll()
    {
        return myUyeService.uye_findAll();
    }

    @GetMapping(path = "/find/{id}")
    public DtoUye uye_findId(@PathVariable(name = "id", required = true) int id)
    {
        return myUyeService.uye_findId(id);
    }

    @PostMapping(path = "/save")
    public DtoUye uye_save(@RequestBody DtoUyeIU dtoUyeIU)
    {
        return myUyeService.uye_save(dtoUyeIU);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean uye_delete(@PathVariable(name = "id", required = true) Integer id)
    {
        return myUyeService.uye_delete(id);
    }

    @PutMapping(path = "/update/{id}")
    public DtoUye uye_update(@PathVariable(name = "id", required = true ) Integer id, @RequestBody DtoUyeIU dtoUyeIU)
    {
        return myUyeService.uye_update(id, dtoUyeIU);
    }

}
