package com.hq.secondhand_book.service.serviceimpl;

import com.hq.secondhand_book.entity.BookCategory;
import com.hq.secondhand_book.repository.BookCategoryRepository;
import com.hq.secondhand_book.service.BookCategoryService;
import com.hq.secondhand_book.util.Constant;
import com.hq.secondhand_book.util.resp.Response;
import com.hq.secondhand_book.util.resp.ResultResp;
import com.hq.secondhand_book.vo.bookcategory.BookCategoryListVo;
import com.hq.secondhand_book.vo.bookcategory.BookCategoryPageVo;
import com.hq.secondhand_book.vo.bookcategory.BookCategoryVo;
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
public class BookCategoryServiceImpl implements BookCategoryService {
    @Resource
    BookCategoryRepository bookCategoryRepository;

    @Override
    public List<BookCategoryVo> findAll() {
        List<BookCategory> bookCategories = bookCategoryRepository.findAllByUsable(Constant.USABLE);
        ArrayList<BookCategoryVo> bookCategoryVos = new ArrayList<>();
        if(bookCategories!=null){
            for (BookCategory bookCategory : bookCategories) {
                bookCategoryVos.add(new BookCategoryVo(bookCategory.getId(),bookCategory.getBookCategoryName()));
            }
        }
        return bookCategoryVos;
    }

