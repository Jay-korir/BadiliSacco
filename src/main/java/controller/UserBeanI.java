package controller;


import model.Members;

public interface UserBeanI {
    Members register(Members members) throws Exception;

    Members login(Members member) throws Exception;
}

