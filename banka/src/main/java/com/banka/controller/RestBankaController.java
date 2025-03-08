package com.banka.controller;

import com.banka.dto.DtoBanka;
import com.banka.dto.DtoBankaIU;
import com.banka.services.BankaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "rest/api/banka")
public class RestBankaController
{
    @Autowired
    BankaService myBankaService;

    @GetMapping(path = "/bakiyeSorgula/{tcKimlikNo}/{siparisTutari}")
    public String bakiyeSorgula(@PathVariable(name = "tcKimlikNo") Integer tcKimlikNo, @PathVariable(name = "siparisTutari") int siparisTutari)
    {
        return myBankaService.bakiyeSorgula(tcKimlikNo, siparisTutari);
    }


    @GetMapping(path = "/findAll")
    public ArrayList<DtoBanka> banka_findAll()
    {
        return myBankaService.banka_findAll();
    }

    @GetMapping(path = "/find/{id}")
    public DtoBanka banka_findId(@PathVariable(name = "id", required = true) int id)
    {
        return myBankaService.banka_findId(id);
    }

    @PostMapping(path = "/save")
    public DtoBanka banka_save(@RequestBody DtoBankaIU DtoBankaIU)
    {
        return myBankaService.banka_save(DtoBankaIU);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean banka_delete(@PathVariable(name = "id", required = true) Integer id)
    {
        return myBankaService.banka_delete(id);
    }

    @PutMapping(path = "/update/{id}")
    public DtoBanka banka_update(@PathVariable(name = "id", required = true ) Integer id, @RequestBody DtoBankaIU DtoBankaIU)
    {
        return myBankaService.banka_update(id, DtoBankaIU);
    }

    @GetMapping(path = "/bakiyeAdd/{id}/{bakiyeAdd}")
    public void banka_bakiyeAdd(@PathVariable(name = "id") int id, @PathVariable(name = "bakiyeAdd") int bakiyeAdd)
    {
        myBankaService.banka_bakiyeAdd(id, bakiyeAdd);
    }
}