    @Override
    public ResultResp bookCategoryList(int page,int size) {
        System.out.println("page:"+page+"size:"+size);
        List<BookCategoryListVo> resultList=new ArrayList<>();
        BookCategoryPageVo bookCategoryPageVo=new BookCategoryPageVo();
        if(page<1){
            return Response.dataErr("页码数不能小于1");
        }
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"cstModify");
        Page<BookCategory> pager = bookCategoryRepository.findAllByUsable(Constant.USABLE, pageable);
        List<BookCategory> books = bookCategoryRepository.findAllByUsable(Constant.USABLE);
        List<BookCategory> list=pager.getContent();
        if (!list.isEmpty()){
            for(BookCategory bookCategory:list){
                BookCategoryListVo bookCategoryListVo=new BookCategoryListVo();
                bookCategoryListVo.setBookCategoryId(bookCategory.getId());
                bookCategoryListVo.setBookCategoryName(bookCategory.getBookCategoryName());
                BookCategory bookCategory1 = bookCategoryRepository.getById(bookCategory.getBookCategoryFatherId());
                if(bookCategory1!=null){
                    bookCategoryListVo.setBookCategoryFatherName(bookCategory1.getBookCategoryName());
                }else {
                    bookCategoryListVo.setBookCategoryFatherName("没有父类别");
                }
                bookCategoryListVo.setBooklevel(bookCategory.getBookCategoryLevel());
                resultList.add(bookCategoryListVo);
            }
        }else {
            return Response.dataErr("找不到资源");
        }
        bookCategoryPageVo.setList(resultList);
        bookCategoryPageVo.setRowCount(books.size());
        return Response.ok(bookCategoryPageVo);
    }

    /**
     * 获取主要类别
     * @return
     */
    @Override
    public ResultResp PrimaryCategory() {
        return null;
    }

    /**
     * 根据父类获取所有子类
     * @param fatherId
     * @return
     */
    @Override
    public ResultResp Subcategory(int fatherId) {
        return null;
    }

    /**
     * 根据图书类别名查找图书类别
     * @param category
     * @param page
     * @param size
     * @return
     */
    @Override
    public ResultResp findByBookCategoryNameList(String category, int page, int size) {
        List<BookCategoryListVo> resultList=new ArrayList<>();
        BookCategoryPageVo bookCategoryPageVo=new BookCategoryPageVo();
        if(page<1){
            return Response.dataErr("页码数不能小于1");
        }
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"cstModify");
        try {
            Page<BookCategory> pager = bookCategoryRepository.findByBookCategoryNameContainingAndUsable(category, Constant.USABLE, pageable);
            List<BookCategory> books = bookCategoryRepository.findByBookCategoryNameContainingAndUsable(category, Constant.USABLE);
            List<BookCategory> list = pager.getContent();
            if (!list.isEmpty()){
                for(BookCategory bookCategory:list){
                    BookCategoryListVo bookCategoryListVo=new BookCategoryListVo();
                    bookCategoryListVo.setBookCategoryId(bookCategory.getId());
                    bookCategoryListVo.setBookCategoryName(bookCategory.getBookCategoryName());
                    BookCategory bookCategory1 = bookCategoryRepository.getById(bookCategory.getBookCategoryFatherId());
                    if(bookCategory1!=null){
                        bookCategoryListVo.setBookCategoryFatherName(bookCategory1.getBookCategoryName());
                    }else {
                        bookCategoryListVo.setBookCategoryFatherName("没有父类别");
                    }
                    bookCategoryListVo.setBooklevel(bookCategory.getBookCategoryLevel());
                    resultList.add(bookCategoryListVo);
                }
            }else {
                bookCategoryPageVo.setList(resultList);
                bookCategoryPageVo.setRowCount(books.size());
                return Response.ok(bookCategoryPageVo);
            }
            bookCategoryPageVo.setList(resultList);
            bookCategoryPageVo.setRowCount(books.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok(bookCategoryPageVo);
    }

    @Override
    public ResultResp deleteBookCategory(String categoryName) {
        BookCategory bookCategory = bookCategoryRepository.getByBookCategoryName(categoryName);
        if(bookCategory != null){
            bookCategory.setUsable(Constant.UN_USABLE);
            bookCategoryRepository.saveAndFlush(bookCategory);
        }
        return Response.ok();
    }

    @Override
    public ResultResp addCategory(String catergoryName, String fatherCategoryName) {
        BookCategory bookCategory = new BookCategory();
        if(bookCategoryRepository.getByBookCategoryName(catergoryName) != null){
            if(bookCategory.getUsable()==Constant.UN_USABLE){
                bookCategory.setUsable(Constant.USABLE);
            }
            bookCategory.setBookCategoryName(catergoryName);
            if(bookCategoryRepository.getByBookCategoryName(fatherCategoryName) != null){
                bookCategory.setBookCategoryFatherId(bookCategoryRepository.getByBookCategoryName(fatherCategoryName).getId());
                bookCategory.setBookCategoryLevel(bookCategoryRepository.getByBookCategoryName(fatherCategoryName).getBookCategoryLevel()+1);
            }else {
                bookCategory.setBookCategoryFatherId(0);
                bookCategory.setBookCategoryLevel(0);
            }
            bookCategory.setCstModify(new Date());
            bookCategoryRepository.saveAndFlush(bookCategory);
        }else{
            bookCategory.setBookCategoryName(catergoryName);
            BookCategory category = bookCategoryRepository.getByBookCategoryName(fatherCategoryName);
            if(category != null){
                bookCategory.setBookCategoryFatherId(category.getId());
                bookCategory.setBookCategoryLevel(category.getBookCategoryLevel()+1);
            }else {
                bookCategory.setBookCategoryFatherId(0);
                bookCategory.setBookCategoryLevel(0);
            }
            bookCategory.setUsable(Constant.USABLE);
            bookCategory.setCstCreate(new Date());
            bookCategory.setCstModify(new Date());
            bookCategoryRepository.saveAndFlush(bookCategory);
        }
        return Response.ok();
    }

    @Override
    public ResultResp updateCategory(int categoryId,String catergoryName, String fatherCategoryName) {
        BookCategory bookCategory = bookCategoryRepository.getById(categoryId);
        if(bookCategory!=null){
            bookCategory.setBookCategoryName(catergoryName);
            BookCategory category = bookCategoryRepository.getByBookCategoryName(fatherCategoryName);
            if(category != null){
                bookCategory.setBookCategoryFatherId(category.getId());
                bookCategory.setBookCategoryLevel(category.getBookCategoryLevel()+1);
            }else {
                bookCategory.setBookCategoryFatherId(0);
                bookCategory.setBookCategoryLevel(0);
            }
            bookCategory.setCstModify(new Date());
            bookCategoryRepository.saveAndFlush(bookCategory);
        }
        return Response.ok();
    }
}
