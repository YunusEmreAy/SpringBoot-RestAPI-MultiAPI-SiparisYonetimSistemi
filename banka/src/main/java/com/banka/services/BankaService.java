package com.banka.services;

import com.banka.dto.DtoBanka;
import com.banka.dto.DtoBankaIU;
import com.banka.entity.Banka;
import com.banka.repositories.IBankaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BankaService
{
    @Autowired
    IBankaRepository myBankaRepository;

    public String bakiyeSorgula(Integer tcKimlikNo, int siparisTutari)
    {
        Optional<Banka> optional = myBankaRepository.findById(tcKimlikNo);

        if(optional.isPresent())
        {
            Banka banka = optional.get();
            if(banka.getBakiye() >= siparisTutari)
            {
                banka.setBakiye(banka.getBakiye() - siparisTutari);
                myBankaRepository.save(banka);
                return "Ödeme İşlemi Başarılı Bir Şekilde Tamamlandı!";
            }
            else
            {
                return "Müşteri Bakiyesi Yetersiz!";
            }

        }
        return "Girilen TC Kimlik Numarasına Ait Müşteri Bulunamadı!";
    }


    public DtoBanka dbBankaToDtoBanka (Banka b)
    {
        DtoBanka dtoBanka = new DtoBanka();
        dtoBanka.setTcKimlikNo(b.getTcKimlikNo());
        dtoBanka.setBakiye(b.getBakiye());
        return dtoBanka;
    }

    public ArrayList<DtoBanka> banka_findAll()
    {
        ArrayList<DtoBanka> bankaList = new ArrayList<>();
        for(Banka b : myBankaRepository.findAll())
        {
            bankaList.add(dbBankaToDtoBanka(b));
        }
        return bankaList;
    }

    public DtoBanka banka_findId(int id)
    {
        Optional<Banka> optional = myBankaRepository.findById(id);
        if(optional.isPresent())
        {
            return dbBankaToDtoBanka(optional.get());
        }
        return null;
    }

    public DtoBanka banka_save(DtoBankaIU dtoBankaIU)
    {
        Banka banka = new Banka();
        banka.setTcKimlikNo(dtoBankaIU.getTcKimlikNo());
        banka.setBakiye(dtoBankaIU.getBakiye());
        Banka dbBanka = myBankaRepository.save(banka);

        return dbBankaToDtoBanka(dbBanka);
    }

    public boolean banka_delete(int id)
    {
        if(myBankaRepository.existsById(id))
        {
            myBankaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public DtoBanka banka_update(int id, DtoBankaIU dtoBankaIU)
    {
        Optional<Banka> optional = myBankaRepository.findById(id);
        if(optional.isPresent())
        {
            Banka banka = optional.get();
            banka.setBakiye(dtoBankaIU.getBakiye());
            banka.setTcKimlikNo(dtoBankaIU.getTcKimlikNo());
            Banka dbBanka = myBankaRepository.save(banka);
            return dbBankaToDtoBanka(dbBanka);
        }
        return null;
    }

    public void banka_bakiyeAdd(int id, int bakiyeAdd)
    {
        Optional<Banka> optional = myBankaRepository.findById(id);
        if(optional.isPresent())
        {
            Banka banka = optional.get();
            banka.setBakiye(banka.getBakiye() + bakiyeAdd);
        }
    }

}
