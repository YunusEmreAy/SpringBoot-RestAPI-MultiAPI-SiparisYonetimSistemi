package com.siparis.controller;

import com.siparis.dto.DtoSiparis;
import com.siparis.dto.DtoSiparisIU;
import com.siparis.services.SiparisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "rest/api/siparis")
public class RestSiparisController
{
    @Autowired
    SiparisService mySiparisServices;

    @PostMapping(path = "/save")
    public String siparis_save(@RequestBody DtoSiparisIU DtoSiparisIU)
    {
        return mySiparisServices.siparis_save(DtoSiparisIU);
    }

    @GetMapping(path = "/findAll")
    public ArrayList<DtoSiparis> siparis_findAll()
    {
        return mySiparisServices.siparis_findAll();
    }

    @GetMapping(path = "/find/{id}")
    public DtoSiparis siparis_findId(@PathVariable(name = "id", required = true) int id)
    {
        return mySiparisServices.siparis_findId(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean siparis_delete(@PathVariable(name = "id", required = true) Integer id)
    {
        return mySiparisServices.siparis_delete(id);
    }



}
