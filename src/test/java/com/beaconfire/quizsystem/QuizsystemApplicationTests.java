package com.beaconfire.quizsystem;

import com.beaconfire.quizsystem.dao.*;
import com.beaconfire.quizsystem.entity.hentity.*;
import com.beaconfire.quizsystem.entity.mentity.Choice;
import com.beaconfire.quizsystem.entity.mentity.Question;
import com.beaconfire.quizsystem.entity.mentity.TakeQuiz;
import com.beaconfire.quizsystem.entity.mentity.User;
import com.beaconfire.quizsystem.service.ChoiceService;
import com.beaconfire.quizsystem.service.QuizService;
import com.beaconfire.quizsystem.service.TakeQuizService;
import com.beaconfire.quizsystem.utils.Constants;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class QuizsystemApplicationTests {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private Random numberGenerator;
    @Autowired
    private ChoiceMapper choiceMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TakeQuizMapper takeQuizMapper;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ChoiceService choiceService;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private QuizDAO quizDao;
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private TakeQuizDAO takeQuizDao;
    @Autowired
    private QuizResultDAO quizResultDAO;
    @Autowired
    private TakeQuizService takeQuizService;
    @Autowired
    private QuizService quizService;

    /**
     * @Async("customExecutor")
     *     public CompletableFuture<List<TakeQuizEntity>> findAll_Async(){
     *         return CompletableFuture.completedFuture(takeQuizDAO.findAll());
     *     }
     *
     *     @Async("customExecutor")
     *     public CompletableFuture<List<TakeQuizEntity>> findRecordFromTo_Async(Integer from, Integer limit, Integer sortedBy, Integer quizId){
     *         return CompletableFuture.completedFuture(takeQuizDAO.getRecordFromTo(from, limit, sortedBy, quizId));
     *     }
     *
     *     @Async("customExecutor")
     *     public CompletableFuture<Long> findCountOfRecordQuizIdSeletive_Async(Integer quizId){
     *         return CompletableFuture.completedFuture(takeQuizDAO.getTotalNumberOfTakeQuizRecord(quizId));
     *     }
     * */
    @Test
    public void testNonAsync(){
        int quizId = -1;
        int toPage = 1;
        int limit = 20;
        int sortedBy = 1;
        int from = (toPage - 1) * limit;
        Long startTime = System.currentTimeMillis();
        for(int i=0; i<100; i++){
            takeQuizService.findAll();
            takeQuizService.findRecordFromTo(from, limit, sortedBy, quizId);
            takeQuizService.findCountOfRecordQuizIdSeletive(quizId);
        }
        Long endTime = System.currentTimeMillis();
        Long duration = endTime - startTime;
        System.out.println("Sync takes " + duration + " mills");
    }

    @Test
    public void testAsync() throws ExecutionException, InterruptedException {
        int quizId = -1;
        int toPage = 1;
        int limit = 20;
        int sortedBy = 1;
        int from = (toPage - 1) * limit;
        Long startTime = System.currentTimeMillis();
        for(int i=0; i<100; i++){
            CompletableFuture<List<TakeQuizEntity>> c1= takeQuizService.findAll_Async();
            CompletableFuture<List<TakeQuizEntity>> c2 = takeQuizService.findRecordFromTo_Async(from, limit, sortedBy, quizId);
            CompletableFuture<Long> c3 = takeQuizService.findCountOfRecordQuizIdSeletive_Async(quizId);
            CompletableFuture.allOf(c1, c2, c3).join();
        }
        Long endTime = System.currentTimeMillis();
        Long duration = endTime - startTime;
        System.out.println("Async takes " + duration + " mills");
    }
    @Test
    void testAdminManageControllerTime(){
        int quizId = -1;
        int toPage = 1;
        int limit = 20;
        int sortedBy = 1;
        Long startTime = System.currentTimeMillis();
        Long totalCount = takeQuizService.findCountOfRecordQuizIdSeletive(quizId);
        List<QuizEntity> quizEntities = quizService.selectAll();
        int from = (toPage - 1) * limit;
        List<TakeQuizEntity> takeQuizEntities = takeQuizService.findRecordFromTo(from, limit, sortedBy, quizId);
        Long endTime = System.currentTimeMillis();
        System.out.println("Takes " + (endTime - startTime) + " millSec");
    }
    @Test
    void testStringSplit(){
        String str = "";
        String[] strs = str.split(" ");
        System.out.println(strs.length);
        for(String s : strs){
            System.out.println(s);
        }
    }
    @Test
    void testQuizResultEntity(){
        List<QuizResultEntity> quizResultEntities = quizResultDAO.getResultByTakeQuizId(11);
        for(QuizResultEntity quizResultEntity : quizResultEntities){
            System.out.println(quizResultEntity.getQuestion().getQuestionContent());
            for(ChoiceEntity choiceEntity : quizResultEntity.getQuestion().getChoiceEntities()){
                System.out.println("**  "+choiceEntity.getOptionContent());
            }
            System.out.println("------------------------------------------------");
        }
    }

    @Test
    void getQuestionsTest(){
        List<QuestionEntity> questionEntities = questionDAO.getRandomQuestions(6, 10);
        for(QuestionEntity q : questionEntities){
            System.out.println(q.getQuestionContent());
            System.out.println(q.getChoiceEntities().get(1).getOptionContent());
            System.out.println("-----------------------------------");
        }
    }
    @Test
    void testFindUserByUserName(){
        /*List<UserEntity> users = userDAO.findUserByUsername("admin");
        if(users == null){
            System.out.println("user is null");
            System.out.println(users.get(0));
        }else{
            System.out.println(users.get(0));
        }
        System.out.println(users);
        *//*for(UserEntity user : users){
            System.out.println(user);
        }*/
    }

    @Test
    void contextLoads() {
    }

    @Test
    void  QuestionGenerator(){
        // insert operands for addition, 20 case were generated;
        Set<String> set = new HashSet<>();
        int count = 0;
        while(count < 20){
            int num1 = numberGenerator.nextInt(10) + 1;
            int num2 = numberGenerator.nextInt(10) + 1;
            // check if duplicate questions exist
            String str1 = num1+" + "+num2;
            String str2 = num2+" + "+num1;
            int result = num1 + num2;
            if(set.contains(str1) || set.contains(str2)){
                continue;
            }

            /*if(set.contains(str1)){
                continue;
            }*/

            Question newQuestion = new Question();
            newQuestion.setQuestionContent(str1);
            newQuestion.setQuizId(6);
            newQuestion.setQuestionStatus("Normal");
            // newQuestion will get the id automatically
            questionMapper.insert(newQuestion);

            set.add(str1);
            set.add(str2);

            Choice correctChoice = new Choice();
            correctChoice.setQuestionId(newQuestion.getQuestionId());
            correctChoice.setIsCorrect((byte) 1);
            correctChoice.setOptionContent(result+"");
            choiceMapper.insert(correctChoice);
            // generate three related wrong choice
            Set<Integer> used = new HashSet<Integer>();
            /*Set<Double> used = new HashSet<>();*/
            used.add(result);
            int j = 0;
            while(j<3){
                int diff1 = numberGenerator.nextInt(10);
                int diff2 = numberGenerator.nextInt(10);
                Integer incorrectResult = result + diff1 - diff2;
                if(used.contains(incorrectResult)){
                    continue;
                }
                Choice relatedChoice = new Choice();
                relatedChoice.setQuestionId(newQuestion.getQuestionId());
                relatedChoice.setIsCorrect((byte)0);
                relatedChoice.setOptionContent(incorrectResult+"");
                choiceMapper.insert(relatedChoice);
                used.add(incorrectResult);
                j++;
            }

            count++;
        }
    }
    @Test
    void findUserByUserName(){
        User user = userMapper.selectByUsername("system");
        System.out.println(user);
    }

    @Test
    void insertTakeQuizRecord(){
        TakeQuiz record = new TakeQuiz();
        record.setStartTime(new Date().getTime());
        record.setFinishTime(new Date().getTime());
        record.setScore(1);
        record.setUserId(1);
        record.setQuizId(1);
        takeQuizMapper.insertSelective(record);
        System.out.println(record.getTakeQuizId());
    }

    @Test
    void insertDivisionQuestion(){
        for(int i=3; i<50; i++){
            if(i%2 == 1){
                Question newQuestion = new Question();
                newQuestion.setQuestionContent(i + " / " + 2);
                newQuestion.setQuizId(11);
                newQuestion.setQuestionStatus("multi");
                // newQuestion will get the id automatically
                questionMapper.insert(newQuestion);

                int res = i/2;
                for(int j=0; j<4; j++){
                    Choice c = new Choice();
                    c.setQuestionId(newQuestion.getQuestionId());
                    if(j == 0){
                        c.setIsCorrect((byte)1);
                        c.setOptionContent(res+"");
                    }else if(j == 1){
                        c.setIsCorrect((byte)1);
                        int secRes = res + 1;
                        c.setOptionContent(secRes+"");
                    }else if(j==2){
                        c.setIsCorrect((byte)0);
                        int fakeRes = res + numberGenerator.nextInt(5) + 2;
                        c.setOptionContent(fakeRes+"");
                    }else{
                        c.setIsCorrect((byte)0);
                        int fakeRes = res - (numberGenerator.nextInt(5) + 1);
                        c.setOptionContent(fakeRes+"");
                    }
                    choiceMapper.insert(c);
                }
            }
        }
    }

    @Test
    void findQuestions(){
        List<Question> questionList = questionMapper.selectByQuizId(3, 10);
        System.out.println(questionList.size());
        for(Question q : questionList){
            System.out.println(q);
        }
    }

    @Test
    void hibernateUser(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        UserEntity user = session.get(UserEntity.class, 7);
        System.out.println(user);
        for(FeedbackEntity feedback : user.getFeedbacks()){
            System.out.println(feedback.getReview());
        }
        for(TakeQuizEntity takeQuiz : user.getTakeQuizs()){
            System.out.println(takeQuiz.getScore());
        }
        session.getTransaction().commit();
        session.close();
    }

    @Test
    void hibernateTakeQuiz(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        TakeQuizEntity takeQuiz = session.get(TakeQuizEntity.class, 1);
        System.out.println(takeQuiz);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    void hibernateChoice(){
        ChoiceEntity choice = choiceService.getChoiceById_hibernate(723);
        System.out.println(choice.getOptionContent());
        System.out.println(choice.getQuestion().getQuestionContent());
    }

    @Test
    void getQuizEntity(){
        List<QuizEntity> list = quizDao.getAllQuiz();
        if(list == null){
            System.out.println("list is null");
        }else{
            for(QuizEntity quizEntity : list){
                System.out.println(quizEntity);
            }
        }
    }

    @Test
    void testTakeQuizDao(){
        List<TakeQuizEntity> res = takeQuizDao.getRecordFromTo(0, 2, Constants.SORTED_BY_STARTTIME, null);
        for(TakeQuizEntity takeQuizEntity : res){
            System.out.println(takeQuizEntity.getUser().getUserName());
            System.out.println(takeQuizEntity.getQuiz().getQuizName());
            System.out.println(takeQuizEntity.getScore());
        }
        /*<td>${records.get(i).quiz.quizName}</td>
                    <td>${records.get(i).user.userName}</td>
                    <td>${records.get(i).score}</td>*/
    }

    @Test
    void testTakeQuizDao2(){
        System.out.println(takeQuizDao.getTotalNumberOfTakeQuizRecord(Constants.NO_QUIZTYPE_ASSIGNED));
    }


}
