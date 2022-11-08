package controller;

import model.Members;

import java.util.List;

public interface MembersBeanI {
    void add(Members members) throws Exception;

    List<Members> getList() throws Exception;

    void update(Members members) throws Exception;

    void delete(Members members) throws Exception;
}
