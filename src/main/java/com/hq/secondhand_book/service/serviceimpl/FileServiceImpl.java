package com.hq.secondhand_book.service.serviceimpl;

import com.hq.secondhand_book.entity.Book;
import com.hq.secondhand_book.entity.User;
import com.hq.secondhand_book.repository.BookRepository;
import com.hq.secondhand_book.repository.UserRepositiry;
import com.hq.secondhand_book.service.FileService;
import com.hq.secondhand_book.util.resp.Response;
import com.hq.secondhand_book.util.resp.ResultResp;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    UserRepositiry userRepositiry;
    @Resource
    BookRepository bookRepository;

    /**
     * 编辑头像
     * @param file
     * @param userName
     * @return
     */
    @Override
    public ResultResp singleFile(MultipartFile file,String userName) {
        try {
            String name = file.getOriginalFilename();//上传文件的真实名称
            String suffixName = name.substring(name.lastIndexOf(".")+1);//获取后缀名
            String fileName = UUID.randomUUID()+"."+suffixName;
            //图片的存储位置
            String filePath = "C:\\Users\\HouTao\\Desktop\\secondhand_books\\src\\main\\resources\\static\\picture\\user\\";
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Optional<User> userOptional = userRepositiry.getByUserNameAndUsable(userName,1);
            if(userOptional.isPresent()){
                User user = userOptional.get();
                user.setUserPic(fileName);
                userRepositiry.saveAndFlush(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.dataErr();
        }
        return Response.ok();
    }

    @Override
    public ResultResp moreFile(MultipartFile[] files, String userName,String bookName,
                               String bookSynopsis, double bookPrice,int bookCategoryId) {
        for(MultipartFile file : files){
            try {
                String name = file.getOriginalFilename();//上传文件的真实名称
                String suffixName = name.substring(name.lastIndexOf(".")+1);//获取后缀名
                String fileName = UUID.randomUUID()+"."+suffixName;
                //图片的存储位置
                String filePath = "C:\\Users\\HouTao\\Desktop\\secondhand_books\\src\\main\\resources\\static\\picture\\book\\";
                File dest = new File(filePath + fileName);
                User user = userRepositiry.findByUserName(userName);
                Book book;
                if(user!=null){
                    if(user.getUsable()==1){
                        System.out.println(user);
                        int userId = user.getId();
                        book = bookRepository.findByUserIdAndBookNameAndBookSysnopsisAndBookPriceAndBookCategoryId(userId,
                                bookName,bookSynopsis,bookPrice,bookCategoryId);
                        if(book!=null){
                            System.out.println(book);
                            String pic = book.getBookPicture()+"#"+fileName;
                            book.setBookPicture(pic);
                            book.setCstModify(new Date());
                            bookRepository.saveAndFlush(book);
                        }else{
                            book = new Book();
                            book.setUserId(userId);
                            book.setBookName(bookName);
                            book.setBookPrice(bookPrice);
                            book.setBookCategoryId(bookCategoryId);
                            book.setBookSysnopsis(bookSynopsis);
                            book.setBookPicture(fileName);
                            book.setUsable(1);
                            book.setCstCreate(new Date());
                            book.setCstModify(new Date());
                            bookRepository.save(book);
                        }
                    }
                }
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {
                    file.transferTo(dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Response.dataErr("上传失败");
            }
        }
        return Response.ok("上传成功");
    }
}
