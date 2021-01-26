package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import service.UserService;
import util.MailUtils;
import util.UuidUtil;

public class UserServiceImpl implements UserService{
    private UserDao userDao=new UserDaoImpl();


    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        User u = userDao.findByUserName(user.getUsername());
        if(u!=null){
            return false;
        }
        user.setCode(UuidUtil.getUuid());//设置激活码，唯一字符串
        System.out.println("添加激活码8888");
        user.setStatus("N");
        userDao.save(user);

        //激活邮件发送，邮件正文http://localhost:8080/register_ok.html
        String content ="<a href ='http://localhost:8080/activeUserServlet?code="+user.getCode()+"'>点击激活【盛磊的旅游网】</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }
    //激活用户
    @Override
    public boolean active(String code) {
        //更具激活码查询用户
       User user= userDao.findByCode(code);
            if (user!=null){
                //调用dao修改激活状态

                userDao.updateStatus(user);
                return true;
            }else {
                return false;
            }




    }
}
