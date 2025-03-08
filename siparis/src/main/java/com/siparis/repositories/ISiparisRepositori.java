package com.siparis.repositories;

import com.siparis.entity.Siparis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISiparisRepositori extends JpaRepository<Siparis, Integer>
{
}
