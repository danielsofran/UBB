package repository;

import bilete.domain.User;

public interface UserRepository extends Repository<Integer, User>{
    public User login(String username, String password);
}
