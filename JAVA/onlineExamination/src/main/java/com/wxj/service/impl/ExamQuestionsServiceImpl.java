package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.wxj.constant.ExamConstant;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.OperationException;
import com.wxj.mapper.EntryStandardAnswerDetailsMapper;
import com.wxj.mapper.ExamPaperQuestionsMapper;
import com.wxj.mapper.ExamQuestionsMapper;
import com.wxj.mapper.SubjectOneMapper;
import com.wxj.model.DTO.EntryStandardAnswerDetailsDTO;
import com.wxj.model.DTO.ExamQuestionsParamsDTO;
import com.wxj.model.DTO.ExamQuestionsSaveDTO;
import com.wxj.model.DTO.PageDTO;
import com.wxj.model.PO.*;
import com.wxj.model.VO.EntryStandardAnswerDetailsVO;
import com.wxj.model.VO.ExamQuestionsDetailsVO;
import com.wxj.model.VO.ExamQuestionsVO;
import com.wxj.model.VO.SubjectOneVO;
import com.wxj.service.ExamQuestionsServiceI;
import com.wxj.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    Logger logger = LoggerFactory.getLogger(ExamQuestionsServiceImpl.class);
    @Autowired
    ExamQuestionsMapper examQuestionsMapper;
    @Autowired
    EntryStandardAnswerDetailsMapper entryStandardAnswerDetailsMapper;
    @Autowired
    SubjectOneMapper subjectOneMapper;
    @Autowired
    ExamPaperQuestionsMapper examPaperQuestionsMapper;

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
        int examQuestionsInsertSize;
        int entryStandardAnswerDetailsInsertSize = 0;
        Date date = new Date();
        ExamQuestions examQuestions = new ExamQuestions();
        BeanUtils.copyProperties(examQuestionsSaveDTO, examQuestions);
        examQuestions.setCode(StringUtil.getRandom());
        examQuestions.setABCD(examQuestionsSaveDTO);
        examQuestions.setCreateTime(date);
        examQuestions.setModifyTime(date);
        examQuestions.setDelFlag(SystemConstant.NOUGHT);
        examQuestionsInsertSize = examQuestionsMapper.insertSelective(examQuestions);
        if (SystemConstant.ZERO == examQuestionsInsertSize) {
            throw new OperationException("examQuestions插入失败");
        }

        if (ExamConstant.EXAM_QUESTIONS_TYPE_SIX.equals(examQuestions.getType())) {
            List<EntryStandardAnswerDetails> list = Lists.newArrayList();
            EntryStandardAnswerDetails entryStandardAnswerDetails;
            for (int i=0,size=examQuestionsSaveDTO.getEntryStandardAnswerDetailsDTOList().size(); i<size; i++) {
                entryStandardAnswerDetails = new EntryStandardAnswerDetails();
                EntryStandardAnswerDetailsDTO entryStandardAnswerDetailsDTO = examQuestionsSaveDTO.getEntryStandardAnswerDetailsDTOList().get(i);

                BeanUtils.copyProperties(entryStandardAnswerDetailsDTO, entryStandardAnswerDetails);
                entryStandardAnswerDetails.setSummary(entryStandardAnswerDetailsDTO.getSummary());
                entryStandardAnswerDetails.setEntryAnswerId(examQuestions.getId());
                entryStandardAnswerDetails.setRow(new Integer(i).byteValue());
                entryStandardAnswerDetails.setSubject1(entryStandardAnswerDetailsDTO.getSubject1());
                entryStandardAnswerDetails.setCreateTime(date);
                entryStandardAnswerDetails.setModifyTime(date);
                entryStandardAnswerDetails.setDelFlag(SystemConstant.NOUGHT);
                list.add(entryStandardAnswerDetails);
            }
            try {
                entryStandardAnswerDetailsInsertSize = entryStandardAnswerDetailsMapper.batchInsert(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (SystemConstant.ZERO == entryStandardAnswerDetailsInsertSize) {
                throw new OperationException("entryStandardAnswerDetails插入失败");
            }
        }
        return examQuestionsInsertSize;
    }

    @Override
    public int modify(ExamQuestionsSaveDTO examQuestionsSaveDTO) {
        Date date = new Date();
        ExamQuestions examQuestions = new ExamQuestions();
        BeanUtils.copyProperties(examQuestionsSaveDTO, examQuestions);

        examQuestions.setABCD(examQuestionsSaveDTO);
        examQuestions.setModifyTime(date);

        int examQuestionsUpdateSize = examQuestionsMapper.updateByPrimaryKeySelective(examQuestions);
        if (SystemConstant.ZERO == examQuestionsUpdateSize) {
            throw new OperationException("examQuestions更新失败");
        }

        if (ExamConstant.EXAM_QUESTIONS_TYPE_SIX.equals(examQuestions.getType())) {
            EntryStandardAnswerDetails entryStandardAnswerDetails;
            for (int i=0,size=examQuestionsSaveDTO.getEntryStandardAnswerDetailsDTOList().size(); i<size; i++) {
                entryStandardAnswerDetails = new EntryStandardAnswerDetails();
                EntryStandardAnswerDetailsDTO entryStandardAnswerDetailsDTO = examQuestionsSaveDTO.getEntryStandardAnswerDetailsDTOList().get(i);
                BeanUtils.copyProperties(entryStandardAnswerDetailsDTO, entryStandardAnswerDetails);
                entryStandardAnswerDetails.setRow(new Integer(i).byteValue());
                entryStandardAnswerDetails.setSubject1(entryStandardAnswerDetailsDTO.getSubject1());
                entryStandardAnswerDetails.setModifyTime(date);
                entryStandardAnswerDetailsMapper.updateByPrimaryKeySelective(entryStandardAnswerDetails);
            }
        }
        return 0;
    }

    @Override
    public void delete(Integer id) {
        try {
            examQuestionsMapper.deleteByPrimaryKey(id);

            ExamPaperQuestionsExample examPaperQuestionsExample = new ExamPaperQuestionsExample();
            examPaperQuestionsExample.createCriteria().andExamQuestionsIdEqualTo(id);
            examPaperQuestionsMapper.deleteByExample(examPaperQuestionsExample);
        } catch (Exception e) {
            logger.error("com.wxj.service.impl.ExamQuestionsServiceImpl.delete", e);
            throw new OperationException("删除失败");
        }
    }

    @Override
    public int examQuestionsImport(List<ExamQuestions> examQuestionsList) {
        int i;
        try {
            i = examQuestionsMapper.batchInsert(examQuestionsList);
        } catch (Exception e) {
            logger.error("com.wxj.service.impl.ExamQuestionsServiceImpl.examQuestionsImport", e);
            throw new OperationException("导入试题失败");
        }
        return i;
    }

    @Override
    public List<ExamQuestionsDetailsVO> listExamQuestions(PageDTO pageDTO) {
        PageBounds pageBounds = new PageBounds(pageDTO.getCurrentPage(), pageDTO.getPageSize());
        return examQuestionsMapper.selectExamQuestions(pageBounds);
    }

    @Override
    public Long countExamQuestions() {
        return examQuestionsMapper.countExamQuestions();
    }

    @Override
    public List<SubjectOneVO> listSubjectOne() {
        return subjectOneMapper.selectSubjectOne();
    }
}