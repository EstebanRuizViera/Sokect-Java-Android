/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myserver.myDatabase.model;

/**
 *
 * @author pc
 */
public class Commentary {

    private int nick;
    private String comment;

    public Commentary(int nick, String comment) {
        this.nick = nick;
        this.comment = comment;
    }

    public int getNick() {
        return nick;
    }

    public String getComment() {
        return comment;
    }


}
