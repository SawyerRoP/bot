package com.rdcall.bot;

import javax.persistence.*;

@Entity
@Table(name = "RDCALL")
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String call;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    @Override
    public String toString() {
        return call;
    }
}
