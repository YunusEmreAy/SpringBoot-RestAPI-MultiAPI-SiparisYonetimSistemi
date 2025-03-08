package com.siparis.controller;

import com.siparis.dto.DtoUrun;
import com.siparis.dto.DtoUrunIU;
import com.siparis.services.UrunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "rest/api/urun")
public class RestUrunController {

    @Autowired
    UrunService myUrunService;

    @GetMapping(path = "/findAll")
    public ArrayList<DtoUrun> urun_findAll()
    {
        return myUrunService.urun_findAll();
    }

    @GetMapping(path = "/find/{id}")
    public DtoUrun urun_findId(@PathVariable(name = "id", required = true) int id)
    {
        return myUrunService.urun_findId(id);
    }

    @PostMapping(path = "/save")
    public DtoUrun urun_save(@RequestBody DtoUrunIU dtoUrunIU)
    {
        return myUrunService.urun_save(dtoUrunIU);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean urun_delete(@PathVariable(name = "id", required = true) Integer id)
    {
        return myUrunService.urun_delete(id);
    }

    @PutMapping(path = "/update/{id}")
    public DtoUrun urun_update(@PathVariable(name = "id", required = true ) Integer id, @RequestBody DtoUrunIU dtoUrunIU)
    {
        return myUrunService.urun_update(id, dtoUrunIU);
    }


















}
