package br.com.compass.tests;

import br.com.compass.models.Plane;

public class PlaneInstaceTest {
    public static void main(String[] args) {
        Plane plane =new Plane();
        plane.getSeats().stream().forEach(s->{
            System.out.println(s.getId()+","+s.isAvailable());
        });
    }
}
