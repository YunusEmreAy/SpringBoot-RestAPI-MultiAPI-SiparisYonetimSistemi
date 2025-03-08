package com.banka.repositories;

import com.banka.entity.Banka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBankaRepository extends JpaRepository<Banka, Integer>
{
//    @Query(value = "SELECT bakiye from api2.Banka WHERE tcKimlikNo=?1", nativeQuery = true)
//    public Optional<Integer> bakiyeSorgula(Integer tcKimlikNo);
}
