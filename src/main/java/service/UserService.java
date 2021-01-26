package service;

import domain.User;

public interface UserService {

    /**
     * 注册用户
     * @param user
     * @return
     */
    public boolean regist(User user);

    boolean active(String code);
}
