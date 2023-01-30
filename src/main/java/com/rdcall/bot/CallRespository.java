package com.rdcall.bot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRespository extends JpaRepository<Call,Integer> {

}
