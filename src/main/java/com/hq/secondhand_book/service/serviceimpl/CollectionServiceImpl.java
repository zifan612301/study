package com.hq.secondhand_book.service.serviceimpl;

import com.hq.secondhand_book.entity.Book;
import com.hq.secondhand_book.entity.Collection;
import com.hq.secondhand_book.entity.User;
import com.hq.secondhand_book.repository.BookRepository;
import com.hq.secondhand_book.repository.CollectionRepository;
import com.hq.secondhand_book.repository.UserRepositiry;
import com.hq.secondhand_book.service.CollectionService;
import com.hq.secondhand_book.util.Constant;
import com.hq.secondhand_book.util.resp.Response;
import com.hq.secondhand_book.util.resp.ResultResp;
import com.hq.secondhand_book.vo.book.BookListPageVo;
import com.hq.secondhand_book.vo.book.BookListVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Resource
    UserRepositiry userRepositiry;
    @Resource
    CollectionRepository collectionRepository;
    @Resource
    BookRepository bookRepository;

    @Override
    public ResultResp cancel(String userName, int bookId) {
        User user = userRepositiry.findByUserNameAndUsable(userName, Constant.USABLE);
        Book book = bookRepository.findByIdAndUsable(bookId,Constant.USABLE);
        if(user != null){
            if(book != null){
                Collection collection = collectionRepository.getByBookIdAndUserId(bookId,user.getId());
                if(collection!=null){
                    if(collection.getUsable()==Constant.USABLE){
                        collection.setUsable(Constant.UN_USABLE);
                        collection.setCstModify(new Date());
                        collectionRepository.saveAndFlush(collection);
                        return Response.ok();
                    }
                }else{
                    Response.dataErr("还未收藏过");
                }
            }else {
                return  Response.dataErr("图书已下架");
            }

        }
        return Response.dataErr("用户不存在");
    }

    @Override
    public ResultResp start(String userName, int bookId) {
        User user = userRepositiry.findByUserNameAndUsable(userName, Constant.USABLE);
        Book book = bookRepository.findByIdAndUsable(bookId,Constant.USABLE);
        if(user != null){
            if(book != null){
                Collection collection = collectionRepository.getByBookIdAndUserId(bookId,user.getId());
                if(collection!=null){
                    if(collection.getUsable()==Constant.UN_USABLE){
                        collection.setUsable(Constant.USABLE);
                        collectionRepository.saveAndFlush(collection);
                        return Response.ok(collection.getUsable());
                    }
                }else{
                    Collection collection1 = new Collection();
                    collection1.setUserId(user.getId());
                    collection1.setBookId(bookId);
                    collection1.setUsable(Constant.USABLE);
                    collection1.setCstCreate(new Date());
                    collection1.setCstModify(new Date());
                    collectionRepository.saveAndFlush(collection1);
                    return Response.ok();
                }
            }else {
                return  Response.dataErr("图书已下架");
            }

        }
        return Response.dataErr("用户不存在");
    }

    @Override
    public ResultResp startList(String userName, int pageIndex, int pageSize) {
        BookListPageVo bookListPageVo=new BookListPageVo();
        List<BookListVo> resultList=new ArrayList<>();
        User user = userRepositiry.findByUserNameAndUsable(userName,Constant.USABLE);
        if(pageIndex<1){
            return Response.dataErr("页码数不能小于1");
        }
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize, Sort.Direction.DESC,"cstModify");
        if(user!=null){
            List<Collection> collections = collectionRepository.getByUserIdAndUsable(user.getId(),Constant.USABLE);
            Page<Collection> pager = collectionRepository.getByUserIdAndUsable(user.getId(),Constant.USABLE,pageable);
            if(!collections.isEmpty()){
                for(Collection collection:collections){
                    Optional<Book> optional = bookRepository.findById(collection.getBookId());
                    if(optional.isPresent()){
                        Book book = optional.get();
                        BookListVo bookListVo=new BookListVo();
                        bookListVo.setBookName(book.getBookName());
                        String pic = "../picture/book/"+ book.getBookPicture().split("#")[0];
                        bookListVo.setBookPicture(pic);
                        DecimalFormat df = new DecimalFormat("#.00");
                        bookListVo.setBookPrice(df.format(book.getBookPrice()));
                        bookListVo.setBookId(book.getId());
                        resultList.add(bookListVo);
                    }
                }
            }
            bookListPageVo.setList(resultList);
            bookListPageVo.setRowCount(collections.size());
            return Response.ok(bookListPageVo);
        }
        return Response.dataErr("找不到资源");
    }
}
