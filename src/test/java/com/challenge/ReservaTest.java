package com.challenge;

import org.junit.Test;

import com.challenge.controller.ReservaController;
import com.challenge.entity.Reserva;

public class ReservaTest {
    public static void main(String[] args) {
        saveReserva();
        updateReserva();
        deleteReserva();
    }

    @Test
    public static void saveReserva() {
        Reserva reserva = new Reserva();
        reserva.setFechaEntrada(null);
        reserva.setFechaSalida(null);
        reserva.setFormaDePago("creacion");
        reserva.setValor(100f);

        ReservaController reservaController = new ReservaController();
        reservaController.save(reserva);
    }

    @Test
    public static void updateReserva() {
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        reserva.setFechaEntrada(null);
        reserva.setFechaSalida(null);
        reserva.setFormaDePago("actualizacion");
        reserva.setValor(200f);

        ReservaController reservaController = new ReservaController();
        reservaController.update(reserva);
        System.out.println(reservaController.findById(1L));
    }

    @Test
    public static void deleteReserva() {
        ReservaController reservaController = new ReservaController();
        reservaController.removeById(1L);
    }
}
