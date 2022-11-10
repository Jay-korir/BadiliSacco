package controller;

import model.Members;

import java.util.List;

public interface MembersBeanI {
    void add(Members members) throws Exception;

    List<Members> list() throws Exception;

    void update(Members members) throws Exception;

    void delete(Long memberId) throws Exception;

    Members getMember(Long id) throws Exception;


}
