package com.hq.secondhand_book.service.serviceimpl;

import com.hq.secondhand_book.dto.LeaveWordDto;
import com.hq.secondhand_book.entity.Book;
import com.hq.secondhand_book.entity.LeaveWord;
import com.hq.secondhand_book.entity.User;
import com.hq.secondhand_book.repository.BookRepository;
import com.hq.secondhand_book.repository.LeaveWordRepository;
import com.hq.secondhand_book.repository.UserRepositiry;
import com.hq.secondhand_book.service.LeaveWordService;
import com.hq.secondhand_book.util.Constant;
import com.hq.secondhand_book.util.resp.Response;
import com.hq.secondhand_book.util.resp.ResultResp;
import com.hq.secondhand_book.vo.leaveword.LeaveWordListVo;
import com.hq.secondhand_book.vo.leaveword.LeaveWordPageVo;
import com.hq.secondhand_book.vo.leaveword.LeaveWordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class LeaveWordServiceImpl implements LeaveWordService {
    @Autowired
    UserRepositiry userRepositiry;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    LeaveWordRepository leaveWordRepository;

    /**
     * 发布留言
     * @param leaveWordDto
     * @return
     */
    @Override
    public ResultResp addLeaveWord(LeaveWordDto leaveWordDto) {
        LeaveWord leaveWord = new LeaveWord();
        User user = userRepositiry.findByUserName(leaveWordDto.getUserName());
        if(user!=null){
            leaveWord.setUserId(user.getId());
        }else {
            Response.dataErr("用户不存在");
        }
        Book book = bookRepository.findByIdAndUsable(leaveWordDto.getBookId(), Constant.USABLE);
        if(book!=null){
            leaveWord.setBookId(book.getId());
        }else {
            Response.dataErr("图书不存在");
        }
        leaveWord.setLeaveContent(leaveWordDto.getLeaveContent());
        leaveWord.setLeaveFatherId(0);
        leaveWord.setUsable(Constant.USABLE);
        leaveWord.setCstCreate(new Date());
        leaveWord.setCstModify(new Date());
        leaveWordRepository.saveAndFlush(leaveWord);
        /**
         * 查询该图书的所有留言
         */
        List<LeaveWordVo> leaveWordVoList = new ArrayList<>();
        List<LeaveWord> leaveWords = leaveWordRepository.findByBookIdOrderByCstModifyDesc(book.getId());
        if(!leaveWords.isEmpty()){
            for(LeaveWord word:leaveWords){
                LeaveWordVo leaveWordVo=new LeaveWordVo();
                leaveWordVo.setLeaveId(word.getId());
                System.out.println(word.getUserId());
                User lwUser = userRepositiry.findByIdAndUsable(word.getUserId(),Constant.USABLE);
                if(lwUser!=null){
                    leaveWordVo.setUserName(lwUser.getUserName());
                    String pic = "picture/user/"+ lwUser.getUserPic();
                    leaveWordVo.setUserPic(pic);
                }
                leaveWordVo.setLeaveFatherId(word.getLeaveFatherId());
                leaveWordVo.setLeaveContent(word.getLeaveContent());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                leaveWordVo.setLeaveDate(sdf.format(word.getCstModify()));
                leaveWordVoList.add(leaveWordVo);
            }
        }
        return Response.ok(leaveWordVoList);
    }

    /**
     * 留言管理列表
     * @param page
     * @param size
     * @return
     */
    @Override
    public ResultResp leaveWordList(int page, int size) {
        List<LeaveWordListVo> resultList=new ArrayList<>();
        LeaveWordPageVo leaveWordPageVo=new LeaveWordPageVo();
        if(page<1){
            return Response.dataErr("页码数不能小于1");
        }
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"cstModify");
        Page<LeaveWord> pager = leaveWordRepository.findAllByUsable(Constant.USABLE, pageable);
        List<LeaveWord> leaveWords = leaveWordRepository.findAllByUsable(Constant.USABLE);
        List<LeaveWord> list=pager.getContent();
        if (!list.isEmpty()){
            for(LeaveWord word:list){
                LeaveWordListVo listVo=new LeaveWordListVo();
                listVo.setLeaveId(word.getId());
                listVo.setLeaveContent(word.getLeaveContent());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                listVo.setLeaveDate(sdf.format(word.getCstModify()));
                User user = userRepositiry.findByIdAndUsable(word.getUserId(),Constant.USABLE);
                if(user!=null){
                    listVo.setUserName(user.getUserName());
                }else {
                    listVo.setUserName("用户已失效");
                }
                resultList.add(listVo);
            }
        }/*else {
            return Response.dataErr("找不到资源");
        }*/
        leaveWordPageVo.setList(resultList);
        leaveWordPageVo.setRowCount(leaveWords.size());
        return Response.ok(leaveWordPageVo);
    }

    @Override
    public ResultResp findLeaveWordList(String leaveWord, int page, int size) {
        List<LeaveWordListVo> resultList=new ArrayList<>();
        LeaveWordPageVo leaveWordPageVo=new LeaveWordPageVo();
        if(page<1){
            return Response.dataErr("页码数不能小于1");
        }
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"cstModify");
        Page<LeaveWord> pager = leaveWordRepository.findByLeaveContentContainingAndUsable(leaveWord,Constant.USABLE, pageable);
        List<LeaveWord> leaveWords = leaveWordRepository.findByLeaveContentContainingAndUsable(leaveWord, Constant.USABLE);
        List<LeaveWord> list=pager.getContent();
        if (!list.isEmpty()){
            for(LeaveWord word:list){
                LeaveWordListVo listVo=new LeaveWordListVo();
                listVo.setLeaveId(word.getId());
                listVo.setLeaveContent(word.getLeaveContent());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                listVo.setLeaveDate(sdf.format(word.getCstModify()));
                User user = userRepositiry.findByIdAndUsable(word.getUserId(),Constant.USABLE);
                if(user!=null){
                    listVo.setUserName(user.getUserName());
                }else {
                    listVo.setUserName("用户已失效");
                }
                resultList.add(listVo);
            }
        }else {
            return Response.dataErr("找不到资源");
        }
        leaveWordPageVo.setList(resultList);
        leaveWordPageVo.setRowCount(leaveWords.size());
        return Response.ok(leaveWordPageVo);
    }

    @Override
    public ResultResp deleteLeaveWord(int leaveId) {
        LeaveWord leaveWord = leaveWordRepository.getById(leaveId);
        if(leaveWord != null){
            leaveWord.setUsable(Constant.UN_USABLE);
            leaveWordRepository.saveAndFlush(leaveWord);
        }
        return Response.ok();
    }
}
