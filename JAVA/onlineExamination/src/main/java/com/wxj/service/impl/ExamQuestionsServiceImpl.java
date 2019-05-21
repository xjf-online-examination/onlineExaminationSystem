package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.wxj.constant.ExamConstant;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.OperationException;
import com.wxj.mapper.EntryStandardAnswerDetailsMapper;
import com.wxj.mapper.ExamQuestionsMapper;
import com.wxj.model.DTO.EntryStandardAnswerDetailsDTO;
import com.wxj.model.DTO.ExamQuestionsParamsDTO;
import com.wxj.model.DTO.ExamQuestionsSaveDTO;
import com.wxj.model.PO.EntryStandardAnswerDetails;
import com.wxj.model.PO.ExamQuestions;
import com.wxj.model.VO.EntryStandardAnswerDetailsVO;
import com.wxj.model.VO.ExamQuestionsDetailsVO;
import com.wxj.model.VO.ExamQuestionsVO;
import com.wxj.service.ExamQuestionsServiceI;
import com.wxj.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: ExamQuestionsServiceImpl</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-14 23:37
 */
@Service
public class ExamQuestionsServiceImpl implements ExamQuestionsServiceI {
    @Autowired
    ExamQuestionsMapper examQuestionsMapper;
    @Autowired
    EntryStandardAnswerDetailsMapper entryStandardAnswerDetailsMapper;

    @Override
    public List<ExamQuestionsVO> listExamQuestionsByParams(ExamQuestionsParamsDTO examQuestionsParamsDTO) {
        PageBounds pageBounds = new PageBounds(examQuestionsParamsDTO.getCurrentPage(), examQuestionsParamsDTO.getPageSize());
        return examQuestionsMapper.selectExamQuestionsByParams(examQuestionsParamsDTO, pageBounds);
    }

    @Override
    public Long countExamQuestionsByParams(ExamQuestionsParamsDTO examQuestionsParamsDTO) {
        return examQuestionsMapper.countExamQuestionsByParams(examQuestionsParamsDTO);
    }

    @Override
    public ExamQuestionsDetailsVO getExamQuestionsDetailsById(Integer id) {
        ExamQuestionsDetailsVO examQuestionsDetailsVO = examQuestionsMapper.getExamQuestionsDetailsById(id);
        if (ExamConstant.EXAM_QUESTIONS_TYPE_SIX.equals(examQuestionsDetailsVO.getType())) {
            List<EntryStandardAnswerDetailsVO> entryStandardAnswerDetailsVOList = entryStandardAnswerDetailsMapper.selectEntryStandardAnswerDetailsVOByEntryStandardAnswerId(examQuestionsDetailsVO.getId());

            examQuestionsDetailsVO.setEntryStandardAnswerDetailsVOList(entryStandardAnswerDetailsVOList);
        }
        return examQuestionsDetailsVO;
    }

    @Transactional
    @Override
    public int save(ExamQuestionsSaveDTO examQuestionsSaveDTO) {
        int examQuestionsInsertSize = 0;
        int entryStandardAnswerDetailsInsertSize = 0;
        Date date = new Date();
        ExamQuestions examQuestions = new ExamQuestions();
        BeanUtils.copyProperties(examQuestionsSaveDTO, examQuestions);
        examQuestions.setCode(StringUtil.getRandom());
        examQuestions.setOptiona(examQuestionsSaveDTO.getOptionA());
        examQuestions.setOptionb(examQuestionsSaveDTO.getOptionB());
        examQuestions.setOptionc(examQuestionsSaveDTO.getOptionC());
        examQuestions.setOptiond(examQuestionsSaveDTO.getOptionD());
        examQuestions.setOptione(examQuestionsSaveDTO.getOptionE());
        examQuestions.setCreateTime(date);
        examQuestions.setModifyTime(date);
        examQuestions.setDelFlag(SystemConstant.NOUGHT);
        examQuestionsInsertSize = examQuestionsMapper.insertSelective(examQuestions);

        if (ExamConstant.EXAM_QUESTIONS_TYPE_SIX.equals(examQuestions.getType())) {
            List<EntryStandardAnswerDetails> list = Lists.newArrayList();
            EntryStandardAnswerDetails entryStandardAnswerDetails;
            for (int i=0,size=examQuestionsSaveDTO.getEntryStandardAnswerDetailsDTOList().size(); i<size; i++) {
                entryStandardAnswerDetails = new EntryStandardAnswerDetails();
                EntryStandardAnswerDetailsDTO entryStandardAnswerDetailsDTO = examQuestionsSaveDTO.getEntryStandardAnswerDetailsDTOList().get(i);
                BeanUtils.copyProperties(entryStandardAnswerDetailsDTO, entryStandardAnswerDetails);
                entryStandardAnswerDetails.setEntryAnswerId(examQuestions.getId());
                entryStandardAnswerDetails.setRow(entryStandardAnswerDetailsDTO.getRow().byteValue());
                entryStandardAnswerDetails.setSubject1(entryStandardAnswerDetailsDTO.getSubject1().byteValue());
                entryStandardAnswerDetails.setCreateTime(date);
                entryStandardAnswerDetails.setModifyTime(date);
                entryStandardAnswerDetails.setDelFlag(SystemConstant.NOUGHT);
                list.add(entryStandardAnswerDetails);
            }
            entryStandardAnswerDetailsInsertSize = entryStandardAnswerDetailsMapper.batchInsert(list);
        }
        if (SystemConstant.ZERO == examQuestionsInsertSize || SystemConstant.ZERO == entryStandardAnswerDetailsInsertSize) {
            throw new OperationException("插入失败");
        }
        return examQuestionsInsertSize;
    }

    @Override
    public int modify(ExamQuestionsSaveDTO examQuestionsSaveDTO) {
        Date date = new Date();
        ExamQuestions examQuestions = new ExamQuestions();
        BeanUtils.copyProperties(examQuestionsSaveDTO, examQuestions);

        examQuestions.setOptiona(examQuestionsSaveDTO.getOptionA());
        examQuestions.setOptionb(examQuestionsSaveDTO.getOptionB());
        examQuestions.setOptionc(examQuestionsSaveDTO.getOptionC());
        examQuestions.setOptiond(examQuestionsSaveDTO.getOptionD());
        examQuestions.setOptione(examQuestionsSaveDTO.getOptionE());
        examQuestions.setModifyTime(date);

        examQuestionsMapper.updateByPrimaryKeySelective(examQuestions);

        if (ExamConstant.EXAM_QUESTIONS_TYPE_SIX.equals(examQuestions.getType())) {
            EntryStandardAnswerDetails entryStandardAnswerDetails;
            for (int i=0,size=examQuestionsSaveDTO.getEntryStandardAnswerDetailsDTOList().size(); i<size; i++) {
                entryStandardAnswerDetails = new EntryStandardAnswerDetails();
                EntryStandardAnswerDetailsDTO entryStandardAnswerDetailsDTO = examQuestionsSaveDTO.getEntryStandardAnswerDetailsDTOList().get(i);
                BeanUtils.copyProperties(entryStandardAnswerDetailsDTO, entryStandardAnswerDetails);
                entryStandardAnswerDetails.setRow(entryStandardAnswerDetailsDTO.getRow().byteValue());
                entryStandardAnswerDetails.setSubject1(entryStandardAnswerDetailsDTO.getSubject1().byteValue());
                entryStandardAnswerDetails.setModifyTime(date);
                entryStandardAnswerDetailsMapper.updateByPrimaryKeySelective(entryStandardAnswerDetails);
            }
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        //TODO:
        return 0;
    }
}