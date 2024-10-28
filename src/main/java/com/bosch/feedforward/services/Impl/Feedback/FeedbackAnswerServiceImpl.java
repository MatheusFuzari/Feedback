package com.bosch.feedforward.services.Impl.Feedback;

import com.bosch.feedforward.dto.Feedback.FeedbackAnswerDTO;
import com.bosch.feedforward.entity.Feedback.FeedbackAnswer;
import com.bosch.feedforward.repository.Feedback.FeedbackAnswerRepository;
import com.bosch.feedforward.repository.Feedback.FeedbackRepository;
import com.bosch.feedforward.services.Feedback.FeedbackAnswerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeedbackAnswerServiceImpl implements FeedbackAnswerService {

    @Autowired
    private FeedbackAnswerRepository answerRepository;

    @Override
    public FeedbackAnswer getAnswerById(UUID id) {
        Optional<FeedbackAnswer> answerFound = this.answerRepository.findById(id);

        if(answerFound.isPresent()){
            return answerFound.get();
        }

        return null;
    }

    @Override
    public Page<FeedbackAnswer> getAllAnswers(Specification<FeedbackAnswer> spec, Pageable page) {
        return this.answerRepository.findAll(page);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public List<FeedbackAnswer> createAnswer(FeedbackAnswerDTO answer) {
        return this.answerRepository.saveAllAndFlush(this.dtoToFeedbackList(answer));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public List<FeedbackAnswer> updateAnswer(UUID id, FeedbackAnswerDTO answer) {
        List<FeedbackAnswer> answerFound = this.answerRepository.findAllByFeedbackId(id);
        List<FeedbackAnswer> answerList = dtoToFeedbackList(answer);

        for(int i=0;i<answerFound.size();i++){
            BeanUtils.copyProperties(answerList.get(i), answerFound.get(i));
        }

        return this.answerRepository.saveAllAndFlush(answerFound);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteAnswer(UUID id) {
        Optional<FeedbackAnswer> answerFound = this.answerRepository.findById(id);

        if(answerFound.isPresent()){
            this.answerRepository.delete(answerFound.get());
        }
    }

    public List<FeedbackAnswer> dtoToFeedbackList(FeedbackAnswerDTO answerDTO){
        List<FeedbackAnswer> answerList = new ArrayList<>();

        for (int i=0;i<answerDTO.getAnswer().size();i++){
            answerList.add(
                    FeedbackAnswer.builder()
                            .feedback(answerDTO.mapToFeedback())
                            .feedbackQuestion(answerDTO.mapToQuestion().get(i))
                            .answer(answerDTO.getAnswer().get(i))
                            .respondent(answerDTO.mapToUser())
                            .build()
            );
        }

        return answerList;
    }
}
