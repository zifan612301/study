package com.hq.secondhand_book.vo.placeTransacion;

import lombok.Data;

import java.util.List;

@Data
public class PlaceTransactionPageVo {
    private List<PlaceTransactionListVo> list;
    private int rowCount;
}
