package com.ratmirdudin.backendfoodapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomPageDto {
    private PageMetaDto pageMeta;
    private Object data;
}
