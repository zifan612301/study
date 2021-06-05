package com.hq.secondhand_book.service.serviceimpl;

import com.hq.secondhand_book.entity.PlaceTransaction;
import com.hq.secondhand_book.repository.PlaceTransactionRepository;
import com.hq.secondhand_book.service.PlaceTransactionService;
import com.hq.secondhand_book.util.Constant;
import com.hq.secondhand_book.util.resp.Response;
import com.hq.secondhand_book.util.resp.ResultResp;
import com.hq.secondhand_book.vo.placeTransacion.PlaceTransactionListVo;
import com.hq.secondhand_book.vo.placeTransacion.PlaceTransactionPageVo;
import com.hq.secondhand_book.vo.placeTransacion.PlaceTransactionVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PlaceTransactionServiceImpl implements PlaceTransactionService {
    @Resource
    PlaceTransactionRepository placeTransactionRepository;

    /**
     * 获取交易地点
     * @return
     */
    @Override
    public ResultResp getPlace() {
        List<PlaceTransaction> list = placeTransactionRepository.findAll();
        List<PlaceTransactionVo> vos = new ArrayList<>();
        if(!list.isEmpty()){
            for(PlaceTransaction placeTransaction:list){
                PlaceTransactionVo vo=new PlaceTransactionVo();
                vo.setPlaceName(placeTransaction.getPlaceName());
                vo.setPlaceTransactionId(placeTransaction.getId());
                vos.add(vo);
            }
        }
        return Response.ok(vos);
    }

    @Override
    public ResultResp placeList(int page, int size) {
        List<PlaceTransactionListVo> resultList=new ArrayList<>();
        PlaceTransactionPageVo pageVo=new PlaceTransactionPageVo();
        if(page<1){
            return Response.dataErr("页码数不能小于1");
        }
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"cstModify");
        Page<PlaceTransaction> pager = placeTransactionRepository.findAllByUsable(Constant.USABLE, pageable);
        List<PlaceTransaction> placeTransactions = placeTransactionRepository.findAllByUsable(Constant.USABLE);
        List<PlaceTransaction> list=pager.getContent();
        if (!list.isEmpty()){
            for(PlaceTransaction placeTransaction:list){
                PlaceTransactionListVo listVo=new PlaceTransactionListVo();
                listVo.setPlaceTransactionId(placeTransaction.getId());
                listVo.setPlaceName(placeTransaction.getPlaceName());
                PlaceTransaction transaction = placeTransactionRepository.getById(placeTransaction.getPlaceFatherId());
                if(transaction!=null){
                    listVo.setPlaceFatherName(transaction.getPlaceName());
                }else {
                    listVo.setPlaceFatherName("没有父交易地点");
                }
                listVo.setPlaceLevel(placeTransaction.getPlaceLevel());
                resultList.add(listVo);
            }
        }else {
            return Response.dataErr("找不到资源");
        }
        pageVo.setList(resultList);
        pageVo.setRowCount(placeTransactions.size());
        return Response.ok(pageVo);
    }

    @Override
    public ResultResp findByPlaceNameList(String placeName, int page, int size) {
        List<PlaceTransactionListVo> resultList=new ArrayList<>();
        PlaceTransactionPageVo pageVo=new PlaceTransactionPageVo();
        if(page<1){
            return Response.dataErr("页码数不能小于1");
        }
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"cstModify");
        Page<PlaceTransaction> pager = placeTransactionRepository.findByPlaceNameContainingAndUsable(placeName, Constant.USABLE, pageable);
        List<PlaceTransaction> placeTransactions = placeTransactionRepository.findByPlaceNameContainingAndUsable(placeName, Constant.USABLE);
        List<PlaceTransaction> list=pager.getContent();
        if (!list.isEmpty()){
            for(PlaceTransaction placeTransaction:list){
                PlaceTransactionListVo listVo=new PlaceTransactionListVo();
                listVo.setPlaceTransactionId(placeTransaction.getId());
                listVo.setPlaceName(placeTransaction.getPlaceName());
                PlaceTransaction transaction = placeTransactionRepository.getById(placeTransaction.getPlaceFatherId());
                if(transaction!=null){
                    listVo.setPlaceFatherName(transaction.getPlaceName());
                }else {
                    listVo.setPlaceFatherName("没有父交易地点");
                }
                listVo.setPlaceLevel(placeTransaction.getPlaceLevel());
                resultList.add(listVo);
            }
        }else {
            return Response.dataErr("找不到资源");
        }
        pageVo.setList(resultList);
        pageVo.setRowCount(placeTransactions.size());
        return Response.ok(pageVo);
    }

    @Override
    public ResultResp deletePlace(int placeId) {
        PlaceTransaction placeTransaction = placeTransactionRepository.getById(placeId);
        if(placeTransaction != null){
            placeTransaction.setUsable(Constant.UN_USABLE);
            placeTransactionRepository.saveAndFlush(placeTransaction);
        }
        return Response.ok();
    }

    @Override
    public ResultResp addPlace(String placeName, String fatherplaceName) {
        PlaceTransaction placeTransaction = new PlaceTransaction();
        if(placeTransactionRepository.getByPlaceName(placeName) != null){
            if(placeTransaction.getUsable()==Constant.UN_USABLE){
                placeTransaction.setUsable(Constant.USABLE);
            }
            placeTransaction.setPlaceName(placeName);
            if(placeTransactionRepository.getByPlaceName(fatherplaceName) != null){
                placeTransaction.setPlaceFatherId(placeTransactionRepository.getByPlaceName(fatherplaceName).getId());
                placeTransaction.setPlaceLevel(placeTransactionRepository.getByPlaceName(fatherplaceName).getPlaceLevel()+1);
            }else {
                placeTransaction.setPlaceFatherId(0);
                placeTransaction.setPlaceLevel(0);
            }
            placeTransaction.setCstModify(new Date());
            placeTransactionRepository.saveAndFlush(placeTransaction);
        }else{
            placeTransaction.setPlaceName(placeName);
            PlaceTransaction transaction = placeTransactionRepository.getByPlaceName(fatherplaceName);
            if(transaction != null){
                placeTransaction.setPlaceFatherId(transaction.getId());
                placeTransaction.setPlaceLevel(transaction.getPlaceLevel()+1);
            }else {
                placeTransaction.setPlaceFatherId(0);
                placeTransaction.setPlaceLevel(0);
            }
            placeTransaction.setUsable(Constant.USABLE);
            placeTransaction.setCstCreate(new Date());
            placeTransaction.setCstModify(new Date());
            placeTransactionRepository.saveAndFlush(placeTransaction);
        }
        return Response.ok();
    }

    @Override
    public ResultResp updatePlace(int placeId, String placeName, String fatherplaceName) {
        PlaceTransaction placeTransaction = placeTransactionRepository.getById(placeId);
        if(placeTransaction!=null){
            placeTransaction.setPlaceName(placeName);
            PlaceTransaction fatherPlace = placeTransactionRepository.getByPlaceName(fatherplaceName);
            if(fatherPlace != null){
                placeTransaction.setPlaceFatherId(fatherPlace.getId());
                placeTransaction.setPlaceLevel(fatherPlace.getPlaceLevel()+1);
            }else {
                placeTransaction.setPlaceFatherId(0);
                placeTransaction.setPlaceLevel(0);
            }
            placeTransaction.setCstModify(new Date());
            placeTransactionRepository.saveAndFlush(placeTransaction);
        }
        return Response.ok();
    }
}
