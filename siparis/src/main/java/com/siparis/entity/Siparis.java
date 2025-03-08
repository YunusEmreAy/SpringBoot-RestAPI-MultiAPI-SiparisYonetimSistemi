package com.siparis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Siparis")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Siparis
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "uye_id")
    private Uye uye;

    @ManyToOne
    @JoinColumn(name = "urun_id")
    private Urun urun;

    @Column(name = "adet")
    private int adet;

    @Column(name = "siparisTutari")
    private int siparisTutari;



}
