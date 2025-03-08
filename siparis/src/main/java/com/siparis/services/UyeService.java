package com.siparis.services;

import com.siparis.dto.DtoUye;
import com.siparis.dto.DtoUyeIU;
import com.siparis.entity.Uye;
import com.siparis.repositories.IUyeRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class UyeService
{
    @Autowired
    IUyeRepositori myUyeRepositori;

    public DtoUye dbUyeToDtoUye (Uye u)
    {
        DtoUye dtoUye = new DtoUye();
        dtoUye.setId(u.getId());
        dtoUye.setFirstName(u.getFirstName());
        dtoUye.setLastName(u.getLastName());
        dtoUye.setTcKimlikNo(u.getTcKimlikNo());
        return dtoUye;
    }

    public ArrayList<DtoUye> uye_findAll()
    {
        ArrayList<DtoUye> uyeList = new ArrayList<>();
        for(Uye u : myUyeRepositori.findAll())
        {
            uyeList.add(dbUyeToDtoUye(u));
        }
        return uyeList;
    }

    public DtoUye uye_findId(int id)
    {
        Optional<Uye> optional = myUyeRepositori.findById(id);
        if(optional.isPresent())
        {
            return dbUyeToDtoUye(optional.get());
        }
        return null;
    }

    public DtoUye uye_save(DtoUyeIU dtoUyeIU)
    {
        Uye uye = new Uye();
        uye.setFirstName(dtoUyeIU.getFirstName());
        uye.setLastName(dtoUyeIU.getLastName());
        uye.setTcKimlikNo(dtoUyeIU.getTcKimlikNo());
        Uye dbUye = myUyeRepositori.save(uye);

        return dbUyeToDtoUye(dbUye);
    }

    public boolean uye_delete(int id)
    {
        if(myUyeRepositori.existsById(id))
        {
            myUyeRepositori.deleteById(id);
            return true;
        }
        return false;
    }

    public DtoUye uye_update(int id, DtoUyeIU dtoUyeIU)
    {
        Optional<Uye> optional = myUyeRepositori.findById(id);
        if(optional.isPresent())
        {
            Uye uye = optional.get();
            uye.setFirstName(dtoUyeIU.getFirstName());
            uye.setLastName(dtoUyeIU.getLastName());
            uye.setTcKimlikNo(dtoUyeIU.getTcKimlikNo());
            Uye dbUye = myUyeRepositori.save(uye);
            return dbUyeToDtoUye(dbUye);
        }
        return null;
    }
}
