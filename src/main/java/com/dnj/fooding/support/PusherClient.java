/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.support;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;

/**
 *
 * @author Animesh Samanta
 */
public class PusherClient {
    public static void main(String[] args) {
        PusherOptions pusherOptions=new PusherOptions();
        pusherOptions.setCluster("ap2");
        Pusher client=new Pusher("80ea0764269ada498b32", pusherOptions);
        client.connect();
        Channel channel=client.subscribe("restro365");
        channel.bind("my-event", new SubscriptionEventListener() {

            @Override
            public void onEvent(PusherEvent pe) {
                System.out.println(pe.getData());// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
         while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
       
    }
}
