package com.siparis.services;

import com.siparis.dto.DtoSiparis;
import com.siparis.dto.DtoSiparisIU;
import com.siparis.entity.Siparis;
import com.siparis.entity.Urun;
import com.siparis.entity.Uye;
import com.siparis.repositories.ISiparisRepositori;
import com.siparis.repositories.IUrunRepositori;
import com.siparis.repositories.IUyeRepositori;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kong.unirest.HttpResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class SiparisService
{
    @Autowired
    ISiparisRepositori mySiparisRepositori;

    @Autowired
    IUyeRepositori myUyeRepositori;

    @Autowired
    IUrunRepositori myUrunRepositori;
    
    public String siparis_save(DtoSiparisIU DtoSiparisIU)
    {
        Optional<Urun> optionalUrun = myUrunRepositori.findById(DtoSiparisIU.getUrun_id());
        Optional<Uye> optionalUye = myUyeRepositori.findById(DtoSiparisIU.getUye_id());

        if(!optionalUye.isPresent())
        {
            return "Uye Bulunamdı!";
        }

        if (!optionalUrun.isPresent())
        {
            return "Urun Bulunamadı!";
        }
        Urun urun = optionalUrun.get();
        Uye uye = optionalUye.get();

        if(urun.getStok()<DtoSiparisIU.getAdet())
        {
            return "Urun Stoğu Yetersiz!";
        }

        HttpResponse<String> response = Unirest.get("http://localhost:9091/rest/api/banka/bakiyeSorgula/"
                        +optionalUye.get().getTcKimlikNo()+"/"+DtoSiparisIU.getAdet()*optionalUrun.get().getFiyat()).asString();


        if(response.getBody().equals("Ödeme İşlemi Başarılı Bir Şekilde Tamamlandı!"))
        {
            Siparis siparis = new Siparis();
            siparis.setUye(uye);
            siparis.setUrun(urun);
            siparis.setAdet(DtoSiparisIU.getAdet());
            siparis.setDate(new Date());
            siparis.setSiparisTutari((DtoSiparisIU.getAdet()*optionalUrun.get().getFiyat()));

            Siparis dbSiparis = mySiparisRepositori.save(siparis);

            urun.getSiparis().add(dbSiparis);
            urun.setStok(urun.getStok()-DtoSiparisIU.getAdet());
            myUrunRepositori.save(urun);

            uye.getSiparis().add(dbSiparis);
            myUyeRepositori.save(uye);

        }

        return response.getBody();
    }



    public DtoSiparis dbSiparisToDtoSiparis (Siparis s)
    {
        DtoSiparis DtoSiparis = new DtoSiparis();
        
        DtoSiparis.setId(s.getId());
        DtoSiparis.setDate(s.getDate());
        DtoSiparis.setUye(s.getUye());
        DtoSiparis.setUrun(s.getUrun());
        DtoSiparis.setAdet(s.getAdet());
        DtoSiparis.setSiparisTutari(s.getSiparisTutari());

        return DtoSiparis;
    }

    public ArrayList<DtoSiparis> siparis_findAll()
    {
        ArrayList<DtoSiparis> siparisList = new ArrayList<>();
        for(Siparis b : mySiparisRepositori.findAll())
        {
            siparisList.add(dbSiparisToDtoSiparis(b));
        }
        return siparisList;
    }

    public DtoSiparis siparis_findId(int id)
    {
        Optional<Siparis> optional = mySiparisRepositori.findById(id);
        if(optional.isPresent())
        {
            return dbSiparisToDtoSiparis(optional.get());
        }
        return null;
    }

    public boolean siparis_delete(int id)
    {
        Optional<Siparis> optional = mySiparisRepositori.findById(id);
        if(optional.isPresent())
        {
            HttpResponse<String> response = Unirest.get("http://localhost:9091/rest/api/banka/bakiyeAdd/"
                    +id+"/"+optional.get().getSiparisTutari()).asString();

            mySiparisRepositori.deleteById(id);
            return true;
        }
        return false;
    }

}