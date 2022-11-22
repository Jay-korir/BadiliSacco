package bean;


import model.Members;

public interface UserBeanI {
    Members register(Members members) throws Exception;

    Members login(Members member) throws Exception;

    boolean authMd5(String md5Hash);
}

