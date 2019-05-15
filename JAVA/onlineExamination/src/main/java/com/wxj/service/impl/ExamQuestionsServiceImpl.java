package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.constant.ExamConstant;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.OperationException;
import com.wxj.mapper.ExamQuestionsMapper;
import com.wxj.model.DTO.ExamQuestionsParamsDTO;
import com.wxj.model.DTO.ExamQuestionsSaveDTO;
import com.wxj.model.PO.ExamQuestions;
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
        return examQuestionsMapper.getExamQuestionsDetailsById(id);
    }

    @Transactional
    @Override
    public int save(ExamQuestionsSaveDTO examQuestionsSaveDTO) {
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
        int i = examQuestionsMapper.insertSelective(examQuestions);
        if (SystemConstant.ZERO == i) {
            throw new OperationException("插入失败");
        }
        return i;
    }

    @Override
    public int modify(Integer id, ExamQuestionsSaveDTO examQuestionsSaveDTO) {
        ExamQuestions examQuestions = new ExamQuestions();
        BeanUtils.copyProperties(examQuestionsSaveDTO, examQuestions);

        examQuestions.setOptiona(examQuestionsSaveDTO.getOptionA());
        examQuestions.setOptionb(examQuestionsSaveDTO.getOptionB());
        examQuestions.setOptionc(examQuestionsSaveDTO.getOptionC());
        examQuestions.setOptiond(examQuestionsSaveDTO.getOptionD());
        examQuestions.setOptione(examQuestionsSaveDTO.getOptionE());
        examQuestions.setModifyTime(new Date());

        int i = examQuestionsMapper.updateByPrimaryKeySelective(examQuestions);
        if (SystemConstant.ZERO == i) {
            throw new OperationException("修改失败");
        }
        return i;
    }

    @Override
    public int delete(Integer id) {
        //TODO:
        return 0;
    }
}