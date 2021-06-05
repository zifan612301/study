package com.hq.secondhand_book.service.serviceimpl;

import com.hq.secondhand_book.dto.ReceiveAdressDto;
import com.hq.secondhand_book.entity.ReceivingAddress;
import com.hq.secondhand_book.entity.User;
import com.hq.secondhand_book.repository.ReceivingAddressRepository;
import com.hq.secondhand_book.repository.UserRepositiry;
import com.hq.secondhand_book.service.ReceiveAdressService;
import com.hq.secondhand_book.util.Constant;
import com.hq.secondhand_book.util.resp.Response;
import com.hq.secondhand_book.util.resp.ResultResp;
import com.hq.secondhand_book.vo.ReceiveAddressVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReceiveAdressServiceImpl implements ReceiveAdressService {
    @Resource
    UserRepositiry userRepositiry;
    @Resource
    ReceivingAddressRepository receivingAddressRepository;

    @Override
    public ResultResp getRececiveAdress(String userName) {
        List<ReceiveAddressVo> receiveAddressVos = new ArrayList<>();
        User user = userRepositiry.findByUserName(userName);
        if( user!= null){
            int userId = user.getId();
            List<ReceivingAddress> list = receivingAddressRepository.getByUserIdAndUsable(userId,Constant.USABLE);
            if(!list.isEmpty()){
                for(ReceivingAddress address:list){
                    ReceiveAddressVo vo=new ReceiveAddressVo();
                    vo.setReceiveId(address.getId());
                    vo.setPlaceName(address.getPlaceName());
                    vo.setReceiverName(address.getReceiverName());
                    vo.setReceiverTel(address.getReceiverTel());
                    vo.setIsDefault(address.getIsDefault());
                    receiveAddressVos.add(vo);
                }
            }
        }
        return Response.ok(receiveAddressVos);
    }

    @Override
    public ResultResp addRececiveAdress(ReceiveAdressDto dto) {
        User user = userRepositiry.findByUserName(dto.getUserName());
        ReceivingAddress address = new ReceivingAddress();
        if(user != null){
            address.setUserId(user.getId());
        }else {
            return Response.dataErr("用户不存在");
        }
        address.setPlaceName(dto.getPlaceName());
        address.setReceiverName(dto.getReceiverName());
        address.setReceiverTel(dto.getReceiverTel());
        address.setIsDefault(0);
        address.setUsable(Constant.USABLE);
        address.setCstCreate(new Date());
        address.setCstModify(new Date());
        receivingAddressRepository.saveAndFlush(address);
        return Response.ok();
    }

    @Override
    public ResultResp editRececiveAdress(ReceiveAdressDto dto) {
        User user = userRepositiry.findByUserName(dto.getUserName());
        ReceivingAddress address = new ReceivingAddress();
        if(user != null){
            address.setUserId(user.getId());
        }else {
            return Response.dataErr("用户不存在");
        }
        address.setId(dto.getReceiveId());
        address.setPlaceName(dto.getPlaceName());
        address.setReceiverName(dto.getReceiverName());
        address.setReceiverTel(dto.getReceiverTel());
        address.setIsDefault(0);
        address.setUsable(Constant.USABLE);
        address.setCstCreate(new Date());
        address.setCstModify(new Date());
        receivingAddressRepository.save(address);
        return Response.ok();
    }

    @Override
    public ResultResp deleteReceiveAdress(int id) {
        ReceivingAddress address =  receivingAddressRepository.getById(id);
        if (address!=null){
            if(address.getUsable()==1){
                address.setUsable(Constant.UN_USABLE);
            }
            receivingAddressRepository.saveAndFlush(address);
            return Response.ok();
        }
        return Response.dataErr("该地址不存在");
    }
}
