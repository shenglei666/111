package dao;

import domain.User;

public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User  findByUserName (String username);



    public void save(User user );


    User findByCode(String code);

    void updateStatus(User user);
}
