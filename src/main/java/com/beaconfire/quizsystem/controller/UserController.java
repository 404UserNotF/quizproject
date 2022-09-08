package com.beaconfire.quizsystem.controller;

import com.beaconfire.quizsystem.dao.QuizDAO;
import com.beaconfire.quizsystem.entity.hentity.QuizEntity;
import com.beaconfire.quizsystem.entity.hentity.TakeQuizEntity;
import com.beaconfire.quizsystem.entity.hentity.UserEntity;
import com.beaconfire.quizsystem.entity.mentity.User;
import com.beaconfire.quizsystem.service.QuizService;
import com.beaconfire.quizsystem.service.TakeQuizService;
import com.beaconfire.quizsystem.service.UserService;
import com.beaconfire.quizsystem.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TakeQuizService takeQuizService;
    @Autowired
    private QuizService quizService;

    @RequestMapping(path="user/doLogin", method= RequestMethod.POST)
    public String doLogin(@RequestParam(value="username") String username,
                          @RequestParam(value="password") String password,
                          Model model,
                          HttpServletRequest request){
        UserEntity user = userService.findUserByUsernameHibernate(username);

        if(user == null){
            model.addAttribute("usernameMsg", "user does not exist");
            return "login";
        }

        if("suspend".equals(user.getUserStatus())){
            model.addAttribute("usernameMsg", "user is suspended");
            return "login";
        }

        String correctPwd = user.getUserPassword();
        if(!correctPwd.equals(password)){
            model.addAttribute("passwordMsg", "password is wrong");
            return "login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("isLogin", "true");
        session.setAttribute("userId", user.getUserId());
        // expiry in 60 min
        session.setMaxInactiveInterval(60*60);
        return "redirect:/";
    }
    @RequestMapping(path="user/toLogin", method= RequestMethod.GET)
    public String toLogin(){
        return "login";
    }

    @RequestMapping(path="user/toRegister")
    public String toRegister(Model model){
        model.addAttribute("message","Welcome");
        return "register";
    }
    
    @RequestMapping(path="user/doRegister", method= RequestMethod.POST)
    public String doRegister(@RequestParam(value="username") String username,@RequestParam(value="password") String password,
                             @RequestParam(value="email")String email, Model model){
        UserEntity user = userService.findUserByUsernameHibernate(username);
        if(user != null){
            model.addAttribute("message", "the username has been taken");
            return "register";
        }
        UserEntity newUser = new UserEntity();
        newUser.setUserName(username);
        newUser.setUserPassword(password);
        newUser.setUserEmail(email);
        newUser.setUserRole("test");
        newUser.setUserStatus("active");
        userService.insertHibernate(newUser);
        return "login";
    }

    @RequestMapping(path="user/doLogout")
    public String doLogout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "login";
    }

    @RequestMapping(path="user/admin/toAdminLogin")
    public String toAdminLogin(){
        return "adminLogin";
    }

    @RequestMapping(path="user/admin/doAdminLogin", method=RequestMethod.POST)
    public String doAdminLogin(@RequestParam(value="username") String username,
                               @RequestParam(value="password") String password,
                               HttpServletRequest request,
                               Model model){
        UserEntity user = userService.findUserByUsernameHibernate(username);

        if(user == null){
            model.addAttribute("usernameMsg", "user does not exist");
            return "adminLogin";
        }

        if(!"admin".equals(user.getUserRole())){
            model.addAttribute("usernameMsg", "invalid login");
            return "adminLogin";
        }

        String correctPwd = user.getUserPassword();
        if(!correctPwd.equals(password)){
            model.addAttribute("passwordMsg", "password is wrong");
            return "adminLogin";
        }
        Long totalCount = takeQuizService.findCountOfRecordQuizIdSeletive(Constants.NO_QUIZTYPE_ASSIGNED);
        List<TakeQuizEntity> takeQuizEntities = takeQuizService.findRecordFromTo(0, Constants.DEFAULT_LIMIT_OF_PAGE, Constants.SORTED_BY_STARTTIME, Constants.NO_QUIZTYPE_ASSIGNED);
        List<QuizEntity> quizEntities = quizService.selectAll();

        model.addAttribute("records", takeQuizEntities);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("curPage", 1);
        model.addAttribute("from", 0);
        model.addAttribute("limit", Constants.DEFAULT_LIMIT_OF_PAGE);
        model.addAttribute("sortedBy", Constants.SORTED_BY_STARTTIME);
        model.addAttribute("qz", quizEntities);
        model.addAttribute("picked", Constants.NO_QUIZTYPE_ASSIGNED);

        HttpSession session = request.getSession();
        session.setAttribute("isLogin", "true");
        session.setAttribute("userId", user.getUserId());
        // expiry in 60 min
        session.setMaxInactiveInterval(10*60*60);
        return "adminHomePage";
    }
}
