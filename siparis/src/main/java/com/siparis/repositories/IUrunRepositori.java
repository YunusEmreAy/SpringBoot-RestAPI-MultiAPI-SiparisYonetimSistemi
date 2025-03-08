package com.siparis.repositories;

import com.siparis.entity.Urun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUrunRepositori extends JpaRepository<Urun, Integer>
{
}
