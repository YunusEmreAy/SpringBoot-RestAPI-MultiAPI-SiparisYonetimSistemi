package com.siparis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Uye")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Uye
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "tc_kimlik_no")
    private int tcKimlikNo;

    @OneToMany(mappedBy = "uye", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Siparis> siparis;

}
