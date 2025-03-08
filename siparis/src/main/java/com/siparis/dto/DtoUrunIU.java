package com.siparis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUrunIU
{
    private String name;

    private int stok;

    private int fiyat;
}
