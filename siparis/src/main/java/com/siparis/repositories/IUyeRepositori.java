package com.siparis.repositories;

import com.siparis.entity.Uye;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUyeRepositori extends JpaRepository<Uye, Integer>
{
}
