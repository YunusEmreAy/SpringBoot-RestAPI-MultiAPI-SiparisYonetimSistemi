package com.siparis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.siparis.entity.Urun;
import com.siparis.entity.Uye;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class DtoSiparisIU
{
    private int uye_id;

    private int urun_id;

    private int adet;

}
