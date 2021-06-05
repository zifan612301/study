package com.hq.secondhand_book.service;

import com.hq.secondhand_book.util.resp.ResultResp;

public interface PlaceTransactionService {
    ResultResp getPlace();

    ResultResp placeList(int page,int size);

    ResultResp findByPlaceNameList(String placeName, int page,int size);

    ResultResp deletePlace(int placeId);

    ResultResp addPlace(String placeName,String fatherplaceName);

    ResultResp updatePlace(int placeId, String placeName,String fatherplaceName);
}
