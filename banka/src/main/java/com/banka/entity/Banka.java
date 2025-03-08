package com.banka.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Banka")
@AllArgsConstructor
@NoArgsConstructor
public class Banka
{
    @Id
    @Column(name = "tcKimlikNo")
    private Integer tcKimlikNo;

    @Column(name = "bakiye")
    private int bakiye;
}
