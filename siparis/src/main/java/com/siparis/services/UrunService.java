package com.siparis.services;

import com.siparis.dto.DtoUrun;
import com.siparis.dto.DtoUrunIU;
import com.siparis.dto.DtoUye;
import com.siparis.entity.Urun;
import com.siparis.entity.Uye;
import com.siparis.repositories.IUrunRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UrunService
{

    @Autowired
    IUrunRepositori myUrunRepositori;

    public DtoUrun dbUrunToDtoUrun(Urun u)
    {
        DtoUrun dtoUrun = new DtoUrun();
        dtoUrun.setId(u.getId());
        dtoUrun.setName(u.getName());
        dtoUrun.setStok(u.getStok());
        dtoUrun.setFiyat(u.getFiyat());
        return dtoUrun;
    }

    public ArrayList<DtoUrun> urun_findAll()
    {
        ArrayList<DtoUrun> urunList = new ArrayList<>();
        for(Urun u : myUrunRepositori.findAll())
        {
            urunList.add(dbUrunToDtoUrun(u));
        }
        return urunList;
    }

    public DtoUrun urun_findId(int id)
    {
        Optional<Urun> optional = myUrunRepositori.findById(id);
        if(optional.isPresent())
        {
            return dbUrunToDtoUrun(optional.get());
        }
        return null;
    }

    public DtoUrun urun_save(DtoUrunIU dtoUrunIU)
    {
        Urun urun = new Urun();
        urun.setName(dtoUrunIU.getName());
        urun.setStok(dtoUrunIU.getStok());
        urun.setFiyat(dtoUrunIU.getFiyat());
        Urun dbUrun = myUrunRepositori.save(urun);

        return dbUrunToDtoUrun(dbUrun);
    }

    public boolean urun_delete(int id)
    {
        if(myUrunRepositori.existsById(id))
        {
            myUrunRepositori.deleteById(id);
            return true;
        }
        return false;
    }

    public DtoUrun urun_update(int id, DtoUrunIU dtoUrunIU)
    {
        Optional<Urun> optional = myUrunRepositori.findById(id);
        if(optional.isPresent())
        {
            Urun urun = optional.get();
            urun.setName(dtoUrunIU.getName());
            urun.setStok(dtoUrunIU.getStok());
            urun.setFiyat(dtoUrunIU.getFiyat());
            Urun dbUrun = myUrunRepositori.save(urun);
            return dbUrunToDtoUrun(dbUrun);
        }
        return null;
    }












}
