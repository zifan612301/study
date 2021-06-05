package com.hq.secondhand_book.service.serviceimpl;

import com.hq.secondhand_book.entity.Book;
import com.hq.secondhand_book.entity.OrderForm;
import com.hq.secondhand_book.entity.ReceivingAddress;
import com.hq.secondhand_book.entity.User;
import com.hq.secondhand_book.repository.BookRepository;
import com.hq.secondhand_book.repository.OrderFormRepository;
import com.hq.secondhand_book.repository.ReceivingAddressRepository;
import com.hq.secondhand_book.repository.UserRepositiry;
import com.hq.secondhand_book.service.OrderService;
import com.hq.secondhand_book.util.Constant;
import com.hq.secondhand_book.util.resp.Response;
import com.hq.secondhand_book.util.resp.ResultResp;
import com.hq.secondhand_book.vo.OrderDetailVo;
import com.hq.secondhand_book.vo.OrderListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserRepositiry userRepositiry;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    OrderFormRepository orderFormRepository;
    @Autowired
    ReceivingAddressRepository receivingAddressRepository;

    /**
     * 生成订单
     */
    @Override
    public ResultResp addOrder(int bookId, int addressId, String userName) {
        OrderForm orderForm = new OrderForm();
        User user =  userRepositiry.findByUserName(userName);
        if(user != null){
            orderForm.setUserId(user.getId());
        }else {
            Response.dataErr("用户不存在");
        }
        Book book = bookRepository.findByIdAndUsable(bookId, Constant.USABLE);
        if(book!=null){
            //生成订单图书变为售出状态不再展示
            book.setUsable(Constant.UN_USABLE);
            bookRepository.saveAndFlush(book);
            orderForm.setBookId(bookId);
            ReceivingAddress address = new ReceivingAddress();
            if(address!= null){
                orderForm.setReceiverId(addressId);
            }
            orderForm.setOrderRemark(new String());
            orderForm.setOrderState(Constant.DELIVERY);
        }
        orderForm.setUsable(Constant.USABLE);
        orderForm.setCstCreate(new Date());
        orderForm.setCstModify(new Date());
        orderFormRepository.saveAndFlush(orderForm);
        return Response.ok();
    }

    @Override
    public ResultResp orderList(String userName) {
        User user = userRepositiry.findByUserName(userName);
        List<OrderListVo> listVos = new ArrayList<>();
        if(user!=null){
            //找用户所有的订单
            List<OrderForm> list = orderFormRepository.findByUserIdAndUsableOrderByCstCreateDesc(user.getId(),Constant.USABLE);
            if(list.size()>0){
                for (OrderForm order:list) {
                    OrderListVo vo = new OrderListVo();
                    vo.setOrderId(order.getId());
                    ReceivingAddress receivingAddress = receivingAddressRepository.getById(order.getReceiverId());
                    if(receivingAddress!=null){
                        vo.setReceiverName(receivingAddress.getReceiverName());
                    }
                    vo.setOrderStatus(order.getOrderState());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    vo.setOrderTime(sdf.format(order.getCstModify()));
                    Optional<Book> optional = bookRepository.findById(order.getBookId());
                    if(optional.isPresent()){
                        Book book = optional.get();
                        vo.setBookName(book.getBookName());
                        String pic = "../picture/book/"+ book.getBookPicture().split("#")[0];
                        vo.setImg(pic);
                        DecimalFormat df = new DecimalFormat("#.00");
                        vo.setBookPrice(df.format(book.getBookPrice()));
                    }
                    listVos.add(vo);
                }
            }
            return Response.ok(listVos);
        }else {
            return Response.dataErr("用户不存在");
        }
    }

    /**
     * 订单详情
     */
    @Override
    public ResultResp orderDetail(int orderId) {
        Optional<OrderForm> optional = orderFormRepository.findById(orderId);
        if(optional.isPresent()){
            OrderForm order = optional.get();
            OrderDetailVo vo = new OrderDetailVo();
            vo.setOrderId(order.getId());
            ReceivingAddress receivingAddress = receivingAddressRepository.getById(order.getReceiverId());
            if(receivingAddress!=null){
                vo.setReceiverName(receivingAddress.getReceiverName());
                vo.setPlaceName(receivingAddress.getPlaceName());
                vo.setReceiverTel(receivingAddress.getReceiverTel());
            }
            vo.setOrderStatus(order.getOrderState());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            vo.setOrderTime(sdf.format(order.getCstModify()));
            Optional<Book> optionalbook = bookRepository.findById(order.getBookId());
            if(optionalbook .isPresent()){
                Book book = optionalbook .get();
                vo.setBookName(book.getBookName());
                String pic = "../picture/book/"+ book.getBookPicture().split("#")[0];
                vo.setImg(pic);
                DecimalFormat df = new DecimalFormat("#.00");
                vo.setBookPrice(df.format(book.getBookPrice()));
            }
            return Response.ok(vo);
        }
        return Response.dataErr("订单不存在");

    }

    /**
     * 取消订单
     */
    @Override
    public ResultResp cancelOrder(int orderId) {
        OrderForm orderForm = orderFormRepository.getByIdAndUsable(orderId,Constant.USABLE);
        if(orderForm != null){
            //根据订单找到书籍修改状态为可用，即未售出
            Optional<Book> optional = bookRepository.findById(orderForm.getBookId());
            if(optional.isPresent()){
                Book book = optional.get();
                book.setUsable(Constant.USABLE);
                bookRepository.saveAndFlush(book);
            }
            orderForm.setOrderState(Constant.CANCEL);
            orderFormRepository.saveAndFlush(orderForm);
            return Response.ok();
        }
        return Response.dataErr("订单不存在");
    }

    /**
     * 确认订单
     */
    @Override
    public ResultResp confirmReceipt(int orderId) {
        OrderForm orderForm = orderFormRepository.getByIdAndUsable(orderId,Constant.USABLE);
        if(orderForm != null){
            orderForm.setOrderState(Constant.RECEIVED);
            orderFormRepository.saveAndFlush(orderForm);
            return Response.ok();
        }
        return Response.dataErr("订单不存在");
    }
}
