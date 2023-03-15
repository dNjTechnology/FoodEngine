/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.support;

import com.pusher.rest.Pusher;
import java.util.Collections;



/**
 *
 * @author Animesh Samanta
 */



public class PusherCustom {
    public static void main(String[] args) {
       Pusher pusher = new Pusher("1567688", "80ea0764269ada498b32", "b7f6a5a11153e4f6b2c6");
pusher.setCluster("ap2");
pusher.setEncrypted(true);

pusher.trigger("restro365", "my-event", Collections.singletonMap("message", "hello world mmm"));


}
}
