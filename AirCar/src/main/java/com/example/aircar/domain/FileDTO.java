package com.example.aircar.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FileDTO {

    @NonNull
    private List<Long> carNum;
    private List<String> brandName;
    private List<String> carName;
    private List<String> brandImg;
    private List<String> carImg;
}
