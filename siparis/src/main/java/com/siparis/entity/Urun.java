package com.siparis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Urun")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Urun
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "stok")
    private int stok;

    @Column(name = "fiyat")
    private int fiyat;

    @OneToMany(mappedBy = "urun", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Siparis> siparis;

}
